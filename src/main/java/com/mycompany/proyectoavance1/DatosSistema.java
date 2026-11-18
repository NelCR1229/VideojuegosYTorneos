package com.mycompany.proyectoavance1;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * Mantiene los datos en memoria que compartirán las ventanas.
 */
public class DatosSistema {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Videojuego> videojuegos;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Torneo> torneos;
    private ArrayList<Partida> partidas;

    public DatosSistema() {
        usuarios = new ArrayList<>();
        torneos = new ArrayList<>();
        inscripciones = new ArrayList<>();
        partidas = new ArrayList<>();
        videojuegos = new ArrayList<>();
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public ArrayList<Videojuego> getVideojuegos() {
        return videojuegos;
    }
    public ArrayList<Torneo> getTorneos() {
        return torneos;
    }
    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }
    public ArrayList<Partida> getPartidas() {
        return partidas;
    }
    // Busca por nombre de usuario, devuelve el objeto encontrado o null si no existe.
    public Usuario buscarUsuario(String nombreUsuario) {
        Usuario encontrado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(nombreUsuario)) {
                encontrado = usuario;
                break;
            }
        }

        return encontrado;
    }
    //Busca un usuario por su codigo. Devuelve null si no existe.
    public Usuario buscarUsuarioPorCodigo(String codigo) {
        Usuario encontrado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo().equals(codigo)) {
                encontrado = usuario;
                break;
            }
        }
        return encontrado;
    }
    // Registra un participante si su nombre de usuario no existe .Devuelve true si lo agrega o false si está repetido.
    // Guarda un participante en MySQL y despues en la coleccion.
    public boolean registrarUsuario(String codigo, String nombre,String nombreUsuario, String clave) throws Exception {
        if (buscarUsuario(nombreUsuario) != null) {
            return false;
        }
        if (buscarUsuarioPorCodigo(codigo) != null) {
            return false;
        }
        Usuario nuevoUsuario = new Usuario(codigo, nombre, nombreUsuario, clave, Rol.Participante);
        ConexionSQL conexion = new ConexionSQL();
        try {
            String sql = "INSERT INTO usuarios "
                    + "(codigo, nombre, usuario, clave, rol) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.conectarSQL().prepareStatement(sql);
            ps.setString(1, nuevoUsuario.getCodigo());
            ps.setString(2, nuevoUsuario.getNombre());
            ps.setString(3, nuevoUsuario.getUsuario());
            ps.setString(4, nuevoUsuario.getClave());
            ps.setString(5, nuevoUsuario.getRol().toString());
            ps.executeUpdate();
            usuarios.add(nuevoUsuario);
            return true;

        } finally {
            conexion.desconectarSQL();
        }
    }
    //Comprueba el nombre de usuario y la clave. * Devuelve el usuario autenticado o null si los datos no coinciden.
    public Usuario iniciarSesion(String nombreUsuario, String clave) {
        Usuario encontrado = buscarUsuario(nombreUsuario);
        if (encontrado != null) {
            if (encontrado.getClave().equals(clave)) {
                return encontrado;
            }
        }

        return null;
    }
    // Carga los usuarios guardados en MySQL.
    public void cargarUsuariosDesdeBD() throws Exception {
        ConexionSQL conexion = new ConexionSQL();
        ArrayList<Usuario> usuariosBD = new ArrayList<>();
        try {
            String sql = "SELECT codigo, nombre, usuario, clave, rol "+ "FROM usuarios";
            PreparedStatement ps = conexion.conectarSQL().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuarioBD = new Usuario(rs.getString("codigo"),rs.getString("nombre"),rs.getString("usuario"),rs.getString("clave"),Rol.valueOf(rs.getString("rol")));
                usuariosBD.add(usuarioBD);
            }
            // Sustituye la coleccion solo si termino correctamente la lectura.
            usuarios = usuariosBD;
        } finally {
            conexion.desconectarSQL();
        }
    }
    // Carga los videojuegos guardados en MySQL.
    public void cargarVideojuegosDesdeBD() throws Exception {
        ConexionSQL conexion = new ConexionSQL();
        ArrayList<Videojuego> videojuegosBD = new ArrayList<>();
        try {
            String sql = "SELECT codigo, nombre, genero, plataforma "+ "FROM videojuegos";
            PreparedStatement ps = conexion.conectarSQL().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Videojuego videojuegoBD = new Videojuego(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getString("plataforma")
                );
                videojuegosBD.add(videojuegoBD);
            }
            videojuegos = videojuegosBD;
        } finally {
            conexion.desconectarSQL();
        }
    }

}
