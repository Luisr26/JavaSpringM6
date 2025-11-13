-- DDL para MySQL / MySQL Workbench
-- Este archivo contiene la definición de la tabla `usuarios` pensada para ejecutarse en MySQL / MariaDB
-- Uso: abrir en MySQL Workbench y ejecutar. Si quieres UUIDs automáticos usa UUID() en los INSERTs.

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

-- Nota: si prefieres almacenar UUIDs en formato binario (16 bytes) para ahorrar espacio,
-- cambia `id` a BINARY(16) y convierte con UNHEX(REPLACE(UUID(),'-',''))/UUID_TO_BIN según convenga.
