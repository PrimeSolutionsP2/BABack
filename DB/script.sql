-- Delete if it doesn't exist
DROP DATABASE IF EXISTS botellas_amor;
 
CREATE DATABASE botellas_amor;
 
USE botellas_amor;
 
-- role table
CREATE TABLE role (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);
 
INSERT INTO role(name) VALUES
('ADMINISTRADOR'),
('RECOLECTOR'),
('REPRESENTANTE');
 
 
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
 
INSERT INTO user (id, name, last_name, phone_number, email, password, role_id) 
VALUES 
('123', 'Usuario1', 'Apellido1', '3426236712', 'usuario1@gmail.com', 'contraseña1', 1),
('1234', 'Usuario2', 'Apellido2', '3426236712', 'usuario2@gmail.com', 'contraseña2', 2),
('12345', 'Usuario3', 'Apellido3', '3426236712', 'usuario3@gmail.com', 'contraseña3', 3),
('123456', 'Usuario4', 'Apellido4', '3426236712', 'usuario4@gmail.com', 'contraseña4', 3);
 
 
-- collection_point_status table
CREATE TABLE status (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);
 
INSERT INTO status (name) VALUES
('PENDIENTE'),
('RECHAZADO'),
('ACTIVO'),
('INACTIVO');
 
 
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
 
INSERT INTO collection_point (user_id, name, address, agreement_code, city, state, country, status_id) 
VALUES 
('12345', 'Punto de acopio #1', 'Calle Falsa 123', 'ABC123', 'Medellin', 'Antioquia', 'Colombia', 1),
('1234', 'Punto de acopio #2', 'Avenida Principal 456', 'CBA321', 'Medellin', 'Antioquia', 'Colombia', 2),
('123456', 'Punto de acopio #3', 'Calle Central 789', NULL, 'Medellin', 'Antioquia', 'Colombia', 3);
 
-- pickup_request_status TABLE
CREATE TABLE pickup_request_status (
  id INT PRIMARY KEY AUTO_INCREMENT,	
  name VARCHAR(50) NOT NULL UNIQUE
);
 
-- pickup_request_status DATA
INSERT INTO pickup_request_status (id, name)
VALUES 
(1, 'Pendiente'),
(2, 'Completado'),
(3, 'Agendada');
 
 
-- pickup_request table
CREATE TABLE pickup_request (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id VARCHAR(10),
  collection_point_id INT NOT NULL,
  kilograms INT,
  pickup_date DATETIME NOT NULL,
  commentary VARCHAR(300),
  date_create DATETIME NOT NULL,
  pickup_request_status_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (collection_point_id) REFERENCES collection_point(id),
  FOREIGN KEY (pickup_request_status_id) REFERENCES pickup_request_status(id)
);
 
INSERT INTO pickup_request (user_id, collection_point_id, kilograms, pickup_date, date_create, pickup_request_status_id )
VALUES 
('Cedula1', 3, NULL, '2024-03-29 12:00:00', NOW(), 1),
(null, 3, NULL, '2024-03-29 12:00:00', NOW(), 1);
