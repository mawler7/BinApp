CREATE TABLE IF NOT EXISTS USERS
(
    ID         BINARY      NOT NULL PRIMARY KEY,
    FIRST_NAME varchar(45) NOT NULL,
    LAST_NAME  varchar(45) NOT NULL,
    EMAIL      varchar(45) NOT NULL UNIQUE,
    PASSWORD   varchar(100) DEFAULT NULL,
    ROLE       varchar(10) NOT NULL,
    ACTIVE     boolean     not null
);

CREATE TABLE IF NOT EXISTS CONTAINERS
(
    ID     BINARY         NOT NULL PRIMARY KEY,
    AMOUNT int            NOT NULL,
    NAME   varchar(10)    NOT NULL,
    WIDTH  int            NOT NULL,
    LENGTH int            NOT NULL,
    HEIGHT int            NOT NULL,
    VOLUME decimal(10, 0) NOT NULL,
    PRICE  decimal(10, 0) NOT NULL,
    TOTAL  decimal(10, 0) NOT NULL
);


CREATE TABLE IF NOT EXISTS TRUCKS
(
    ID         BINARY     NOT NULL PRIMARY KEY,
    REG_NUMBER varchar(7) NOT NULL,
    TRUCK_TYPE varchar(6) NOT NULL,
    max_volume int DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS ORDERS
(
    ID             BINARY NOT NULL PRIMARY KEY,
    AMOUNT         int         DEFAULT NULL,
    DATE           datetime    DEFAULT NULL,
    TYPE           varchar(9)  DEFAULT NULL,
    truck_id       BINARY      DEFAULT NULL,
    container_id   BINARY      DEFAULT NULL,
    user_id        BINARY      DEFAULT NULL,
    date_delivered datetime(6) DEFAULT NULL,
    delivered      BOOLEAN     DEFAULT NULL,
    CONSTRAINT FK2bjl2iv61c4cst7rnd3husyfq
        FOREIGN KEY (truck_id) REFERENCES TRUCKS (id),
    CONSTRAINT FK2bjl2iv61c4cst7rnd3husyfx
        FOREIGN KEY (container_id) REFERENCES CONTAINERS (id),
    CONSTRAINT FK2bjl2iv61c4cst7rnd3husyfc
        FOREIGN KEY (user_id) REFERENCES USERS (id)
);
