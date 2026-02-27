-- ============================================
-- TABLE product_type
-- ============================================
CREATE TABLE product_type (
    id          BIGINT PRIMARY KEY,
    label       VARCHAR(255) NOT NULL,
    tax         NUMERIC(4,2) NOT NULL
);

-- ============================================
-- TABLE product
-- ============================================
CREATE TABLE product (
    id          BIGINT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    price       NUMERIC(10,2) NOT NULL,
    origin      VARCHAR(10) NOT NULL,
    type_id     BIGINT NOT NULL,

    CONSTRAINT fk_product_type
        FOREIGN KEY (type_id)
        REFERENCES product_type(id)
);

-- ============================================
-- TABLE command
-- ============================================
CREATE TABLE orders (
    id          BIGINT PRIMARY KEY
);

-- ============================================
-- TABLE command_item
-- ============================================
CREATE TABLE order_item (
    id          BIGINT PRIMARY KEY,
    product_id  BIGINT NOT NULL,
    order_id  BIGINT NOT NULL,
    quantity    INT NOT NULL,

    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id)
        REFERENCES product(id),

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
);