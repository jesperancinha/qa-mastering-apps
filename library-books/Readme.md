# library-books

Book searcher.

This runnable uses the Books Google API to make searches about books. It's a simple coroutine project.

## Project structure

-   [backend-java](backend-java) - under construction
-   [backend-kotlin](backend-kotlin)

## How to start

Make sure to have JDK17 installed before continuing.
I suggest using [SDK-MAN](https://sdkman.io/)

##### 1.  Kotlin version

1.  Run the build with the make file or just run `mvn clean install`:

```shell
make b
```

2.  Run with `java -jar backend-kotlin/target/backend-kotlin.jar` or just run:

```shell
make run
```

## Running examples

```shell
curl http://localhost:8080/?query=coming%20out&language=nl
```
```shell
curl http://localhost:8080/?query=Metallicat&language=nl
```
```shell
curl http://localhost:8080/?query=Kabeljauw&language=nl
```
```shell
curl http://localhost:8080/?query=Kaas&language=nl
```

## Running Docker

You can also run docker to get the application running by running `docker-compose up -d` or:

```shell
make dcup
```

## References

-   [Google Books API V1](https://developers.google.com/books/docs/v1/using)
-   [Spring Boot Caching](https://www.javatpoint.com/spring-boot-caching)

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
