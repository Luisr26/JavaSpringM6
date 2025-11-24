package com.example.usuarios.dominio.puerto.out;

import com.example.usuarios.dominio.modelo.Usuario;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para persistencia de Usuario
 * Define las operaciones que el dominio necesita de la infraestructura
 */
public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(UUID id);

    List<Usuario> listarTodos();

    Usuario elimUsuario(UUID id);
}
