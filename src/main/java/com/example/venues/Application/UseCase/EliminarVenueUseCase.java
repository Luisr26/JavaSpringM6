package com.example.venues.Application.UseCase;

import com.example.venues.dominio.modelo.Venue;
import com.example.venues.dominio.puerto.out.VenueRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EliminarVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public EliminarVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    public boolean ejecutar(UUID id) {
        Venue eliminado = venueRepositoryPort.eliminarVenue(id);
        return eliminado != null;
    }
}
