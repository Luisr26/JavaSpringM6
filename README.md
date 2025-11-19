# JavaSpringM6 - HU-1 (Historia de Usuario 1)

## 🎯 Rama: HU-1

### Descripción
Backend básico con **almacenamiento en memoria** usando **Listas de Java** para gestionar usuarios. No hay persistencia en base de datos.

---

## 🏗️ Arquitectura

### Estructura Simple
- **Modelo**: `Usuario.java` - Entidad básica
- **Servicio**: Lógica de negocio con almacenamiento en memoria
- **Controller**: REST API endpoints

---

## 🚀 Tecnologías

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Web** - REST API
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

---

## 💾 Almacenamiento

### ⚠️ Almacenamiento en Memoria (Listas)
```java
private List<Usuario> usuarios = new ArrayList<>();
```

**Características:**
- ✅ Simple y rápido
- ❌ **Los datos se pierden al reiniciar la aplicación**
- ❌ No hay persistencia
- ❌ Solo existe en memoria RAM

---

## 🏃 Cómo Ejecutar

### 1. Iniciar la aplicación
```bash
mvn spring-boot:run
```

### 2. Acceder a la API
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
├── dominio/
│   └── modelo/
│       └── Usuario.java
├── Application/
│   └── UseCase/
│       ├── CrearUsuarioUseCase.java
│       ├── ObtenerUsuarioUseCase.java
│       └── ListarUsuariosUseCase.java
└── infrastructure/
    └── AdaptadorEntrada/
        └── UsuarioController.java
```

---

## 📝 Notas de la Rama HU-1

- ✅ API REST básica funcionando
- ✅ Swagger UI para documentación
- ✅ Operaciones CRUD básicas (Create, Read)
- ⚠️ **Almacenamiento en memoria (no persistente)**
- ❌ No hay base de datos
- ❌ Los datos se pierden al reiniciar

---

## 🔄 Evolución del Proyecto

- **HU-1**: Almacenamiento en memoria (Listas) ← **Estás aquí**
- **HU-2**: Persistencia en base de datos MySQL
- **HU-3**: Integración con frontend Angular/TypeScript

---

## ⚠️ Limitaciones

| Limitación | Descripción |
|------------|-------------|
| Sin persistencia | Los datos se pierden al reiniciar |
| Solo en memoria | Usa listas de Java (`ArrayList`) |
| CRUD incompleto | Solo Create y Read implementados |
| Sin base de datos | No hay MySQL ni JPA |

---

## 🔜 Próximos Pasos

Para tener persistencia de datos, consulta la rama **HU-2** que incluye:
- ✅ Persistencia en MySQL
- ✅ Spring Data JPA
- ✅ CRUD completo (Create, Read, Update, Delete)
- ✅ Arquitectura Hexagonal completa
