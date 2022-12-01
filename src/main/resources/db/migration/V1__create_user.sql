CREATE TABLE users
(
    id           UUID PRIMARY KEY,
    first_name   VARCHAR(200) NOT NULL,
    last_name    VARCHAR(200) NOT NULL,
    snils        VARCHAR(50)  NOT NULL UNIQUE,
    phone_number BIGINT       NOT NULL UNIQUE
)