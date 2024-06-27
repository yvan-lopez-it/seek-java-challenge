-- V1__Create_table_candidates.sql

CREATE TABLE candidates
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE,
    gender   VARCHAR(10),
    salary   DECIMAL(10, 2),
    expected DECIMAL(10, 2)
);
