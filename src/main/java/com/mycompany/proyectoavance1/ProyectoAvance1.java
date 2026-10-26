package com.mycompany.proyectoavance1;

public class ProyectoAvance1 {

    public static void main(String[] args) {
        DatosSistema datos = new DatosSistema();

// Cuenta temporal para probar el formulario.
        datos.registrarUsuario("U001","Nelson Cardona","nelson","123"        );
        // Cuentas para comprobar los otros roles.
        Usuario administrador = new Usuario("U002","Administrador de prueba","admin","123",Rol.Administrador);
        Usuario organizador = new Usuario("U003","Organizador de prueba","organizador","123",Rol.Organizador);
        datos.getUsuarios().add(administrador);
        datos.getUsuarios().add(organizador);
        java.awt.EventQueue.invokeLater(() -> {
            new Login(datos).setVisible(true);
        });
    }
}
