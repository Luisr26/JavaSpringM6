package com.example.events.Application.UseCase;

import com.example.events.dominio.puerto.out.EventRepositoryPort;
import com.example.events.dominio.modelo.Event;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ObtenerEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public ObtenerEventUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public Event ejecutar(UUID id) {
        Optional<Event> event = eventRepositoryPort.buscarPorId(id);
        return event.orElseThrow(() -> new RuntimeException("Event no encontrado con ID: " + id));
    }
}
