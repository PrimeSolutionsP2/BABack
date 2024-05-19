-- Delete if it doesn't exist
DROP DATABASE IF EXISTS botellas_amor;
 
CREATE DATABASE botellas_amor DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
USE botellas_amor;
 
-- role table
CREATE TABLE role (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);
 
-- user table
CREATE TABLE user (
  id VARCHAR(10) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  phone_number VARCHAR(10),
  password VARCHAR(50) NOT NULL,
  role_id INT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES role(id)
);
-- collection_point_status table
CREATE TABLE status (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);
-- collection_point table
CREATE TABLE collection_point (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id VARCHAR(10) NOT NULL,
  status_id INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  agreement_code VARCHAR(50),
  address VARCHAR(50) NOT NULL,
  city VARCHAR(50) NOT NULL,
  state VARCHAR(50) NOT NULL,
  country VARCHAR(50) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (status_id) REFERENCES status(id)
);
-- pickup_request table
CREATE TABLE pickup_request (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id VARCHAR(10),
  collection_point_id INT NOT NULL,
  kilograms INT,
  pickup_date DATETIME NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (collection_point_id) REFERENCES collection_point(id)
);
