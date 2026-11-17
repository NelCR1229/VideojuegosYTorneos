// Nicolás Hernández
package com.mycompany.proyectoavance1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Torneo {

    private String codigo;
    private String nombre;
    private Videojuego videojuego;
    private int cupoMaximo;
    private EstadoTorneo estado;

    // Constructor Agregar / Editar (el codigo lo escribe el usuario, no es autoincremental)
    public Torneo(String codigo, String nombre, Videojuego videojuego, int cupoMaximo, EstadoTorneo estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.videojuego = videojuego;
        this.cupoMaximo = cupoMaximo;
        this.estado = estado;
    }

    // Constructor Eliminar
    public Torneo(String codigo) {
        this.codigo = codigo;
    }

    // METODOS CRUD (MySQL con PreparedStatement):
    // Si USAR_BD es false no se usa MySQL y solo se devuelve el mensaje de exito.

    // Consultar: devuelve el modelo de la tabla con los torneos guardados en MySQL.
    public static DefaultTableModel consultar(JFrame frame) {
        ConexionSQL conexionSQL = new ConexionSQL();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("VIDEOJUEGO");
        modelo.addColumn("CUPO");
        modelo.addColumn("ESTADO");

        String datos[] = new String[5];

        try {

            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement("SELECT * FROM torneos;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                datos[0] = rs.getString("codigo");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("codigo_videojuego");
                datos[3] = rs.getString("cupo_maximo");
                datos[4] = rs.getString("estado");

                modelo.addRow(datos);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "No se puede cargar los torneos.");
            System.out.println("Exception Consultar: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                    "No se puede establecer conexion con la base de datos");
        } finally {
            conexionSQL.desconectarSQL();
        }

        return modelo;
    }

    // Cargar al iniciar: pasa los torneos de MySQL a la lista en memoria.
    // Llamarlo DESPUES de cargar los videojuegos, porque cada torneo apunta a uno.
    public static String cargarEn(DatosSistema datos) {
        if (!ConexionSQL.USAR_BD) {
            return "Torneos cargados con exito.";
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
            return "Torneos cargados con exito.";
        } catch (SQLException ex) {
            System.out.println("Exception Cargar: " + ex.getMessage());
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
            return "Torneo agregado con exito.";
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
            return "Torneo agregado con exito.";
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
            return "Torneo modificado con exito.";
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
            return "Torneo modificado con exito.";
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
            return "Torneo eliminado con exito.";
        }
        ConexionSQL conexionSQL = new ConexionSQL();

        String sql = "DELETE FROM torneos WHERE codigo = ?;";

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(sql);

            ps.setString(1, this.codigo);

            ps.executeUpdate();
            return "Torneo eliminado con exito.";
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
