# Car Lease

---


[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=car-lease%20🚗&color=informational)](https://github.com/jesperancinha/car-lease)

[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![CLM Build, Test, Coverage and Report](https://github.com/jesperancinha/car-lease/actions/workflows/car-lease-manager.yml/badge.svg)](https://github.com/jesperancinha/car-lease/actions/workflows/car-lease-manager.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/af73a199a556499288d9c91ce94956de)](https://www.codacy.com/gh/jesperancinha/car-lease/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/car-lease&amp;utm_campaign=Badge_Grade)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/af73a199a556499288d9c91ce94956de)](https://www.codacy.com/gh/jesperancinha/car-lease/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/car-lease&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/car-lease/badge.svg?branch=main)](https://coveralls.io/github/jesperancinha/car-lease?branch=main)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/car-lease.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/car-lease.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/car-lease.svg)](#)

---
## Introduction

This is an Auto Lease app

#### Stable releases

-   [0.0.0](https://github.com/jesperancinha/car-lease/tree/0.0.0) - [0b502ad1366c2dbdbb4adecc703cffa3f54dbb84](https://github.com/jesperancinha/car-lease/tree/0.0.0) - JDK 17 / H2 / Spring Boot 3.0.1

## How to test


### Register users
```bash
curl -i -X POST -H "Content-Type: application/json" --data '{ "fistName": "Joao", "lastName": "Esperancinha", "username": "jesperancinha", "password": "admin"}' http://localhost:8081/api/users
curl -i -X POST -H "Content-Type: application/json" --data '{ "fistName": "Joao2", "lastName": "Esperancinha", "username": "jesperancinha2", "password": "admin"}' http://localhost:8081/api/users
```

```json
{
  "fistName": "João",
  "lastName": "Esperancinha",
  "username": "jesperancinha",
  "password": "admin"
}
```

### Login users

```bash
curl -i -X POST -H "Content-Type: application/json" --data '{ "username": "jesperancinha", "password": "admin"}' http://localhost:8081/api/login
````

```json
{
  "username": "jesperancinha",
  "password": "admin"
}
```

### Making requests

```bash
curl -i -X POST -H "Content-Type: application/json" -H "Authorization: Bearer <TOKEN>" http://localhost:8081/api/<ENDPOINT>
````

```bash
curl -i -X POST -H "Content-Type: application/json" -H "Authorization: Bearer <TOKEN>"  --data '{ "make": "Renault", "model": "5", "version": "10", "numberDoors": 4, "co2Emission": 111, "grossPrice": 20000, "netPrice": 15000, "millage": 10000}'  http://localhost:8081/api/cars
curl -i -X POST -H "Content-Type: application/json" -H "Authorization: Bearer <TOKEN>"  --data '{ "name": "Mr. Springfield", "street": "Mr. Springfield Street", "houseNumber": 30, "zipCode": "334455", "place": "Olhao", "email": "9374092hfiohlfihwrif@nsdkldsnflknkfld.com", "phoneNumber": "1234455667" }'  http://localhost:8081/api/customers
curl -i -X POST -H "Content-Type: application/json" -H "Authorization: Bearer <TOKEN>"  --data '{ "carId": 1, "customerId": 2, "duration": 1000, "interestRate": 2}'  http://localhost:8081/api/leases
````


```json
{
  "make": "Renault",
  "model": "5",
  "version": "10",
  "numberDoors": 4,
  "co2Emission": 111,
  "grossPrice": 20000,
  "netPrice": 15000,
  "millage": 10
}
```

```json
{
  "name": "Mr. Springfield",
  "street": "Mr. Springfield Street",
  "houseNumber": 30,
  "zipCode": "334455",
  "place": "Olhao",
  "email": "9374092hfiohlfihwrif@nsdkldsnflknkfld.com",
  "phoneNumber": "1234455667"
}
```

```json
{
  "carId": 2,
  "customerId": 1,
  "duration": 1000,
  "interestRate": 2
}
```

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
