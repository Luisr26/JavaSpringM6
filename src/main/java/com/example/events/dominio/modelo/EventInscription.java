package com.example.events.dominio.modelo;

import java.time.LocalDateTime;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Inscripción de un usuario a un evento")
public class EventInscription {

    @Schema(description = "ID único de la inscripción (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "ID del evento", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID eventId;

    @Schema(description = "ID del usuario inscrito", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID usuarioId;

    @Schema(description = "Fecha de inscripción", example = "2024-12-01T10:00:00")
    private LocalDateTime fechaInscripcion;

    public EventInscription(UUID id, UUID eventId, UUID usuarioId, LocalDateTime fechaInscripcion) {
        this.id = id;
        this.eventId = eventId;
        this.usuarioId = usuarioId;
        this.fechaInscripcion = fechaInscripcion;
    }

    public static EventInscription crearNueva(UUID eventId, UUID usuarioId) {
        return new EventInscription(UUID.randomUUID(), eventId, usuarioId, LocalDateTime.now());
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }
}
