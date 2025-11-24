package com.example.events.Application.UseCase;

import com.example.events.dominio.puerto.out.EventRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarInscritosUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public ListarInscritosUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public List<UUID> ejecutar(UUID eventId) {
        return eventRepositoryPort.listarInscritosPorEvento(eventId);
    }
}
