package com.example.CRUD_List.dominio.puerto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.CRUD_List.dominio.modelo.Usuario;

public interface UsuarioRepositoryPort {
    void guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(UUID id);
    List<Usuario> listarTodos();
}
