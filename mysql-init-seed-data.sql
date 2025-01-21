USE restaurants;
CREATE TABLE restaurants_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    contact_info VARCHAR(255),
    rating VARCHAR(255)
);
ALTER TABLE restaurants_info AUTO_INCREMENT=10001;
INSERT INTO restaurants_info (name, address, contact_info, rating)
VALUES
    ('Pizza Palace', '123 Main St, Cityville', '713-123-4567', '4.8'),
    ('Burger Barn', '456 Elm St, Townsville', '832-123-4567', '5.0'),
    ('Sushi Spot', '789 Oak St, Hamlet', '281-123-4567', '4.1');