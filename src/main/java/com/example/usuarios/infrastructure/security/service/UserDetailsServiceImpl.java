package com.example.usuarios.infrastructure.security.service;

import com.example.usuarios.infrastructure.AdaptadorPersistencia.SpringDataAuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementación de UserDetailsService para Spring Security.
 * Principio SOLID:
 * - Single Responsibility: Solo se encarga de cargar usuarios para autenticación.
 * - Dependency Inversion: Depende de la abstracción del repositorio.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SpringDataAuthUserRepository authUserRepository;

    public UserDetailsServiceImpl(SpringDataAuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado con email: " + username
                ));
    }
}
