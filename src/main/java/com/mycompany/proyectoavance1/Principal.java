package com.mycompany.proyectoavance1;

public class Principal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Principal.class.getName());
    private DatosSistema datos;
    private Usuario usuario;
    private Login login;

    // Termina la sesion actual y recupera la ventana de acceso.
    public void cerrarSesion() {
        usuario = null;
        login.setVisible(true);
        dispose();
    }
    public Principal(DatosSistema datos, Usuario usuario, Login login) {
        initComponents();
        this.datos = datos;
        this.usuario = usuario;
        this.login = login;

        setLocationRelativeTo(null);

        // Muestra los datos de la persona que inicio sesion.
        lblUsuario.setText("Usuario: " + usuario.getNombre());
        lblRol.setText("Rol: " + usuario.getRol());
        aplicarRol();

    }
    // Ajusta las opciones del menu segun el rol autenticado.
    public void aplicarRol() {

        if (usuario.getRol() == Rol.Administrador) {
            btnVideojuegos.setText("Gestionar videojuegos");
            btnTorneos.setText("Gestionar torneos");
            btnCompetencia.setText("Gestionar competencia");

        } else if (usuario.getRol() == Rol.Organizador) {
            btnVideojuegos.setText("Consultar videojuegos");
            btnTorneos.setText("Gestionar torneos");
            btnCompetencia.setText("Gestionar competencia");

        } else {
            btnVideojuegos.setText("Consultar videojuegos");
            btnTorneos.setText("Consultar torneos");
            btnCompetencia.setText("Mi competencia");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        btnTorneos = new javax.swing.JButton();
        btnCompetencia = new javax.swing.JButton();
        btnVideojuegos = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 6));

        lblTitulo.setFont(new java.awt.Font("Stencil", 0, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 0, 255));
        lblTitulo.setText("Videojuegos y torneos");

        lblRol.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        lblRol.setForeground(new java.awt.Color(0, 0, 255));
        lblRol.setText("rol:");

        lblUsuario.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(0, 0, 255));
        lblUsuario.setText("Usuario:");

        btnTorneos.setBackground(new java.awt.Color(51, 204, 0));
        btnTorneos.setFont(new java.awt.Font("Stencil", 0, 22)); // NOI18N
        btnTorneos.setForeground(new java.awt.Color(0, 0, 204));
        btnTorneos.setText("torneos");
        btnTorneos.addActionListener(this::btnTorneosActionPerformed);

        btnCompetencia.setBackground(new java.awt.Color(51, 204, 0));
        btnCompetencia.setFont(new java.awt.Font("Stencil", 0, 22)); // NOI18N
        btnCompetencia.setForeground(new java.awt.Color(0, 0, 204));
        btnCompetencia.setText("Competencia");
        btnCompetencia.addActionListener(this::btnCompetenciaActionPerformed);

        btnVideojuegos.setBackground(new java.awt.Color(51, 204, 0));
        btnVideojuegos.setFont(new java.awt.Font("Stencil", 0, 22)); // NOI18N
        btnVideojuegos.setForeground(new java.awt.Color(0, 0, 204));
        btnVideojuegos.setText("Videojuego");
        btnVideojuegos.addActionListener(this::btnVideojuegosActionPerformed);

        btnCerrarSesion.setBackground(new java.awt.Color(51, 204, 0));
        btnCerrarSesion.setFont(new java.awt.Font("Stencil", 0, 26)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(0, 0, 204));
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(this::btnCerrarSesionActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnVideojuegos, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCerrarSesion)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTorneos, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(btnCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblRol)
                        .addComponent(lblUsuario)))
                .addGap(298, 298, 298))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTorneos, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVideojuegos, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVideojuegosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVideojuegosActionPerformed
        GestionVideojuegos ventana = new GestionVideojuegos(
                this, datos, usuario
        );

        ventana.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnVideojuegosActionPerformed
    private void btnTorneosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTorneosActionPerformed
        GestionTorneos ventana = new GestionTorneos(datos, this, usuario);
        ventana.setVisible(true);
        this.setVisible(false);
// TODO add your handling code here:
    }//GEN-LAST:event_btnTorneosActionPerformed
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        cerrarSesion();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed
    private void btnCompetenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompetenciaActionPerformed
        GestionCompetencia ventana = new GestionCompetencia(datos, this, usuario);
        ventana.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCompetenciaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCompetencia;
    private javax.swing.JButton btnTorneos;
    private javax.swing.JButton btnVideojuegos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
