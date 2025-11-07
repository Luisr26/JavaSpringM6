package com.example.CRUD_List.Application.UseCase;

import java.util.UUID;

import com.example.CRUD_List.dominio.modelo.Usuario;
import com.example.CRUD_List.dominio.puerto.UsuarioRepositoryPort;

public class CrearUsuarioUseCase {
    private final UsuarioRepositoryPort usuarioRepositorioPort;

    public CrearUsuarioUseCase(UsuarioRepositoryPort usuarioRepositorioPort) {
        this.usuarioRepositorioPort = usuarioRepositorioPort;
    }

    public Usuario crearUsuario(UUID id, String name, String cargo, String telefono) {
        Usuario usuario = new Usuario(id, name, cargo, telefono);
        usuarioRepositorioPort.guardar(usuario);
        return usuario;
    }

}