CREATE TABLE PRICES (
    PRICE_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID INT NOT NULL,
    START_DATE DATETIME,
    END_DATE DATETIME,
    PRODUCT_ID INT,
    PRIORITY INT,
    PRICE DECIMAL(10, 2),
    CURRENCY VARCHAR(3)
);


-- Insertar los datos de ejemplo proporcionados
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRODUCT_ID, PRIORITY, PRICE, CURRENCY)
VALUES
(1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 35455, 0, 35.50, 'EUR'),
(1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 35455, 1, 25.45, 'EUR'),
(1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 35455, 1, 30.50, 'EUR'),
(1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 35455, 1, 38.95, 'EUR');
