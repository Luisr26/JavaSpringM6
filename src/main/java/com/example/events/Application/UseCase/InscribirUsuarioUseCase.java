package com.example.events.Application.UseCase;

import com.example.events.dominio.modelo.EventInscription;
import com.example.events.dominio.puerto.out.EventRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InscribirUsuarioUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public InscribirUsuarioUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public EventInscription ejecutar(UUID eventId, UUID usuarioId) {
        // Verificar si ya está inscrito
        if (eventRepositoryPort.estaInscrito(eventId, usuarioId)) {
            throw new RuntimeException("El usuario ya está inscrito en este evento");
        }

        return eventRepositoryPort.inscribirUsuario(eventId, usuarioId);
    }
}
