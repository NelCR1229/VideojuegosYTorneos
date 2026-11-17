// Nicolás Hernández
package com.mycompany.proyectoavance1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    // METODOS CRUD (MySQL con PreparedStatement):
    // agregar, editar y eliminar devuelven null si salio bien (o si USAR_BD es false)
    // y un mensaje de error si algo fallo.

    // Consultar: carga los torneos guardados a la lista en memoria.
    // Llamarlo al iniciar, DESPUES de cargar los videojuegos, porque cada torneo apunta a uno.
    public static String consultar(DatosSistema datos) {
        if (!ConexionSQL.USAR_BD) {
            return null;
        }
        ConexionSQL conexionSQL = new ConexionSQL();

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement("SELECT * FROM torneos;");
            ResultSet rs = ps.executeQuery();

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
            return null;
        } catch (SQLException ex) {
            System.out.println("Exception Consultar: " + ex.getMessage());
            return "No se pueden cargar los torneos.";
        } catch (Exception ex) {
            return "No se puede establecer conexion con la base de datos.";
        } finally {
            conexionSQL.desconectarSQL();
        }
    }

    // Agregar:
    public String agregar() {
        if (!ConexionSQL.USAR_BD) {
            return null;
        }
        ConexionSQL conexionSQL = new ConexionSQL();

        String sql = "INSERT INTO torneos (codigo, nombre, codigo_videojuego, cupo_maximo, estado) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(sql);

            ps.setString(1, this.codigo);
            ps.setString(2, this.nombre);
            ps.setString(3, this.videojuego.getCodigo());
            ps.setInt(4, this.cupoMaximo);
            ps.setString(5, this.estado.toString());

            ps.executeUpdate();
            return null;
        } catch (SQLException ex) {
            System.out.println("Exception Agregar: " + ex.getMessage());
            return "No se pudo agregar el torneo.";
        } catch (Exception ex) {
            return "No se puede establecer conexion con la base de datos.";
        } finally {
            conexionSQL.desconectarSQL();
        }
    }

    // Editar: el codigo no cambia, solo se usa para el WHERE.
    public String editar() {
        if (!ConexionSQL.USAR_BD) {
            return null;
        }
        ConexionSQL conexionSQL = new ConexionSQL();

        String sql = """
                     UPDATE torneos SET
                     nombre = ?,
                     codigo_videojuego = ?,
                     cupo_maximo = ?,
                     estado = ?
                     WHERE codigo = ?;
                     """;

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(sql);

            ps.setString(1, this.nombre);
            ps.setString(2, this.videojuego.getCodigo());
            ps.setInt(3, this.cupoMaximo);
            ps.setString(4, this.estado.toString());
            ps.setString(5, this.codigo);

            ps.executeUpdate();
            return null;
        } catch (SQLException ex) {
            System.out.println("Exception Editar: " + ex.getMessage());
            return "No se pudo modificar el torneo.";
        } catch (Exception ex) {
            return "No se puede establecer conexion con la base de datos.";
        } finally {
            conexionSQL.desconectarSQL();
        }
    }

    // Eliminar:
    public String eliminar() {
        if (!ConexionSQL.USAR_BD) {
            return null;
        }
        ConexionSQL conexionSQL = new ConexionSQL();

        String sql = "DELETE FROM torneos WHERE codigo = ?;";

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(sql);

            ps.setString(1, this.codigo);

            ps.executeUpdate();
            return null;
        } catch (SQLException ex) {
            System.out.println("Exception Eliminar: " + ex.getMessage());
            return "No se pudo eliminar el torneo.";
        } catch (Exception ex) {
            return "No se puede establecer conexion con la base de datos.";
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
    public String toString() {
        return codigo + " - " + nombre + " (" + videojuego.getNombre() + ", cupo " + cupoMaximo + ", " + estado + ")";
    }
}
