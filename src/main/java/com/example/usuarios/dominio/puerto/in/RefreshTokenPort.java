package com.example.usuarios.dominio.puerto.in;

import com.example.usuarios.infrastructure.security.dto.AuthResponse;

/**
 * Puerto de entrada para el caso de uso de refresh de tokens.
 * Define el contrato que debe implementar cualquier caso de uso de refresh token.
 * 
 * Principio SOLID:
 * - Interface Segregation: Interface específica para refresh token.
 * - Dependency Inversion: El dominio define el contrato.
 */
public interface RefreshTokenPort {
    
    /**
     * Genera nuevos tokens JWT usando un refresh token válido.
     * @param refreshToken Token de refresco actual
     * @return Respuesta con nuevos tokens JWT
     * @throws IllegalArgumentException si el refresh token es inválido o expirado
     */
    AuthResponse refresh(String refreshToken);
}
