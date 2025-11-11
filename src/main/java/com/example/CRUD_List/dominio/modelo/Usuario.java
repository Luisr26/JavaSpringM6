package com.example.CRUD_List.dominio.modelo;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Entidad Usuario que representa un usuario del sistema")
public class Usuario {
    @Schema(description = "ID único del usuario (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    
    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    private String name;
    
    @Schema(description = "Cargo del usuario", example = "Desarrollador")
    private String cargo;
    
    @Schema(description = "Teléfono del usuario", example = "123456789")
    private String telefono;

    public Usuario (UUID id, String name, String cargo, String telefono){
        this.id = id;
        this.name = name;
        this.cargo = cargo;
        this.telefono = telefono;
    }

    public static Usuario crearNuevo(String name, String cargo, String telefono){
        return new Usuario (UUID.randomUUID(), name, cargo, telefono);
    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    

    public String getCargo(){
        return cargo;
    }

    public String getTelefono(){
        return telefono;
    }
}
