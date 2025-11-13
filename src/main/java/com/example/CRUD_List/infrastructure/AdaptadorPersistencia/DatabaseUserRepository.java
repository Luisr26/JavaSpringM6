package com.example.CRUD_List.infrastructure.AdaptadorPersistencia;

import com.example.CRUD_List.dominio.modelo.Usuario;
import com.example.CRUD_List.dominio.puerto.UsuarioRepositoryPort;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class DatabaseUserRepository implements UsuarioRepositoryPort {
    private final DataSource dataSource;

    public DatabaseUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id, name, cargo, telefono) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, usuario.getId()); 
            stmt.setString(2, usuario.getName());
            stmt.setString(3, usuario.getCargo());
            stmt.setString(4, usuario.getTelefono());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Ajustar al constructor actual de Usuario (id, name, cargo, telefono)
                Usuario usuario = new Usuario(
                    (UUID) rs.getObject("id"),
                    rs.getString("name"),
                    rs.getString("cargo"),
                    rs.getString("telefono")
                );
                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                    (UUID) rs.getObject("id"),
                    rs.getString("name"),
                    rs.getString("cargo"),
                    rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario elimUsuario(UUID id) {
        // Intentamos obtener el usuario antes de eliminar para devolverlo
        Optional<Usuario> opt = buscarPorId(id);
        if (opt.isEmpty()) {
            return null;
        }
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opt.get();
    }
}
