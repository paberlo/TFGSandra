package com.example.sandra.proyecto0509;

public class BaseDatos {

    String contador;
    String totalContadores;
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getTotalContadores() {
        return totalContadores;
    }

    public void setTotalContadores(String totalContadores) {
        this.totalContadores = totalContadores;
    }


    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }


    public BaseDatos(String contador) {
        this.contador = contador;
    }

    /*public BaseDatos(String contador, String totalContadores, String nombre){
        this.contador=contador;
        this.totalContadores=totalContadores;
        this.nombre=nombre;
    }*/



}
