package com.example.usuarios.dominio.puerto.out;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Puerto de salida para operaciones JWT.
 * Principio SOLID:
 * - Interface Segregation: Solo define operaciones relacionadas con JWT.
 * - Dependency Inversion: El dominio define la interfaz, la infraestructura la implementa.
 */
public interface JwtPort {
    
    /**
     * Genera un token JWT para el usuario especificado.
     * @param userDetails Detalles del usuario autenticado
     * @return Token JWT generado
     */
    String generateToken(UserDetails userDetails);
    
    /**
     * Genera un token de refresco para el usuario especificado.
     * @param userDetails Detalles del usuario autenticado
     * @return Token de refresco generado
     */
    String generateRefreshToken(UserDetails userDetails);
    
    /**
     * Extrae el nombre de usuario (email) del token JWT.
     * @param token Token JWT
     * @return Email/username extraído del token
     */
    String extractUsername(String token);
    
    /**
     * Valida si un token JWT es válido para el usuario especificado.
     * @param token Token JWT a validar
     * @param userDetails Detalles del usuario
     * @return true si el token es válido, false en caso contrario
     */
    boolean isTokenValid(String token, UserDetails userDetails);
    
    /**
     * Verifica si el token ha expirado.
     * @param token Token JWT
     * @return true si el token ha expirado, false en caso contrario
     */
    boolean isTokenExpired(String token);
}
