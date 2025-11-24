```sql
-- DDL para MySQL / MySQL Workbench
-- Este archivo contiene la definición de la tabla 'usuarios' pensada para ejecutarse en MySQL / MariaDB
-- Usar MySQL Workbench y ejecutar: Sql > Execute (using MyISH) en los INSERTS automáticos usa UTF8MB4 y COLLATE=utf8mb4_unicode_ci;

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS JavaM6;

-- Seleccionar la base de datos
USE JavaM6;

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` CHAR(36) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `cargo` VARCHAR(255) NOT NULL,
  `telefono` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Ejemplo de INSERT usando UUID() de MySQL:
-- INSERT INTO `usuarios` (id, name, cargo, telefono) VALUES (UUID(), 'Juan Perez', 'Desarrollador', '555-1234');

-- Tabla de venues (lugares/recintos para eventos)
CREATE TABLE IF NOT EXISTS venues (
    id VARCHAR(36) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(500),
    capacidad INT,
    descripcion TEXT,
    creado_por_usuario_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (creado_por_usuario_id) REFERENCES usuarios(id) ON DELETE RESTRICT
);

-- Tabla de events (eventos)
CREATE TABLE IF NOT EXISTS events (
    id VARCHAR(36) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME NOT NULL,
    venue_id VARCHAR(36),
    creado_por_usuario_id VARCHAR(36) NOT NULL,
    capacidad_maxima INT,
    FOREIGN KEY (venue_id) REFERENCES venues(id) ON DELETE SET NULL,
    FOREIGN KEY (creado_por_usuario_id) REFERENCES usuarios(id) ON DELETE RESTRICT
);

-- Tabla de inscripciones a eventos (relación muchos a muchos entre usuarios y eventos)
CREATE TABLE IF NOT EXISTS event_inscriptions (
    id VARCHAR(36) PRIMARY KEY,
    event_id VARCHAR(36) NOT NULL,
    usuario_id VARCHAR(36) NOT NULL,
    fecha_inscripcion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    UNIQUE KEY unique_inscription (event_id, usuario_id)
);

-- Nota: si prefieres almacenar UUIDs en formato binario (16 bytes) para ahorrar espacio,
-- cambia `id` a BINARY(16) y convierte con UNHEX(REPLACE(UUID(),'-',''))/UUID_TO_BIN según convenga.
