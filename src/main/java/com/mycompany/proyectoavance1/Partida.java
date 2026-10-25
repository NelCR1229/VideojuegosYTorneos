package com.mycompany.proyectoavance1;


public class Partida {
    
    private String codigo;
    private Torneo torneo;
    private Usuario jugador1;
    private Usuario jugador2;
    private Usuario ganador;

    public Partida(String codigo, Torneo torneo, Usuario jugador1, Usuario jugador2) {
        this.codigo = codigo;
        this.torneo = torneo;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.ganador = null; // Inicialmente pendiente
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Usuario getJugador1() {
        return jugador1;
    }

    public void setJugador1(Usuario jugador1) {
        this.jugador1 = jugador1;
    }

    public Usuario getJugador2() {
        return jugador2;
    }

    public void setJugador2(Usuario jugador2) {
        this.jugador2 = jugador2;
    }

    public Usuario getGanador() {
        return ganador;
    }

    public void setGanador(Usuario ganador) {
        this.ganador = ganador;
    }

    @Override
    public String toString() {
        return "Partida [" + codigo + "] " + (jugador1 != null ? jugador1.getNombre() : "J1") + 
               " vs " + (jugador2 != null ? jugador2.getNombre() : "J2");
    }
    
}
