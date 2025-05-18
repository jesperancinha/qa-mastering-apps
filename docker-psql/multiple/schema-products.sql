CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    description TEXT,
    category    VARCHAR(100),
    price       DECIMAL(19, 4)
);