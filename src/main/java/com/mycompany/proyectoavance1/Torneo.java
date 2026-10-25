// Nicolás Hernández
package com.mycompany.proyectoavance1;

import java.util.Objects;

public class Torneo {

    private String codigo;
    private String nombre;
    private Videojuego videojuego;
    private int cupoMaximo;
    private EstadoTorneo estado;

    public Torneo(String codigo, String nombre, Videojuego videojuego, int cupoMaximo, EstadoTorneo estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.videojuego = videojuego;
        this.cupoMaximo = cupoMaximo;
        this.estado = estado;
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

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public EstadoTorneo getEstado() {
        return estado;
    }

    public void setEstado(EstadoTorneo estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Torneo)) {
            return false;
        }
        Torneo otro = (Torneo) obj;
        return codigo.equalsIgnoreCase(otro.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo.toUpperCase());
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + videojuego.getNombre() + ", cupo " + cupoMaximo + ", " + estado + ")";
    }
}
