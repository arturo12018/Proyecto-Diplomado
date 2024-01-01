
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
                         ID_usuario INT NOT NULL ,
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
                                 ('Alemán'),
                                 ('Italiano'),
                                 ('Portugués'),
                                 ('Chino'),
                                 ('Japonés');

-- Inserta editoriales
INSERT INTO editoriales (Nombre) VALUES
                                     ('Editorial A'),
                                     ('Editorial B'),
                                     ('Editorial C'),
                                     ('Editorial D'),
                                     ('Editorial E'),
                                     ('Editorial F'),
                                     ('Editorial G'),
                                     ('Editorial H');


-- Inserta autores
INSERT INTO autores (Apellido_Paterno, Apellido_Materno, Nombre) VALUES
                                                                     ('González', 'Pérez', 'Juan'),
                                                                     ('López', NULL, 'María'),
                                                                     ('Martínez', 'Sánchez', 'Carlos'),
                                                                     ('Pérez', 'Gómez', 'Ana'),
                                                                     ('Hernández', NULL, 'Luisa'),
                                                                     ('Rodríguez', 'Fernández', 'Laura'),
                                                                     ('Díaz', 'Ramírez', 'Miguel'),
                                                                     ('Gómez', 'López', 'Isabel'),
                                                                     ('Sánchez', 'Martínez', 'Pedro'),
                                                                     ('Fernández', 'Gutiérrez', 'Sofía'),
                                                                     ('García', 'Fernández', 'Javier'),
                                                                     ('Ramírez', 'González', 'Alejandra'),
                                                                     ('Díaz', 'Martínez', 'Jorge'),
                                                                     ('Fernández', 'López', 'Lucía'),
                                                                     ('Gutiérrez', 'Sánchez', 'Andrés'),
                                                                     ('López', 'Hernández', 'Valeria'),
                                                                     ('Martínez', 'Gómez', 'Francisco'),
                                                                     ('Sánchez', 'Rodríguez', 'Mariana'),
                                                                     ('Gómez', 'Díaz', 'Alberto'),
                                                                     ('Hernández', 'Pérez', 'Carmen');


-- Insertar libros
INSERT INTO libros (ISBN, Titulo, Descripcion, ID_editorial, ID_idioma, Anio_publicacion, Edicion, Imagen_portada, Valoracion, Precio, Numero_paginas)
VALUES
    (9788494562002, 'Cien años de soledad', 'Una novela que narra la historia de la familia Buendía', 1, 1, '1967-01-01', 1, 'Sin_imagen_disponible.jpg', 4.8, 19.99, 432),
    (9788408160336, 'El Código Da Vinci', 'Un emocionante thriller de conspiraciones y misterio', 2, 2, '2003-02-01', 1, 'Sin_imagen_disponible.jpg', 4.5, 24.99, 589),
    (9789874563211, 'El Alquimista', 'La historia de un joven en busca de su destino personal', 3, 3, '1988-03-01', 1, 'Sin_imagen_disponible.jpg', 4.7, 14.99, 208),
    (9788499891239, '1984', 'Una distopía que presenta un mundo bajo un gobierno totalitario', 4, 4, '1949-06-01', 1, 'Sin_imagen_disponible.jpg', 4.2, 17.99, 328),
    (9788408214332, 'Crónica de una muerte anunciada', 'La historia de un asesinato en un pequeño pueblo', 3, 1, '1981-01-01', 1, 'Sin_imagen_disponible.jpg', 4.6, 16.99, 150),
    (9786073135307, 'Rayuela', 'Una novela que desafía la estructura tradicional', 1, 2, '1963-05-01', 1, 'Sin_imagen_disponible.jpg', 4.4, 22.99, 536),
    (9788433960587, 'Cien años de soledad (Edición especial)', 'Edición especial con contenido adicional', 2, 1, '1970-05-01', 2, 'Sin_imagen_disponible.jpg', 4.9, 29.99, 500),
    (9786075273434, 'La Sombra del Viento', 'Una novela de misterio y literatura', 3, 2, '2001-06-01', 1, 'Sin_imagen_disponible.jpg', 4.6, 21.99, 576),
    (9788498383665, 'El Hobbit', 'Una historia de aventuras en la Tierra Media', 4, 3, '1937-09-21', 1, 'Sin_imagen_disponible.jpg', 4.8, 18.99, 320),
    (9788417092018, 'Sapiens: De animales a dioses', 'Breve historia de la humanidad', 1, 4, '2014-02-10', 1, 'Sin_imagen_disponible.jpg', 4.7, 25.99, 464),
    (9780307476463, 'Los Juegos del Hambre', 'Una trilogía distópica de aventuras', 2, 1, '2008-09-14', 1, 'Sin_imagen_disponible.jpg', 4.5, 27.99, 374),
    (9780553386790, 'El Silmarillion', 'La historia del mundo de Tolkien', 3, 3, '1977-09-15', 1, 'Sin_imagen_disponible.jpg', 4.4, 23.99, 365),
    (9788466347979, 'Don Quijote de la Mancha', 'La historia del caballero loco y su fiel escudero', 1, 1, '1605-01-01', 1, 'Sin_imagen_disponible.jpg', 4.5, 14.99, 928),
    (9788490628377, 'Harry Potter y la piedra filosofal', 'El inicio de las aventuras del joven mago Harry Potter', 2, 2, '1997-06-26', 1, 'Sin_imagen_disponible.jpg', 4.8, 22.99, 320),
    (9788498383627, 'Cincuenta sombras de Grey', 'Una novela erótica que se convirtió en un fenómeno mundial', 3, 3, '2011-05-25', 1, 'Sin_imagen_disponible.jpg', 3.9, 18.99, 514),
    (9788408172735, 'El principito', 'Un cuento filosófico y poético sobre la amistad y la vida', 4, 1, '1943-04-06', 1, 'Sin_imagen_disponible.jpg', 4.7, 12.99, 96),
    (9788420484180, 'Matar a un ruiseñor', 'Un clásico de la literatura que aborda temas de raza e injusticia', 1, 2, '1960-07-11', 1, 'Sin_imagen_disponible.jpg', 4.6, 16.99, 416),
    (9788420404513, '1984', 'Una novela distópica que reflexiona sobre el poder y la libertad', 2, 1, '1949-06-08', 2, 'Sin_imagen_disponible.jpg', 4.4, 15.99, 352),
    (9788498387540, 'Origen', 'Una intrigante novela de misterio y conspiraciones', 3, 4, '2017-10-03', 1, 'Sin_imagen_disponible.jpg', 4.5, 26.99, 640),
    (9788432220832, 'La Odisea', 'Las aventuras del héroe griego Odiseo en su viaje de regreso a casa', 4, 1, '700-01-01', 1, 'Sin_imagen_disponible.jpg', 4.6, 18.99, 416),
    (9788498388783, 'El señor de los anillos: La comunidad del anillo', 'La primera parte de la épica trilogía de J.R.R. Tolkien', 1, 3, '1954-07-29', 1, 'Sin_imagen_disponible.jpg', 4.9, 24.99, 576),
    (9788420668562, 'Cien años de soledad (Edición de lujo)', 'Una edición especial con ilustraciones y contenido exclusivo', 2, 1, '1970-05-01', 3, 'Sin_imagen_disponible.jpg', 5.0, 39.99, 500),
    (9788416213947, 'El Alquimista (Edición conmemorativa)', 'Una edición especial por el 25 aniversario de la obra de Paulo Coelho', 3, 3, '1988-03-01', 2, 'Sin_imagen_disponible.jpg', 4.7, 19.99, 208),
    (9788427215154, 'Crónica de una muerte anunciada (Edición ilustrada)', 'Una edición con ilustraciones que complementan la historia', 1, 2, '1981-01-01', 2, 'Sin_imagen_disponible.jpg', 4.5, 22.99, 150),
    (9786073135314, 'Rayuela (Edición anotada)', 'Una edición con notas y anotaciones que enriquecen la lectura', 2, 4, '1963-05-01', 2, 'Sin_imagen_disponible.jpg', 4.4, 27.99, 536),
    (9788408214349, 'El Código Da Vinci (Edición especial)', 'Una edición especial con contenido adicional', 3, 1, '2003-02-01', 2, 'Sin_imagen_disponible.jpg', 4.6, 29.99, 589),
    (9789874563228, 'El Alquimista (Edición de bolsillo)', 'Una edición compacta para llevar a todas partes', 4, 2, '1988-03-01', 1, 'Sin_imagen_disponible.jpg', 4.5, 9.99, 208);


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
                                             (5, 9788408160336),  -- Luisa Hernández con "El Código Da Vinci"
                                             (1, 9788433960587),  -- Laura Rodríguez Fernández con "Cien años de soledad (Edición especial)"
                                             (2, 9786075273434),  -- Miguel Díaz Ramírez con "La Sombra del Viento"
                                             (3, 9788498383665),  -- Isabel Gómez López con "El Hobbit"
                                             (4, 9788417092018),  -- Pedro Sánchez Martínez con "Sapiens: De animales a dioses"
                                             (5, 9780307476463),  -- Sofía Fernández Gutiérrez con "Los Juegos del Hambre"
                                             (1, 9780553386790),  -- Laura Rodríguez Fernández con "El Silmarillion"
                                             (2, 9780553386790),  -- Miguel Díaz Ramírez con "El Silmarillion"
                                             (3, 9780307476463),  -- Isabel Gómez López con "Los Juegos del Hambre"
                                             (4, 9788433960587),  -- Pedro Sánchez Martínez con "Cien años de soledad (Edición especial)"
                                             (5, 9786075273434),  -- Sofía Fernández Gutiérrez con "La Sombra del Viento"
                                             (6, 9788466347979),    -- Lucía Fernández López con "Don Quijote de la Mancha"
                                             (7, 9788490628377),    -- Francisco Martínez Gómez con "Harry Potter y la piedra filosofal"
                                             (8, 9788498383627),    -- Mariana Sánchez Rodríguez con "Cincuenta sombras de Grey"
                                             (9, 9788408172735),    -- Alberto Gómez Díaz con "El principito"
                                             (10, 9788420484180),   -- Carmen Hernández Pérez con "Matar a un ruiseñor"
                                             (11, 9788420404513),   -- Nuevo autor con "1984"
                                             (12, 9788498387540),   -- Nuevo autor con "Origen"
                                             (13, 9788432220832),   -- Nuevo autor con "La Odisea"
                                             (14, 9788498388783),   -- Nuevo autor con "El señor de los anillos: La comunidad del anillo"
                                             (15, 9788420668562),   -- Nuevo autor con "Cien años de soledad (Edición de lujo)"
                                             (6, 9788416213947),    -- Lucía Fernández López con "El Alquimista (Edición conmemorativa)"
                                             (7, 9788427215154),    -- Francisco Martínez Gómez con "Crónica de una muerte anunciada (Edición ilustrada)"
                                             (8, 9786073135314),    -- Mariana Sánchez Rodríguez con "Rayuela (Edición anotada)"
                                             (9, 9788408214349),    -- Alberto Gómez Díaz con "El Código Da Vinci (Edición especial)"
                                             (10, 9789874563228);  -- Carmen Hernández Pérez con "El Alquimista (Edición de bolsillo)"


-- Insertar usuarios
INSERT INTO usuarios (Nombre, Apellido_Paterno, Apellido_Materno, Correo, Contraseña, Estado_Activo) VALUES
                                                                                                         ('Juan', 'González', 'Pérez', 'juangonzalez@example.com', '$2a$10$8/Sxv2fjYi4e7HSv0HWcYu91/ngy7RLcF0ADBC3zRd1tJGGjM4.wq', 1),
                                                                                                         ('María', 'López', NULL, 'marialopez@example.com', '$2a$10$8/Sxv2fjYi4e7HSv0HWcYu91/ngy7RLcF0ADBC3zRd1tJGGjM4.wq', 1),
                                                                                                         ('Carlos', 'Martínez', 'Sánchez', 'carlosmartinez@example.com', '$2a$10$8/Sxv2fjYi4e7HSv0HWcYu91/ngy7RLcF0ADBC3zRd1tJGGjM4.wq', 0),
                                                                                                         ('Ana', 'Pérez', 'Gómez', 'anaperez@example.com', '$2a$10$8/Sxv2fjYi4e7HSv0HWcYu91/ngy7RLcF0ADBC3zRd1tJGGjM4.wq', 1),
                                                                                                         ('Luisa', 'Hernández', NULL, 'luisahernandez@example.com', '$2a$10$8/Sxv2fjYi4e7HSv0HWcYu91/ngy7RLcF0ADBC3zRd1tJGGjM4.wq', 0);


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

