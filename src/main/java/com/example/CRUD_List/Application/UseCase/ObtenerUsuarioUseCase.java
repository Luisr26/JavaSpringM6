package com.example.CRUD_List.Application.UseCase;

import com.example.CRUD_List.dominio.puerto.out.UsuarioRepositoryPort;
import com.example.CRUD_List.dominio.modelo.Usuario;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ObtenerUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositorio;

    public ObtenerUsuarioUseCase(UsuarioRepositoryPort usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario ejecutar(UUID id) {
        return usuarioRepositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }
}
