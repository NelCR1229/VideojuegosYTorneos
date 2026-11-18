package com.mycompany.proyectoavance1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

    public Videojuego(String codigo) {
        this.codigo = codigo;
    }

    public static DefaultTableModel consultar(JFrame frame) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("GENERO");
        modelo.addColumn("PLATAFORMA");

        if (!ConexionSQL.USAR_BD) {
            return modelo;
        }

        ConexionSQL conexionSQL = new ConexionSQL();
        String datos[] = new String[4];

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(
                    "SELECT codigo, nombre, genero, plataforma FROM videojuegos ORDER BY codigo;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                datos[0] = rs.getString("codigo");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("genero");
                datos[3] = rs.getString("plataforma");

                modelo.addRow(datos);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "No se puede cargar los videojuegos.");
            System.out.println("Exception Consultar: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                    "No se puede establecer conexion con la base de datos");
        } finally {
            conexionSQL.desconectarSQL();
        }

        return modelo;
    }

    public String agregar() {
        if (!ConexionSQL.USAR_BD) {
            return "Videojuego agregado con exito.";
        }

        ConexionSQL conexion = new ConexionSQL();
        String sql = "INSERT INTO videojuegos "
                + "(codigo, nombre, genero, plataforma) "
                + "VALUES (?, ?, ?, ?)";

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

    public String editar() {
        if (!ConexionSQL.USAR_BD) {
            return "Videojuego modificado con exito.";
        }

        ConexionSQL conexion = new ConexionSQL();
        String sql = "UPDATE videojuegos SET "
                + "nombre = ?, genero = ?, plataforma = ? "
                + "WHERE codigo = ?";

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

    public String eliminar() {
        if (!ConexionSQL.USAR_BD) {
            return "Videojuego eliminado con exito.";
        }

        ConexionSQL conexion = new ConexionSQL();
        String sql = "DELETE FROM videojuegos WHERE codigo = ?";

        try {
            PreparedStatement ps = conexion.conectarSQL().prepareStatement(sql);
            ps.setString(1, this.codigo);
            ps.executeUpdate();
            return "Videojuego eliminado con exito.";
        } catch (SQLException ex) {
            System.out.println("Error al eliminar videojuego: " + ex.getMessage());
            return "No se pudo eliminar el videojuego.";
        } catch (Exception ex) {
            return "No se pudo establecer conexion con MySQL.";
        } finally {
            conexion.desconectarSQL();
        }
    }

    
    public static int torneosAsociados(String codigo) {
        if (!ConexionSQL.USAR_BD) {
            return 0;
        }

        ConexionSQL conexionSQL = new ConexionSQL();
        String sql = "SELECT COUNT(*) FROM torneos WHERE codigo_videojuego = ?;";

        try {
            PreparedStatement ps = conexionSQL.conectarSQL().prepareStatement(sql);
            ps.setString(1, codigo);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Exception TorneosAsociados: " + ex.getMessage());
            return -1;
        } catch (Exception ex) {
            return -1;
        } finally {
            conexionSQL.desconectarSQL();
        }
    }

    
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Videojuego)) {
            return false;
        }
        Videojuego otro = (Videojuego) objeto;
        if (codigo == null || otro.codigo == null) {
            return false;
        }
        return codigo.equalsIgnoreCase(otro.codigo);
    }

    @Override
    public int hashCode() {
        return codigo == null ? 0 : codigo.toLowerCase().hashCode();
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
