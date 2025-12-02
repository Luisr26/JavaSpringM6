#!/bin/bash
# Script para lanzar los 3 microservicios en paralelo

echo "🚀 Iniciando los 3 microservicios..."
echo "================================="

# Lanzar microservicio de Usuarios en puerto 8081
echo "📦 Iniciando Usuarios en puerto 8081..."
./mvnw spring-boot:run -Dspring-boot.run.main-class=com.example.usuarios.UsuariosApplication -Dspring-boot.run.profiles=usuarios &
PID_USUARIOS=$!

# Esperar 3 segundos antes de lanzar el siguiente
sleep 3

# Lanzar microservicio de Venues en puerto 8082
echo "📦 Iniciando Venues en puerto 8082..."
./mvnw spring-boot:run -Dspring-boot.run.main-class=com.example.venues.VenuesApplication -Dspring-boot.run.profiles=venues &
PID_VENUES=$!

# Esperar 3 segundos antes de lanzar el siguiente
sleep 3

# Lanzar microservicio de Events en puerto 8083
echo "📦 Iniciando Events en puerto 8083..."
./mvnw spring-boot:run -Dspring-boot.run.main-class=com.example.events.EventsApplication -Dspring-boot.run.profiles=events &
PID_EVENTS=$!

echo ""
echo "✅ Todos los microservicios están iniciando..."
echo "================================="
echo "📌 Usuarios - http://localhost:8081 (PID: $PID_USUARIOS)"
echo "📌 Venues   - http://localhost:8082 (PID: $PID_VENUES)"
echo "📌 Events   - http://localhost:8083 (PID: $PID_EVENTS)"
echo "================================="
echo "Para detener todos los servicios presiona Ctrl+C"
echo ""

# Función para limpiar procesos al recibir Ctrl+C
trap "echo 'Deteniendo todos los servicios...'; kill $PID_USUARIOS $PID_VENUES $PID_EVENTS 2>/dev/null; exit" INT TERM

# Esperar indefinidamente
wait
