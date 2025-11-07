package com.example.CRUD_List.infrastructure.AdaptadorEntrada;
import com.example.CRUD_List.Application.UseCase.CrearUsuarioUseCase;
import com.example.CRUD_List.Application.UseCase.ObtenerUsuarioUseCase;
import com.example.CRUD_List.dominio.modelo.Usuario;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CrearUsuarioUseCase crearUsuario;
    private final ObtenerUsuarioUseCase obtenerUsuario;

    public UsuarioController(CrearUsuarioUseCase crearUsuario, ObtenerUsuarioUseCase obtenerUsuario) {
        this.crearUsuario = crearUsuario;
        this.obtenerUsuario = obtenerUsuario;
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return crearUsuario.crearUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable UUID id) {
        return obtenerUsuario.obtenerUsuarioPorId(id);
    }
}