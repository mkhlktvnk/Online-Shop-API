DELETE FROM products;
DELETE FROM brands;

INSERT INTO brands (name, description) VALUES ('Nike', 'Nike description');
INSERT INTO brands (name, description) VALUES ('Adidas', 'Adidas description');
INSERT INTO brands (name, description) VALUES ('New balance', 'New Balance description');
INSERT INTO brands (name, description) VALUES ('Jordan', 'Jordan description');


INSERT INTO products (name, description, price, season_type, brand_id)
VALUES ('Nike Dunk Low', 'Nike Dunk Low description', 250.50, 'SUMMER', 1);
INSERT INTO products (name, description, price, season_type, brand_id)
VALUES ('Nike Air Force 1', 'Nike Air Force 1 description', 250.50, 'MULTI_SEASON', 1);
INSERT INTO products (name, description, price, season_type, brand_id)
VALUES ('Adidas ZX 750', 'Adidas ZX 750 description', 320.25, 'MULTI_SEASON', 2);
INSERT INTO products (name, description, price, season_type, brand_id)
VALUES ('New Balance 550', 'NB 550 description', 240.50, 'SUMMER', 3);
INSERT INTO products (name, description, price, season_type, brand_id)
VALUES ('Jordan 1 High', 'Jordan 1 high description', 300.50, 'SUMMER', 4)