# Rockstars Manager

---


[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=rockstars-manager%20🤘&color=informational)](https://github.com/jesperancinha/rockstars-manager)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![RSM Build, Test, Coverage and Report](https://github.com/jesperancinha/rockstars-manager/actions/workflows/rockstarts-manager-races.yml/badge.svg)](https://github.com/jesperancinha/rockstars-manager/actions/workflows/rockstarts-manager-races.yml)
[![RSM Build on PR](https://github.com/jesperancinha/rockstars-manager/actions/workflows/rockstarts-manager-pull-request.yml/badge.svg)](https://github.com/jesperancinha/rockstars-manager/actions/workflows/rockstarts-manager-pull-request.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/3bcf372c7e6e42d6827a1393d0517d90)](https://www.codacy.com/gh/jesperancinha/rockstars-manager/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/rockstars-manager&amp;utm_campaign=Badge_Grade)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/3bcf372c7e6e42d6827a1393d0517d90)](https://www.codacy.com/gh/jesperancinha/rockstars-manager/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/rockstars-manager&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/rockstars-manager/badge.svg?branch=main)](https://coveralls.io/github/jesperancinha/rockstars-manager?branch=main)
[![codecov](https://codecov.io/gh/jesperancinha/rockstars-manager/branch/main/graph/badge.svg?token=5JTaC7z68Y)](https://codecov.io/gh/jesperancinha/rockstars-manager)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/rockstars-manager.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/rockstars-manager.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/rockstars-manager.svg)](#)

---

## Introduction

---- Under Construction ----

This application is a Rockstars manager

#### Stable releases

-   [0.0.1](https://github.com/jesperancinha/rockstars-manager/tree/0.0.1) - [4ecb6333713e31c30e195a2c9b5ea033d785f198](https://github.com/jesperancinha/rockstars-manager/tree/0.0.1) - ?
-   [0.0.2](https://github.com/jesperancinha/rockstars-manager/tree/0.0.2) - [73e941a8634e69abaf73a001af970e060192fd03](https://github.com/jesperancinha/rockstars-manager/tree/0.0.2) - JDK 17 / Mockito / Java / JUnit assertion
-   [1.0.0](https://github.com/jesperancinha/rockstars-manager/tree/1.0.0) - [27b82491eecc13b6044ecb6207aeaae23386d3bd](https://github.com/jesperancinha/rockstars-manager/tree/1.0.0) - JDK 19 / Kotlin 1.8.0 / Kotest / Spring Mockk / Spring Boot 3.0.1

## RESTfull requests

### GET Requests

-   Get artist by name
```bash
curl http://localhost:8080/rockstars/artists/200
curl http://localhost:8080/rockstars/artists/filter/name/3%20Doors%20Down
```

-   Get song by name

```bash
curl http://localhost:8080/rockstars/songs/1901
curl http://localhost:8080/rockstars/songs/filter/name/666
```

### POST requests

-   Post artist
```bash
curl -H "Content-Type: application/json" -X POST --data "{\"Name\": \"Ariana Grande\"}" http://localhost:8080/rockstars/artists
```

-   Post song
```bash
curl -H "Content-Type: application/json" -X POST --data "{\"Name\": \"God is a woman\",\"Year\": 2018,\"Artist\": \"Ariana Grande\", \"Shortname\": \"godisawoman\",\"Bpm\": 145,\"Duration\": 197000,\"Genre\": \"Pop\",\"Album\": \"Sweetener\"}" http://localhost:8080/rockstars/artists
```

### PUT requests

-   Put artist
```bash
curl -H "Content-Type: application/json" -X PUT --data "{\"Name\": \"Ariana Grande\"}" http://localhost:8080/rockstars/artists/200
```

-   Put song
```bash
curl -H "Content-Type: application/json" -X PUT --data "{\"Name\": \"God is a woman\",\"Year\": 2018,\"Artist\": \"Ariana Grande\", \"Shortname\": \"godisawoman\",\"Bpm\": 145,\"Duration\": 197000,\"Genre\": \"Pop\",\"Album\": \"Sweetener\"}" http://localhost:8080/rockstars/songs/1901
```

### DELETE requests

-   Delete artist
```bash
curl -X DELETE http://localhost:8080/rockstars/artists/200
```

-   Delete
```bash
curl -X DELETE http://localhost:8080/rockstars/songs/1901
```
### OAUTH

```bash
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d "grant_type=password&username=jofisaes@gmail.com&password=123&client_id=rockstars-client&client_secret=rockstars&scope=read&redirect_uri=http://localhost:8080/oauth" http://localhost:8080/rockstars/oauth/token
curl -H "Authorization: Bearer TOKEN" http://localhost:8080/rockstars/artists/200
```

## Running everything

Please have docker desktop running. Finally run:

```bash
./build.sh
```

## References

-   [Cucumber 10 Minute Tutorial](https://cucumber.io/docs/guides/10-minute-tutorial/)
-   [Cucumber Tests in Spring Boot with Dependency Injection](https://thepracticaldeveloper.com/2018/03/31/cucumber-tests-spring-boot-dependency-injection/)
-   [Mock MVC](https://spring.io/guides/gs/testing-web/)

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
