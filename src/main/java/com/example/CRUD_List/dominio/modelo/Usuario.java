package com.example.CRUD_List.dominio.modelo;

public class Usuario {
    private String name;
    private String cargo;
    private String telefono;

    public Usuario (String name, String cargo, String telefono){
        this.name = name;
        this.cargo = cargo;
        this.telefono = telefono;
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
