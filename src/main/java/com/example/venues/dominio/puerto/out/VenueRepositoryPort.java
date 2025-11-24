package com.example.venues.dominio.puerto.out;

import com.example.venues.dominio.modelo.Venue;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para persistencia de Venue
 * Define las operaciones que el dominio necesita de la infraestructura
 */
public interface VenueRepositoryPort {
    Venue guardar(Venue venue);

    Optional<Venue> buscarPorId(UUID id);

    List<Venue> listarTodos();

    Venue eliminarVenue(UUID id);
}
