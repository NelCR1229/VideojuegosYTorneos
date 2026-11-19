package com.mycompany.proyectoavance1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Inscripcion {

    private String codigo;
    private Usuario usuario;
    private Torneo torneo;

    public Inscripcion(String codigo, Usuario usuario, Torneo torneo) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.torneo = torneo;
    }
    
    
    public String agregar(){
        if (!ConexionSQL.USAR_BD) {
            
            return "Inscripcion registrada correctamente";
        }
        
        ConexionSQL conexionSQL = new ConexionSQL();
        String sql = "INSERT INTO inscripciones (codigo, codigo_usuario, codigo_torneo) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(sql);
            ps.setString(1, this.codigo);
            ps.setString(2, this.usuario.getCodigo());
            ps.setString(3, this.torneo.getCodigo());
            ps.executeUpdate();
            return "Inscripción registrada con éxito.";
        } catch (SQLException ex) {
            System.out.println("Exception Agregar Inscripcion:" + ex.getMessage());
            return "No se pudo guardar la inscripcion en MySQL";
        } catch (Exception ex) {
            return "No se puede establecer conexión con la base de datos.";
        }finally {
            conexionSQL.desconectarSQL();
        }
    }
    
    public static String cargarEn(DatosSistema datos) {
        if (!ConexionSQL.USAR_BD) {
            return "Inscripciones cargadas con éxito.";
        }
        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement("SELECT * FROM inscripciones;");
            ResultSet rs = ps.executeQuery();
            datos.getInscripciones().clear();
            while (rs.next()) {
                Usuario u = datos.buscarUsuarioPorCodigo(rs.getString("codigo_usuario"));
                Torneo t = null;
                for (Torneo tor : datos.getTorneos()) {
                    if (tor.getCodigo().equals(rs.getString("codigo_torneo"))) {
                        t = tor;
                        break;
                    }
                }
                if (u != null && t != null) {
                    datos.getInscripciones().add(new Inscripcion(
                            rs.getString("codigo"),
                            u,
                            t
                    ));
                }
            }
            return "Inscripciones cargadas con éxito.";
        } catch (SQLException ex) {
            System.out.println("Exception Cargar Inscripciones: " + ex.getMessage());
            return "No se pueden cargar las inscripciones.";
        } catch (Exception ex) {
            return "No se puede establecer conexión con la base de datos.";
        } finally {
            conexionSQL.desconectarSQL();
        }
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
        String nombreUsuario = "N/A";
        if (usuario != null) {
            nombreUsuario = usuario.getNombre();
        }
        return "Inscripcion [" + codigo + "] - Usuario: "+ nombreUsuario;
    }
}
