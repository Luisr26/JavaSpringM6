package com.example.CRUD_List.infrastructure.AdaptadorPersistencia;

import com.example.CRUD_List.dominio.modelo.Usuario;
import com.example.CRUD_List.dominio.puerto.out.UsuarioRepositoryPort;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DatabaseUserRepository implements UsuarioRepositoryPort {
    private final SpringDataUsuarioRepository springDataUsuarioRepository;

    public DatabaseUserRepository(SpringDataUsuarioRepository springDataUsuarioRepository) {
        this.springDataUsuarioRepository = springDataUsuarioRepository;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);
        UsuarioEntity savedEntity = springDataUsuarioRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return springDataUsuarioRepository.findById(id.toString())
                .map(this::toDomain);
    }

    @Override
    public List<Usuario> listarTodos() {
        return springDataUsuarioRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Usuario elimUsuario(UUID id) {
        Optional<UsuarioEntity> entity = springDataUsuarioRepository.findById(id.toString());
        if (entity.isPresent()) {
            springDataUsuarioRepository.deleteById(id.toString());
            return toDomain(entity.get());
        }
        return null;
    }

    // Mappers: Domain <-> Entity
    private UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId().toString(),
                usuario.getName(),
                usuario.getCargo(),
                usuario.getTelefono());
    }

    private Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                UUID.fromString(entity.getId()),
                entity.getName(),
                entity.getCargo(),
                entity.getTelefono());
    }
}
