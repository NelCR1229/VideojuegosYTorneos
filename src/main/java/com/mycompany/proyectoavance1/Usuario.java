package com.mycompany.proyectoavance1;
/*
 * Guarda su identificación, sus datos de acceso y su rol.
 */
public class Usuario {

    private String codigo;
    private String nombre;
    private String usuario;
    private String clave;
    private Rol rol;

    /* Constructor: */
    public Usuario(String codigo, String nombre, String usuario,
            String clave, Rol rol) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
    }

    /*Devuelve los datos generales del usuario.*/
    @Override
    public String toString() {
         return codigo + " - " + nombre;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
