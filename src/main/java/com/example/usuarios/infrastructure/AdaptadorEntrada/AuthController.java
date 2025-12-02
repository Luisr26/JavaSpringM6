package com.example.usuarios.infrastructure.AdaptadorEntrada;

import com.example.usuarios.dominio.puerto.in.LoginUserPort;
import com.example.usuarios.dominio.puerto.in.RefreshTokenPort;
import com.example.usuarios.dominio.puerto.in.RegisterUserPort;
import com.example.usuarios.infrastructure.security.dto.AuthResponse;
import com.example.usuarios.infrastructure.security.dto.LoginRequest;
import com.example.usuarios.infrastructure.security.dto.RefreshTokenRequest;
import com.example.usuarios.infrastructure.security.dto.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para autenticación.
 * Principio SOLID:
 * - Single Responsibility: Solo maneja endpoints de autenticación.
 * - Dependency Inversion: Depende de puertos de entrada (interfaces), no de implementaciones.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "API para autenticación de usuarios (registro, login, refresh token)")
public class AuthController {

    private final RegisterUserPort registerUserPort;
    private final LoginUserPort loginUserPort;
    private final RefreshTokenPort refreshTokenPort;

    public AuthController(
            RegisterUserPort registerUserPort,
            LoginUserPort loginUserPort,
            RefreshTokenPort refreshTokenPort
    ) {
        this.registerUserPort = registerUserPort;
        this.loginUserPort = loginUserPort;
        this.refreshTokenPort = refreshTokenPort;
    }

    @Operation(
            summary = "Registrar nuevo usuario",
            description = "Crea una nueva cuenta de usuario y retorna tokens JWT de acceso"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario registrado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o email ya registrado"
            )
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(registerUserPort.register(request));
    }

    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica al usuario y retorna tokens JWT de acceso"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login exitoso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciales inválidas"
            )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginUserPort.login(request));
    }

    @Operation(
            summary = "Refrescar token",
            description = "Genera nuevos tokens JWT usando el refresh token"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tokens refrescados exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Refresh token inválido o expirado"
            )
    })
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(refreshTokenPort.refresh(request.getRefreshToken()));
    }
}
