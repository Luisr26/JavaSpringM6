package com.example.venues.infrastructure.AdaptadorEntrada;

import com.example.venues.Application.UseCase.*;
import com.example.venues.dominio.modelo.Venue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/venues")
@Tag(name = "Venues", description = "API para gestión de lugares/recintos para eventos")
public class VenueController {

    private final CrearVenueUseCase crearVenueUseCase;
    private final ObtenerVenueUseCase obtenerVenueUseCase;
    private final ListarVenuesUseCase listarVenuesUseCase;
    private final ActualizarVenueUseCase actualizarVenueUseCase;
    private final EliminarVenueUseCase eliminarVenueUseCase;

    public VenueController(
            CrearVenueUseCase crearVenueUseCase,
            ObtenerVenueUseCase obtenerVenueUseCase,
            ListarVenuesUseCase listarVenuesUseCase,
            ActualizarVenueUseCase actualizarVenueUseCase,
            EliminarVenueUseCase eliminarVenueUseCase) {
        this.crearVenueUseCase = crearVenueUseCase;
        this.obtenerVenueUseCase = obtenerVenueUseCase;
        this.listarVenuesUseCase = listarVenuesUseCase;
        this.actualizarVenueUseCase = actualizarVenueUseCase;
        this.eliminarVenueUseCase = eliminarVenueUseCase;
    }

    @Operation(summary = "Crear un nuevo venue", description = "Crea un nuevo lugar/recinto para eventos. Si no se proporciona un ID, se genera uno automáticamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venue.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")
    })
    @PostMapping
    public Venue crear(@RequestBody VenueRequest request) {
        UUID id = request.getId() != null ? request.getId() : UUID.randomUUID();
        return crearVenueUseCase.ejecutar(
                id,
                request.getNombre(),
                request.getDireccion(),
                request.getCapacidad(),
                request.getDescripcion(),
                request.getCreadoPorUsuarioId());
    }

    @Schema(description = "Datos para crear o actualizar un venue")
    static class VenueRequest {
        @Schema(description = "ID único del venue (UUID). Si no se proporciona, se genera automáticamente", example = "550e8400-e29b-41d4-a716-446655440000")
        private UUID id;

        @Schema(description = "Nombre del venue", example = "Auditorio Nacional", required = true)
        private String nombre;

        @Schema(description = "Dirección del venue", example = "Av. Paseo de la Reforma 50")
        private String direccion;

        @Schema(description = "Capacidad máxima de personas", example = "5000")
        private Integer capacidad;

        @Schema(description = "Descripción del venue", example = "Auditorio con capacidad para eventos masivos")
        private String descripcion;

        @Schema(description = "ID del usuario que crea el venue", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
        private UUID creadoPorUsuarioId;

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

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public Integer getCapacidad() {
            return capacidad;
        }

        public void setCapacidad(Integer capacidad) {
            this.capacidad = capacidad;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public UUID getCreadoPorUsuarioId() {
            return creadoPorUsuarioId;
        }

        public void setCreadoPorUsuarioId(UUID creadoPorUsuarioId) {
            this.creadoPorUsuarioId = creadoPorUsuarioId;
        }
    }

    @Operation(summary = "Obtener venue por ID", description = "Busca y retorna un venue específico usando su identificador único (UUID)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venue.class))),
            @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    })
    @GetMapping("/{id}")
    public Venue obtener(
            @Parameter(description = "ID único del venue (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID id) {
        return obtenerVenueUseCase.ejecutar(id);
    }

    @Operation(summary = "Listar todos los venues", description = "Retorna una lista con todos los venues registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de venues obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venue.class)))
    })
    @GetMapping
    public List<Venue> listarTodos() {
        return listarVenuesUseCase.ejecutar();
    }

    @Operation(summary = "Actualizar un venue existente", description = "Actualiza los datos de un venue específico usando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venue.class))),
            @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    })
    @PutMapping("/{id}")
    public Venue actualizar(
            @Parameter(description = "ID único del venue (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID id,
            @RequestBody VenueRequest request) {
        return actualizarVenueUseCase.ejecutar(
                id,
                request.getNombre(),
                request.getDireccion(),
                request.getCapacidad(),
                request.getDescripcion(),
                request.getCreadoPorUsuarioId())
                .orElseThrow(() -> new RuntimeException("Venue no encontrado con ID: " + id));
    }

    @Operation(summary = "Eliminar un venue", description = "Elimina un venue del sistema usando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID único del venue (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID id) {
        boolean eliminado = eliminarVenueUseCase.ejecutar(id);
        if (!eliminado) {
            throw new RuntimeException("Venue no encontrado con ID: " + id);
        }
    }
}
