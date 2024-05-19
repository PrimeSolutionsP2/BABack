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
('1111111111', 'Laura', 'Gómez', '3426236712', 'laura.gomez@example.com', '12345', 1),
('2222222222', 'Andrés', 'Martínez', '3217095829', 'andres.martinez@example.com', '12345', 2),
('1045492052', 'Daniel Ricardo', 'Palacios Diego', '3217095829', 'danielpalacios@example.com', '12345', 2),
('3333333333', 'Isabel', 'Fernández', '3218524670', 'isabel.fernandez@example.com', '12345', 3),
('4444444444', 'Pedro', 'Sánchez', '3218027475', 'pedro.sanchez@example.com', '12345', 3),
('5555555555', 'Carolina', 'Ramírez', '3014244529', 'carolina.ramirez@example.com', '12345', 3);
 
 
 
-- collection_point_status table
CREATE TABLE status (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO status (name) VALUES
('PENDIENTE'),
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
  user_id_file TINYINT(1) NOT NULL DEFAULT 0,
  place_image TINYINT(1) NOT NULL DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (status_id) REFERENCES status(id)
);
 
INSERT INTO collection_point (user_id, name, address, agreement_code, city, state, country, status_id) 
VALUES 
('3333333333', 'Punto de acopio #4', '123 Calle Principal', 'XYZ789', 'Bogotá', 'Cundinamarca', 'Colombia', 1),
('3333333333', 'Punto de acopio #5', '456 Avenida Central', NULL, 'Cali', 'Valle del Cauca', 'Colombia', 1),
('3333333333', 'Punto de acopio #6', '789 Carrera Norte', 'ABC321', 'Barranquilla', 'Atlántico', 'Colombia', 1),
('3333333333', 'Punto de acopio #7', '101 Plaza del Sol', 'CBA123', 'Cartagena', 'Bolívar', 'Colombia', 1),
('3333333333', 'Punto de acopio #8', '222 Avenida del Mar', NULL, 'Santa Marta', 'Magdalena', 'Colombia', 1),
('3333333333', 'Punto de acopio #9', '333 Calle del Río', 'XYZ321', 'Pereira', 'Risaralda', 'Colombia', 2),
('3333333333', 'Punto de acopio #10', '444 Avenida del Bosque', 'ABC789', 'Manizales', 'Caldas', 'Colombia', 2),
('3333333333', 'Punto de acopio #11', '555 Carrera del Lago', NULL, 'Ibagué', 'Tolima', 'Colombia', 2),
('3333333333', 'Punto de acopio #12', '666 Plaza del Parque', 'XYZ123', 'Villavicencio', 'Meta', 'Colombia', 2),
('4444444444', 'Punto de acopio #13', '777 Calle del Puente', 'CBA789', 'Pasto', 'Nariño', 'Colombia', 2),
('4444444444', 'Punto de acopio #14', '888 Avenida del Cerro', NULL, 'Armenia', 'Quindío', 'Colombia', 2),
('4444444444', 'Punto de acopio #15', '999 Carrera del Valle', 'ABC987', 'Popayán', 'Cauca', 'Colombia', 3),
('4444444444', 'Punto de acopio #16', '111 Calle del Pueblo', 'XYZ987', 'Tunja', 'Boyacá', 'Colombia', 3),
('4444444444', 'Punto de acopio #17', '222 Avenida del Campo', NULL, 'Riohacha', 'La Guajira', 'Colombia', 3),
('4444444444', 'Punto de acopio #18', '333 Plaza del Monte', 'CBA987', 'Quibdó', 'Chocó', 'Colombia', 3),
('4444444444', 'Punto de acopio #19', '444 Calle del Mar', 'ABC987', 'Neiva', 'Huila', 'Colombia', 3),
('5555555555', 'Punto de acopio #20', '555 Avenida del Sol', NULL, 'Sincelejo', 'Sucre', 'Colombia', 3);
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
('1111111111', 3, NULL, '2024-03-29 12:00:00', NOW(), 1),
('1111111111', 3, NULL, '2024-03-29 12:00:00', NOW(), 1);
