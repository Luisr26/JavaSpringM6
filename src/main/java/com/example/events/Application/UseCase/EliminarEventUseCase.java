package com.example.events.Application.UseCase;

import com.example.events.dominio.modelo.Event;
import com.example.events.dominio.puerto.out.EventRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EliminarEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public EliminarEventUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public boolean ejecutar(UUID id) {
        Event eliminado = eventRepositoryPort.eliminarEvent(id);
        return eliminado != null;
    }
}
