package com.example.usuarios.infrastructure.AdaptadorPersistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio Spring Data JPA para AuthUserEntity.
 * Principio SOLID: Interface Segregation - Solo define operaciones de consulta necesarias.
 */
@Repository
public interface SpringDataAuthUserRepository extends JpaRepository<AuthUserEntity, UUID> {
    
    /**
     * Busca un usuario por email.
     * @param email Email del usuario
     * @return Optional con la entidad si existe
     */
    Optional<AuthUserEntity> findByEmail(String email);
    
    /**
     * Verifica si existe un usuario con el email especificado.
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByEmail(String email);
}
