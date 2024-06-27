-- V1__Create_and_populate_users_and_roles.sql

-- Insert users
INSERT INTO users (username, password, enabled, name, lastname, email)
VALUES ('admin', '$2a$10$D.fuTL1dmViVstbWlHEnQeMzQZucVscPE9NXth37UPVXggELRZr8e', 1, 'Juan', 'Vargas', 'juan.vargas@mail.com'),
       ('user', '$2a$10$X9SGazzekxK5VIwz4GefWOHivnPeSL/HXjcnKeEeFMz5l61vyV4Ha', 1, 'Benito', 'Palotes', 'benito.palotes@mail.com');

-- Insert roles
INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

-- Insert user roles
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1), -- Admin user gets ROLE_ADMIN
       (1, 2), -- Admin user also gets ROLE_USER
       (2, 2); -- Normal user gets ROLE_USER
