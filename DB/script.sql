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

INSERT INTO user (id, name, last_name, email, phone_number, password, role_id) VALUES
(1, 'Juan', 'Pérez', 'juan@example.com', '1234567890', 'password123', 1),
(123, 'Usuario1', 'Apellido1', 'usuario1@gmail.com', '3426236712', 'contraseña1', 1),
(1234, 'Usuario2', 'Apellido2', 'usuario2@gmail.com', '3426236712', 'contraseña2', 2),
(12345, 'Usuario3', 'Apellido3', 'usuario3@gmail.com', '3426236712', 'contraseña3', 3),
(123456, 'Usuario4', 'Apellido4', 'usuario4@gmail.com', '3426236712', 'contraseña4', 2),
(2, 'María', 'Gómez', 'maria@example.com', '2345678901', 'password456', 2),
(3, 'Carlos', 'Rodríguez', 'carlos@example.com', '3456789012', 'password789', 3),
(4, 'Laura', 'López', 'laura@example.com', '4567890123', 'passwordabc', 1),
(5, 'Andrés', 'Martínez', 'andres@example.com', '5678901234', 'passworddef', 2),
(6, 'Ana', 'Sánchez', 'ana@example.com', '6789012345', 'passwordghi', 3),
(7, 'Pedro', 'García', 'pedro@example.com', '7890123456', 'passwordjkl', 1),
(8, 'Sofía', 'Hernández', 'sofia@example.com', '8901234567', 'passwordmno', 2),
(9, 'Diego', 'Díaz', 'diego@example.com', '9012345678', 'passwordpqr', 3);


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
  created_at DATETIME DEFAULT NOW(),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (status_id) REFERENCES status(id)
);

INSERT INTO collection_point (user_id, status_id, name, agreement_code, address, city, state, country, created_at) VALUES
(12345, 1, 'Punto de acopio #1', 'ABC123', 'Calle Falsa 123', 'Medellin', 'Antioquia', 'Colombia', '2024-05-16 00:28:50'),
(1234, 2, 'Punto de acopio #2', 'CBA321', 'Avenida Principal 456', 'Medellin', 'Antioquia', 'Colombia', '2024-05-16 00:28:50'),
(123456, 3, 'Punto de acopio #3', NULL, 'Calle Central 789', 'Medellin', 'Antioquia', 'Colombia', '2024-05-16 00:28:50'),
(1, 1, 'Punto de Recolección A', 'AGMT1234', 'Calle 123', 'Bogotá', 'Bogotá D.C.', 'Colombia', '2024-05-01 10:00:00'),
(2, 2, 'Punto de Recolección B', 'AGMT5678', 'Carrera 456', 'Medellín', 'Antioquia', 'Colombia', '2024-05-02 11:00:00'),
(3, 1, 'Punto de Recolección C', 'AGMT9101', 'Avenida 789', 'Cali', 'Valle del Cauca', 'Colombia', '2024-05-03 12:00:00'),
(4, 3, 'Punto de Recolección D', NULL, 'Calle 101', 'Barranquilla', 'Atlántico', 'Colombia', '2024-05-04 13:00:00'),
(5, 1, 'Punto de Recolección E', 'AGMT1121', 'Carrera 202', 'Cartagena', 'Bolívar', 'Colombia', '2024-05-05 14:00:00'),
(6, 2, 'Punto de Recolección F', 'AGMT3141', 'Avenida 505', 'Santa Marta', 'Magdalena', 'Colombia', '2024-05-06 15:00:00'),
(7, 3, 'Punto de Recolección G', NULL, 'Calle 707', 'Pasto', 'Nariño', 'Colombia', '2024-05-07 16:00:00'),
(8, 1, 'Punto de Recolección H', 'AGMT6171', 'Carrera 818', 'Villavicencio', 'Meta', 'Colombia', '2024-05-08 17:00:00'),
(9, 2, 'Punto de Recolección I', 'AGMT9202', 'Avenida 929', 'Cúcuta', 'Norte de Santander', 'Colombia', '2024-05-09 18:00:00'),
(1, 2, 'Punto de Recolección F', 'AGMT3141', 'Carrera 123', 'Bogotá', 'Bogotá D.C.', 'Colombia', '2024-05-10 10:00:00'),
(2, 1, 'Punto de Recolección G', 'AGMT4151', 'Avenida 456', 'Medellín', 'Antioquia', 'Colombia', '2024-05-11 11:00:00'),
(3, 3, 'Punto de Recolección H', NULL, 'Calle 789', 'Cali', 'Valle del Cauca', 'Colombia', '2024-05-12 12:00:00'),
(4, 1, 'Punto de Recolección I', 'AGMT6171', 'Carrera 101', 'Barranquilla', 'Atlántico', 'Colombia', '2024-05-13 13:00:00'),
(5, 2, 'Punto de Recolección J', 'AGMT7181', 'Avenida 202', 'Cartagena', 'Bolívar', 'Colombia', '2024-05-14 14:00:00'),
(1, 3, 'Punto de Recolección K', NULL, 'Calle 303', 'Bogotá', 'Bogotá D.C.', 'Colombia', '2024-05-15 15:00:00'),
(2, 1, 'Punto de Recolección L', 'AGMT8191', 'Carrera 404', 'Medellín', 'Antioquia', 'Colombia', '2024-05-16 16:00:00'),
(3, 2, 'Punto de Recolección M', 'AGMT9202', 'Avenida 505', 'Cali', 'Valle del Cauca', 'Colombia', '2024-05-17 17:00:00'),
(4, 3, 'Punto de Recolección N', NULL, 'Calle 606', 'Barranquilla', 'Atlántico', 'Colombia', '2024-05-18 18:00:00'),
(5, 1, 'Punto de Recolección O', 'AGMT0313', 'Carrera 707', 'Cartagena', 'Bolívar', 'Colombia', '2024-05-19 19:00:00'),
(1, 2, 'Punto de Recolección P', 'AGMT3141', 'Carrera 123', 'Bogotá', 'Bogotá D.C.', 'Colombia', '2024-04-20 08:00:00'),
(2, 1, 'Punto de Recolección Q', 'AGMT4151', 'Avenida 456', 'Medellín', 'Antioquia', 'Colombia', '2024-04-22 09:30:00'),
(3, 3, 'Punto de Recolección R', NULL, 'Calle 789', 'Cali', 'Valle del Cauca', 'Colombia', '2024-04-24 10:45:00'),
(4, 1, 'Punto de Recolección S', 'AGMT6171', 'Carrera 101', 'Barranquilla', 'Atlántico', 'Colombia', '2024-04-26 11:15:00'),
(5, 2, 'Punto de Recolección T', 'AGMT7181', 'Avenida 202', 'Cartagena', 'Bolívar', 'Colombia', '2024-04-28 12:00:00'),
(1, 3, 'Punto de Recolección U', NULL, 'Calle 303', 'Bogotá', 'Bogotá D.C.', 'Colombia', '2024-04-30 13:30:00'),
(2, 1, 'Punto de Recolección V', 'AGMT8191', 'Carrera 404', 'Medellín', 'Antioquia', 'Colombia', '2024-05-02 14:45:00'),
(3, 2, 'Punto de Recolección W', 'AGMT9202', 'Avenida 505', 'Cali', 'Valle del Cauca', 'Colombia', '2024-05-04 15:20:00'),
(4, 3, 'Punto de Recolección X', NULL, 'Calle 606', 'Barranquilla', 'Atlántico', 'Colombia', '2024-05-06 16:00:00'),
(5, 1, 'Punto de Recolección Y', 'AGMT0313', 'Carrera 707', 'Cartagena', 'Bolívar', 'Colombia', '2024-05-08 17:10:00'),
(1, 1, 'Punto de Recolección Z', 'AGMT0414', 'Calle 808', 'Bucaramanga', 'Santander', 'Colombia', '2024-05-10 08:00:00'),
(2, 2, 'Punto de Recolección AA', 'AGMT1415', 'Carrera 909', 'Ibagué', 'Tolima', 'Colombia', '2024-05-11 09:00:00'),
(3, 3, 'Punto de Recolección AB', NULL, 'Avenida 1010', 'Neiva', 'Huila', 'Colombia', '2024-05-12 10:00:00'),
(4, 1, 'Punto de Recolección AC', 'AGMT2425', 'Calle 1111', 'Manizales', 'Caldas', 'Colombia', '2024-05-13 11:00:00'),
(5, 2, 'Punto de Recolección AD', 'AGMT3435', 'Carrera 1212', 'Pereira', 'Risaralda', 'Colombia', '2024-05-14 12:00:00'),
(6, 3, 'Punto de Recolección AE', NULL, 'Avenida 1313', 'Montería', 'Córdoba', 'Colombia', '2024-05-15 13:00:00'),
(7, 1, 'Punto de Recolección AF', 'AGMT4445', 'Calle 1414', 'Armenia', 'Quindío', 'Colombia', '2024-05-16 14:00:00'),
(8, 2, 'Punto de Recolección AG', 'AGMT5456', 'Carrera 1515', 'Tunja', 'Boyacá', 'Colombia', '2024-05-17 15:00:00'),
(9, 3, 'Punto de Recolección AH', NULL, 'Avenida 1616', 'Popayán', 'Cauca', 'Colombia', '2024-05-18 16:00:00'),
(1, 1, 'Punto de Recolección AI', 'AGMT6467', 'Calle 1717', 'Valledupar', 'Cesar', 'Colombia', '2024-05-19 17:00:00'),
(2, 2, 'Punto de Recolección AJ', 'AGMT7478', 'Carrera 1818', 'Florencia', 'Caquetá', 'Colombia', '2024-05-20 18:00:00'),
(3, 3, 'Punto de Recolección AK', NULL, 'Avenida 1919', 'Mocoa', 'Putumayo', 'Colombia', '2024-05-21 19:00:00'),
(4, 1, 'Punto de Recolección AL', 'AGMT8489', 'Calle 2020', 'Yopal', 'Casanare', 'Colombia', '2024-05-22 20:00:00'),
(5, 2, 'Punto de Recolección AM', 'AGMT9490', 'Carrera 2121', 'Mitú', 'Vaupés', 'Colombia', '2024-05-23 21:00:00'),
(6, 3, 'Punto de Recolección AN', NULL, 'Avenida 2222', 'Inírida', 'Guainía', 'Colombia', '2024-05-24 22:00:00'),
(7, 1, 'Punto de Recolección AO', 'AGMT0516', 'Calle 2323', 'San José del Guaviare', 'Guaviare', 'Colombia', '2024-05-25 23:00:00'),
(8, 2, 'Punto de Recolección AP', 'AGMT1517', 'Carrera 2424', 'Puerto Carreño', 'Vichada', 'Colombia', '2024-05-26 08:00:00'),
(9, 3, 'Punto de Recolección AQ', NULL, 'Avenida 2525', 'Leticia', 'Amazonas', 'Colombia', '2024-05-27 09:00:00'),
(1, 1, 'Punto de Recolección AR', 'AGMT2526', 'Calle 2626', 'Quibdó', 'Chocó', 'Colombia', '2024-05-28 10:00:00'),
(2, 2, 'Punto de Recolección AS', 'AGMT3536', 'Carrera 2727', 'Riohacha', 'La Guajira', 'Colombia', '2024-05-29 11:00:00'),
(3, 3, 'Punto de Recolección AT', NULL, 'Avenida 2828', 'Arauca', 'Arauca', 'Colombia', '2024-05-30 12:00:00'),
(4, 1, 'Punto de Recolección AU', 'AGMT4546', 'Calle 2929', 'San Andrés', 'San Andrés y Providencia', 'Colombia', '2024-06-01 13:00:00'),
(5, 2, 'Punto de Recolección AV', 'AGMT5557', 'Carrera 3030', 'Villanueva', 'La Guajira', 'Colombia', '2024-06-02 14:00:00'),
(6, 3, 'Punto de Recolección AW', NULL, 'Avenida 3131', 'Sincelejo', 'Sucre', 'Colombia', '2024-06-03 15:00:00'),
(7, 1, 'Punto de Recolección AX', 'AGMT6567', 'Calle 3232', 'San Gil', 'Santander', 'Colombia', '2024-06-04 16:00:00'),
(8, 2, 'Punto de Recolección AY', 'AGMT7578', 'Carrera 3333', 'Caucasia', 'Antioquia', 'Colombia', '2024-06-05 17:00:00'),
(9, 3, 'Punto de Recolección AZ', NULL, 'Avenida 3434', 'Sogamoso', 'Boyacá', 'Colombia', '2024-06-06 18:00:00'),
(1, 1, 'Punto de Recolección BA', 'AGMT8589', 'Calle 3535', 'Tumaco', 'Nariño', 'Colombia', '2024-06-07 19:00:00'),
(2, 2, 'Punto de Recolección BB', 'AGMT9590', 'Carrera 3636', 'Ipiales', 'Nariño', 'Colombia', '2024-06-08 20:00:00'),
(3, 3, 'Punto de Recolección BC', NULL, 'Avenida 3737', 'Tuluá', 'Valle del Cauca', 'Colombia', '2024-06-09 21:00:00'),
(4, 1, 'Punto de Recolección BD', 'AGMT0601', 'Calle 3838', 'Palmira', 'Valle del Cauca', 'Colombia', '2024-06-10 22:00:00'),
(5, 2, 'Punto de Recolección BE', 'AGMT1611', 'Carrera 3939', 'Yumbo', 'Valle del Cauca', 'Colombia', '2024-06-11 23:00:00'),
(6, 3, 'Punto de Recolección BF', NULL, 'Avenida 4040', 'Jamundí', 'Valle del Cauca', 'Colombia', '2024-06-12 08:00:00'),
(7, 1, 'Punto de Recolección BG', 'AGMT2621', 'Calle 4141', 'Buga', 'Valle del Cauca', 'Colombia', '2024-06-13 09:00:00'),
(8, 2, 'Punto de Recolección BH', 'AGMT3632', 'Carrera 4242', 'Candelaria', 'Valle del Cauca', 'Colombia', '2024-06-14 10:00:00'),
(9, 3, 'Punto de Recolección BI', NULL, 'Avenida 4343', 'Florida', 'Valle del Cauca', ' Colombia', '2024-06-15 11:00:00'),
(1, 1, 'Punto de Recolección BJ', 'AGMT4644', 'Calle 4444', 'Pradera', 'Valle del Cauca', 'Colombia', '2024-06-16 12:00:00'),
(2, 2, 'Punto de Recolección BK', 'AGMT5655', 'Carrera 4545', 'El Cerrito', 'Valle del Cauca', 'Colombia', '2024-06-17 13:00:00'),
(3, 3, 'Punto de Recolección BL', NULL, 'Avenida 4646', 'La Unión', 'Valle del Cauca', 'Colombia', '2024-06-18 14:00:00'),
(4, 1, 'Punto de Recolección BM', 'AGMT6667', 'Calle 4747', 'Zarzal', 'Valle del Cauca', 'Colombia', '2024-06-19 15:00:00'),
(5, 2, 'Punto de Recolección BN', 'AGMT7678', 'Carrera 4848', 'Roldanillo', 'Valle del Cauca', 'Colombia', '2024-06-20 16:00:00'),
(6, 3, 'Punto de Recolección BO', NULL, 'Avenida 4949', 'Bugalagrande', 'Valle del Cauca', 'Colombia', '2024-06-21 17:00:00'),
(7, 1, 'Punto de Recolección BP', 'AGMT8689', 'Calle 5050', 'Ansermanuevo', 'Valle del Cauca', 'Colombia', '2024-06-22 18:00:00'),
(8, 2, 'Punto de Recolección BQ', 'AGMT9690', 'Carrera 5151', 'Andalucía', 'Valle del Cauca', 'Colombia', '2024-06-23 19:00:00');

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
('123456', 3, NULL, '2024-03-29 12:00:00', NOW(), 1),
(null, 3, NULL, '2024-03-29 12:00:00', NOW(), 1);
