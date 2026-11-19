package com.mycompany.proyectoavance1;

import javax.swing.JOptionPane;

public class ProyectoAvance1 {
    public static void main(String[] args) {
        ConexionSQL.USAR_BD = true;
        DatosSistema datos = new DatosSistema();
        try {
            datos.cargarUsuariosDesdeBD();
            datos.cargarVideojuegosDesdeBD();
            String mensaje = Torneo.cargarEn(datos);
            if (!mensaje.equals("Torneos cargados con exito.")) {
                JOptionPane.showMessageDialog(null, mensaje);
                return;
            }
            Inscripcion.cargarEn(datos);
            Partida.cargarEn(datos);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                    "No se pudieron cargar los datos de MySQL.");
            return;
        }

        java.awt.EventQueue.invokeLater(() -> {new Login(datos).setVisible(true);});
    }
}