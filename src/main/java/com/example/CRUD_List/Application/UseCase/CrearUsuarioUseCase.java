package com.example.CRUD_List.Application.UseCase;

import com.example.CRUD_List.dominio.modelo.Usuario;
import com.example.CRUD_List.dominio.puerto.UsuarioRepositorioPort;

public class CrearUsuarioUseCase {
    private final UsuarioRepositorioPort usuarioRepositorioPort;

    public CrearUsuarioUseCase(UsuarioRepositorioPort usuarioRepositorioPort){
        this.usuarioRepositorioPort = usuarioRepositorioPort;
    }

    public Usuario crearUsuario(Usuario usuario){
        usuarioRepositorioPort.guardar(usuario);
        return usuario;
    }
}