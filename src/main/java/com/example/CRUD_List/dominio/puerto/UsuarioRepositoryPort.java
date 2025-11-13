package com.example.CRUD_List.dominio.puerto;
import com.example.CRUD_List.dominio.modelo.Usuario;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(UUID id);
    List<Usuario> listarTodos();
    Usuario elimUsuario(UUID id);
}
