package com.example.usuarios.Application.UseCase;

import com.example.usuarios.dominio.modelo.Role;
import com.example.usuarios.dominio.puerto.in.RegisterUserPort;
import com.example.usuarios.dominio.puerto.out.AuthUserRepositoryPort;
import com.example.usuarios.dominio.puerto.out.JwtPort;
import com.example.usuarios.infrastructure.AdaptadorPersistencia.AuthUserEntity;
import com.example.usuarios.infrastructure.AdaptadorPersistencia.SpringDataAuthUserRepository;
import com.example.usuarios.infrastructure.security.dto.AuthResponse;
import com.example.usuarios.infrastructure.security.dto.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para registro de usuarios.
 * Implementa el puerto de entrada RegisterUserPort.
 * 
 * Principio SOLID:
 * - Single Responsibility: Solo maneja la lógica de registro.
 * - Dependency Inversion: Depende de abstracciones (puertos e interfaces).
 * - Open/Closed: Implementa interfaz, extensible sin modificar.
 */
@Service
public class RegisterUseCase implements RegisterUserPort {

    private final AuthUserRepositoryPort authUserRepository;
    private final SpringDataAuthUserRepository jpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtPort jwtService;

    public RegisterUseCase(
            AuthUserRepositoryPort authUserRepository,
            SpringDataAuthUserRepository jpaRepository,
            PasswordEncoder passwordEncoder,
            JwtPort jwtService
    ) {
        this.authUserRepository = authUserRepository;
        this.jpaRepository = jpaRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param request Datos de registro
     * @return Respuesta con tokens JWT
     * @throws IllegalArgumentException si el email ya está registrado
     */
    @Override
    public AuthResponse register(RegisterRequest request) {
        // Verificar si el email ya existe
        if (authUserRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado: " + request.getEmail());
        }

        // Crear la entidad de usuario
        AuthUserEntity userEntity = new AuthUserEntity(
                null, // UUID será generado automáticamente
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getNombre(),
                Role.USER,
                true
        );

        // Guardar usuario
        AuthUserEntity savedUser = jpaRepository.save(userEntity);

        // Generar tokens
        String accessToken = jwtService.generateToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(savedUser.getEmail())
                .nombre(savedUser.getNombre())
                .role(savedUser.getRole().name())
                .build();
    }
}
