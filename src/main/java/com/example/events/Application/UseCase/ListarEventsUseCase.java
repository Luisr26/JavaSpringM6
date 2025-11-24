package com.example.events.Application.UseCase;

import com.example.events.dominio.modelo.Event;
import com.example.events.dominio.puerto.out.EventRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarEventsUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public ListarEventsUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public List<Event> ejecutar() {
        return eventRepositoryPort.listarTodos();
    }
}
