-- Tabla de torneos (Nicolas). Depende de la tabla videojuegos (Gabriel): crear esa primero.
CREATE DATABASE IF NOT EXISTS videojuegos_torneos;
USE videojuegos_torneos;

CREATE TABLE IF NOT EXISTS torneos (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigo_videojuego VARCHAR(20) NOT NULL,
    cupo_maximo INT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (codigo_videojuego) REFERENCES videojuegos(codigo)
);

-- Registro de prueba (requiere que exista el videojuego V001).
INSERT INTO torneos (codigo, nombre, codigo_videojuego, cupo_maximo, estado)
VALUES ('T001', 'Copa Fidelitas', 'V001', 2, 'Abierto');

SELECT * FROM torneos;
