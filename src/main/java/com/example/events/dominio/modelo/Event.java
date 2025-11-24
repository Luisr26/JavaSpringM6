package com.example.events.dominio.modelo;

import java.time.LocalDateTime;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad Event que representa un evento")
public class Event {

    @Schema(description = "ID único del evento (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Nombre del evento", example = "Concierto de Rock")
    private String nombre;

    @Schema(description = "Descripción del evento", example = "Gran concierto de rock con bandas internacionales")
    private String descripcion;

    @Schema(description = "Fecha y hora de inicio", example = "2024-12-25T20:00:00")
    private LocalDateTime fechaInicio;

    @Schema(description = "Fecha y hora de fin", example = "2024-12-25T23:00:00")
    private LocalDateTime fechaFin;

    @Schema(description = "ID del venue donde se realiza (opcional)", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID venueId;

    @Schema(description = "ID del usuario que creó el evento", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID creadoPorUsuarioId;

    @Schema(description = "Capacidad máxima de inscripciones", example = "500")
    private Integer capacidadMaxima;

    public Event(UUID id, String nombre, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            UUID venueId, UUID creadoPorUsuarioId, Integer capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.venueId = venueId;
        this.creadoPorUsuarioId = creadoPorUsuarioId;
        this.capacidadMaxima = capacidadMaxima;
    }

    public static Event crearNuevo(String nombre, String descripcion, LocalDateTime fechaInicio,
            LocalDateTime fechaFin, UUID venueId, UUID creadoPorUsuarioId, Integer capacidadMaxima) {
        return new Event(UUID.randomUUID(), nombre, descripcion, fechaInicio, fechaFin, venueId, creadoPorUsuarioId,
                capacidadMaxima);
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

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public UUID getVenueId() {
        return venueId;
    }

    public UUID getCreadoPorUsuarioId() {
        return creadoPorUsuarioId;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }
}
