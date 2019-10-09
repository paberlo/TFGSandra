package com.example.sandra.proyecto0509.Objetos;

public class Usuario {
    private String id;
    private String nombre_completo;
    private int contadores;
    private String beca;
    private float mesada;

    public Usuario(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }
    public int getContadores(){
        return contadores;
    }
    public void setContadores(int contadores){
       this.contadores=contadores;
    }


    @Override
    public String toString() {
        return "Estudiante: \n" +
                "  id=" + id + "\n" +
                "  nombre_completo=" + nombre_completo + "\n" +
                " contadores " + contadores + "\n";
    }
}