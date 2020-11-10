DROP TABLE IF EXISTS people;

CREATE TABLE people
(
    person_id  BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name  VARCHAR(20)
);


-- DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
    CUSTOMER_ID          BIGINT IDENTITY NOT NULL PRIMARY KEY,
    NAME                 VARCHAR(20),
    AGE                  int(10),
    ADDRESS              VARCHAR(20),
    ACCOUNT_OPENING_DATE DATE            NOT NULL
);

CREATE SCHEMA rwd;

CREATE TABLE rwd.BALANCE_AND_INDEX
(
    ID                         BIGINT AUTO_INCREMENT NOT NULL,
    ACCOUNT_NUMBER             VARCHAR(12)           NOT NULL,
    PROFILE_ACCOUNT_NUMBER     VARCHAR(50)           NOT NULL,
    CIF                        VARCHAR(12)           NOT NULL,
    MOBILE_NUMBER              VARCHAR(12)           NOT NULL,
    INVOLVED_PARTY_UUID        VARCHAR(36)           NOT NULL,
    PRODUCT_AGREEMENTS_UUID    VARCHAR(36)           NOT NULL,
    ACCOUNT_INDEX              VARCHAR(10)           NULL,
    EOD_BALANCE                NUMERIC(19, 2)        NULL,
    HHAB                       NUMERIC(19, 2)        NULL,
    ACCOUNT_OPENING_DATE       DATE                  NOT NULL,
    PRODUCT_TYPE               VARCHAR(8)            NOT NULL,
    ACCOUNT_STATUS             VARCHAR(50)           NULL,
    CREATED_DATETIME           DATETIME(6)           NOT NULL,
    CREATED_BY                 VARCHAR(150)          NOT NULL,
    UPDATED_DATETIME           DATETIME(6)           NULL,
    UPDATED_BY                 VARCHAR(150)          NULL,
    IS_RETURNING_CUSTOMER      CHAR(1)               NULL,
    CURRENT_ACTIVE_ACCOUNT_NBR VARCHAR(50)           NULL,
);

