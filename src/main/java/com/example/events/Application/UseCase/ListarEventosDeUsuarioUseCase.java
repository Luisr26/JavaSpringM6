package com.example.events.Application.UseCase;

import com.example.events.dominio.modelo.Event;
import com.example.events.dominio.puerto.out.EventRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarEventosDeUsuarioUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public ListarEventosDeUsuarioUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public List<Event> ejecutar(UUID usuarioId) {
        return eventRepositoryPort.listarEventosPorUsuario(usuarioId);
    }
}
