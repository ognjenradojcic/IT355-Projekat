CREATE TABLE `order`
(
    order_id     INT          NOT NULL AUTO_INCREMENT,
    quantity     INT          NULL,
    address      VARCHAR(255) NULL,
    ordered_date date         NULL,
    product_id   INT          NULL,
    customer_id  INT          NULL,
    CONSTRAINT pk_order PRIMARY KEY (order_id)
);

ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES user (user_id);

ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (product_id);