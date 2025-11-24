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
    CONSTRAINT fk_event_inscription_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
    CONSTRAINT fk_event_inscription_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    CONSTRAINT unique_event_usuario UNIQUE (event_id, usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Nota: si prefieres almacenar UUIDs en formato binario (16 bytes) para ahorrar espacio,
-- cambia `id` a BINARY(16) y convierte con UNHEX(REPLACE(UUID(),'-',''))/UUID_TO_BIN según convenga.

-- ========================================
-- DATOS DE EJEMPLO
-- ========================================

-- Ejemplo de INSERT usando UUID() de MySQL para usuarios:
INSERT INTO `usuarios` (id, name, cargo, telefono) VALUES 
    (UUID(), 'Juan Pérez', 'Desarrollador', '555-1234'),
    (UUID(), 'María García', 'Diseñadora', '555-5678'),
    (UUID(), 'Carlos López', 'Product Manager', '555-9012');

-- Ejemplos de INSERT para venues (lugares/recintos):
INSERT INTO venues (id, nombre, direccion, capacidad, descripcion, creado_por_usuario_id) VALUES
    (UUID(), 'Centro de Convenciones Principal', 'Av. Libertador 1234, Ciudad', 500, 'Amplio centro de convenciones con tecnología de punta', (SELECT id FROM usuarios WHERE name = 'Juan Pérez' LIMIT 1)),
    (UUID(), 'Auditorio Municipal', 'Calle Central 567, Centro', 200, 'Auditorio ideal para conferencias y presentaciones', (SELECT id FROM usuarios WHERE name = 'María García' LIMIT 1)),
    (UUID(), 'Sala de Eventos El Roble', 'Av. Los Robles 890, Norte', 150, 'Sala elegante para eventos corporativos', (SELECT id FROM usuarios WHERE name = 'Carlos López' LIMIT 1)),
    (UUID(), 'Teatro Nacional', 'Plaza Mayor s/n, Centro Histórico', 800, 'Teatro con excelente acústica y vista panorámica', (SELECT id FROM usuarios WHERE name = 'Juan Pérez' LIMIT 1));

-- Ejemplos de INSERT para events (eventos):
INSERT INTO events (id, nombre, descripcion, fecha_inicio, fecha_fin, venue_id, creado_por_usuario_id, capacidad_maxima) VALUES
    (UUID(), 'Conferencia de Tecnología 2024', 'Conferencia anual sobre las últimas tendencias en tecnología', '2024-12-15 09:00:00', '2024-12-15 17:00:00', 
     (SELECT id FROM venues WHERE nombre = 'Centro de Convenciones Principal' LIMIT 1),
     (SELECT id FROM usuarios WHERE name = 'Juan Pérez' LIMIT 1), 450),
    (UUID(), 'Workshop de Diseño UX/UI', 'Taller práctico sobre diseño de experiencia de usuario', '2024-12-20 14:00:00', '2024-12-20 18:00:00',
     (SELECT id FROM venues WHERE nombre = 'Auditorio Municipal' LIMIT 1),
     (SELECT id FROM usuarios WHERE name = 'María García' LIMIT 1), 180),
    (UUID(), 'Networking Empresarial', 'Evento de networking para profesionales del sector tecnológico', '2024-12-10 18:00:00', '2024-12-10 21:00:00',
     (SELECT id FROM venues WHERE nombre = 'Sala de Eventos El Roble' LIMIT 1),
     (SELECT id FROM usuarios WHERE name = 'Carlos López' LIMIT 1), 100),
    (UUID(), 'Concierto de Año Nuevo', 'Gran concierto para celebrar el año nuevo', '2024-12-31 20:00:00', '2025-01-01 00:30:00',
     (SELECT id FROM venues WHERE nombre = 'Teatro Nacional' LIMIT 1),
     (SELECT id FROM usuarios WHERE name = 'Juan Pérez' LIMIT 1), 750);

-- Nota: Para inscribir usuarios a eventos, primero necesitas los IDs reales.
-- Ejemplo de cómo inscribir usuarios (ejecutar después de tener los datos):
-- INSERT INTO event_inscriptions (id, event_id, usuario_id, fecha_inscripcion) VALUES
--     (UUID(), 'ID_DEL_EVENTO', 'ID_DEL_USUARIO', NOW());
