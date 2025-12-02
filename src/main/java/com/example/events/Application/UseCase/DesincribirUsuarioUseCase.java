package com.example.events.Application.UseCase;

import com.example.events.dominio.puerto.out.EventRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DesincribirUsuarioUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public DesincribirUsuarioUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public boolean ejecutar(UUID eventId, UUID usuarioId) {
        boolean desinscrito = eventRepositoryPort.desincribirUsuario(eventId, usuarioId);
        if (!desinscrito) {
            throw new RuntimeException("No se encontró la inscripción para eliminar");
        }
        return true;
    }
}
