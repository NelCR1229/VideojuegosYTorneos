package com.mycompany.proyectoavance1;


public class Inscripcion {
    
    private String codigo;
    private Usuario usuario;
    private Torneo torneo;

    public Inscripcion(String codigo, Usuario usuario, Torneo torneo) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.torneo = torneo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    @Override
    public String toString() {
        return "Inscripción [" + codigo + "] - Usuario: " + (usuario != null ? usuario.getNombre() : "N/A");
    }
    
}
