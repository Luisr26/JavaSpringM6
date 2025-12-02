package com.example.usuarios.dominio.puerto.in;

import com.example.usuarios.infrastructure.security.dto.AuthResponse;
import com.example.usuarios.infrastructure.security.dto.LoginRequest;

/**
 * Puerto de entrada para el caso de uso de login de usuario.
 * Define el contrato que debe implementar cualquier caso de uso de login.
 * 
 * Principio SOLID:
 * - Interface Segregation: Interface específica para login.
 * - Dependency Inversion: El dominio define el contrato.
 */
public interface LoginUserPort {
    
    /**
     * Autentica un usuario en el sistema.
     * @param request Credenciales del usuario
     * @return Respuesta con tokens JWT de autenticación
     * @throws org.springframework.security.core.AuthenticationException si las credenciales son inválidas
     */
    AuthResponse login(LoginRequest request);
}
