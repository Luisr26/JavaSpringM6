package com.example.events.infrastructure.AdaptadorPersistencia;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_inscriptions", uniqueConstraints = @UniqueConstraint(columnNames = { "event_id", "usuario_id" }))
public class EventInscriptionEntity {

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "event_id", nullable = false, columnDefinition = "VARCHAR(36)")
    private String eventId;

    @Column(name = "usuario_id", nullable = false, columnDefinition = "VARCHAR(36)")
    private String usuarioId;

    @Column(name = "fecha_inscripcion")
    private LocalDateTime fechaInscripcion;

    // Constructor vacío requerido por JPA
    public EventInscriptionEntity() {
    }

    public EventInscriptionEntity(String id, String eventId, String usuarioId, LocalDateTime fechaInscripcion) {
        this.id = id;
        this.eventId = eventId;
        this.usuarioId = usuarioId;
        this.fechaInscripcion = fechaInscripcion;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
