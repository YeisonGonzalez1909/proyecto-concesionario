-- Estructura de tablas y relaciones para verificación

CREATE TABLE categorias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255)
);

CREATE TABLE productos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    anio INT,
    precio DOUBLE,
    stock INT,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

CREATE TABLE detalles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255),
    precio DOUBLE,
    producto_id INT,
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

CREATE TABLE orden (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cantidad INT,
    productoId INT,
    total DOUBLE,
    comprador VARCHAR(255),
    metodoPago VARCHAR(255),
    fecha DATETIME
    -- No hay relación directa con producto, solo campo productoId
);
