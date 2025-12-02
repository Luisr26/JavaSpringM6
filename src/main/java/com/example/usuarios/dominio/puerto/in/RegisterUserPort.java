package com.example.usuarios.dominio.puerto.in;

import com.example.usuarios.infrastructure.security.dto.AuthResponse;
import com.example.usuarios.infrastructure.security.dto.RegisterRequest;

/**
 * Puerto de entrada para el caso de uso de registro de usuario.
 * Define el contrato que debe implementar cualquier caso de uso de registro.
 * 
 * Principio SOLID:
 * - Interface Segregation: Interface específica para registro.
 * - Dependency Inversion: El dominio define el contrato.
 */
public interface RegisterUserPort {
    
    /**
     * Registra un nuevo usuario en el sistema.
     * @param request Datos de registro del usuario
     * @return Respuesta con tokens JWT de autenticación
     * @throws IllegalArgumentException si el email ya está registrado
     */
    AuthResponse register(RegisterRequest request);
}
