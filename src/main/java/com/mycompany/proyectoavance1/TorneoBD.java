package com.mycompany.proyectoavance1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Operaciones de la tabla torneos en MySQL (JDBC con PreparedStatement).
 * Si Conexion.USAR_BD es false, no hacen nada y el programa sigue solo en memoria.
 */
public class TorneoBD {

    // INSERT: guarda un torneo nuevo.
    public static void insertar(Torneo torneo) throws SQLException {
        if (!Conexion.USAR_BD) {
            return;
        }
        String sql = "INSERT INTO torneos (codigo, nombre, codigo_videojuego, cupo_maximo, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, torneo.getCodigo());
            ps.setString(2, torneo.getNombre());
            ps.setString(3, torneo.getVideojuego().getCodigo());
            ps.setInt(4, torneo.getCupoMaximo());
            ps.setString(5, torneo.getEstado().toString());
            ps.executeUpdate();
        }
    }

    // UPDATE: cambia los datos de un torneo (el codigo no cambia).
    public static void actualizar(String codigo, String nombre, Videojuego videojuego,
            int cupo, EstadoTorneo estado) throws SQLException {
        if (!Conexion.USAR_BD) {
            return;
        }
        String sql = "UPDATE torneos SET nombre = ?, codigo_videojuego = ?, cupo_maximo = ?, estado = ? WHERE codigo = ?";
        try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, videojuego.getCodigo());
            ps.setInt(3, cupo);
            ps.setString(4, estado.toString());
            ps.setString(5, codigo);
            ps.executeUpdate();
        }
    }

    // DELETE: borra un torneo por su codigo.
    public static void eliminar(String codigo) throws SQLException {
        if (!Conexion.USAR_BD) {
            return;
        }
        String sql = "DELETE FROM torneos WHERE codigo = ?";
        try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        }
    }

    // SELECT: carga los torneos guardados a la lista en memoria.
    // Hay que llamarlo al iniciar, DESPUES de cargar los videojuegos, porque cada torneo apunta a uno.
    public static void cargarEn(DatosSistema datos) throws SQLException {
        if (!Conexion.USAR_BD) {
            return;
        }
        String sql = "SELECT codigo, nombre, codigo_videojuego, cupo_maximo, estado FROM torneos";
        try (Connection con = Conexion.obtener();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Videojuego videojuego = null;
                for (Videojuego v : datos.getVideojuegos()) {
                    if (v.getCodigo().equals(rs.getString("codigo_videojuego"))) {
                        videojuego = v;
                    }
                }
                if (videojuego != null) {
                    datos.getTorneos().add(new Torneo(
                            rs.getString("codigo"),
                            rs.getString("nombre"),
                            videojuego,
                            rs.getInt("cupo_maximo"),
                            EstadoTorneo.valueOf(rs.getString("estado"))));
                }
            }
        }
    }

}
