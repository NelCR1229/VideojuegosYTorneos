package com.mycompany.proyectoavance1;

import java.util.ArrayList;

/*
 * Mantiene los datos en memoria que compartirán las ventanas.
 */
public class DatosSistema {

    private ArrayList<Usuario> usuarios;

    public DatosSistema() {
        usuarios = new ArrayList<>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
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
    public boolean registrarUsuario(String codigo, String nombre, String nombreUsuario, String clave) {

        Usuario existente = buscarUsuario(nombreUsuario);

        if (existente != null) {
            return false;
        }
        Usuario codigoExistente = buscarUsuarioPorCodigo(codigo);

        if (codigoExistente != null) {
            return false;
        }
        Usuario nuevoUsuario = new Usuario(
                codigo,
                nombre,
                nombreUsuario,
                clave,
                Rol.Participante
        );

        usuarios.add(nuevoUsuario);

        return true;
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

}
