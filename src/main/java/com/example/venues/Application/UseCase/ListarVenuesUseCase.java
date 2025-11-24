package com.example.venues.Application.UseCase;

import com.example.venues.dominio.modelo.Venue;
import com.example.venues.dominio.puerto.out.VenueRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarVenuesUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public ListarVenuesUseCase(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    public List<Venue> ejecutar() {
        return venueRepositoryPort.listarTodos();
    }
}
