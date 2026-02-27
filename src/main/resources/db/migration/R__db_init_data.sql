INSERT INTO product_type (id, label, tax) VALUES
  (1, 'basic', 0),
  (2, 'book', 0.1),
  (3, 'other', 0.2);

INSERT INTO product (id, name, price, origin, type_id) VALUES
  (1, 'livre', 12.49, 'FR', 2),
  (2, 'CD musical', 14.99, 'FR', 3),
  (3, 'barre de chocolat', 0.85, 'FR', 1),
  (4, 'boîte de chocolat importée type 1', 10, 'SW', 1),
  (5, 'boîte de chocolat importée type 2', 11.25, 'SW', 1),
  (6, 'flacon de parfum importé type 1', 47.50, 'GR', 3),
  (9, 'flacon de parfum importé type 2', 27.99, 'GR', 3),
  (7, 'flacon de parfum', 18.99, 'FR', 3),
  (8, 'boîte de pilule contre la migraine', 9.75, 'FR', 1);

-- ORDER 1
INSERT INTO orders (id) VALUES
  (1);

INSERT INTO order_item (id, product_id, order_id, quantity) VALUES
  (1, 1, 1, 2),
  (2, 2, 1, 1),
  (3, 3, 1, 3);

-- ORDER 2
INSERT INTO orders (id) VALUES
  (2);

INSERT INTO order_item (id, product_id, order_id, quantity) VALUES
  (4, 4, 2, 2),
  (5, 6, 2, 3);

-- ORDER 3
INSERT INTO orders (id) VALUES
  (3);

INSERT INTO order_item (id, product_id, order_id, quantity) VALUES
  (6, 9, 3, 2),
  (7, 7, 3, 1),
  (8, 8, 3, 3),
  (9, 5, 3, 2);