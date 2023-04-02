CREATE TABLE invoice_item
(
    invoice_item_id INT AUTO_INCREMENT NOT NULL,
    product_id      INT                NULL,
    quantity        INT                NULL,
    invoice_id      INT                NOT NULL,
    CONSTRAINT pk_invoice_item PRIMARY KEY (invoice_item_id)
);

ALTER TABLE invoice_item
    ADD CONSTRAINT FK_INVOICE_ITEM_ON_INVOICE_ITEM FOREIGN KEY (invoice_id) REFERENCES invoice (invoice_id);

ALTER TABLE invoice_item
    ADD CONSTRAINT FK_INVOICE_ITEM_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (product_id);