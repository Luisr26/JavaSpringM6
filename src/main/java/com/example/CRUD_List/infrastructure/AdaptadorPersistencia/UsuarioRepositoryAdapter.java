package com.example.CRUD_List.infrastructure.AdaptadorPersistencia;

import org.springframework.stereotype.Component;
import com.example.CRUD_List.dominio.modelo.Usuario;
import com.example.CRUD_List.dominio.puerto.UsuarioRepositoryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    // Almacenamiento en memoria usando ArrayList
    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepositoryAdapter() {
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        // Si el usuario ya existe (mismo ID), lo reemplazamos
        usuarios.removeIf(u -> u.getId().equals(usuario.getId()));
        usuarios.add(usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }
}