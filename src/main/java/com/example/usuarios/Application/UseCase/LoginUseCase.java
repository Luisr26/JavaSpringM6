package com.example.usuarios.Application.UseCase;

import com.example.usuarios.dominio.puerto.in.LoginUserPort;
import com.example.usuarios.dominio.puerto.out.JwtPort;
import com.example.usuarios.infrastructure.AdaptadorPersistencia.AuthUserEntity;
import com.example.usuarios.infrastructure.AdaptadorPersistencia.SpringDataAuthUserRepository;
import com.example.usuarios.infrastructure.security.dto.AuthResponse;
import com.example.usuarios.infrastructure.security.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para login de usuarios.
 * Implementa el puerto de entrada LoginUserPort.
 * 
 * Principio SOLID:
 * - Single Responsibility: Solo maneja la lógica de autenticación/login.
 * - Dependency Inversion: Depende de abstracciones (puertos e interfaces).
 * - Open/Closed: Implementa interfaz, extensible sin modificar.
 */
@Service
public class LoginUseCase implements LoginUserPort {

    private final SpringDataAuthUserRepository authUserRepository;
    private final JwtPort jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginUseCase(
            SpringDataAuthUserRepository authUserRepository,
            JwtPort jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.authUserRepository = authUserRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Autentica un usuario en el sistema.
     * @param request Datos de login
     * @return Respuesta con tokens JWT
     * @throws AuthenticationException si las credenciales son inválidas
     */
    @Override
    public AuthResponse login(LoginRequest request) {
        // Autenticar al usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Buscar usuario
        AuthUserEntity user = authUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Generar tokens
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .nombre(user.getNombre())
                .role(user.getRole().name())
                .build();
    }
}
