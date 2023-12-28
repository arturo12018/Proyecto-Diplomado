
DROP DATABASE IF EXISTS libreria ;

-- Creacion de la base de datos
CREATE DATABASE libreria;
USE libreria;

DROP TABLE IF EXISTS administrador;
DROP TABLE IF EXISTS rol;
DROP TABLE IF EXISTS compra_libro;
DROP TABLE IF EXISTS compras;
DROP TABLE IF EXISTS estados;
DROP TABLE IF EXISTS paises;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS autor_libro;
DROP TABLE IF EXISTS autores;
DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS editoriales;
DROP TABLE IF EXISTS idiomas;







-- Creacion de tablas
CREATE TABLE rol (
                     id_rol INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                     descripcion VARCHAR(50) NOT NULL UNIQUE
);

-- Crear tabla Administrador
CREATE TABLE administrador (
                               ID_admin INT AUTO_INCREMENT PRIMARY KEY,
                               Nombre VARCHAR(50) NOT NULL,
                               Apellido_Paterno VARCHAR(50) NOT NULL,
                               Apellido_Materno VARCHAR(50),
                               Correo VARCHAR(100) NOT NULL UNIQUE,
                               Contraseña CHAR(150) NOT NULL,
                               ID_rol INT NOT NULL,
                               Estado_Activo BIT DEFAULT 1,
                               FOREIGN KEY (ID_rol) REFERENCES rol(ID_rol)
);



-- Crear tabla idioma
CREATE TABLE idiomas (
                         ID_idioma INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                         Nombre VARCHAR(50) NOT NULL UNIQUE
);


-- Crear tabla editorial
CREATE TABLE editoriales (
                             ID_editorial INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                             Nombre VARCHAR(50) NOT NULL UNIQUE
);


-- Crear tabla autor
CREATE TABLE autores (
                         ID_autor INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                         Apellido_Paterno VARCHAR(50) NOT NULL,
                         Apellido_Materno VARCHAR(50),
                         Nombre VARCHAR(50) NOT NULL
);

-- Crear tabla libro
CREATE TABLE libros (
                        ISBN BIGINT  PRIMARY KEY NOT NULL UNIQUE,
                        Titulo VARCHAR(100) NOT NULL,
                        Descripcion VARCHAR(250) NOT NULL,
                        ID_editorial INT NOT NULL,
                        ID_idioma INT NOT NULL,
                        Anio_publicacion DATE NOT NULL,
                        Edicion INT NOT NULL,
                        Imagen_portada VARCHAR(255) NOT NULL,
                        Valoracion DECIMAL(3,2) NOT NULL DEFAULT 0.00 CHECK (Precio>0) ,
                        Precio DECIMAL(10,2) NOT NULL CHECK (Precio>=0),
                        Numero_paginas INT NOT NULL CHECK (Numero_paginas > 0),
                        FOREIGN KEY (ID_editorial) REFERENCES editoriales(ID_editorial),
                        FOREIGN KEY (ID_idioma) REFERENCES idiomas(ID_idioma)
);


-- Crear tabla autor_libro
CREATE TABLE autor_libro (
                             ID_autor INT NOT NULL,
                             ISBN BIGINT  NOT NULL,
                             PRIMARY KEY (ID_autor, ISBN),
                             FOREIGN KEY (ID_autor) REFERENCES autores(ID_autor),
                             FOREIGN KEY (ISBN) REFERENCES libros(ISBN)
);


-- Crear tabla usuarios
CREATE TABLE usuarios (
                          ID_usuario INT PRIMARY KEY  NOT NULL AUTO_INCREMENT,
                          Nombre VARCHAR(50) NOT NULL,
                          Apellido_Paterno VARCHAR(50) NOT NULL,
                          Apellido_Materno VARCHAR(50),
                          Correo VARCHAR(100) UNIQUE NOT NULL,
                          Contraseña CHAR(150) NOT NULL,
                          Estado_Activo BIT NOT NULL DEFAULT 1
);

-- Crear tabla Pais
CREATE TABLE paises (
                        ID_pais INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                        Nombre VARCHAR(50) NOT NULL UNIQUE
);

-- Crear tabla Estado
CREATE TABLE estados (
                         ID_estado INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                         Nombre VARCHAR(50) NOT NULL,
                         ID_pais INT NOT NULL,
                         FOREIGN KEY (ID_pais) REFERENCES paises(ID_pais)
);



-- Crear tabla Compras
CREATE TABLE compras (
                         ID_usuario INT,
                         ID_compra INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                         Fecha DATETIME NOT NULL DEFAULT NOW(),
                         Total DECIMAL(10,2) NOT NULL CHECK(Total>=0),
                         Tarjeta_credito_numero VARCHAR(16) NOT NULL,
                         Tarjeta_credito_cvv VARCHAR(3) NOT NULL,
                         Tarjeta_credito_mes_expiracion INT NOT NULL,
                         Tarjeta_credito_anio_expiracion INT NOT NULL,
                         Direccion VARCHAR(150) NOT NULL,
                         ID_estado INT NOT NULL,
                         ID_pais INT NOT NULL,
                         FOREIGN KEY (ID_usuario) REFERENCES usuarios(ID_usuario),
                         FOREIGN KEY (ID_estado) REFERENCES estados(ID_estado),
                         FOREIGN KEY (ID_pais) REFERENCES paises(ID_pais)
);



-- Crear tabla Compra_Libro
CREATE TABLE compra_libro (
                              ID_compra INT NOT NULL,
                              ISBN BIGINT NOT NULL,
                              Cantidad INT NOT NULL CHECK(Cantidad>=0),
                              Precio_unitario DECIMAL(10,2) NOT NULL CHECK(Precio_unitario>=0),
                              FOREIGN KEY (ID_compra) REFERENCES compras(ID_compra),
                              FOREIGN KEY (ISBN) REFERENCES libros(ISBN),
                              PRIMARY KEY (ID_compra, ISBN)
);


-- Insters de prueba

-- ROL
-- Insertar roles en la tabla Rol
INSERT INTO rol (Descripcion)
VALUES
    ('Administración_general'),
    ('Administración_de_libros'),
    ('Administración_de_usuarios_y_administradores');


-- Insertar registros de administradores en la tabla Administrador
-- Insertar ejemplos de registros de administradores en la tabla Administrador
INSERT INTO administrador (Nombre, Apellido_Paterno, Apellido_Materno, Correo, Contraseña, ID_rol, Estado_Activo)
VALUES
    ('Ana', 'García', 'López', 'ana.garcia@example.com', '$2a$10$DxzByNC64eZA9twAQJjTteA2.0AMLHbIjU8nM.jxpX2hI1Jje2NtW', 1, 1),
    ('Juan', 'Martínez', 'Sánchez', 'juan.martinez@example.com', '$2a$10$DxzByNC64eZA9twAQJjTteA2.0AMLHbIjU8nM.jxpX2hI1Jje2NtW', 2, 1),
    ('María', 'López', 'Hernández', 'maria.lopez@example.com', '$2a$10$DxzByNC64eZA9twAQJjTteA2.0AMLHbIjU8nM.jxpX2hI1Jje2NtW',  1, 0),
    ('Pedro', 'Ramírez', 'Gómez', 'pedro.ramirez@example.com', '$2a$10$DxzByNC64eZA9twAQJjTteA2.0AMLHbIjU8nM.jxpX2hI1Jje2NtW', 3, 1),
    ('Laura', 'González', 'Pérez', 'laura.gonzalez@example.com', '$2a$10$DxzByNC64eZA9twAQJjTteA2.0AMLHbIjU8nM.jxpX2hI1Jje2NtW',  2, 0);

-- Inserta idiomas
INSERT INTO idiomas (Nombre) VALUES
                                 ('Español'),
                                 ('Inglés'),
                                 ('Francés'),
                                 ('Alemán');

-- Inserta editoriales
INSERT INTO editoriales (Nombre) VALUES
                                     ('Editorial A'),
                                     ('Editorial B'),
                                     ('Editorial C'),
                                     ('Editorial D');


-- Inserta autores
INSERT INTO autores (Apellido_Paterno, Apellido_Materno, Nombre) VALUES
                                                                     ('González', 'Pérez', 'Juan'),
                                                                     ('López', NULL, 'María'),
                                                                     ('Martínez', 'Sánchez', 'Carlos'),
                                                                     ('Pérez', 'Gómez', 'Ana'),
                                                                     ('Hernández', NULL, 'Luisa');


-- Insertar libros
INSERT INTO libros (ISBN, Titulo, Descripcion, ID_editorial, ID_idioma, Anio_publicacion, Edicion, Imagen_portada, Valoracion, Precio, Numero_paginas)
VALUES
    (9788494562002, 'Cien años de soledad', 'Una novela que narra la historia de la familia Buendía', 1, 1, '1967-01-01', 1, 'Sin_imagen_disponible.jpg', 4.8, 19.99, 432),
    (9788408160336, 'El Código Da Vinci', 'Un emocionante thriller de conspiraciones y misterio', 2, 2, '2003-02-01', 1, 'Sin_imagen_disponible.jpg', 4.5, 24.99, 589),
    (9789874563211, 'El Alquimista', 'La historia de un joven en busca de su destino personal', 3, 3, '1988-03-01', 1, 'Sin_imagen_disponible.jpg', 4.7, 14.99, 208),
    (9788499891239, '1984', 'Una distopía que presenta un mundo bajo un gobierno totalitario', 4, 4, '1949-06-01', 1, 'Sin_imagen_disponible.jpg', 4.2, 17.99, 328),
    (9788408214332, 'Crónica de una muerte anunciada', 'La historia de un asesinato en un pequeño pueblo', 3, 1, '1981-01-01', 1, 'Sin_imagen_disponible.jpg', 4.6, 16.99, 150),
    (9786073135307, 'Rayuela', 'Una novela que desafía la estructura tradicional', 1, 2, '1963-05-01', 1, 'Sin_imagen_disponible.jpg', 4.4, 22.99, 536);



-- Ejemplos de relación entre autores y libros
INSERT INTO autor_libro (ID_autor, ISBN) VALUES
                                             (1, 9788494562002),  -- Juan González Pérez con "Cien años de soledad"
                                             (2, 9788408160336),  -- María López con "El Código Da Vinci"
                                             (3, 9789874563211),  -- Carlos Martínez Sánchez con "El Alquimista"
                                             (4, 9788499891239),  -- Ana Pérez Gómez con "1984"
                                             (5, 9788494562002),  -- Luisa Hernández con "Cien años de soledad"
                                             (1, 9788408214332),  -- Juan González Pérez con "Crónica de una muerte anunciada"
                                             (2, 9788408214332),  -- María López con "Crónica de una muerte anunciada"
                                             (3, 9786073135307),  -- Carlos Martínez Sánchez con "Rayuela"
                                             (4, 9786073135307),  -- Ana Pérez Gómez con "Rayuela"
                                             (5, 9788408160336);  -- Luisa Hernández con "El Código Da Vinci"


-- Insertar usuarios
INSERT INTO usuarios (Nombre, Apellido_Paterno, Apellido_Materno, Correo, Contraseña, Estado_Activo) VALUES
                                                                                                         ('Juan', 'González', 'Pérez', 'juangonzalez@example.com', 'clave123', 1),
                                                                                                         ('María', 'López', NULL, 'marialopez@example.com', 'abc456', 1),
                                                                                                         ('Carlos', 'Martínez', 'Sánchez', 'carlosmartinez@example.com', 'pass789', 0),
                                                                                                         ('Ana', 'Pérez', 'Gómez', 'anaperez@example.com', 'qwerty', 1),
                                                                                                         ('Luisa', 'Hernández', NULL, 'luisahernandez@example.com', 'password123', 0);


-- Inserts para la tabla Paises
INSERT INTO paises (Nombre)
VALUES
    ('México'),
    ('Estados Unidos'),
    ('Canadá'),
    ('España'),
    ('Argentina');

-- Inserts para la tabla Estados
INSERT INTO estados (Nombre, ID_pais)
VALUES
    ('Ciudad de México', 1),
    ('Jalisco', 1),
    ('California', 2),
    ('Texas', 2),
    ('Ontario', 3),
    ('Quebec', 3),
    ('Madrid', 4),
    ('Barcelona', 4),
    ('Buenos Aires', 5);


-- Insertar compras
INSERT INTO compras (ID_usuario, Fecha, Total, Tarjeta_credito_numero, Tarjeta_credito_cvv, Tarjeta_credito_mes_expiracion, Tarjeta_credito_anio_expiracion, Direccion, ID_estado, ID_pais)
VALUES
    (1,  '2023-06-01 10:30:00', 50.99, '1234567890123456', '123', 12, 2025, 'Calle Principal 123', 1, 1),
    (2,  '2023-06-02 15:45:00', 79.99, '9876543210987654', '456', 11, 2024, 'Avenida Central 456', 4, 2),
    (3,  '2023-06-03 09:15:00', 35.50, '5678901234567890', '789', 9, 2023, 'Calle Secundaria 789', 6, 3),
    (4,  '2023-06-03 09:15:01', 35.50, '5678901234567890', '789', 9, 2023, 'Calle Secundaria 789', 1, 1);

-- Insertar compras sin fecha
INSERT INTO compras (ID_usuario, Total, Tarjeta_credito_numero, Tarjeta_credito_cvv, Tarjeta_credito_mes_expiracion, Tarjeta_credito_anio_expiracion, Direccion, ID_estado, ID_pais)
VALUES  (4, 40.50, '5678901234567890', '789', 9, 2023, 'Calle Secundaria 789', 1, 1);


-- Insertar libros por compra
-- Insertar datos en la tabla Compra_libro
INSERT INTO compra_libro (ID_compra, ISBN, Cantidad, Precio_unitario)
VALUES
    (1, 9788494562002, 2, 19.99),
    (1, 9788408160336, 1, 24.99),
    (2, 9789874563211, 3, 14.99),
    (2, 9788499891239, 1, 17.99),
    (3, 9788408214332, 2, 16.99),
    (4, 9786073135307, 1, 22.99);



-- Pruebas
SELECT * FROM rol;
SELECT * FROM administrador;
SELECT * FROM idiomas;
SELECT * FROM editoriales;
SELECT * FROM libros;
SELECT * FROM autores;
SELECT * FROM autor_libro;
SELECT * FROM usuarios;
SELECT * FROM paises;
SELECT * FROM estados;
SELECT * FROM compras;
SELECT * FROM compra_libro;


-- Prueba libro con idioma y editorial
SELECT  A.titulo,B.nombre,C.nombre FROM  libros A
                                             LEFT JOIN editoriales B ON B.ID_editorial=A.ID_editorial
                                             LEFT JOIN idiomas C ON C.ID_idioma=A.ID_idioma;

-- Prueba nombre de autores con libros
SELECT B.*,C.* FROM  libros A
                         LEFT JOIN autor_libro B ON B.ISBN=A.ISBN
                         LEFT JOIN autores C ON C.ID_autor=B.ID_autor
WHERE A.ISBN=9788408160336;

-- Prueba compra  por usuarios
SELECT B.* FROM  usuarios A
                     LEFT JOIN compras B ON B.id_usuario=A.id_usuario
WHERE A.id_usuario=4;

-- Prueba compra por libros
SELECT B.* FROM  compras A
                     LEFT JOIN compra_libro B ON B.id_compra=A.id_compra
                     LEFT JOIN libros C ON C.ISBN=B.ISBN
WHERE B.id_compra=2;

