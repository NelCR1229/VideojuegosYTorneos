package com.mycompany.proyectoavance1;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Videojuego {

    private String codigo;
    private String nombre;
    private String genero;
    private String plataforma;

    public Videojuego(String codigo, String nombre, String genero, String plataforma) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.plataforma = plataforma;
    }

    // Guarda este videojuego en MySQL.
    public String agregar() {
        ConexionSQL conexion = new ConexionSQL();
        String sql = "INSERT INTO videojuegos " + "(codigo, nombre, genero, plataforma) " + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.conectarSQL().prepareStatement(sql);
            ps.setString(1, this.codigo);
            ps.setString(2, this.nombre);
            ps.setString(3, this.genero);
            ps.setString(4, this.plataforma);
            ps.executeUpdate();
            return "Videojuego agregado con exito.";

        } catch (SQLException ex) {
            System.out.println("Error al agregar videojuego: " + ex.getMessage());
            return "No se pudo agregar el videojuego.";
        } catch (Exception ex) {
            return "No se pudo establecer conexion con MySQL.";
        } finally {
            conexion.desconectarSQL();
        }
    }
    // Actualiza los datos del videojuego conservando su codigo.
    public String editar() {
        ConexionSQL conexion = new ConexionSQL();
        String sql = "UPDATE videojuegos SET " + "nombre = ?, genero = ?, plataforma = ? " + "WHERE codigo = ?";
        try {
            PreparedStatement ps = conexion.conectarSQL().prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setString(2, this.genero);
            ps.setString(3, this.plataforma);
            ps.setString(4, this.codigo);
            ps.executeUpdate();
            return "Videojuego modificado con exito.";
        } catch (SQLException ex) {
            System.out.println("Error al editar videojuego: " + ex.getMessage());
            return "No se pudo modificar el videojuego.";
        } catch (Exception ex) {
            return "No se pudo establecer conexion con MySQL.";
        } finally {
            conexion.desconectarSQL();
        }
    }
    // Elimina este videojuego de MySQL.
    public String eliminar() {
        ConexionSQL conexion = new ConexionSQL();
        String sql = "DELETE FROM videojuegos WHERE codigo = ?";
        try {
            PreparedStatement ps = conexion.conectarSQL().prepareStatement(sql);
            ps.setString(1, this.codigo);
            ps.executeUpdate();
            return "Videojuego eliminado con exito.";
        } catch (SQLException ex) {
            System.out.println("Error al eliminar videojuego: "+ ex.getMessage());
            return "No se pudo eliminar el videojuego.";
        } catch (Exception ex) {
            return "No se pudo establecer conexion con MySQL.";
        } finally {
            conexion.desconectarSQL();
        }
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
    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + plataforma + ")";
    }
}
