---
name: supermarket-quality-practices
description: Quality practices for the supermarket and supermarket-with-features projects, derived from a code review of both. Use this whenever writing, reviewing, or refactoring code in supermarket/ or supermarket-with-features/, especially delivery/invoice/summary services, repository tests, and exception handling.
---

# Quality practices for supermarket / supermarket-with-features

These are concrete issues found in a review of `supermarket` (package-by-layer) and
`supermarket-with-features` (package-by-feature) and the practices to apply going
forward to avoid reintroducing them.

## 1. Don't assign IDs client-side on `@GeneratedValue` entities

Setting `id = UUID.randomUUID()` before `save()` on an entity still annotated
`@GeneratedValue` makes Spring Data treat every insert as a `merge` (extra SELECT)
instead of a `persist`. Either drop `@GeneratedValue` and own ID generation, or let
the database/JPA generate the ID and don't set it beforehand.

Seen in: `supermarket-with-features/.../delivery/domain/DeliveryService.kt`.

## 2. Query with a ranged repository method, don't filter in memory

Prefer `findByStartedAtBetweenOrderByStartedAtAsc(...)`-style derived queries over
`getAll()` followed by in-memory filtering. The former scales with the result set;
the latter loads the whole table on every call.

Seen in: `supermarket-with-features/.../summary/service/BusinessSummaryService.kt`
(regression versus `supermarket/.../repository/Repositories.kt`, which already does
this correctly).

## 3. Fail loudly on missing IDs in money-related flows

`findAllById(...)` silently drops IDs that don't exist. In billing/invoicing code,
missing deliveries must not be silently excluded from the result — validate the
requested IDs against what was returned and throw/report the gap.

Seen in: `supermarket-with-features/.../invoice/service/InvoiceProcessingService.kt`.

## 4. Every test must assert something

A test that only `println`s a result, or branches its outcome on `Math.random()`,
provides no coverage and must fail review. Every test method needs at least one
real assertion tied to the behavior under test.

Seen in: `supermarket/idiomatic/DomainSafePrimitivesTest.kt`,
`ErrorHandlingTest.kt`, `PolymorphicCollectionProcessingTest.kt`.

## 5. Don't mock the interface you're testing

A repository test that mocks the repository interface itself and asserts the stub's
return value is tautological — it proves nothing about the real query. Custom JPQL/
derived queries need to run against a real database (Testcontainers or an
in-memory DB), not a mock of themselves.

Seen in: `supermarket/.../repository/DeliveryRepositoryTest.kt`.

## 6. Integration tests must not require a manually-started external service

`@SpringBootTest` integration tests that hardcode `jdbc:postgresql://localhost:5432/...`
only work if a developer has already run `docker-compose up`. Use Testcontainers (or
an embedded/H2 profile) so `mvn test` / `./gradlew test` is self-sufficient and CI-safe.

Seen in: `supermarket-with-features/.../DeliveryControllerIntegrationTest.kt`,
`InvoiceControllerIntegrationTest.kt`, `SummaryControllerIntegrationTest.kt`.

## 7. Name things for what they actually do

A class named `NoopInvoiceClient` must not perform a real blocking HTTP call — if it
does something real, rename it (and check for other stale renames: dead exception
handlers, filename/class-name mismatches like `InvoiceProcessingService.kt` declaring
`class InvoiceService`, or `*IntegrationTest.kt` files declaring class `*IT`).

Seen in: `supermarket-with-features/.../invoice/client/NoopInvoiceClientImplementation.kt`,
`error/GlobalExceptionHandler.kt` (handles `ConstraintViolationException` but no
controller uses `@Validated`), `invoice/service/InvoiceProcessingService.kt`.

## 8. Add a catch-all exception handler

Both projects let unmapped exceptions (e.g. a plain `RuntimeException("Delivery $id
not found!")`) fall through to Spring's default handler, returning a raw 500 instead
of a proper 404/400 with a clean body. Add a `@ControllerAdvice` with both specific
handlers and a generic `Exception` fallback.

Seen in: `supermarket/.../Service.kt`, `supermarket-with-features/.../GlobalExceptionHandler.kt`.

## 9. No credentials in committed config

Don't hardcode real-looking DB credentials (`postgres`/`admin`) in
`application.yml` or `.env` files that get committed. Use placeholders plus a
documented way to supply real values locally (env vars, untracked override file).

Seen in: both projects' `src/main/resources/application.yml` and `docker-psql/.env`.

## 10. Remove dead code as you find it

Unused service methods (e.g. `DeliveryService.getById()` with zero call sites) and
unreachable exception handlers should be deleted, not left in place — they inflate
the surface area reviewers and future maintainers have to reason about.
