-- use root user to execute the sql script

-- Drop the fwrp database if it exists
DROP DATABASE IF EXISTS fwrp;

-- Create a new database named fwrp
CREATE DATABASE fwrp;

-- Switch to the fwrp database
USE fwrp;

-- Create a new user named fwrpr with the password fwrpfwrp
CREATE USER 'fwrp'@'%' IDENTIFIED BY 'fwrpfwrp';

-- Grant all privileges on the fwrp database to the user fwrpr
GRANT ALL PRIVILEGES ON fwrp.* TO 'fwrp'@'%';

-- Refresh the privileges to ensure that the changes take effect
FLUSH PRIVILEGES;


-- Create User table
CREATE TABLE user (
  uid INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20) DEFAULT NULL,
  email VARCHAR(50) DEFAULT NULL,
  password VARCHAR(50) DEFAULT NULL,
  user_type VARCHAR(20) DEFAULT NULL,
  last_login DATETIME DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Create Food table
CREATE TABLE food (
  fid INT PRIMARY KEY AUTO_INCREMENT,
  fname VARCHAR(50) DEFAULT NULL,
  expiration DATE DEFAULT NULL,
  price DECIMAL(8,0) DEFAULT NULL,
  inventory INT DEFAULT NULL,
  discount DOUBLE DEFAULT NULL,
  ftid INT DEFAULT NULL,
  is_donate INT DEFAULT '0',
  store_id INT DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Create Food Type table
CREATE TABLE food_type (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Create Orders table
CREATE TABLE orders (
  oid INT PRIMARY KEY AUTO_INCREMENT,
  uid INT DEFAULT NULL,
  fid INT DEFAULT NULL,
  money DECIMAL(8,2) DEFAULT NULL,
  num INT DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Create Store table
CREATE TABLE store (
  store_id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'No',
  store_name VARCHAR(50) DEFAULT NULL COMMENT 'name',
  city VARCHAR(50) DEFAULT NULL COMMENT 'city',
  uid INT DEFAULT NULL COMMENT 'Retailer id'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Create Subscribe table
CREATE TABLE subscribe (
  sid INT PRIMARY KEY AUTO_INCREMENT,
  uid INT DEFAULT NULL,
  fid INT DEFAULT NULL,
  create_time DATE DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table subscribe add column alert_type varchar(255) not null;
alter table subscribe add column email varchar(255) not null;


-- Create Rating and Feedback table
CREATE TABLE RatingAndFeedback (
    RatingID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT NOT NULL,
    FoodID INT NOT NULL,
    Rating INT CHECK (Rating >= 1 AND Rating <= 5),
    Review TEXT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES user(uid),
    FOREIGN KEY (FoodID) REFERENCES food(fid)
);

-- Create Advanced Search view
CREATE VIEW food_search AS
SELECT 
  f.fid, 
  f.fname, 
  f.expiration, 
  f.price, 
  f.inventory, 
  f.discount, 
  f.is_donate, 
  ft.name AS food_type, 
  s.store_name, 
  s.city
FROM 
  food f
JOIN 
  food_type ft ON f.ftid = ft.id
JOIN 
  store s ON f.store_id = s.store_id;

-- Insert initial data into User table
INSERT INTO user (name, email, password, user_type, last_login) VALUES
('Alice', 'alice@example.com', 'password1', 'consumer', NOW()),
('Bob', 'bob@example.com', 'password2', 'retailer', NOW()),
('Charlie', 'charlie@example.com', 'password3', 'organization', NOW()),
('David', 'david@example.com', 'password4', 'consumer', NOW()),
('Eve', 'eve@example.com', 'password5', 'retailer', NOW()),
('Frank', 'frank@example.com', 'password6', 'organization', NOW()),
('Grace', 'grace@example.com', 'password7', 'consumer', NOW()),
('Hank', 'hank@example.com', 'password8', 'retailer', NOW()),
('Ivy', 'ivy@example.com', 'password9', 'organization', NOW()),
('Jack', 'jack@example.com', 'password10', 'consumer', NOW());

-- Insert initial data into Food table
INSERT INTO food (fname, expiration, price, inventory, discount, ftid, is_donate, store_id) VALUES
('Apple', '2024-12-01', 1.00, 100, 0.10, 1, 0, 1),
('Banana', '2024-11-01', 0.50, 200, 0.05, 2, 0, 1),
('Carrot', '2024-10-01', 0.30, 150, 0.08, 3, 1, 2),
('Donut', '2024-09-01', 2.00, 50, 0.20, 4, 0, 2),
('Eggplant', '2024-08-01', 1.50, 70, 0.15, 5, 1, 3),
('Fish', '2024-07-01', 3.00, 80, 0.25, 6, 0, 3),
('Grapes', '2024-06-01', 2.50, 60, 0.18, 7, 0, 4),
('Honey', '2024-05-01', 5.00, 90, 0.30, 8, 0, 4),
('Ice Cream', '2024-04-01', 4.00, 40, 0.22, 9, 1, 5),
('Jam', '2024-03-01', 3.50, 110, 0.28, 10, 0, 5);

-- Insert initial data into Food Type table
INSERT INTO food_type (name) VALUES
('Fruit'),
('Vegetable'),
('Dairy'),
('Baked Goods'),
('Meat'),
('Seafood'),
('Beverage'),
('Snack'),
('Frozen'),
('Condiment');

-- Insert initial data into Orders table
INSERT INTO orders (uid, fid, money, num) VALUES
(1, 1, 1.00, 10),
(2, 2, 0.50, 20),
(3, 3, 0.30, 15),
(4, 4, 2.00, 5),
(5, 5, 1.50, 7),
(6, 6, 3.00, 8),
(7, 7, 2.50, 6),
(8, 8, 5.00, 9),
(9, 9, 4.00, 4),
(10, 10, 3.50, 11);

-- Insert initial data into Store table
INSERT INTO store (store_name, city, uid) VALUES
('Store A', 'City X', 2),
('Store B', 'City Y', 5),
('Store C', 'City Z', 8),
('Store D', 'City W', 10),
('Store E', 'City V', 11),
('Store F', 'City U', 12),
('Store G', 'City T', 13),
('Store H', 'City S', 14),
('Store I', 'City R', 15),
('Store J', 'City Q', 16);

-- Insert initial data into Subscribe table
INSERT INTO subscribe (uid, fid, create_time) VALUES
(1, 1, '2024-01-01'),
(2, 2, '2024-01-02'),
(3, 3, '2024-01-03'),
(4, 4, '2024-01-04'),
(5, 5, '2024-01-05'),
(6, 6, '2024-01-06'),
(7, 7, '2024-01-07'),
(8, 8, '2024-01-08'),
(9, 9, '2024-01-09'),
(10, 10, '2024-01-10');

-- Insert initial data into Rating and Feedback table
INSERT INTO RatingAndFeedback (UserID, FoodID, Rating, Review) VALUES
(1, 1, 5, 'Excellent quality!'),
(2, 2, 4, 'Very good, will buy again.'),
(3, 3, 3, 'Average, nothing special.'),
(4, 4, 5, 'Loved it! Highly recommend.'),
(5, 5, 4, 'Good value for money.'),
(6, 6, 3, 'It was okay.'),
(7, 7, 5, 'Delicious and fresh!'),
(8, 8, 4, 'Quite tasty.'),
(9, 9, 2, 'Not great, won\'t buy again.'),
(10, 10, 5, 'Fantastic product!');