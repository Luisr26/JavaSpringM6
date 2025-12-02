package com.example.events.infrastructure.AdaptadorEntrada;

import com.example.events.Application.UseCase.*;
import com.example.events.dominio.modelo.Event;
import com.example.events.dominio.modelo.EventInscription;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "API para gestión de eventos e inscripciones")
public class EventController {

    private final CrearEventUseCase crearEventUseCase;
    private final ObtenerEventUseCase obtenerEventUseCase;
    private final ListarEventsUseCase listarEventsUseCase;
    private final ActualizarEventUseCase actualizarEventUseCase;
    private final EliminarEventUseCase eliminarEventUseCase;
    private final InscribirUsuarioUseCase inscribirUsuarioUseCase;
    private final DesincribirUsuarioUseCase desincribirUsuarioUseCase;
    private final ListarInscritosUseCase listarInscritosUseCase;
    private final ListarEventosDeUsuarioUseCase listarEventosDeUsuarioUseCase;

    public EventController(
            CrearEventUseCase crearEventUseCase,
            ObtenerEventUseCase obtenerEventUseCase,
            ListarEventsUseCase listarEventsUseCase,
            ActualizarEventUseCase actualizarEventUseCase,
            EliminarEventUseCase eliminarEventUseCase,
            InscribirUsuarioUseCase inscribirUsuarioUseCase,
            DesincribirUsuarioUseCase desincribirUsuarioUseCase,
            ListarInscritosUseCase listarInscritosUseCase,
            ListarEventosDeUsuarioUseCase listarEventosDeUsuarioUseCase) {
        this.crearEventUseCase = crearEventUseCase;
        this.obtenerEventUseCase = obtenerEventUseCase;
        this.listarEventsUseCase = listarEventsUseCase;
        this.actualizarEventUseCase = actualizarEventUseCase;
        this.eliminarEventUseCase = eliminarEventUseCase;
        this.inscribirUsuarioUseCase = inscribirUsuarioUseCase;
        this.desincribirUsuarioUseCase = desincribirUsuarioUseCase;
        this.listarInscritosUseCase = listarInscritosUseCase;
        this.listarEventosDeUsuarioUseCase = listarEventosDeUsuarioUseCase;
    }

    @Operation(summary = "Crear un nuevo evento", description = "Crea un nuevo evento. Si no se proporciona un ID, se genera uno automáticamente. Incluye información del creador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")
    })
    @PostMapping
    public Event crear(@RequestBody EventRequest request) {
        UUID id = request.getId() != null ? request.getId() : UUID.randomUUID();
        return crearEventUseCase.ejecutar(
                id,
                request.getNombre(),
                request.getDescripcion(),
                request.getFechaInicio(),
                request.getFechaFin(),
                request.getVenueId(),
                request.getCreadoPorUsuarioId(),
                request.getCapacidadMaxima());
    }

    @Schema(description = "Datos para crear o actualizar un evento")
    static class EventRequest {
        @Schema(description = "ID único del evento (UUID). Si no se proporciona, se genera automáticamente", example = "550e8400-e29b-41d4-a716-446655440000")
        private UUID id;

        @Schema(description = "Nombre del evento", example = "Concierto de Rock", required = true)
        private String nombre;

        @Schema(description = "Descripción del evento", example = "Gran concierto de rock con bandas internacionales")
        private String descripcion;

        @Schema(description = "Fecha y hora de inicio", example = "2024-12-25T20:00:00", required = true)
        private LocalDateTime fechaInicio;

        @Schema(description = "Fecha y hora de fin", example = "2024-12-25T23:00:00", required = true)
        private LocalDateTime fechaFin;

        @Schema(description = "ID del venue donde se realiza (opcional)", example = "550e8400-e29b-41d4-a716-446655440000")
        private UUID venueId;

        @Schema(description = "ID del usuario que crea el evento", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
        private UUID creadoPorUsuarioId;

        @Schema(description = "Capacidad máxima de inscripciones", example = "500")
        private Integer capacidadMaxima;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public LocalDateTime getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(LocalDateTime fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public LocalDateTime getFechaFin() {
            return fechaFin;
        }

        public void setFechaFin(LocalDateTime fechaFin) {
            this.fechaFin = fechaFin;
        }

        public UUID getVenueId() {
            return venueId;
        }

        public void setVenueId(UUID venueId) {
            this.venueId = venueId;
        }

        public UUID getCreadoPorUsuarioId() {
            return creadoPorUsuarioId;
        }

        public void setCreadoPorUsuarioId(UUID creadoPorUsuarioId) {
            this.creadoPorUsuarioId = creadoPorUsuarioId;
        }

        public Integer getCapacidadMaxima() {
            return capacidadMaxima;
        }

        public void setCapacidadMaxima(Integer capacidadMaxima) {
            this.capacidadMaxima = capacidadMaxima;
        }
    }

    @Operation(summary = "Obtener evento por ID", description = "Busca y retorna un evento específico usando su identificador único (UUID), incluyendo el ID del creador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class))),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @GetMapping("/{id}")
    public Event obtener(
            @Parameter(description = "ID único del evento (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID id) {
        return obtenerEventUseCase.ejecutar(id);
    }

    @Operation(summary = "Listar todos los eventos", description = "Retorna una lista con todos los eventos registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de eventos obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)))
    })
    @GetMapping
    public List<Event> listarTodos() {
        return listarEventsUseCase.ejecutar();
    }

    @Operation(summary = "Actualizar un evento existente", description = "Actualiza los datos de un evento específico usando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class))),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @PutMapping("/{id}")
    public Event actualizar(
            @Parameter(description = "ID único del evento (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID id,
            @RequestBody EventRequest request) {
        return actualizarEventUseCase.ejecutar(
                id,
                request.getNombre(),
                request.getDescripcion(),
                request.getFechaInicio(),
                request.getFechaFin(),
                request.getVenueId(),
                request.getCreadoPorUsuarioId(),
                request.getCapacidadMaxima())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
    }

    @Operation(summary = "Eliminar un evento", description = "Elimina un evento del sistema usando su ID. También elimina todas las inscripciones asociadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID único del evento (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID id) {
        boolean eliminado = eliminarEventUseCase.ejecutar(id);
        if (!eliminado) {
            throw new RuntimeException("Evento no encontrado con ID: " + id);
        }
    }

    // === ENDPOINTS DE INSCRIPCIONES ===

    @Operation(summary = "Inscribir usuario en un evento", description = "Inscribe un usuario a un evento específico. No permite inscripciones duplicadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario inscrito exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventInscription.class))),
            @ApiResponse(responseCode = "400", description = "El usuario ya está inscrito en este evento")
    })
    @PostMapping("/{eventId}/inscribir/{usuarioId}")
    public EventInscription inscribirUsuario(
            @Parameter(description = "ID del evento", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID eventId,
            @Parameter(description = "ID del usuario", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID usuarioId) {
        return inscribirUsuarioUseCase.ejecutar(eventId, usuarioId);
    }

    @Operation(summary = "Desinscribir usuario de un evento", description = "Cancela la inscripción de un usuario a un evento específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario desinscrito exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inscripción no encontrada")
    })
    @DeleteMapping("/{eventId}/desinscribir/{usuarioId}")
    public void desincribirUsuario(
            @Parameter(description = "ID del evento", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID eventId,
            @Parameter(description = "ID del usuario", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID usuarioId) {
        desincribirUsuarioUseCase.ejecutar(eventId, usuarioId);
    }

    @Operation(summary = "Listar usuarios inscritos en un evento", description = "Retorna una lista con los IDs de todos los usuarios inscritos en un evento específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de inscritos obtenida exitosamente")
    })
    @GetMapping("/{eventId}/inscritos")
    public List<UUID> listarInscritos(
            @Parameter(description = "ID del evento", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID eventId) {
        return listarInscritosUseCase.ejecutar(eventId);
    }

    @Operation(summary = "Listar eventos de un usuario", description = "Retorna una lista con todos los eventos en los que está inscrito un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de eventos obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)))
    })
    @GetMapping("/usuario/{usuarioId}")
    public List<Event> listarEventosDeUsuario(
            @Parameter(description = "ID del usuario", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID usuarioId) {
        return listarEventosDeUsuarioUseCase.ejecutar(usuarioId);
    }
}
