package com.example.usuarios.dominio.puerto.in;

import com.example.usuarios.dominio.modelo.Usuario;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de entrada para operaciones CRUD de Usuario
 * Define los casos de uso que la aplicación expone
 */
public interface UsuarioServicePort {
    Usuario crearUsuario(UUID id, String name, String cargo, String telefono);

    Optional<Usuario> obtenerUsuario(UUID id);

    List<Usuario> listarUsuarios();

    Optional<Usuario> actualizarUsuario(UUID id, String name, String cargo, String telefono);

    boolean eliminarUsuario(UUID id);
}
