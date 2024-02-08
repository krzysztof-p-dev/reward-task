-- -- Create customers table
-- CREATE TABLE customers
-- (
--     id   INT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(255) NOT NULL
-- );
--
-- -- Create transactionDetails table
-- CREATE TABLE transactions
-- (
--     id               INT AUTO_INCREMENT PRIMARY KEY,
--     customer_id      INT            NOT NULL,
--     amount           DECIMAL(10, 2) NOT NULL,
--     transaction_date DATE           NOT NULL,
--     FOREIGN KEY (customer_id) REFERENCES customers (id)
-- );

-- Insert test data into customers table
INSERT INTO customers (name)
VALUES ('John Doe'),
       ('Jane Smith'),
       ('Tom Mark');

-- Insert test data into transactionDetails table
INSERT INTO transactions (customer_id, amount, transaction_date)
VALUES (1, 120.00, '2024-01-01'),
       (1, 75.00, '2024-01-15'),
       (1, 200.00, '2024-02-01'),
       (1, 150.00, '2024-02-13'),
       (1, 200.00, '2024-03-01'),
       (1, 100.00, '2024-03-04'),
       (2, 50.00, '2024-01-01'),
       (2, 150.00, '2024-02-01'),
       (2, 250.00, '2024-03-01'),
       (3, 100.00, '2024-02-01');
COMMIT;