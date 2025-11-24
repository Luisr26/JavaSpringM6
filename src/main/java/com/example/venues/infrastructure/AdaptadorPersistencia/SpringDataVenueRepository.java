package com.example.venues.infrastructure.AdaptadorPersistencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataVenueRepository extends JpaRepository<VenueEntity, String> {
}
