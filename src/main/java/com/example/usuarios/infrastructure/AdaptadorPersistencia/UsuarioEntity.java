package com.example.usuarios.infrastructure.AdaptadorPersistencia;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cargo", nullable = false)
    private String cargo;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    // Constructor vacío requerido por JPA
    public UsuarioEntity() {
    }

    public UsuarioEntity(String id, String name, String cargo, String telefono) {
        this.id = id;
        this.name = name;
        this.cargo = cargo;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
