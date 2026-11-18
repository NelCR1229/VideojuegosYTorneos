package com.mycompany.proyectoavance1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class GestionVideojuegos extends javax.swing.JFrame {

    private Principal principal;
    private DatosSistema datos;
    private Usuario usuario;

    
    private Videojuego videojuegoSeleccionado;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GestionVideojuegos.class.getName());

    public GestionVideojuegos(Principal principal, DatosSistema datos, Usuario usuario) {
        initComponents();
        this.principal = principal;
        this.datos = datos;
        this.usuario = usuario;
        setTitle("Gestión de Videojuegos");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                volverPrincipal();
            }
        });

        if (usuario.getRol() != Rol.Administrador) {
            txtCodigo.setEnabled(false);
            txtNombre.setEnabled(false);
            txtGenero.setEnabled(false);
            txtPlataforma.setEnabled(false);
            btnAgregar.setEnabled(false);
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }

        tbVideojuegos.setRowHeight(25);
        tbVideojuegos.getTableHeader().setReorderingAllowed(false);
        tbVideojuegos.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 18));
        tbVideojuegos.getTableHeader().setForeground(new Color(102, 153, 0));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tbVideojuegos
                .getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        cargarTabla();
        limpiar();
        setLocationRelativeTo(null);
    }

    public void volverPrincipal() {
        principal.setVisible(true);
        dispose();
    }

    public void limpiar() {
        videojuegoSeleccionado = null;
        tbVideojuegos.clearSelection();

        boolean esAdministrador = usuario != null && usuario.getRol() == Rol.Administrador;
        txtCodigo.setEnabled(esAdministrador);
        txtCodigo.setEditable(esAdministrador);
        txtCodigo.setText("");
        txtNombre.setText("");
        txtGenero.setText("");
        txtPlataforma.setText("");

        if (esAdministrador) {
            btnAgregar.setEnabled(true);
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }

        txtCodigo.requestFocusInWindow();
    }

    
    private void cargarTabla() {
        if (ConexionSQL.USAR_BD) {
            tbVideojuegos.setModel(Videojuego.consultar(this));
        } else {
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

            for (Videojuego videojuego : datos.getVideojuegos()) {
                modelo.addRow(new Object[]{
                    videojuego.getCodigo(),
                    videojuego.getNombre(),
                    videojuego.getGenero(),
                    videojuego.getPlataforma()
                });
            }

            tbVideojuegos.setModel(modelo);
        }

        tbVideojuegos.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbVideojuegos.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbVideojuegos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbVideojuegos.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbVideojuegos.clearSelection();
    }

    private Videojuego buscarVideojuego(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Videojuego videojuego : datos.getVideojuegos()) {
            if (videojuego.getCodigo() != null
                    && videojuego.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return videojuego;
            }
        }
        return null;
    }

    private String texto(int fila, int columna) {
        Object valor = tbVideojuegos.getValueAt(fila, columna);
        return valor == null ? "" : valor.toString();
    }

    private String validar() {
        if (txtCodigo.getText().trim().isEmpty()
                || txtNombre.getText().trim().isEmpty()
                || txtGenero.getText().trim().isEmpty()
                || txtPlataforma.getText().trim().isEmpty()) {
            return "Todos los campos son obligatorios.";
        }
        return null;
    }

    
    private List<Torneo> torneosEnMemoria(String codigo) {
        List<Torneo> asociados = new ArrayList<>();
        for (Torneo torneo : datos.getTorneos()) {
            if (torneo.getVideojuego() != null
                    && torneo.getVideojuego().getCodigo() != null
                    && torneo.getVideojuego().getCodigo().equalsIgnoreCase(codigo)) {
                asociados.add(torneo);
            }
        }
        return asociados;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtGenero = new javax.swing.JTextField();
        txtPlataforma = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVideojuegos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Código:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Género:");

        jLabel4.setText("Plataforma:");

        txtCodigo.addActionListener(this::txtCodigoActionPerformed);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnVolver.setText("Volver");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        tbVideojuegos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Género", "Plataforma"
            }
        ));
        tbVideojuegos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbVideojuegosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbVideojuegos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                    .addComponent(txtCodigo)
                                    .addComponent(txtGenero)
                                    .addComponent(txtPlataforma))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addGap(40, 40, 40)
                                .addComponent(btnEditar)
                                .addGap(49, 49, 49)
                                .addComponent(btnEliminar)
                                .addGap(46, 46, 46)))
                        .addComponent(btnLimpiar)
                        .addGap(46, 46, 46)
                        .addComponent(btnVolver)
                        .addGap(22, 22, 22)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnVolver))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed

    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (usuario.getRol() != Rol.Administrador) {
            JOptionPane.showMessageDialog(this, "Su rol solo permite consultar videojuegos.");
            return;
        }

        String error = validar();
        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }

        String codigo = txtCodigo.getText().trim();

        if (buscarVideojuego(codigo) != null) {
            JOptionPane.showMessageDialog(this, "Ya existe un videojuego con ese código.");
            return;
        }

        Videojuego nuevo = new Videojuego(
                codigo,
                txtNombre.getText().trim(),
                txtGenero.getText().trim(),
                txtPlataforma.getText().trim());

        String mensaje = nuevo.agregar();
        if (mensaje.startsWith("No se")) {
            JOptionPane.showMessageDialog(this, mensaje);
            return;
        }

        datos.getVideojuegos().add(nuevo);
        cargarTabla();
        limpiar();
        JOptionPane.showMessageDialog(this, mensaje);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        volverPrincipal();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (usuario.getRol() != Rol.Administrador) {
            JOptionPane.showMessageDialog(this, "Su rol solo permite consultar videojuegos.");
            return;
        }

        if (videojuegoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un videojuego de la tabla para editarlo.");
            return;
        }

        String error = validar();
        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }

        String codigoOriginal = videojuegoSeleccionado.getCodigo();
        if (!codigoOriginal.equalsIgnoreCase(txtCodigo.getText().trim())) {
            JOptionPane.showMessageDialog(this,
                    "El código no se puede modificar. Se conserva: " + codigoOriginal);
            txtCodigo.setText(codigoOriginal);
            return;
        }

        Videojuego cambios = new Videojuego(
                codigoOriginal,
                txtNombre.getText().trim(),
                txtGenero.getText().trim(),
                txtPlataforma.getText().trim());

        String mensaje = cambios.editar();
        if (mensaje.startsWith("No se")) {
            JOptionPane.showMessageDialog(this, mensaje);
            return;
        }

        
        videojuegoSeleccionado.setNombre(cambios.getNombre());
        videojuegoSeleccionado.setGenero(cambios.getGenero());
        videojuegoSeleccionado.setPlataforma(cambios.getPlataforma());

        cargarTabla();
        limpiar();
        JOptionPane.showMessageDialog(this, mensaje);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (usuario.getRol() != Rol.Administrador) {
            JOptionPane.showMessageDialog(this, "Su rol solo permite consultar videojuegos.");
            return;
        }

        if (videojuegoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un videojuego de la tabla para eliminarlo.");
            return;
        }

        String codigo = videojuegoSeleccionado.getCodigo();

        List<Torneo> asociados = torneosEnMemoria(codigo);
        if (!asociados.isEmpty()) {
            StringBuilder detalle = new StringBuilder();
            for (Torneo torneo : asociados) {
                detalle.append("\n- ").append(torneo.getCodigo())
                        .append(": ").append(torneo.getNombre());
            }
            JOptionPane.showMessageDialog(this,
                    "No se puede eliminar el videojuego " + codigo + ".\n"
                    + "Está asociado a " + asociados.size() + " torneo(s):" + detalle);
            return;
        }

        if (Videojuego.torneosAsociados(codigo) > 0) {
            JOptionPane.showMessageDialog(this,
                    "No se puede eliminar el videojuego " + codigo + ".\n"
                    + "La base de datos registra torneos que lo utilizan.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Desea eliminar el videojuego " + codigo + " - "
                + videojuegoSeleccionado.getNombre() + "?",
                "ELIMINAR VIDEOJUEGO",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        String mensaje = new Videojuego(codigo).eliminar();
        if (mensaje.startsWith("No se")) {
            JOptionPane.showMessageDialog(this, mensaje);
            return;
        }

        datos.getVideojuegos().removeIf(videojuego ->
                videojuego.getCodigo() != null
                && videojuego.getCodigo().equalsIgnoreCase(codigo));

        cargarTabla();
        limpiar();
        JOptionPane.showMessageDialog(this, mensaje);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tbVideojuegosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVideojuegosMousePressed
        int fila = tbVideojuegos.getSelectedRow();
        if (fila < 0) {
            return;
        }

        String codigo = texto(fila, 0);
        if (codigo.isEmpty()) {
            return;
        }

        Videojuego encontrado = buscarVideojuego(codigo);
        if (encontrado == null) {

            encontrado = new Videojuego(codigo, texto(fila, 1), texto(fila, 2), texto(fila, 3));
            datos.getVideojuegos().add(encontrado);
        }

        videojuegoSeleccionado = encontrado;

        txtCodigo.setText(encontrado.getCodigo());
        txtNombre.setText(encontrado.getNombre());
        txtGenero.setText(encontrado.getGenero());
        txtPlataforma.setText(encontrado.getPlataforma());

        txtCodigo.setEnabled(false);
        txtCodigo.setEditable(false);

        if (usuario.getRol() == Rol.Administrador) {
            btnAgregar.setEnabled(false);
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tbVideojuegosMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbVideojuegos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPlataforma;
    // End of variables declaration//GEN-END:variables
}
