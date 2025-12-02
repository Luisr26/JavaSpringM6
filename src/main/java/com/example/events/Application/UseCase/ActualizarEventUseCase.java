package com.example.events.Application.UseCase;

import com.example.events.dominio.modelo.Event;
import com.example.events.dominio.puerto.out.EventRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActualizarEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public ActualizarEventUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public Optional<Event> ejecutar(UUID id, String nombre, String descripcion, LocalDateTime fechaInicio,
            LocalDateTime fechaFin, UUID venueId, UUID creadoPorUsuarioId, Integer capacidadMaxima) {
        Optional<Event> existente = eventRepositoryPort.buscarPorId(id);

        if (existente.isPresent()) {
            Event event = new Event(id, nombre, descripcion, fechaInicio, fechaFin, venueId, creadoPorUsuarioId,
                    capacidadMaxima);
            Event actualizado = eventRepositoryPort.guardar(event);
            return Optional.of(actualizado);
        }

        return Optional.empty();
    }
}
