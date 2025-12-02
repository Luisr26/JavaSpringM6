package com.example.usuarios.Application.UseCase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.usuarios.dominio.modelo.Usuario;
import com.example.usuarios.dominio.puerto.out.UsuarioRepositoryPort;

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
