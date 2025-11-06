package com.example.CRUD_List.infrastructure.AdaptadorPersistencia;
import com.example.CRUD_List.infrastructure.entidad.UsuarioEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public class JpaUsuarioRepository extends JpaRepository<UsuarioEntidad, UUID> {
    
}