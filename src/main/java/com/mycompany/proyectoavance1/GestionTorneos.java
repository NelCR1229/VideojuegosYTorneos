// Nicolás Hernández
package com.mycompany.proyectoavance1;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestionTorneos extends javax.swing.JFrame {

    private DatosSistema datos;
    private javax.swing.JFrame ventanaAnterior;

    public GestionTorneos(DatosSistema datos, javax.swing.JFrame ventanaAnterior) {
        this.datos = datos;
        this.ventanaAnterior = ventanaAnterior;
        initComponents();
        setLocationRelativeTo(null);
        cmbVideojuego.setModel(new DefaultComboBoxModel<>(datos.getVideojuegos().toArray(new Videojuego[0])));
        cmbEstado.setModel(new DefaultComboBoxModel<>(EstadoTorneo.values()));
        cargarTabla();
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tblTorneos.getModel();
        modelo.setRowCount(0);
        for (Torneo t : datos.getTorneos()) {
            modelo.addRow(new Object[]{t.getCodigo(), t.getNombre(), t.getVideojuego().getNombre(), t.getCupoMaximo(), t.getEstado()});
        }
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCupo.setText("");
        cmbEstado.setSelectedIndex(0);
    }

    private String validar() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            return "Debe ingresar el código.";
        }
        for (Torneo t : datos.getTorneos()) {
            if (t.getCodigo().equalsIgnoreCase(codigo)) {
                return "Ya existe un torneo con ese código.";
            }
        }
        if (txtNombre.getText().trim().isEmpty()) {
            return "Debe ingresar el nombre.";
        }
        if (cmbVideojuego.getSelectedItem() == null) {
            return "Debe seleccionar un videojuego. Registre uno primero.";
        }
        try {
            if (Integer.parseInt(txtCupo.getText().trim()) <= 0) {
                return "El cupo debe ser mayor que cero.";
            }
        } catch (NumberFormatException e) {
            return "El cupo debe ser un número entero.";
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblVideojuego = new javax.swing.JLabel();
        cmbVideojuego = new javax.swing.JComboBox<>();
        lblCupo = new javax.swing.JLabel();
        txtCupo = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        scrTorneos = new javax.swing.JScrollPane();
        tblTorneos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de torneos");

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 18));
        lblTitulo.setText("Gestión de torneos");

        lblCodigo.setText("Código:");

        lblNombre.setText("Nombre:");

        lblVideojuego.setText("Videojuego:");

        lblCupo.setText("Cupo máximo:");

        lblEstado.setText("Estado:");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        tblTorneos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Videojuego", "Cupo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrTorneos.setViewportView(tblTorneos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTorneos, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(6, 6, 6)
                        .addComponent(btnEditar)
                        .addGap(6, 6, 6)
                        .addComponent(btnEliminar)
                        .addGap(6, 6, 6)
                        .addComponent(btnLimpiar)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCodigo)
                                    .addComponent(lblNombre)
                                    .addComponent(lblVideojuego)
                                    .addComponent(lblCupo)
                                    .addComponent(lblEstado))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbVideojuego, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCupo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVideojuego)
                    .addComponent(cmbVideojuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCupo)
                    .addComponent(txtCupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnRegresar))
                .addGap(12, 12, 12)
                .addComponent(scrTorneos, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String error = validar();
        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }
        Torneo torneo = new Torneo(
                txtCodigo.getText().trim().toUpperCase(),
                txtNombre.getText().trim(),
                (Videojuego) cmbVideojuego.getSelectedItem(),
                Integer.parseInt(txtCupo.getText().trim()),
                (EstadoTorneo) cmbEstado.getSelectedItem());
        datos.getTorneos().add(torneo);
        cargarTabla();
        limpiar();
        JOptionPane.showMessageDialog(this, "Torneo agregado.");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        JOptionPane.showMessageDialog(this, "Disponible en el siguiente avance.");
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        JOptionPane.showMessageDialog(this, "Disponible en el siguiente avance.");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        dispose();
        if (ventanaAnterior != null) {
            ventanaAnterior.setVisible(true);
        }
    }//GEN-LAST:event_btnRegresarActionPerformed

    public static void main(String args[]) {
        DatosSistema datos = new DatosSistema();
        datos.getVideojuegos().add(new Videojuego("V001", "EA Sports FC 25", "Deportes", "PlayStation 5"));
        datos.getVideojuegos().add(new Videojuego("V002", "Street Fighter 6", "Pelea", "PC"));
        java.awt.EventQueue.invokeLater(() -> new GestionTorneos(datos, null).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<EstadoTorneo> cmbEstado;
    private javax.swing.JComboBox<Videojuego> cmbVideojuego;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCupo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVideojuego;
    private javax.swing.JScrollPane scrTorneos;
    private javax.swing.JTable tblTorneos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCupo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
