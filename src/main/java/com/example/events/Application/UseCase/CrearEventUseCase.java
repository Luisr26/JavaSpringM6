package com.example.events.Application.UseCase;

import com.example.events.dominio.modelo.Event;
import com.example.events.dominio.puerto.out.EventRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CrearEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public CrearEventUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public Event ejecutar(UUID id, String nombre, String descripcion, LocalDateTime fechaInicio,
            LocalDateTime fechaFin, UUID venueId, UUID creadoPorUsuarioId, Integer capacidadMaxima) {
        Event event = new Event(id, nombre, descripcion, fechaInicio, fechaFin, venueId, creadoPorUsuarioId,
                capacidadMaxima);
        return eventRepositoryPort.guardar(event);
    }
}
