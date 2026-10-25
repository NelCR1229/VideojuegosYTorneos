package com.mycompany.proyectoavance1;

/*
 * Representa un videojuego que puede asociarse a un torneo.
 */
public class Videojuego {

    private String codigo;
    private String nombre;
    private String genero;
    private String plataforma;

    public Videojuego(String codigo, String nombre,
            String genero, String plataforma) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.plataforma = plataforma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    // Descripcion que mostrara el selector de videojuegos.
    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}