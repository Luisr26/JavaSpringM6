package com.example.usuarios.infrastructure.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para solicitud de registro.
 * Principio SOLID: Single Responsibility - Solo transporta datos de registro.
 */
@Schema(description = "Datos de solicitud de registro de usuario")
public class RegisterRequest {
    
    @Schema(description = "Email del usuario", example = "usuario@example.com", required = true)
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;
    
    @Schema(description = "Contraseña del usuario (mínimo 6 caracteres)", example = "password123", required = true)
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez", required = true)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, String nombre) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
