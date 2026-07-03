---
name: repo-anti-patterns
description: Anti-patterns found across the wider qa-mastering-apps repo (JVM services, Go rewrites, frontends) — complements the supermarket-specific practices skill. Use this whenever writing, reviewing, or refactoring code anywhere in the repo, especially before adding a REST endpoint, an entity/DTO with an id, error handling, a global exception handler, a new test, or a Go handler backed by shared state.
---

# Anti-patterns found across the repo

These were found in a review of the JVM services (airports-app, car-lease, mancalaje,
online-product-shop, rockstars-manager, library-books, radioactive-narwhals-shop), the
Go rewrites under `go-lang/`, and the Angular frontend in `mancalaje/kala-game-web`.
For supermarket/supermarket-features specifically, see the `practices` skill — this
skill covers the same *kinds* of problems recurring elsewhere, plus issues specific to
Go and frontend code.

## Recurring across most JVM services

### 1. Missing `@ControllerAdvice` is the default, not the exception
Only `kalah-game-service` and the supermarket projects have a global exception handler.
`car-lease-service`, `rockstars-manager`, `online-product-shop`, `library-books`
(both backends), and all `radioactive-narwhals-shop` backends have none — any unhandled
exception falls through to Spring Boot's default whitelabel/500, leaking a
stack-trace-shaped body and giving every service a different error contract. Add a
`@ControllerAdvice` (specific handlers + a generic `Exception` fallback) to every new
Spring service; don't treat it as optional.

### 2. Hardcoded credentials in committed config
`rockstars-manager/src/main/resources/application-prod.properties` (a **prod** profile)
has `spring.datasource.password=password`; `online-product-shop/src/main/resources/application.properties`
has `spring.r2dbc.password=admin`; `mancalaje/docker-psql/.env` has
`POSTGRES_PASSWORD=admin` and is tracked in git. Same issue as `practices` item 9, but
it recurs well beyond supermarket. Never commit real-looking credentials; use env vars
or an untracked override file, and keep only local/H2 defaults in versioned properties.

### 3. Client-supplied id accepted on create, causing a silent overwrite
`car-lease-service`'s `CarDto`/`CustomerDto`/`LeaseDto` expose a settable `id` that
flows straight into the entity (`converters/Converters.kt`) before `save()`. Since JPA
`save()` with a non-null id performs a merge/update, `POST /cars` with an id that
already exists silently overwrites another row instead of creating a new one. Use
separate create/update DTOs, or explicitly null out the id on create.

### 4. Silent `null`/no-op instead of a 404 in money-relevant flows
`LeaseService.createLease` (car-lease-service) returns `null` when the referenced car
or customer id doesn't exist, and the controller passes that straight through as an
HTTP 200 with an empty body. This is the same failure shape as `practices` item 3
(silently dropping missing ids in billing code) — throw a domain exception and map it
via the `@ControllerAdvice`, don't return null from a service method that represents a
paid transaction.

### 5. Try/catch that doesn't guard the risky code
`radioactive-narwhals-shop/auto-loader-backend/.../AutoLoaderNarwhalsShopLauncher.kt`
wraps `call() { 0 }` in a try/catch that catches nothing meaningful, while the actual
file-parsing/DAO work happens in `run()`, outside any try/catch — a bad input file
crashes the app instead of producing the controlled exit code the code appears to
promise. `rn-data-reader/NarwhalsDataReader.kt` has the mirror problem: it does catch
the real work, but discards the exception with no logging, so a failure gives zero
diagnostic information. Catch where the risk actually is, and always log what was
caught before swallowing it.

### 6. Disabled or empty tests left in the suite
`airports-app/src/test/kotlin/.../QueryAirportServiceIT.kt` is `@Disabled` and its
`should get airports by country code` test has a completely empty body — it would pass
trivially if re-enabled. `MainContainerIT.kt` is likewise permanently disabled. This is
the same problem as `practices` item 4 (tests with no real assertions), compounded by
`@Disabled` hiding the fact that a subsystem has zero coverage. Delete tests you're not
going to fix, or fix them — don't leave disabled/empty ones as decoration.

### 7. Misleading names cause callers to do the wrong thing
`LeaseService.deleteCustomerById(id)` actually calls `leaseRepository.deleteById(id)` —
it deletes a *Lease*, not a customer. Same family as `practices` item 7. Grep for
name/behavior mismatches whenever touching a service method you didn't write.

## Go rewrites (`go-lang/`)

The Go rewrites are structurally thinner than their JVM originals and reintroduce
problems the JVM versions had already solved:

### 8. Shared mutable state with no synchronization
`go-lang/car-lease/cmd/server/main.go`, `online-product-shop/cmd/server/main.go`, and
`rockstars-manager/cmd/server/main.go` use a package-level slice/map (`var cars = []...`,
`var artists = map[int64]...`) as the datastore, written to directly from handlers with
no mutex. `net/http` serves each request on its own goroutine, so concurrent requests
can race — the map case can crash the whole process with
`fatal error: concurrent map writes`. Guard shared state with `sync.Mutex`/`sync.RWMutex`,
or use a real store.

### 9. Decode/parse errors dropped instead of returned as 400s
`json.NewDecoder(r.Body).Decode(&x)` and `strconv.Atoi`/`ParseInt` calls across nearly
every Go handler (`car-lease`, `online-product-shop`, `rockstars-manager`,
`supermarket`, `supermarket-with-features`, `radioactive-narwhals-shop`) ignore the
returned error. Malformed JSON becomes an empty struct and a non-numeric path segment
becomes `0` instead of failing the request — e.g. a garbage `{days}` value silently
returns a valid-looking stock response. Always check these errors and write a 400 on
failure.

### 10. Business logic embedded directly in HTTP handlers
Only `go-lang/airports-app` has a `service` package; every other Go project puts
storage, id assignment, and lookups straight into `main.go` handler functions. This is
a structural regression versus the layered JVM originals and makes the handlers hard to
unit test. Keep handlers thin; put logic in a separate package even for small services.

### 11. No `go.mod`, no tests, anywhere in `go-lang/`
None of the 9 Go projects has a `go.mod`, despite importing external packages
(`gorilla/mux`, `google/uuid`) and internal packages by module path — none of them
currently build. There is also not a single `*_test.go` in the tree, unlike every JVM
sibling. Before adding features to a Go project here, first make it a working module
with `go mod init`/`go mod tidy` and at least a smoke test per handler.

### 12. Endpoints that simulate persistence without actually persisting
`go-lang/mancalaje/cmd/server/main.go`'s `move`/`join` handlers accept `{boardId}`/
`{pitId}` path params but never read `mux.Vars(r)` — every call just returns a
brand-new random board, so the "game" never has real state across requests.
`go-lang/airports-app/cmd/server/main.go` initializes `FullAirportInfo` as permanently
empty, so both query endpoints always return `[]` regardless of input. Don't leave a
handler that looks functional but is actually a stub — mark it clearly (e.g. `panic("not implemented")`
or a `501`) if the backing logic isn't there yet.

## Frontend (`mancalaje/kala-game-web`)

### 13. Copy-pasted error swallowing with no user-facing error state
`board.service.ts`, `player.service.ts`, and `app.component.ts` each define their own
identical `handleError()` that does `console.error` + `return of(undefined)`. Every
HTTP failure (login, join, move) disappears silently from the user's perspective, and
the handler is duplicated three times instead of shared. Extract one error-handling
service/interceptor and surface failures in the UI.

### 14. Hardcoded localhost URL despite having environment config
`app.component.ts` does `window.location.href = "http://localhost:4200/login"` even
though the project already has `environment.ts`/`environment.prod.ts` for exactly this
purpose. Any non-local deployment redirects to a dead address. Read redirect/base URLs
from `environment`, never hardcode them.

## Lower-priority but worth knowing about

- **Plaintext password storage**: `car-lease-service`'s `User` entity stores `password`
  as a plain column with zero uses of `PasswordEncoder`/`BCrypt` in the module. Hash
  before persisting if this code path is ever exercised for real auth.
- **Weak `Referrer-Policy`**: `mancalaje/docker-files/nginx.conf` sets
  `Referrer-Policy: unsafe-url`, leaking full URLs (including query strings) to
  third-party origins. Use `strict-origin-when-cross-origin` instead.
- **Diverged/dead forked modules**: `library-books/backend-service-ktor-fork-gradle`
  and `-fork-maven` no longer run the real Ktor app in `Main.kt` (just an IDE-template
  `println`), while their tests build a separate inline app and never touch `Main.kt`.
  Each also sleeps `delay(5.seconds)` per test for no assertion purpose, adding
  30-70s+ of dead CI time per module. Delete forks that no longer serve their stated
  purpose rather than letting them silently rot.
- **`go-lang/supermarket-with-features` is a byte-for-byte duplicate** of
  `go-lang/supermarket` (only the startup log string differs) despite the name implying
  extended functionality — either finish it or remove the duplicate.
