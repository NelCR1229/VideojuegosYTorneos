package com.mycompany.proyectoavance1;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestionCompetencia extends javax.swing.JFrame {
    
    private DatosSistema datos;
    private DefaultTableModel modeloTabla;
    
    public GestionCompetencia(DatosSistema datos) {
        initComponents();
        this.datos = datos;
        setLocationRelativeTo(null);

        cargarListasDesplegables();

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código Partida");
        modeloTabla.addColumn("Torneo");
        modeloTabla.addColumn("Jugador 1");
        modeloTabla.addColumn("Jugador 2");
        modeloTabla.addColumn("Ganador");
        
        if (tbRegistros != null) {
            tbRegistros.setModel(modeloTabla);
        }

        actualizarTabla();
    }

    private void cargarListasDesplegables() {
        if (cbTorneo != null) {
            cbTorneo.removeAllItems();
            if (datos != null && datos.getTorneos() != null) {
                for (Torneo t : datos.getTorneos()) {
                    // Usamos casting para evitar el conflicto con el tipo de NetBeans
                    ((javax.swing.JComboBox) cbTorneo).addItem(t);
                }
            }
        }
        
        if (cbJugador1 != null) {
            cbJugador1.removeAllItems();
            if (datos != null && datos.getUsuarios() != null) {
                for (Usuario u : datos.getUsuarios()) {
                    ((javax.swing.JComboBox) cbJugador1).addItem(u);
                }
            }
        }
        
        if (cbJugador2 != null) {
            cbJugador2.removeAllItems();
            if (datos != null && datos.getUsuarios() != null) {
                for (Usuario u : datos.getUsuarios()) {
                    ((javax.swing.JComboBox) cbJugador2).addItem(u);
                }
            }
        }
    }

    private void actualizarTabla() {
        if (modeloTabla == null) return;
        modeloTabla.setRowCount(0);

        if (datos != null && datos.getPartidas() != null) {
            for (Partida p : datos.getPartidas()) {
                modeloTabla.addRow(new Object[]{
                    p.getCodigo(),
                    p.getTorneo() != null ? p.getTorneo().getNombre() : "",
                    p.getJugador1() != null ? p.getJugador1().getNombre() : "",
                    p.getJugador2() != null ? p.getJugador2().getNombre() : "",
                    p.getGanador() != null ? p.getGanador().getNombre() : "Pendiente"
                });
            }
        }
    }
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GestionCompetencia.class.getName());

   
    public GestionCompetencia() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoPartida = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbTorneo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbJugador1 = new javax.swing.JComboBox<>();
        cbJugador2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRegistros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Stencil", 0, 36)); // NOI18N
        jLabel1.setText("Gestion de competencia");

        jLabel2.setFont(new java.awt.Font("Stencil", 0, 18)); // NOI18N
        jLabel2.setText("Codigo de Partida:");

        jLabel3.setFont(new java.awt.Font("Stencil", 0, 18)); // NOI18N
        jLabel3.setText("Seleccionar Torneo:");

        cbTorneo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Stencil", 0, 18)); // NOI18N
        jLabel4.setText("Jugador 1:");

        cbJugador1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbJugador2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Stencil", 0, 18)); // NOI18N
        jLabel5.setText("Jugador 2:");

        btnRegistrar.setFont(new java.awt.Font("Stencil", 0, 18)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        btnRegresar.setFont(new java.awt.Font("Stencil", 0, 18)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);

        tbRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbRegistros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtCodigoPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTorneo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)))
                .addContainerGap(100, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigoPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbTorneo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        String codigoPartida = txtCodigoPartida.getText().trim();
        Torneo torneoSel = (Torneo) cbTorneo.getSelectedItem();
        Usuario j1Sel = (Usuario) cbJugador1.getSelectedItem();
        Usuario j2Sel = (Usuario) cbJugador2.getSelectedItem();

        if (codigoPartida.isEmpty() || torneoSel == null || j1Sel == null || j2Sel == null) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos y seleccionar un torneo y dos jugadores.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (j1Sel.equals(j2Sel)) {
            JOptionPane.showMessageDialog(this, "Un jugador no puede enfrentarse a sí mismo.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Partida nuevaPartida = new Partida(codigoPartida, torneoSel, j1Sel, j2Sel);
        datos.getPartidas().add(nuevaPartida);

        actualizarTabla();
        JOptionPane.showMessageDialog(this, "Partida registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        txtCodigoPartida.setText("");
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    
    public static void main(String args[]) {      
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> new GestionCompetencia().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbJugador1;
    private javax.swing.JComboBox<String> cbJugador2;
    private javax.swing.JComboBox<String> cbTorneo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbRegistros;
    private javax.swing.JTextField txtCodigoPartida;
    // End of variables declaration//GEN-END:variables
}
