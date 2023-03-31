CREATE TABLE product
(
    product_id   INT AUTO_INCREMENT NOT NULL,
    name         VARCHAR(255)       NULL,
    manufacturer VARCHAR(255)       NULL,
    price        DOUBLE             NULL,
    category_id  INT                NULL,
    CONSTRAINT pk_product PRIMARY KEY (product_id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (category_id);