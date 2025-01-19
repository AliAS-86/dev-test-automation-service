USE restaurants;
CREATE TABLE restaurants_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    contact_info VARCHAR(255),
    key_id VARCHAR(255) UNIQUE NOT NULL
);
INSERT INTO restaurants_info (name, address, contact_info, key_id)
VALUES
    ('Pizza Palace', '123 Main St, Cityville', '713-123-4567', '1001'),
    ('Burger Barn', '456 Elm St, Townsville', '832-123-4567', '1002'),
    ('Sushi Spot', '789 Oak St, Hamlet', '281-123-4567', '1003');