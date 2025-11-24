package com.example.venues.Application.UseCase;

import com.example.venues.dominio.modelo.Venue;
import com.example.venues.dominio.puerto.out.VenueRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ActualizarVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public ActualizarVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    public Optional<Venue> ejecutar(UUID id, String nombre, String direccion, Integer capacidad, String descripcion,
            UUID creadoPorUsuarioId) {
        Optional<Venue> existente = venueRepositoryPort.buscarPorId(id);

        if (existente.isPresent()) {
            Venue venue = new Venue(id, nombre, direccion, capacidad, descripcion, creadoPorUsuarioId);
            Venue actualizado = venueRepositoryPort.guardar(venue);
            return Optional.of(actualizado);
        }

        return Optional.empty();
    }
}
