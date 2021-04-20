# Car Lease

## How to install

```bash
sdk install java 16.0.0.hs-adpt
sdk use java 16.0.0.hs-adpt
```

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
