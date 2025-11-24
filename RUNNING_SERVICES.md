# Cómo ejecutar los microservicios

Este proyecto contiene 3 microservicios independientes que comparten la misma base de datos:

## Microservicios

1. **Usuarios** - Puerto 8081
2. **Venues** - Puerto 8082  
3. **Events** - Puerto 8083

## Iniciar todos los microservicios automáticamente

### ✅ Opción 1: Comando Maven (Recomendado)

```bash
./mvnw exec:exec
```

¡Es así de simple! Este comando iniciará automáticamente los 3 microservicios en orden con un delay de 3 segundos entre cada uno.

Para detener todos los servicios: presiona `Ctrl+C`

### Opción 2: Script Bash directo

```bash
./start-all-services.sh
```

### Opción 3: Manualmente (uno por uno)

Abre **3 terminales diferentes** y ejecuta en cada una:

**Terminal 1 - Usuarios:**
```bash
./mvnw spring-boot:run -Dspring-boot.run.mainClass=com.example.usuarios.UsuariosApplication -Dspring-boot.run.profiles=usuarios
```

**Terminal 2 - Venues:**
```bash
./mvnw spring-boot:run -Dspring-boot.run.mainClass=com.example.venues.VenuesApplication -Dspring-boot.run.profiles=venues
```

**Terminal 3 - Events:**
```bash
./mvnw spring-boot:run -Dspring-boot.run.mainClass=com.example.events.EventsApplication -Dspring-boot.run.profiles=events
```

## Acceder a la documentación Swagger

Una vez iniciados, puedes acceder a la documentación de cada microservicio:

- **Usuarios:** http://localhost:8081/swagger-ui.html
- **Venues:** http://localhost:8082/swagger-ui.html
- **Events:** http://localhost:8083/swagger-ui.html

## Verificar que están corriendo

Puedes verificar que los servicios están activos con:

```bash
curl http://localhost:8081/api/usuarios
curl http://localhost:8082/api/venues
curl http://localhost:8083/api/events
```

## Notas Importantes

- **Los 3 microservicios comparten la misma base de datos** `javadb`
- Asegúrate de que MySQL esté corriendo antes de iniciar los servicios
- El script `bd.sql` crea todas las tablas necesarias para los 3 microservicios
