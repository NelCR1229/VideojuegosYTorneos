package com.mycompany.proyectoavance1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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
    
    
    public String agregar() {
        if (!ConexionSQL.USAR_BD) {
            return "Partida registrada exitosamente.";
        }
        ConexionSQL conexionSQL = new ConexionSQL();
        String sql = "INSERT INTO partidas (codigo, codigo_torneo, codigo_jugador1, codigo_jugador2, codigo_ganador) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(sql);
            ps.setString(1, this.codigo);
            ps.setString(2, this.torneo.getCodigo());
            ps.setString(3, this.jugador1.getCodigo());
            ps.setString(4, this.jugador2.getCodigo());
            if (this.ganador != null) {
                ps.setString(5, this.ganador.getCodigo());
            } else {
                ps.setNull(5, Types.VARCHAR);
            }
            ps.executeUpdate();
            return "Partida registrada exitosamente.";
        } catch (SQLException ex) {
            System.out.println("Exception Agregar Partida: " + ex.getMessage());
            return "No se pudo guardar la partida en MySQL.";
        } catch (Exception ex) {
            return "No se puede establecer conexión con la base de datos.";
        } finally {
            conexionSQL.desconectarSQL();
        }
    }
    
    
    public String actualizarGanador() {
        if (!ConexionSQL.USAR_BD) {
            return "Ganador registrado exitosamente.";
        }
        ConexionSQL conexionSQL = new ConexionSQL();
        String sql = "UPDATE partidas SET codigo_ganador = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(sql);
            if (this.ganador != null) {
                ps.setString(1, this.ganador.getCodigo());
            } else {
                ps.setNull(1, Types.VARCHAR);
            }
            ps.setString(2, this.codigo);
            ps.executeUpdate();
            return "Ganador registrado exitosamente.";
        } catch (SQLException ex) {
            System.out.println("Exception Actualizar Ganador: " + ex.getMessage());
            return "No se pudo actualizar el ganador en MySQL.";
        } catch (Exception ex) {
            return "No se puede establecer conexión con la base de datos.";
        } finally {
            conexionSQL.desconectarSQL();
        }
    }
    
    
    public static String cargarEn(DatosSistema datos) {
        if (!ConexionSQL.USAR_BD) {
            return "Partidas cargadas con éxito.";
        }
        ConexionSQL conexionSQL = new ConexionSQL();
        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement("SELECT * FROM partidas;");
            ResultSet rs = ps.executeQuery();
            datos.getPartidas().clear();
            while (rs.next()) {
                Torneo t = null;
                for (Torneo tor : datos.getTorneos()) {
                    if (tor.getCodigo().equals(rs.getString("codigo_torneo"))) {
                        t = tor;
                        break;
                    }
                }
                Usuario j1 = datos.buscarUsuarioPorCodigo(rs.getString("codigo_jugador1"));
                Usuario j2 = datos.buscarUsuarioPorCodigo(rs.getString("codigo_jugador2"));
                String codGanador = rs.getString("codigo_ganador");
                Usuario gan = (codGanador != null) ? datos.buscarUsuarioPorCodigo(codGanador) : null;

                if (t != null && j1 != null && j2 != null) {
                    Partida p = new Partida(rs.getString("codigo"), t, j1, j2);
                    p.setGanador(gan);
                    datos.getPartidas().add(p);
                }
            }
            return "Partidas cargadas con éxito.";
        } catch (SQLException ex) {
            System.out.println("Exception Cargar Partidas: " + ex.getMessage());
            return "No se pueden cargar las partidas.";
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
        String nombreJugador1 = "J1";
        String nombreJugador2 = "J2";
        if (jugador1 != null) {
            nombreJugador1 = jugador1.getNombre();
        }
        if (jugador2 != null) {
            nombreJugador2 = jugador2.getNombre();
        }
        return "Partida [" + codigo + "] " + nombreJugador1 + " vs " + nombreJugador2;
    }

}
