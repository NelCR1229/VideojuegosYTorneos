package com.mycompany.proyectoavance1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {

    // false = el programa trabaja solo en memoria (sin MySQL).
    // Cambiar a true cuando la base de datos ya este creada.
    public static boolean USAR_BD = false;

    private String url;
    private String db;
    private String user;
    private String password;
    private String driver;
    private Connection connection;

    public ConexionSQL() {
        this.url = "jdbc:mysql://localhost:3306/";
        this.db = "videojuegos_torneos";
        this.user = "root";
        this.password = ""; // poner la clave del MySQL de cada compu
        this.driver = "com.mysql.cj.jdbc.Driver";
    }

    public Connection conectarSQL() throws Exception {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url + db, user, password);
            return connection;
        } catch (ClassNotFoundException ex) {
            System.out.println("Exception Class: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Exception SQL: " + ex.getMessage());
        }
        throw new Exception();
    }

    public void desconectarSQL() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion Cerrada.");
            } catch (SQLException ex) {
                System.out.println("Exception SQL: " + ex.getMessage());
            }
        }
    }

}
