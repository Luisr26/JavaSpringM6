package com.example.venues.Application.UseCase;

import com.example.venues.dominio.modelo.Venue;
import com.example.venues.dominio.puerto.out.VenueRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CrearVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public CrearVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    public Venue ejecutar(UUID id, String nombre, String direccion, Integer capacidad, String descripcion,
            UUID creadoPorUsuarioId) {
        Venue venue = new Venue(id, nombre, direccion, capacidad, descripcion, creadoPorUsuarioId);
        return venueRepositoryPort.guardar(venue);
    }
}
