package com.example.venues.infrastructure.AdaptadorPersistencia;

import com.example.venues.dominio.modelo.Venue;
import com.example.venues.dominio.puerto.out.VenueRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DatabaseVenueRepository implements VenueRepositoryPort {

    private final SpringDataVenueRepository springDataVenueRepository;

    public DatabaseVenueRepository(SpringDataVenueRepository springDataVenueRepository) {
        this.springDataVenueRepository = springDataVenueRepository;
    }

    @Override
    public Venue guardar(Venue venue) {
        VenueEntity entity = toEntity(venue);
        VenueEntity savedEntity = springDataVenueRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Venue> buscarPorId(UUID id) {
        return springDataVenueRepository.findById(id.toString())
                .map(this::toDomain);
    }

    @Override
    public List<Venue> listarTodos() {
        return springDataVenueRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Venue eliminarVenue(UUID id) {
        Optional<VenueEntity> entity = springDataVenueRepository.findById(id.toString());
        if (entity.isPresent()) {
            springDataVenueRepository.deleteById(id.toString());
            return toDomain(entity.get());
        }
        return null;
    }

    // Mappers: Domain <-> Entity
    private VenueEntity toEntity(Venue venue) {
        return new VenueEntity(
                venue.getId().toString(),
                venue.getNombre(),
                venue.getDireccion(),
                venue.getCapacidad(),
                venue.getDescripcion(),
                venue.getCreadoPorUsuarioId().toString());
    }

    private Venue toDomain(VenueEntity entity) {
        return new Venue(
                UUID.fromString(entity.getId()),
                entity.getNombre(),
                entity.getDireccion(),
                entity.getCapacidad(),
                entity.getDescripcion(),
                UUID.fromString(entity.getCreadoPorUsuarioId()));
    }
}
