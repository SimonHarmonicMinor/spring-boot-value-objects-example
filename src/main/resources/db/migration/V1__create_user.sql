CREATE TABLE users
(
    id           UUID PRIMARY KEY,
    phone_number BIGINT       NOT NULL UNIQUE
);