package com.example.venues.Application.UseCase;

import com.example.venues.dominio.puerto.out.VenueRepositoryPort;
import com.example.venues.dominio.modelo.Venue;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ObtenerVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public ObtenerVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    public Venue ejecutar(UUID id) {
        Optional<Venue> venue = venueRepositoryPort.buscarPorId(id);
        return venue.orElseThrow(() -> new RuntimeException("Venue no encontrado con ID: " + id));
    }
}
