package com.example.CRUD_List.infrastructure.AdaptadorPersistencia;

import org.springframework.stereotype.Component;
import com.example.CRUD_List.dominio.modelo.Usuario;
import com.example.CRUD_List.dominio.puerto.UsuarioRepositoryPort;
import com.example.CRUD_List.infrastructure.entidad.UsuarioEntity;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final JpaUsuarioRepository jpaRepo;

    public UsuarioRepositoryAdapter(JpaUsuarioRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNombre(usuario.getNombre());
        entity.setEmail(usuario.getEmail());
        jpaRepo.save(entity);
        return usuario;
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return jpaRepo.findById(id)
                .map(e -> new Usuario(e.getId(), e.getNombre(), e.getEmail()));
    }

    @Override
    public List<Usuario> listarTodos() {
        return jpaRepo.findAll().stream()
                .map(e -> new Usuario(e.getId(), e.getNombre(), e.getEmail()))
                .toList();
    }
}