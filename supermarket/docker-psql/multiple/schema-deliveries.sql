CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE deliveries
(
    id          UUID PRIMARY KEY,
    vehicle_id  VARCHAR(255)             NOT NULL,
    address     VARCHAR(255)             NOT NULL,
    started_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    finished_at TIMESTAMP WITH TIME ZONE,
    status      VARCHAR(50)              NOT NULL
);
