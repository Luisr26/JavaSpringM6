package com.example.CRUD_List.infrastructure.AdaptadorPersistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, String> {
}
