package com.example.CRUD_List.Application.UseCase;
import com.example.CRUD_List.dominio.puerto.UsuarioRepositoryPort;
import com.example.CRUD_List.dominio.modelo.Usuario;

import java.util.UUID;

public class ObtenerUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositorio;

    public ObtenerUsuarioUseCase(UsuarioRepositoryPort usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario obtenerUsuarioPorId(UUID id) {
        return usuarioRepositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }
}
