/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectoavance1;

/**
 *
 * @author nelda
 */
public class ProyectoAvance1 {

    public static void main(String[] args) {
        DatosSistema datos = new DatosSistema();

// Cuenta temporal para probar el formulario.
        datos.registrarUsuario(
                "U001",
                "Nelson Cardona",
                "nelson",
                "clavePrueba"
        );

        java.awt.EventQueue.invokeLater(() -> {new Login(datos).setVisible(true);
        });
    }
}