package com.mycompany.proyectoavance1;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestionTorneos extends javax.swing.JFrame {

    private DatosSistema datos;
    private javax.swing.JFrame ventanaAnterior;
    private Usuario usuario;

    public GestionTorneos(DatosSistema datos, javax.swing.JFrame ventanaAnterior, Usuario usuario) {
        this.datos = datos;
        this.ventanaAnterior = ventanaAnterior;
        this.usuario = usuario;
        initComponents();
        setLocationRelativeTo(null);
        // Prepara un arreglo con los videojuegos de la coleccion.
        Videojuego[] listaVideojuegos = new Videojuego[datos.getVideojuegos().size()];
        for (int i = 0; i < datos.getVideojuegos().size(); i++) {
            listaVideojuegos[i] = datos.getVideojuegos().get(i);
        }
        cmbVideojuego.setModel(new DefaultComboBoxModel<>(listaVideojuegos));
        EstadoTorneo[] estados = {EstadoTorneo.Abierto, EstadoTorneo.EnCurso, EstadoTorneo.Finalizado};
        cmbEstado.setModel(new DefaultComboBoxModel<>(estados));
        cargarTabla();
        // Solo administrador y organizador pueden agregar torneos.
        boolean puedeGestionar = usuario.getRol() == Rol.Administrador
                || usuario.getRol() == Rol.Organizador;

        btnAgregar.setEnabled(puedeGestionar);
        btnEditar.setEnabled(puedeGestionar);
        btnEliminar.setEnabled(puedeGestionar);

        // Al seleccionar una fila de la tabla, sus datos pasan al formulario para poder editarlos.
        tblTorneos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTorneos.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    cargarSeleccionado();
                }
            }
        });
    }

// Reconstruye la tabla a partir de los torneos registrados.
    private void cargarTabla() {

        // Con MySQL activado la tabla se llena directo de la base de datos.
        if (ConexionSQL.USAR_BD) {
            tblTorneos.setModel(Torneo.consultar(this));
            return;
        }

        // Las celdas no se pueden editar a mano, pero las filas si se pueden seleccionar.
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Videojuego");
        modelo.addColumn("Cupo");
        modelo.addColumn("Estado");

        for (Torneo torneo : datos.getTorneos()) {
            modelo.addRow(new Object[]{
                torneo.getCodigo(),
                torneo.getNombre(),
                torneo.getVideojuego().getNombre(),
                torneo.getCupoMaximo(),
                torneo.getEstado()
            });
        }

        tblTorneos.setModel(modelo);
    }

    private void limpiar() {
        tblTorneos.clearSelection();
        txtCodigo.setEnabled(true); // el codigo solo se bloquea mientras se edita un torneo
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCupo.setText("");
        cmbEstado.setSelectedItem(EstadoTorneo.Abierto);
    }

    // Busca un torneo por su codigo. Devuelve null si no existe.
    private Torneo buscarTorneo(String codigo) {
        for (Torneo t : datos.getTorneos()) {
            if (t.getCodigo().equals(codigo)) {
                return t;
            }
        }
        return null;
    }

    // Devuelve el torneo de la fila seleccionada, o null si no hay ninguna.
    private Torneo obtenerSeleccionado() {
        int fila = tblTorneos.getSelectedRow();
        if (fila < 0) {
            return null;
        }
        return buscarTorneo((String) tblTorneos.getValueAt(fila, 0));
    }

    // Pasa los datos del torneo seleccionado al formulario.
    private void cargarSeleccionado() {
        Torneo torneo = obtenerSeleccionado();
        if (torneo == null) {
            return;
        }
        txtCodigo.setText(torneo.getCodigo());
        txtCodigo.setEnabled(false); // al editar se conserva el codigo
        txtNombre.setText(torneo.getNombre());
        cmbVideojuego.setSelectedItem(torneo.getVideojuego());
        txtCupo.setText("" + torneo.getCupoMaximo());
        cmbEstado.setSelectedItem(torneo.getEstado());
    }

    private boolean puedeGestionar() {
        return usuario.getRol() == Rol.Administrador
                || usuario.getRol() == Rol.Organizador;
    }

    // Cuantas personas estan inscritas en el torneo.
    private int contarInscritos(Torneo torneo) {
        int total = 0;
        for (Inscripcion ins : datos.getInscripciones()) {
            if (ins.getTorneo().getCodigo().equals(torneo.getCodigo())) {
                total++;
            }
        }
        return total;
    }

    // Cuantas partidas tiene registradas el torneo.
    private int contarPartidas(Torneo torneo) {
        int total = 0;
        for (Partida partida : datos.getPartidas()) {
            if (partida.getTorneo() != null
                    && partida.getTorneo().getCodigo().equals(torneo.getCodigo())) {
                total++;
            }
        }
        return total;
    }

    // Validaciones para editar: nombre, videojuego existente y cupo positivo que no baje de los inscritos.
    private String validarEdicion(Torneo torneo) {
        if (txtNombre.getText().trim().equals("")) {
            return "Debe ingresar el nombre.";
        }
        Videojuego videojuego = (Videojuego) cmbVideojuego.getSelectedItem();
        if (videojuego == null) {
            return "Debe seleccionar un videojuego.";
        }
        boolean existe = false;
        for (Videojuego v : datos.getVideojuegos()) {
            if (v.getCodigo().equals(videojuego.getCodigo())) {
                existe = true;
            }
        }
        if (!existe) {
            return "El videojuego seleccionado ya no existe.";
        }
        int cupo;
        try {
            cupo = Integer.parseInt(txtCupo.getText().trim());
        } catch (NumberFormatException e) {
            return "El cupo debe ser un número entero.";
        }
        if (cupo <= 0) {
            return "El cupo debe ser mayor que cero.";
        }
        int inscritos = contarInscritos(torneo);
        if (cupo < inscritos) {
            return "El cupo no puede ser menor que la cantidad de inscritos (" + inscritos + ").";
        }
        return null;
    }
    private String validar() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.equals("")) {
            return "Debe ingresar el código.";
        }
        for (Torneo t : datos.getTorneos()) {
            if (t.getCodigo().equalsIgnoreCase(codigo)) {
                return "Ya existe un torneo con ese código.";
            }
        }
        if (txtNombre.getText().trim().equals("")) {
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
    public void volverPrincipal() {
    if (ventanaAnterior != null) {
        ventanaAnterior.setVisible(true);
    }

    dispose();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestión de torneos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                CerrarVentana(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
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
        if (usuario.getRol() != Rol.Administrador
                && usuario.getRol() != Rol.Organizador) {

            JOptionPane.showMessageDialog(this,
                    "Su rol solo permite consultar torneos.");
            return;
        }
        String error = validar();
        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }
        Torneo torneo = new Torneo(
                txtCodigo.getText().trim(),
                txtNombre.getText().trim(),
                (Videojuego) cmbVideojuego.getSelectedItem(),
                Integer.parseInt(txtCupo.getText().trim()),
                (EstadoTorneo) cmbEstado.getSelectedItem());
        String mensajeBD = torneo.agregar();
        if (mensajeBD.startsWith("No se")) {
            JOptionPane.showMessageDialog(this, mensajeBD);
            return;
        }
        datos.getTorneos().add(torneo);
        cargarTabla();
        limpiar();
        JOptionPane.showMessageDialog(this, "Torneo agregado.");
    }//GEN-LAST:event_btnAgregarActionPerformed
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (!puedeGestionar()) {
            JOptionPane.showMessageDialog(this, "Su rol solo permite consultar torneos.");
            return;
        }
        Torneo torneo = obtenerSeleccionado();
        if (torneo == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un torneo de la tabla.");
            return;
        }
        String error = validarEdicion(torneo);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }
        String nombre = txtNombre.getText().trim();
        Videojuego videojuego = (Videojuego) cmbVideojuego.getSelectedItem();
        int cupo = Integer.parseInt(txtCupo.getText().trim());
        EstadoTorneo estado = (EstadoTorneo) cmbEstado.getSelectedItem();
        // Se cambian los datos del MISMO objeto: el codigo y los demas modulos (Competencia) lo siguen viendo.
        String nombreAnt = torneo.getNombre();
        Videojuego videojuegoAnt = torneo.getVideojuego();
        int cupoAnt = torneo.getCupoMaximo();
        EstadoTorneo estadoAnt = torneo.getEstado();
        torneo.setNombre(nombre);
        torneo.setVideojuego(videojuego);
        torneo.setCupoMaximo(cupo);
        torneo.setEstado(estado);
        String mensajeBD = torneo.editar();
        if (mensajeBD.startsWith("No se")) {
            // Fallo la base: se devuelven los datos anteriores para no dejar memoria y BD distintas.
            torneo.setNombre(nombreAnt);
            torneo.setVideojuego(videojuegoAnt);
            torneo.setCupoMaximo(cupoAnt);
            torneo.setEstado(estadoAnt);
            JOptionPane.showMessageDialog(this, mensajeBD);
            return;
        }
        cargarTabla();
        limpiar();
        JOptionPane.showMessageDialog(this, "Torneo actualizado.");
    }//GEN-LAST:event_btnEditarActionPerformed
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (!puedeGestionar()) {
            JOptionPane.showMessageDialog(this, "Su rol solo permite consultar torneos.");
            return;
        }
        Torneo torneo = obtenerSeleccionado();
        if (torneo == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un torneo de la tabla.");
            return;
        }
        // Un torneo con inscripciones o partidas no se puede borrar.
        int inscritos = contarInscritos(torneo);
        int partidas = contarPartidas(torneo);
        if (inscritos > 0 || partidas > 0) {
            JOptionPane.showMessageDialog(this, "No se puede eliminar el torneo porque tiene "
                    + inscritos + " inscripción(es) y " + partidas + " partida(s).");
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Desea eliminar el torneo " + torneo.getCodigo() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }
        String mensajeBD = torneo.eliminar();
        if (mensajeBD.startsWith("No se")) {
            JOptionPane.showMessageDialog(this, mensajeBD);
            return;
        }
        datos.getTorneos().remove(torneo);
        cargarTabla();
        limpiar();
        JOptionPane.showMessageDialog(this, "Torneo eliminado.");
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        volverPrincipal();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void CerrarVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_CerrarVentana
    volverPrincipal();
    }//GEN-LAST:event_CerrarVentana

    public static void main(String args[]) {
        DatosSistema datos = new DatosSistema();
        datos.getVideojuegos().add(new Videojuego("V001", "EA Sports FC 25", "Deportes", "PlayStation 5"));
        datos.getVideojuegos().add(new Videojuego("V002", "Street Fighter 6", "Pelea", "PC"));
        // Usuario exclusivo para ejecutar este formulario como prueba.
        Usuario usuarioPrueba = new Usuario(
                "UT01",
                "Organizador de prueba",
                "organizador",
                "clavePrueba",
                Rol.Organizador
        );

        java.awt.EventQueue.invokeLater(() -> new GestionTorneos(datos, null, usuarioPrueba).setVisible(true)
        );
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
