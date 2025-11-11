package com.example.CRUD_List.Application.UseCase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.CRUD_List.dominio.modelo.Usuario;
import com.example.CRUD_List.dominio.puerto.UsuarioRepositoryPort;

@Service
public class ListarUsuariosUseCase {
    
    private final UsuarioRepositoryPort usuarioRepositorioPort;

    public ListarUsuariosUseCase(UsuarioRepositoryPort usuarioRepositorioPort) {
        this.usuarioRepositorioPort = usuarioRepositorioPort;
    }

    public List<Usuario> ejecutar() {
        return usuarioRepositorioPort.listarTodos();
    }
}

