package com.example.CRUD_List.infrastructure.AdaptadorEntrada;
import com.example.CRUD_List.Application.UseCase.CrearUsuarioUseCase;
import com.example.CRUD_List.Application.UseCase.ObtenerUsuarioUseCase;
import com.example.CRUD_List.dominio.modelo.Usuario;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CrearUsuarioUseCase crearUser;
    private final ObtenerUsuarioUseCase obtenerUsuario;

    public UsuarioController(CrearUsuarioUseCase crearUser, ObtenerUsuarioUseCase obtenerUsuario) {
        this.crearUser = crearUser;
        this.obtenerUsuario = obtenerUsuario;
    }

    @PostMapping
    public Usuario crear(@RequestBody UUID id, String name, String cargo, String telefono) {
        return crearUser.crearUsuario(id, name, cargo, telefono);
    }

    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable UUID id) {
        return obtenerUsuario.obtenerUsuarioPorId(id);
    }
}