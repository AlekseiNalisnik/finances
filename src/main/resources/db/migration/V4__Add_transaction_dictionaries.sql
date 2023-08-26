ALTER TABLE transactions RENAME COLUMN type TO payment_type;
ALTER TABLE transactions ALTER COLUMN payment_type SET NOT NULL;
ALTER TABLE transactions ADD CHECK (payment_type IN ('CASH', 'CREDIT_CARD', 'CRYPTO_CURRENCY', 'DIGITAL_CURRENCY'));

ALTER TABLE transactions DROP COLUMN category;
ALTER TABLE transactions ADD COLUMN purpose_id bigint NOT NULL;

ALTER TABLE transactions DROP COLUMN name;
ALTER TABLE transactions ADD COLUMN purchase_place varchar(150) NOT NULL;

ALTER TABLE transactions RENAME COLUMN money TO price;

ALTER TABLE transactions ADD COLUMN description varchar(250);

CREATE TABLE transaction_purpose_dict (
    id bigserial PRIMARY KEY,
    code integer NOT NULL,
    description varchar(100) NOT NULL
);

ALTER TABLE transactions ADD COLUMN transaction_purpose_dict_id bigserial NOT NULL;

INSERT INTO transaction_purpose_dict (id, code, description)
VALUES
    (1, 199, 'Еда и быт'),
    (2, 198, 'Здоровье');
