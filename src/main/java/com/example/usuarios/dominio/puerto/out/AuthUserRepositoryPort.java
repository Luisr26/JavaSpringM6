package com.example.usuarios.dominio.puerto.out;

import com.example.usuarios.dominio.modelo.AuthUser;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para persistencia de usuarios de autenticación.
 * Principio SOLID:
 * - Interface Segregation: Solo operaciones de persistencia de AuthUser.
 * - Dependency Inversion: El dominio define la interfaz, la infraestructura la implementa.
 */
public interface AuthUserRepositoryPort {
    
    /**
     * Guarda un usuario de autenticación.
     * @param authUser Usuario a guardar
     * @return Usuario guardado
     */
    AuthUser save(AuthUser authUser);
    
    /**
     * Busca un usuario por su email.
     * @param email Email del usuario
     * @return Optional con el usuario si existe
     */
    Optional<AuthUser> findByEmail(String email);
    
    /**
     * Busca un usuario por su ID.
     * @param id ID del usuario
     * @return Optional con el usuario si existe
     */
    Optional<AuthUser> findById(UUID id);
    
    /**
     * Verifica si existe un usuario con el email especificado.
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByEmail(String email);
}
