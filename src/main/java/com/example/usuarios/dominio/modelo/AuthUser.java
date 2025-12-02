package com.example.usuarios.dominio.modelo;

import java.util.UUID;

/**
 * Modelo de dominio para usuarios de autenticación.
 * Principio SOLID: Single Responsibility - Solo representa los datos de autenticación.
 * Separado del modelo Usuario para mantener separación de responsabilidades.
 */
public class AuthUser {
    
    private UUID id;
    private String email;
    private String password;
    private String nombre;
    private Role role;
    private boolean activo;

    public AuthUser() {
    }

    public AuthUser(UUID id, String email, String password, String nombre, Role role, boolean activo) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.role = role;
        this.activo = activo;
    }

    /**
     * Factory method para crear un nuevo usuario con valores por defecto.
     */
    public static AuthUser crearNuevo(String email, String password, String nombre) {
        return new AuthUser(UUID.randomUUID(), email, password, nombre, Role.USER, true);
    }

    /**
     * Factory method para crear un nuevo admin.
     */
    public static AuthUser crearAdmin(String email, String password, String nombre) {
        return new AuthUser(UUID.randomUUID(), email, password, nombre, Role.ADMIN, true);
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
