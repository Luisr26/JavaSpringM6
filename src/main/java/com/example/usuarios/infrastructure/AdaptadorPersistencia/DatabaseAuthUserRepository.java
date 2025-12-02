package com.example.usuarios.infrastructure.AdaptadorPersistencia;

import com.example.usuarios.dominio.modelo.AuthUser;
import com.example.usuarios.dominio.puerto.out.AuthUserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Adaptador de persistencia que implementa el puerto de repositorio de usuarios de auth.
 * Principio SOLID:
 * - Dependency Inversion: Implementa la interfaz definida en el dominio.
 * - Single Responsibility: Solo se encarga de la conversión y delegación a JPA.
 */
@Component
public class DatabaseAuthUserRepository implements AuthUserRepositoryPort {

    private final SpringDataAuthUserRepository jpaRepository;

    public DatabaseAuthUserRepository(SpringDataAuthUserRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public AuthUser save(AuthUser authUser) {
        AuthUserEntity entity = toEntity(authUser);
        AuthUserEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<AuthUser> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public Optional<AuthUser> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    /**
     * Convierte una entidad JPA a modelo de dominio.
     */
    private AuthUser toDomain(AuthUserEntity entity) {
        return new AuthUser(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getNombre(),
                entity.getRole(),
                entity.isActivo()
        );
    }

    /**
     * Convierte un modelo de dominio a entidad JPA.
     */
    private AuthUserEntity toEntity(AuthUser authUser) {
        return new AuthUserEntity(
                authUser.getId(),
                authUser.getEmail(),
                authUser.getPassword(),
                authUser.getNombre(),
                authUser.getRole(),
                authUser.isActivo()
        );
    }
}
