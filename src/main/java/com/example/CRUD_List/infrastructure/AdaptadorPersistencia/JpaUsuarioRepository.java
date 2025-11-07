package com.example.CRUD_List.infrastructure.AdaptadorPersistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CRUD_List.infrastructure.entidad.UsuarioEntity;
import java.util.UUID;

public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
}