# Car Lease

---

[![Twitter URL](https://img.shields.io/twitter/url?logoColor=blue&style=social&url=https%3A%2F%2Fimg.shields.io%2Ftwitter%2Furl%3Fstyle%3Dsocial)](https://twitter.com/intent/tweet?text=%20Checkout%20this%20%40github%20repo%20by%20%40joaofse%20%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB%3A%20https%3A//github.com/jesperancinha/car-lease)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=car-lease%20🚗&color=informational)](https://github.com/jesperancinha/car-lease)

[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![CLM Build, Test, Coverage and Report](https://github.com/jesperancinha/car-lease/actions/workflows/car-lease-manager.yml/badge.svg)](https://github.com/jesperancinha/car-lease/actions/workflows/car-lease-manager.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/af73a199a556499288d9c91ce94956de)](https://www.codacy.com/gh/jesperancinha/car-lease/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/car-lease&amp;utm_campaign=Badge_Grade)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/c087adeb61914ad9ab259e0abbcb6c27)](https://www.codacy.com/gh/jesperancinha/car-lease/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/car-lease&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/car-lease/badge.svg?branch=main)](https://coveralls.io/github/jesperancinha/car-lease?branch=main)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/car-lease.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/car-lease.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/car-lease.svg)](#)

---
## Introduction

This is an Auto Lease app


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

## About me 👨🏽‍💻🚀🏳️‍🌈

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/mastodon-20.png "Mastodon")](https://masto.ai/@jesperancinha)
| [Sessionize](https://sessionize.com/joao-esperancinha/)
| [Spotify](https://open.spotify.com/user/jlnozkcomrxgsaip7yvffpqqm?si=b54b89eae8894960)
| [Medium](https://medium.com/@jofisaes)
| [Buy me a coffee](https://www.buymeacoffee.com/jesperancinha)
| [Credly Badges](https://www.credly.com/users/joao-esperancinha)
| [Google Apps](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
| [Sonatype Search Repos](https://search.maven.org/search?q=org.jesperancinha)
| [Docker Images](https://hub.docker.com/u/jesperancinha)
| [Stack Overflow Profile](https://stackoverflow.com/users/3702839/joao-esperancinha)
| [Reddit](https://www.reddit.com/user/jesperancinha/)
| [Dev.TO](https://dev.to/jofisaes)
| [Hackernoon](https://hackernoon.com/@jesperancinha)
| [Code Project](https://www.codeproject.com/Members/jesperancinha)
| [BitBucket](https://bitbucket.org/jesperancinha)
| [GitLab](https://gitlab.com/jesperancinha)
| [Coursera](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
| [FreeCodeCamp](https://www.freecodecamp.org/jofisaes)
| [HackerRank](https://www.hackerrank.com/jofisaes)
| [LeetCode](https://leetcode.com/jofisaes)
| [Codebyte](https://coderbyte.com/profile/jesperancinha)
| [CodeWars](https://www.codewars.com/users/jesperancinha)
| [Code Pen](https://codepen.io/jesperancinha)
| [Hacker Earth](https://www.hackerearth.com/@jofisaes)
| [Khan Academy](https://www.khanacademy.org/profile/jofisaes)
| [Hacker News](https://news.ycombinator.com/user?id=jesperancinha)
| [InfoQ](https://www.infoq.com/profile/Joao-Esperancinha.2/)
| [LinkedIn](https://www.linkedin.com/in/joaoesperancinha/)
| [Xing](https://www.xing.com/profile/Joao_Esperancinha/cv)
| [Tumblr](https://jofisaes.tumblr.com/)
| [Pinterest](https://nl.pinterest.com/jesperancinha/)
| [Quora](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
| [VMware Spring Professional 2021](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
| [Oracle Certified Professional, Java SE 11 Programmer](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
| [Oracle Certified Professional, JEE7 Developer](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
| [IBM Cybersecurity Analyst Professional](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
| [Certified Advanced JavaScript Developer](https://cancanit.com/certified/1462/)
| [Certified Neo4j Professional](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
| [Deep Learning](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
| [![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)
