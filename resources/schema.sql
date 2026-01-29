CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS menu_items (
    id SERIAL PRIMARY KEY,
    category_id INT REFERENCES categories(id),
    name VARCHAR(100) NOT NULL UNIQUE,
    price DOUBLE PRECISION NOT NULL,
    type VARCHAR(50),
    extra_info TEXT
    );


INSERT INTO categories (name) VALUES ('Food'), ('Drink') ON CONFLICT DO NOTHING;
INSERT INTO menu_items (category_id, name, price, type)
VALUES (1, 'Pizza', 2500.0, 'Food') ON CONFLICT DO NOTHING;
