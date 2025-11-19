# JavaSpringM6 - HU-2 (Historia de Usuario 2)

## 🎯 Rama: HU-2

### Descripción
Backend con **CRUD completo** usando **Arquitectura Hexagonal**, **Spring Data JPA** y **persistencia en base de datos MySQL**.

---

## 🏗️ Arquitectura Hexagonal (Ports & Adapters)

### Capas Implementadas

#### 📦 **Dominio** (`dominio/`)
- **Modelo**: `Usuario.java` - Entidad de dominio pura
- **Puertos de Entrada** (`puerto/in/`): `UsuarioServicePort.java` - Define operaciones del servicio
- **Puertos de Salida** (`puerto/out/`): `UsuarioRepositoryPort.java` - Define operaciones de persistencia

#### 💼 **Aplicación** (`Application/UseCase/`)
Casos de uso que implementan la lógica de negocio:
- `CrearUsuarioUseCase.java` - Crear usuario
- `ObtenerUsuarioUseCase.java` - Obtener usuario por ID
- `ListarUsuariosUseCase.java` - Listar todos los usuarios
- `ActualizarUsuarioUseCase.java` - Actualizar usuario
- `EliminarUsuarioUseCase.java` - Eliminar usuario

#### 🔌 **Infraestructura** (`infrastructure/`)

**Adaptador de Persistencia** (`AdaptadorPersistencia/`):
- `UsuarioEntity.java` - Entidad JPA para base de datos
- `SpringDataUsuarioRepository.java` - JpaRepository de Spring Data
- `DatabaseUserRepository.java` - Implementación del puerto con mapeo Domain ↔ Entity

**Adaptador de Entrada** (`AdaptadorEntrada/`):
- `UsuarioController.java` - REST API con Swagger UI

---

## 🚀 Tecnologías

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA** - Persistencia
- **MySQL 8.0** - Base de datos
- **Swagger/OpenAPI** - Documentación de API
- **Maven** - Gestión de dependencias
- **Lombok** - Reducción de boilerplate

---

## 📡 API REST Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/usuarios` | Crear usuario |
| GET | `/api/usuarios` | Listar todos los usuarios |
| GET | `/api/usuarios/{id}` | Obtener usuario por ID |
| PUT | `/api/usuarios/{id}` | Actualizar usuario |
| DELETE | `/api/usuarios/{id}` | Eliminar usuario |

---

## 💾 Persistencia en Base de Datos

### Configuración MySQL
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/javaHU2
spring.datasource.username=root
spring.datasource.password=Qwe.123*
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

### Tabla de Base de Datos
```sql
CREATE TABLE usuarios (
  id CHAR(36) NOT NULL,
  name VARCHAR(255) NOT NULL,
  cargo VARCHAR(255) NOT NULL,
  telefono VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

---

## 🏃 Cómo Ejecutar

### 1. Crear la base de datos
```sql
CREATE DATABASE javaHU2;
```

### 2. Iniciar la aplicación
```bash
mvn spring-boot:run
```

### 3. Acceder a la API
- **API REST**: http://localhost:8082/api/usuarios
- **Swagger UI**: http://localhost:8082/swagger-ui.html

---

## 📊 Modelo de Datos

### Usuario
```json
{
  "id": "uuid",
  "name": "string",
  "cargo": "string",
  "telefono": "string"
}
```

---

## 🗂️ Estructura del Proyecto

```
src/main/java/com/example/CRUD_List/
├── Application/
│   └── UseCase/
│       ├── CrearUsuarioUseCase.java
│       ├── ObtenerUsuarioUseCase.java
│       ├── ListarUsuariosUseCase.java
│       ├── ActualizarUsuarioUseCase.java
│       └── EliminarUsuarioUseCase.java
├── dominio/
│   ├── modelo/
│   │   └── Usuario.java
│   └── puerto/
│       ├── in/
│       │   └── UsuarioServicePort.java
│       └── out/
│           └── UsuarioRepositoryPort.java
└── infrastructure/
    ├── AdaptadorEntrada/
    │   └── UsuarioController.java
    └── AdaptadorPersistencia/
        ├── UsuarioEntity.java
        ├── SpringDataUsuarioRepository.java
        └── DatabaseUserRepository.java
```

---

## 📝 Notas de la Rama HU-2

- ✅ CRUD completo implementado
- ✅ Arquitectura Hexagonal con puertos `in/out`
- ✅ **Persistencia en MySQL** (cambio principal desde HU-1)
- ✅ Spring Data JPA para manejo de base de datos
- ✅ API REST documentada con Swagger
- ✅ Todos los datos se guardan en base de datos MySQL

---

## 🔄 Evolución del Proyecto

- **HU-1**: Almacenamiento en memoria (Listas)
- **HU-2**: Persistencia en base de datos MySQL ← **Estás aquí**
- **HU-3**: Integración con frontend Angular/TypeScript

---

## 🆚 Diferencias con HU-1

| Característica | HU-1 | HU-2 |
|----------------|------|------|
| Almacenamiento | Listas en memoria | Base de datos MySQL |
| Persistencia | ❌ Se pierde al reiniciar | ✅ Permanente |
| Spring Data JPA | ❌ No | ✅ Sí |
| Entidad JPA | ❌ No | ✅ UsuarioEntity |
| JpaRepository | ❌ No | ✅ SpringDataUsuarioRepository |
