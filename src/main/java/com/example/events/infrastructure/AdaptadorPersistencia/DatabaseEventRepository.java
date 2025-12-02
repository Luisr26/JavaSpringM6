package com.example.events.infrastructure.AdaptadorPersistencia;

import com.example.events.dominio.modelo.Event;
import com.example.events.dominio.modelo.EventInscription;
import com.example.events.dominio.puerto.out.EventRepositoryPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DatabaseEventRepository implements EventRepositoryPort {

    private final SpringDataEventRepository springDataEventRepository;
    private final SpringDataEventInscriptionRepository springDataEventInscriptionRepository;

    public DatabaseEventRepository(SpringDataEventRepository springDataEventRepository,
            SpringDataEventInscriptionRepository springDataEventInscriptionRepository) {
        this.springDataEventRepository = springDataEventRepository;
        this.springDataEventInscriptionRepository = springDataEventInscriptionRepository;
    }

    @Override
    public Event guardar(Event event) {
        EventEntity entity = toEntity(event);
        EventEntity savedEntity = springDataEventRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Event> buscarPorId(UUID id) {
        return springDataEventRepository.findById(id.toString())
                .map(this::toDomain);
    }

    @Override
    public List<Event> listarTodos() {
        return springDataEventRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Event eliminarEvent(UUID id) {
        Optional<EventEntity> entity = springDataEventRepository.findById(id.toString());
        if (entity.isPresent()) {
            springDataEventRepository.deleteById(id.toString());
            return toDomain(entity.get());
        }
        return null;
    }

    @Override
    public EventInscription inscribirUsuario(UUID eventId, UUID usuarioId) {
        EventInscriptionEntity entity = new EventInscriptionEntity(
                UUID.randomUUID().toString(),
                eventId.toString(),
                usuarioId.toString(),
                LocalDateTime.now());
        EventInscriptionEntity saved = springDataEventInscriptionRepository.save(entity);
        return toInscriptionDomain(saved);
    }

    @Override
    public boolean desincribirUsuario(UUID eventId, UUID usuarioId) {
        Optional<EventInscriptionEntity> inscription = springDataEventInscriptionRepository
                .findByEventIdAndUsuarioId(eventId.toString(), usuarioId.toString());

        if (inscription.isPresent()) {
            springDataEventInscriptionRepository.deleteById(inscription.get().getId());
            return true;
        }
        return false;
    }

    @Override
    public List<UUID> listarInscritosPorEvento(UUID eventId) {
        return springDataEventInscriptionRepository.findByEventId(eventId.toString())
                .stream()
                .map(entity -> UUID.fromString(entity.getUsuarioId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> listarEventosPorUsuario(UUID usuarioId) {
        List<String> eventIds = springDataEventInscriptionRepository.findByUsuarioId(usuarioId.toString())
                .stream()
                .map(EventInscriptionEntity::getEventId)
                .collect(Collectors.toList());

        if (eventIds.isEmpty()) {
            return Collections.emptyList();
        }

        return springDataEventRepository.findAllById(eventIds)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean estaInscrito(UUID eventId, UUID usuarioId) {
        return springDataEventInscriptionRepository
                .findByEventIdAndUsuarioId(eventId.toString(), usuarioId.toString())
                .isPresent();
    }

    // Mappers: Domain <-> Entity
    private EventEntity toEntity(Event event) {
        return new EventEntity(
                event.getId().toString(),
                event.getNombre(),
                event.getDescripcion(),
                event.getFechaInicio(),
                event.getFechaFin(),
                event.getVenueId() != null ? event.getVenueId().toString() : null,
                event.getCreadoPorUsuarioId().toString(),
                event.getCapacidadMaxima());
    }

    private Event toDomain(EventEntity entity) {
        return new Event(
                UUID.fromString(entity.getId()),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getFechaInicio(),
                entity.getFechaFin(),
                entity.getVenueId() != null ? UUID.fromString(entity.getVenueId()) : null,
                UUID.fromString(entity.getCreadoPorUsuarioId()),
                entity.getCapacidadMaxima());
    }

    private EventInscription toInscriptionDomain(EventInscriptionEntity entity) {
        return new EventInscription(
                UUID.fromString(entity.getId()),
                UUID.fromString(entity.getEventId()),
                UUID.fromString(entity.getUsuarioId()),
                entity.getFechaInscripcion());
    }
}
