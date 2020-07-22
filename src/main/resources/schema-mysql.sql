SET MODE MYSQL;

CREATE TABLE client_user(
    id INTEGER NOT NULL AUTO_INCREMENT,
    username VARCHAR(100),
    password VARCHAR(50),
    access_token VARCHAR(100) NULL,
    access_token_validity datetime NULL,
    refresh_token VARCHAR(100) NULL
);
