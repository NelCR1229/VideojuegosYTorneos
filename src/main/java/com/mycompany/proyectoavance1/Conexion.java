package com.mycompany.proyectoavance1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Datos de conexion a MySQL, compartidos por todos los modulos.
 * Cambiar USUARIO y CLAVE por los del MySQL de cada computadora.
 */
public class Conexion {

    // false = trabaja solo en memoria (sin MySQL). Cambiar a true cuando la base ya este creada.
    public static boolean USAR_BD = false;

    private static final String URL = "jdbc:mysql://localhost:3306/videojuegos_torneos";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    public static Connection obtener() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

}
