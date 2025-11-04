package com.example.CRUD_List.dominio.puerto;

import java.util.List;
import java.util.Optional;

import com.example.CRUD_List.dominio.modelo.Usuario;

public interface UsuarioRepositorio {
    void guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(String id);
    List<Usuario> listarTodos();
}
