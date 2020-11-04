DROP TABLE IF EXISTS people;

CREATE TABLE people  (
                         person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
                         first_name VARCHAR(20),
                         last_name VARCHAR(20)
);


DROP TABLE IF EXISTS customer;
CREATE TABLE customer  (
                         CUSTOMER_ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
                         NAME VARCHAR(20),
                         AGE int(10),
                         ADDRESS VARCHAR(20)
);