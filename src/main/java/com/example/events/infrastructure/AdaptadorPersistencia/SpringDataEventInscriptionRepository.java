package com.example.events.infrastructure.AdaptadorPersistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringDataEventInscriptionRepository extends JpaRepository<EventInscriptionEntity, String> {

    @Query("SELECT ei FROM EventInscriptionEntity ei WHERE ei.eventId = :eventId AND ei.usuarioId = :usuarioId")
    Optional<EventInscriptionEntity> findByEventIdAndUsuarioId(@Param("eventId") String eventId,
            @Param("usuarioId") String usuarioId);

    @Query("SELECT ei FROM EventInscriptionEntity ei WHERE ei.eventId = :eventId")
    List<EventInscriptionEntity> findByEventId(@Param("eventId") String eventId);

    @Query("SELECT ei FROM EventInscriptionEntity ei WHERE ei.usuarioId = :usuarioId")
    List<EventInscriptionEntity> findByUsuarioId(@Param("usuarioId") String usuarioId);
}
