#!/bin/bash
# ==========================================
# Script de automatización para Backend Java
# Arquitectura Hexagonal + Docker + MySQL + JWT
# ==========================================
# Uso: ./create_backend.sh com.mycompany.project

set -e

if [ -z "$1" ]; then
  echo "❌ Error: Debes proporcionar el paquete base."
  echo "Ejemplo: ./create_backend.sh com.mycompany.project"
  exit 1
fi

PACKAGE_PATH=$(echo $1 | tr '.' '/')
BASE_DIR="src/main/java/$PACKAGE_PATH"
RESOURCES_DIR="src/main/resources"

echo "🚀 Creando arquitectura Hexagonal Estricta para: $1"
echo "=================================================="

# ==================== Crear directorios ====================
echo "📁 Creando estructura de directorios..."

# Application Layer
mkdir -p $BASE_DIR/application/{usecases,services,dto}

# Domain Layer
mkdir -p $BASE_DIR/domain/{model,ports/{in,out}}

# Infrastructure Layer
mkdir -p $BASE_DIR/infrastructure/{adapters/{in/rest,out/persistence},config,entities,security/{jwt,config,dto,exception,service}}

# Resources
mkdir -p $RESOURCES_DIR

# ==================== Crear archivos placeholder ====================
echo "📄 Creando archivos base..."

# Application Layer
touch $BASE_DIR/application/usecases/RegisterUseCase.java
touch $BASE_DIR/application/usecases/LoginUseCase.java
touch $BASE_DIR/application/usecases/RefreshTokenUseCase.java
touch $BASE_DIR/application/dto/AuthRequest.java
touch $BASE_DIR/application/dto/AuthResponse.java

# Domain Layer
touch $BASE_DIR/domain/model/User.java
touch $BASE_DIR/domain/model/Role.java
touch $BASE_DIR/domain/ports/in/RegisterUserPort.java
touch $BASE_DIR/domain/ports/in/LoginUserPort.java
touch $BASE_DIR/domain/ports/in/RefreshTokenPort.java
touch $BASE_DIR/domain/ports/out/UserRepositoryPort.java
touch $BASE_DIR/domain/ports/out/JwtPort.java

# Infrastructure Layer
touch $BASE_DIR/infrastructure/adapters/in/rest/AuthController.java
touch $BASE_DIR/infrastructure/adapters/in/rest/UserController.java
touch $BASE_DIR/infrastructure/adapters/out/persistence/JpaUserRepository.java
touch $BASE_DIR/infrastructure/adapters/out/persistence/UserEntity.java
touch $BASE_DIR/infrastructure/config/SecurityConfig.java
touch $BASE_DIR/infrastructure/config/CorsConfig.java
touch $BASE_DIR/infrastructure/config/SwaggerConfig.java
touch $BASE_DIR/infrastructure/security/jwt/JwtService.java
touch $BASE_DIR/infrastructure/security/jwt/JwtAuthenticationFilter.java
touch $BASE_DIR/infrastructure/security/service/UserDetailsServiceImpl.java

# Main Application
touch $BASE_DIR/Application.java

# Resources
touch $RESOURCES_DIR/application.yml
touch $RESOURCES_DIR/application-local.yml
touch $RESOURCES_DIR/application-docker.yml

# ==================== Docker ====================
echo "🐳 Generando configuración Docker..."

# Dockerfile multi-stage
cat <<'EOF' > Dockerfile
# ==================== MULTI-STAGE BUILD ====================
# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN addgroup -g 1001 -S appgroup && adduser -u 1001 -S appuser -G appgroup
COPY --from=build /app/target/*.jar app.jar
RUN chown -R appuser:appgroup /app
USER appuser
EXPOSE 8080
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
EOF

# Docker Compose
cat <<'EOF' > docker-compose.yml
services:
  app:
    build: .
    container_name: backend-app
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
      - JWT_SECRET_KEY=your-secret-key-base64-encoded
    depends_on:
      db:
        condition: service_healthy
    networks:
      - backend-network

  db:
    image: mysql:8.0
    container_name: mysql-db
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: mydb
    ports:
      - "3306:3306"
    volumes:
      - db_data:/var/lib/mysql
    networks:
      - backend-network
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-u", "root", "-proot"]
      interval: 10s
      timeout: 5s
      retries: 10

volumes:
  db_data:

networks:
  backend-network:
    driver: bridge
EOF

# .gitignore
cat <<'EOF' > .gitignore
target/
!.mvn/wrapper/maven-wrapper.jar
*.log
*.iml
.idea/
*.class
.DS_Store
.env
*.env
application-*.properties
!application-example.properties
EOF

# ==================== Resumen ====================
echo ""
echo "✅ Estructura creada exitosamente!"
echo ""
echo "📂 Estructura de directorios:"
echo "   $BASE_DIR/"
echo "   ├── application/"
echo "   │   ├── usecases/     <- Casos de uso"
echo "   │   ├── services/     <- Servicios de dominio"
echo "   │   └── dto/          <- DTOs"
echo "   ├── domain/"
echo "   │   ├── model/        <- Entidades de dominio"
echo "   │   └── ports/"
echo "   │       ├── in/       <- Puertos de entrada"
echo "   │       └── out/      <- Puertos de salida"
echo "   └── infrastructure/"
echo "       ├── adapters/"
echo "       │   ├── in/rest/  <- Controllers REST"
echo "       │   └── out/      <- Persistencia JPA"
echo "       ├── config/       <- Configuración"
echo "       └── security/     <- JWT, Filters"
echo ""
echo "🐳 Archivos Docker:"
echo "   - Dockerfile (multi-stage build)"
echo "   - docker-compose.yml (app + MySQL)"
echo ""
echo "🚀 Comandos útiles:"
echo "   docker-compose up -d          # Iniciar servicios"
echo "   docker-compose logs -f app    # Ver logs"
echo "   docker-compose down           # Detener servicios"
echo ""
