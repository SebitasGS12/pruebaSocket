package com.cibertec.BBDD;

import java.io.Serializable;

public class Imagen implements Serializable {
    

    public Imagen(String nombre, int tamano, byte[] archivo) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.archivo = archivo;
    }
    private String nombre;
    private int tamano;
    private byte[] archivo;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getTamano() {
        return tamano;
    }
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    public byte[] getArchivo() {
        return archivo;
    }
    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
 

    
}
