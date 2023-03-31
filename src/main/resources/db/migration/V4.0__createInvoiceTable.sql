CREATE TABLE invoice
(
    invoice_id   INT AUTO_INCREMENT NOT NULL,
    address      VARCHAR(255)       NULL,
    ordered_date date               NULL,
    user_id      INT                NULL,
    CONSTRAINT pk_invoice PRIMARY KEY (invoice_id)
);

ALTER TABLE invoice
    ADD CONSTRAINT FK_INVOICE_ON_USER FOREIGN KEY (user_id) REFERENCES user (user_id);