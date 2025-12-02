package com.example.usuarios.Application.UseCase;

import com.example.usuarios.dominio.puerto.in.RefreshTokenPort;
import com.example.usuarios.dominio.puerto.out.JwtPort;
import com.example.usuarios.infrastructure.AdaptadorPersistencia.AuthUserEntity;
import com.example.usuarios.infrastructure.AdaptadorPersistencia.SpringDataAuthUserRepository;
import com.example.usuarios.infrastructure.security.dto.AuthResponse;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para refrescar tokens JWT.
 * Implementa el puerto de entrada RefreshTokenPort.
 * 
 * Principio SOLID:
 * - Single Responsibility: Solo maneja la lógica de refresh de tokens.
 * - Dependency Inversion: Depende de abstracciones (puertos e interfaces).
 * - Open/Closed: Implementa interfaz, extensible sin modificar.
 */
@Service
public class RefreshTokenUseCase implements RefreshTokenPort {

    private final SpringDataAuthUserRepository authUserRepository;
    private final JwtPort jwtService;

    public RefreshTokenUseCase(
            SpringDataAuthUserRepository authUserRepository,
            JwtPort jwtService
    ) {
        this.authUserRepository = authUserRepository;
        this.jwtService = jwtService;
    }

    /**
     * Genera nuevos tokens JWT usando un refresh token válido.
     * @param refreshToken Token de refresco actual
     * @return Respuesta con nuevos tokens JWT
     * @throws IllegalArgumentException si el token es inválido o expirado
     */
    @Override
    public AuthResponse refresh(String refreshToken) {
        // Extraer el email del token de refresh
        String userEmail = jwtService.extractUsername(refreshToken);
        
        if (userEmail == null) {
            throw new IllegalArgumentException("Token de refresco inválido");
        }

        // Buscar usuario
        AuthUserEntity user = authUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Validar el token de refresh
        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new IllegalArgumentException("Token de refresco expirado o inválido");
        }

        // Generar nuevos tokens
        String newAccessToken = jwtService.generateToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .email(user.getEmail())
                .nombre(user.getNombre())
                .role(user.getRole().name())
                .build();
    }
}
