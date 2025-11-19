package com.example.CRUD_List.infrastructure.AdaptadorEntrada;
import com.example.CRUD_List.Application.UseCase.CrearUsuarioUseCase;
import com.example.CRUD_List.Application.UseCase.ListarUsuariosUseCase;
import com.example.CRUD_List.Application.UseCase.ObtenerUsuarioUseCase;
import com.example.CRUD_List.Application.UseCase.ActualizarUsuarioUseCase;
import com.example.CRUD_List.Application.UseCase.EliminarUsuarioUseCase;
import com.example.CRUD_List.dominio.modelo.Usuario;
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
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "API para gestión de usuarios")
public class UsuarioController {

    private final CrearUsuarioUseCase crearUsuarioUseCase;
    private final ObtenerUsuarioUseCase obtenerUsuarioUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final ActualizarUsuarioUseCase actualizarUsuarioUseCase;
    private final EliminarUsuarioUseCase eliminarUsuarioUseCase;

    public UsuarioController(
            CrearUsuarioUseCase crearUsuarioUseCase, 
            ObtenerUsuarioUseCase obtenerUsuarioUseCase,
            ListarUsuariosUseCase listarUsuariosUseCase,
            ActualizarUsuarioUseCase actualizarUsuarioUseCase,
            EliminarUsuarioUseCase eliminarUsuarioUseCase) {
        this.crearUsuarioUseCase = crearUsuarioUseCase;
        this.obtenerUsuarioUseCase = obtenerUsuarioUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.actualizarUsuarioUseCase = actualizarUsuarioUseCase;
        this.eliminarUsuarioUseCase = eliminarUsuarioUseCase;
    }

    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Crea un nuevo usuario en el sistema. Si no se proporciona un ID, se genera uno automáticamente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos proporcionados"
            )
    })
    @PostMapping
    public Usuario crear(@RequestBody UsuarioRequest request) {
        UUID id = request.getId() != null ? request.getId() : UUID.randomUUID();
        return crearUsuarioUseCase.ejecutar(id, request.getName(), request.getCargo(), request.getTelefono());
    }

    // Clase interna para recibir los datos del request
    @Schema(description = "Datos para crear o actualizar un usuario")
    static class UsuarioRequest {
        @Schema(description = "ID único del usuario (UUID). Si no se proporciona, se genera automáticamente", example = "550e8400-e29b-41d4-a716-446655440000")
        private UUID id;
        
        @Schema(description = "Nombre del usuario", example = "Juan Pérez", required = true)
        private String name;
        
        @Schema(description = "Cargo del usuario", example = "Desarrollador", required = true)
        private String cargo;
        
        @Schema(description = "Teléfono del usuario", example = "123456789", required = true)
        private String telefono;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCargo() {
            return cargo;
        }

        public void setCargo(String cargo) {
            this.cargo = cargo;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
    }

    @Operation(
            summary = "Obtener usuario por ID",
            description = "Busca y retorna un usuario específico usando su identificador único (UUID)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado"
            )
    })
    @GetMapping("/{id}")
    public Usuario obtener(
            @Parameter(description = "ID único del usuario (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id) {
        return obtenerUsuarioUseCase.ejecutar(id);
    }

    @Operation(
            summary = "Listar todos los usuarios",
            description = "Retorna una lista con todos los usuarios registrados en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de usuarios obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
            )
    })
    @GetMapping
    public List<Usuario> listarTodos() {
        return listarUsuariosUseCase.ejecutar();
    }

    @Operation(
            summary = "Actualizar un usuario existente",
            description = "Actualiza los datos de un usuario específico usando su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario actualizado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado"
            )
    })
    @PutMapping("/{id}")
    public Usuario actualizar(
            @Parameter(description = "ID único del usuario (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id,
            @RequestBody UsuarioRequest request) {
        return actualizarUsuarioUseCase.ejecutar(id, request.getName(), request.getCargo(), request.getTelefono())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Operation(
            summary = "Eliminar un usuario",
            description = "Elimina un usuario del sistema usando su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario eliminado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado"
            )
    })
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID único del usuario (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id) {
        boolean eliminado = eliminarUsuarioUseCase.ejecutar(id);
        if (!eliminado) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}