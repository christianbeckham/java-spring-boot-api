CREATE DATABASE ecommerce;

CREATE TABLE ecommerce.users (
    id int NOT NULL AUTO_INCREMENT,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    registration_date TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY(id)
);

CREATE TABLE ecommerce.roles (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE ecommerce.user_roles (
    user_id int,
    role_id int,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
	PRIMARY KEY (user_id, role_id)
);

CREATE TABLE ecommerce.products (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    description varchar(255),
    price int NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE ecommerce.shoppingcart_item (
	id int NOT NULL AUTO_INCREMENT,
    user_id int,
    product_id int,
    quantity int DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    PRIMARY KEY (id),
    CONSTRAINT UniqueUP UNIQUE (user_id, product_id)
);

INSERT INTO ecommerce.users (email, password, registration_date) VALUES ('oda@gmail.com', 'OdasPass123', '2020-1-20');
INSERT INTO ecommerce.users (email, password, registration_date) VALUES ('afton@gmail.com', 'AftonsPass123', '2020-1-20');
INSERT INTO ecommerce.users (email, password, registration_date) VALUES ('bibi@gmail.com', 'BibisPass123', '2017-4-6');
INSERT INTO ecommerce.users (email, password, registration_date) VALUES ('janett@gmail.com', 'JanettsPass123', '2015-10-15');
INSERT INTO ecommerce.users (email, password, registration_date) VALUES ('gary@gmail.com', 'GarysPass123', '2012-10-15');


INSERT INTO ecommerce.roles (name) VALUES ('Customer');
INSERT INTO ecommerce.roles (name) VALUES ('Employee');
INSERT INTO ecommerce.roles (name) VALUES ('Admin');

INSERT INTO ecommerce.user_roles (user_id, role_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'oda@gmail.com'), (SELECT id FROM ecommerce.roles WHERE name = 'Customer'));
INSERT INTO ecommerce.user_roles (user_id, role_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'afton@gmail.com'), (SELECT id FROM ecommerce.roles WHERE name = 'Customer'));
INSERT INTO ecommerce.user_roles (user_id, role_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'bibi@gmail.com'), (SELECT id FROM ecommerce.roles WHERE name = 'Employee'));
INSERT INTO ecommerce.user_roles (user_id, role_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'janett@gmail.com'), (SELECT id FROM ecommerce.roles WHERE name = 'Employee'));
INSERT INTO ecommerce.user_roles (user_id, role_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'gary@gmail.com'), (SELECT id FROM ecommerce.roles WHERE name = 'Admin'));


INSERT INTO ecommerce.products (name, description, price) VALUES ('Gazelle 22272 T4 Pop-Up', ' A 90 second set-up and packs up into a 67.5 inch duffle bag. Spaciously sleep up to four people, standing 78 in tall.', 279);
INSERT INTO ecommerce.products (name, description, price) VALUES ('Freedom from the Known - Jiddu Krishnamurti', 'Krishnamurti shows how people can free themselves radically and immediately from the tyranny of the expected, no matter what their ageopening the door to transforming society and their relationships.', 14);
INSERT INTO ecommerce.products (name, description, price) VALUES ('Ball Mason Jar-32 oz.', 'CLASSIC BRAND: Ball Wide Mouth (32 oz.) Jars with Lid and Band', 8);
INSERT INTO ecommerce.products (name, description, price) VALUES ('Stellar Basic Flute Key of G - Native American Style Flute', 'The Basic G flute is our most popular key. It is small enough that most people can comfortably play it.', 127);
INSERT INTO ecommerce.products (name, description, price) VALUES ('Catan The Board Game', 'The incredibly popular, multi award winning civilization building board game of harvesting and trading resources', 43);
INSERT INTO ecommerce.products (name, description, price) VALUES ('Apple Watch Series 3', 'Fitness Tracker, Sleep Monitor, GPS, Pedometer, Heart Rate Monitor', 169);
INSERT INTO ecommerce.products (name, description, price) VALUES ('Nintendo Switch', 'Get the gaming system that lets you play the games you want, wherever you are, however you like.', 299);

INSERT INTO ecommerce.shoppingcart_item (user_id, product_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'oda@gmail.com'), 1);
INSERT INTO ecommerce.shoppingcart_item (user_id, product_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'oda@gmail.com'), 3);
INSERT INTO ecommerce.shoppingcart_item (user_id, product_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'oda@gmail.com'), 4);
INSERT INTO ecommerce.shoppingcart_item (user_id, product_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'oda@gmail.com'), 7);
INSERT INTO ecommerce.shoppingcart_item (user_id, product_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'afton@gmail.com'), 7);
INSERT INTO ecommerce.shoppingcart_item (user_id, product_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'afton@gmail.com'), 2);
INSERT INTO ecommerce.shoppingcart_item (user_id, product_id, quantity) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'afton@gmail.com'), 3, 10);
INSERT INTO ecommerce.shoppingcart_item (user_id, product_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'afton@gmail.com'), 5);
INSERT INTO ecommerce.shoppingcart_item (user_id, product_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'janett@gmail.com'), 2);
INSERT INTO ecommerce.shoppingcart_item (user_id, product_id) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'janett@gmail.com'), 5);
INSERT INTO ecommerce.shoppingcart_item (user_id, product_id, quantity) VALUES ((SELECT id FROM ecommerce.users WHERE email = 'bibi@gmail.com'), 6, 5);