# Online product shop

## How to start

```shell

make dcup-full

```

## Examples

More available on the scratch pad file here: [example.http](example.http).

#### Create a product

```json
{
  "name": "Block of buttons",
  "description": "Buttons for the block",
  "category": "Super Smartphones",
  "price": 100
}
```

```shell
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Block of buttons",
    "description": "Buttons for the block",
    "category": "Super Smartphones",
    "price": 100
  }'
```

#### Update a product

```json
{
  "name": "Block of buttons",
  "description": "Buttons for the block",
  "category": "Super Smartphones",
  "price": 10000
}
```

```shell
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Block of buttons",
    "description": "Buttons for the block",
    "category": "Super Smartphones",
    "price": 10000
  }'
```

#### Search results

```shell
curl -X GET http://localhost:8080/products/search?q=buttons
```

#### Reset System (Not part of assignment)

```shell
curl -X POST http://localhost:8080/products/reset
```
