package com.example.CRUD_List.Application.UseCase;

import com.example.CRUD_List.dominio.modelo.Usuario;
import com.example.CRUD_List.dominio.puerto.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EliminarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public EliminarUsuarioUseCase(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    public boolean ejecutar(UUID id) {
        Usuario usuarioEliminado = usuarioRepositoryPort.elimUsuario(id);
        return usuarioEliminado != null;
    }
}
