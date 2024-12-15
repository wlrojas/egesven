CREATE DATABASE IF NOT EXISTS egesven CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE egesven;

DROP TABLE IF EXISTS recibo_producto;
DROP TABLE IF EXISTS recibo;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS estado_envio;
DROP TABLE IF EXISTS usuario;

CREATE TABLE categoria (
    id          INT(3) NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(20) NOT NULL,
    descripcion VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE estado_envio (
    id          INT(1) NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO estado_envio (descripcion) VALUES ('Pendiente'),('Procesando'),('Enviado'),('Entregado');

CREATE TABLE usuario (
    rut         VARCHAR(12) NOT NULL,
    nombre       VARCHAR(50) NOT NULL,
    apellido     VARCHAR(50) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    direccion    VARCHAR(100),
    password        VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(1) NOT NULL,
    PRIMARY KEY (rut)
);

CREATE TABLE producto (
    id           INT(6) NOT NULL AUTO_INCREMENT,
    nombre       VARCHAR(20) NOT NULL,
    precio       INT NOT NULL,
    descripcion  VARCHAR(100) NOT NULL,
    stock        INT(4) NOT NULL,
    id_categoria INT(3) NOT NULL,
    url_imagen    VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT producto_categoria_fk FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

CREATE TABLE recibo (
    id              INT(10) NOT NULL AUTO_INCREMENT,
    fecha           DATE NOT NULL,
    total           INT NOT NULL,
    id_cliente      VARCHAR(12) NOT NULL,
    id_estado_envio INT(1) NOT NULL,
    metodo_pago      VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT recibo_estado_envio_fk FOREIGN KEY (id_estado_envio) REFERENCES estado_envio(id),
    CONSTRAINT recibo_usuario_fk FOREIGN KEY (id_cliente) REFERENCES usuario(rut)
);

CREATE TABLE recibo_producto (
    id              BIGINT NOT NULL AUTO_INCREMENT,
    cantidad        INT(3) NOT NULL,
    precio_unitario INT NOT NULL,
    id_producto     INT(6) NOT NULL,
    id_recibo       INT(10) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT recibo_producto_producto_fk FOREIGN KEY (id_producto) REFERENCES producto(id),
    CONSTRAINT recibo_producto_recibo_fk FOREIGN KEY (id_recibo) REFERENCES recibo(id)
);

INSERT INTO categoria (nombre, descripcion) VALUES
('Bebidas', 'Bebidas típicas de Chile'),
('Comida', 'Comidas típicas de Chile'),
('Artesanías', 'Artesanías tradicionales de Chile');

INSERT INTO producto (nombre, precio, descripcion, stock, id_categoria, url_imagen) VALUES
('Vino Cabernet', 15000, 'Vino tinto de alta calidad producido en el Valle de Maipo.', 50, 1, 'https://ejemplo.com/vino_cabernet.jpg'),
('Empanada de Pino', 3000, 'Empanada tradicional rellena de carne, cebolla, huevo, aceitunas y pasas.', 200, 2, 'https://ejemplo.com/empanada_pino.jpg'),
('Pisco Sour', 8000, 'Cóctel clásico chileno hecho con pisco, limón, azúcar, clara de huevo y amargo de angostura.', 100, 1, 'https://ejemplo.com/pisco_sour.jpg'),
('Merquén en Polvo', 2500, 'Condimento chileno hecho a base de ají cacho de cabra ahumado y molido.', 150, 2, 'https://ejemplo.com/merquen.jpg'),
('Artesanía de Madera', 5000, 'Figura de madera artesanal representando el Cerro San Cristóbal en Santiago.', 30, 3, 'https://ejemplo.com/artesania_cerro.jpg'),
('Mote con Huesillos', 2000, 'Bebida tradicional de mote con huesillos, hecha con trigo mote y duraznos secos.', 100, 2, 'https://ejemplo.com/mote_huesillos.jpg');

insert into usuario (rut, nombre, apellido, email, direccion, password, tipo_usuario) VALUES
    ('123456789', 'admin', 'admin', 'admin@admin.cl', 'adminhouse','$2a$10$A/vG50EXWVIcbQbDWBium.WTNKnrSS6vSJ7thyl832s7ayciZXC/2', 'A');