# JavaSpringM6 - HU-3 (Historia de Usuario 3)

## 🎯 Rama: HU-3

### Descripción
Backend con **CRUD completo** usando **Arquitectura Hexagonal** y **Spring Data JPA**, preparado para conectarse con un **frontend Angular/TypeScript**.

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

## ⚙️ Configuración

### Base de Datos MySQL
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/javaHU2
spring.datasource.username=root
spring.datasource.password=Qwe.123*
spring.jpa.hibernate.ddl-auto=update
```

### Puerto del Servidor
```properties
server.port=8082
```

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

## 🔗 Integración con Frontend Angular

### CORS Configurado
La aplicación está preparada para recibir peticiones desde un frontend Angular.

### Ejemplo de Consumo desde Angular/TypeScript

```typescript
// usuario.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = 'http://localhost:8082/api/usuarios';

  constructor(private http: HttpClient) {}

  // Listar todos
  getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.apiUrl);
  }

  // Obtener por ID
  getUsuario(id: string): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/${id}`);
  }

  // Crear
  createUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.apiUrl, usuario);
  }

  // Actualizar
  updateUsuario(id: string, usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.apiUrl}/${id}`, usuario);
  }

  // Eliminar
  deleteUsuario(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
```

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

## 📝 Notas de la Rama HU-3

- ✅ CRUD completo implementado
- ✅ Arquitectura Hexagonal con puertos `in/out`
- ✅ Persistencia con Spring Data JPA y MySQL
- ✅ API REST documentada con Swagger
- ✅ Preparado para integración con frontend Angular/TypeScript
- 🔜 Próximo paso: Conectar con frontend Angular

---

## 🔄 Evolución del Proyecto

- **HU-1**: Almacenamiento en memoria (Listas)
- **HU-2**: Persistencia en base de datos MySQL
- **HU-3**: Preparado para integración con frontend Angular ← **Estás aquí**
6