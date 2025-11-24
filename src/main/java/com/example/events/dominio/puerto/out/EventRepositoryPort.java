package com.example.events.dominio.puerto.out;

import com.example.events.dominio.modelo.Event;
import com.example.events.dominio.modelo.EventInscription;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para persistencia de Event
 * Define las operaciones que el dominio necesita de la infraestructura
 */
public interface EventRepositoryPort {
    // Operaciones CRUD de Event
    Event guardar(Event event);

    Optional<Event> buscarPorId(UUID id);

    List<Event> listarTodos();

    Event eliminarEvent(UUID id);

    // Operaciones de inscripción
    EventInscription inscribirUsuario(UUID eventId, UUID usuarioId);

    boolean desincribirUsuario(UUID eventId, UUID usuarioId);

    List<UUID> listarInscritosPorEvento(UUID eventId);

    List<Event> listarEventosPorUsuario(UUID usuarioId);

    boolean estaInscrito(UUID eventId, UUID usuarioId);
}
