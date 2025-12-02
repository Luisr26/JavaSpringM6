package com.example.venues.dominio.modelo;

import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad Venue que representa un lugar/recinto para eventos")
public class Venue {

    @Schema(description = "ID único del venue (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Nombre del venue", example = "Auditorio Nacional")
    private String nombre;

    @Schema(description = "Dirección del venue", example = "Av. Paseo de la Reforma 50")
    private String direccion;

    @Schema(description = "Capacidad máxima de personas", example = "5000")
    private Integer capacidad;

    @Schema(description = "Descripción del venue", example = "Auditorio con capacidad para eventos masivos")
    private String descripcion;

    @Schema(description = "ID del usuario que creó el venue", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID creadoPorUsuarioId;

    public Venue(UUID id, String nombre, String direccion, Integer capacidad, String descripcion,
            UUID creadoPorUsuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.creadoPorUsuarioId = creadoPorUsuarioId;
    }

    public static Venue crearNuevo(String nombre, String direccion, Integer capacidad, String descripcion,
            UUID creadoPorUsuarioId) {
        return new Venue(UUID.randomUUID(), nombre, direccion, capacidad, descripcion, creadoPorUsuarioId);
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public UUID getCreadoPorUsuarioId() {
        return creadoPorUsuarioId;
    }
}
