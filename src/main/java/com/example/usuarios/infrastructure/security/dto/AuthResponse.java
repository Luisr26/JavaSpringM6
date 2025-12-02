package com.example.usuarios.infrastructure.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para respuesta de autenticación.
 * Principio SOLID: Single Responsibility - Solo transporta datos de respuesta de auth.
 */
@Schema(description = "Respuesta de autenticación con tokens JWT")
public class AuthResponse {
    
    @Schema(description = "Token de acceso JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;
    
    @Schema(description = "Token de refresco JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String refreshToken;
    
    @Schema(description = "Tipo de token", example = "Bearer")
    private String tokenType;
    
    @Schema(description = "Email del usuario autenticado", example = "usuario@example.com")
    private String email;
    
    @Schema(description = "Nombre del usuario autenticado", example = "Juan Pérez")
    private String nombre;
    
    @Schema(description = "Rol del usuario", example = "USER")
    private String role;

    public AuthResponse() {
        this.tokenType = "Bearer";
    }

    private AuthResponse(Builder builder) {
        this.accessToken = builder.accessToken;
        this.refreshToken = builder.refreshToken;
        this.tokenType = "Bearer";
        this.email = builder.email;
        this.nombre = builder.nombre;
        this.role = builder.role;
    }

    // Builder Pattern para construcción fluida
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String accessToken;
        private String refreshToken;
        private String email;
        private String nombre;
        private String role;

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(this);
        }
    }

    // Getters y Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
