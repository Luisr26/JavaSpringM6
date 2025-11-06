package com.example.CRUD_List.dominio.modelo;
import java.util.UUID;


public class Usuario {
    private UUID id;
    private String name;
    private String cargo;
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
