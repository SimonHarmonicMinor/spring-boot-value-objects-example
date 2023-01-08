CREATE TABLE users
(
    id           UUID PRIMARY KEY,
    phone_number VARCHAR(100) NOT NULL UNIQUE,
    passport     VARCHAR(100) NOT NULL UNIQUE
);