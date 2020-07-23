SET MODE MYSQL;

CREATE TABLE client_user(
    id INTEGER NOT NULL AUTO_INCREMENT,
    access_token VARCHAR(100) NULL,
    access_token_validity datetime NULL,
    refresh_token VARCHAR(100) NULL
);
