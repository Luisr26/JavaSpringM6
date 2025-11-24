package com.example.events.infrastructure.AdaptadorPersistencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEventRepository extends JpaRepository<EventEntity, String> {
}
