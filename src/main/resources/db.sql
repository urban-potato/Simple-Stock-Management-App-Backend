CREATE TABLE product
(
    id          serial PRIMARY KEY NOT NULL,
    brand       text               NOT NULL,
    model       text               NOT NULL,
    description text,
    price       integer            NOT NULL,
    quantity    integer            NOT NULL
);

INSERT INTO product (brand, model, description, price, quantity)
VALUES ('Xiaomi', 'POCO M5', 'smartphone', 8900, 21),
       ('Xiaomi', 'POCO M6', 'smartphone', 9900, 15),
       ('Xiaomi', 'POCO M7', 'smartphone', 10900, 7),
       ('Apple', 'iPhone 13', 'smartphone', 64152, 4),
       ('Apple', 'iPhone 14', 'smartphone', 74152, 2),
       ('Apple', 'iPhone 15', 'smartphone', 94152, 1),
       ('Samsung', 'Galaxy M11', 'smartphone', 4940, 11),
       ('Samsung', 'Galaxy M12', 'smartphone', 5940, 3),
       ('Samsung', 'Galaxy M13', 'smartphone', 6940, 2);
