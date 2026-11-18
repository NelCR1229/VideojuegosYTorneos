CREATE DATABASE IF NOT EXISTS VideojuegosTorneosDB;

USE VideojuegosTorneosDB;

-- USUARIOS
CREATE TABLE IF NOT EXISTS usuarios (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    clave VARCHAR(255) NOT NULL,
    rol ENUM('Administrador', 'Organizador', 'Participante') NOT NULL
);

-- VIDEOJUEGOS
CREATE TABLE IF NOT EXISTS videojuegos (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(100) NOT NULL,
    plataforma VARCHAR(100) NOT NULL
);

-- TORNEOS
CREATE TABLE IF NOT EXISTS torneos (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigo_videojuego VARCHAR(20) NOT NULL,
    cupo_maximo INT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (codigo_videojuego)
        REFERENCES videojuegos(codigo)
);

-- INSCRIPCIONES
CREATE TABLE IF NOT EXISTS inscripciones (
    codigo VARCHAR(20) PRIMARY KEY,
    codigo_usuario VARCHAR(20) NOT NULL,
    codigo_torneo VARCHAR(20) NOT NULL,

    FOREIGN KEY (codigo_usuario)
        REFERENCES usuarios(codigo),

    FOREIGN KEY (codigo_torneo)
        REFERENCES torneos(codigo)
);

-- PARTIDAS
CREATE TABLE IF NOT EXISTS partidas (
    codigo VARCHAR(20) PRIMARY KEY,
    codigo_torneo VARCHAR(20) NOT NULL,
    codigo_jugador1 VARCHAR(20) NOT NULL,
    codigo_jugador2 VARCHAR(20) NOT NULL,
    codigo_ganador VARCHAR(20),

    FOREIGN KEY (codigo_torneo)
        REFERENCES torneos(codigo),

    FOREIGN KEY (codigo_jugador1)
        REFERENCES usuarios(codigo),

    FOREIGN KEY (codigo_jugador2)
        REFERENCES usuarios(codigo),

    FOREIGN KEY (codigo_ganador)
        REFERENCES usuarios(codigo)
);
SELECT * FROM usuarios;
SELECT * FROM videojuegos;
SELECT * FROM torneos;
SELECT * FROM inscripciones;
SELECT * FROM partidas;


-- CUENTAS DE DEMOSTRACION
INSERT INTO usuarios (codigo, nombre, usuario, clave, rol)
VALUES
('U001', 'Nelson Cardona', 'nelson', '123', 'Participante'),
('U002', 'Administrador de prueba', 'admin', '123', 'Administrador'),
('U003', 'Organizador de prueba', 'organizador', '123', 'Organizador'),
('U004', 'Ana Prueba', 'ana', '123', 'Participante'),
('U005', 'Bruno Prueba', 'bruno', '123', 'Participante');

-- VIDEOJUEGO DE DEMOSTRACION
INSERT INTO videojuegos (codigo, nombre, genero, plataforma)
VALUES ('V001', 'Juego de prueba', 'Deportes', 'PC');

-- TORNEO ABIERTO CON DOS CUPOS
INSERT INTO torneos (
    codigo, nombre, codigo_videojuego, cupo_maximo, estado
)
VALUES ('T001', 'Torneo de prueba', 'V001', 2, 'Abierto');





SELECT codigo, nombre, usuario, rol
FROM usuarios
WHERE codigo = 'UPR01';