# Car Lease

## How to install

```bash
sdk install java 16.0.0.hs-adpt
sdk use java 16.0.0.hs-adpt
```

## How to test

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