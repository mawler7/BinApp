INSERT INTO USERS(ID,FIRST_NAME, LAST_NAME,EMAIL,PASSWORD,ROLE, ACTIVE)
VALUES ('07cb5486591a11ecbf630242ac130002', 'JAN', 'BŁACHOWICZ', 'jan.blachowicz@gmail.com', '$2a$12$ucsxfaqAZ.JS4vtDtnUEQejBlhJNmYqK5/M5gfBV7OxSWICrVh1Ku
', 'ROLE_USER',  true );

INSERT INTO TRUCKS(ID, REG_NUMBER, TRUCK_TYPE, MAX_VOLUME)
VALUES ('07cb5486591a11ecbf630242ac130003', 'PO12345', 'MEGA', 100 );

INSERT INTO CONTAINERS(ID, AMOUNT, NAME, WIDTH, LENGTH,HEIGHT,VOLUME,PRICE,TOTAL)
VALUES ('07cb5486591a11ecbf630242ac130004', 100, 'CON1', 100, 100, 100, 10000, 10, 1000);

INSERT INTO ORDERS(ID, AMOUNT, DATE, TYPE, TRUCK_ID, CONTAINER_ID, USER_ID, DATE_DELIVERED, DELIVERED)
VALUES ('07cb5486591a11ecbf630242ac130005', 10, '2021-12-30', 'LOADING','07cb5486591a11ecbf630242ac130003','07cb5486591a11ecbf630242ac130004', '07cb5486591a11ecbf630242ac130002', null, false );