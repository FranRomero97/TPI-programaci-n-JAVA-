package com.mycompany.foodstore.service;

import com.mycompany.foodstore.dao.UsuarioDAO;
import com.mycompany.foodstore.entities.Usuario;

import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    

    // ---------------- GUARDAR ----------------
    public void guardarUsuario(Usuario u) throws Exception {

        if (u.getNombre() == null || u.getNombre().trim().isEmpty())
            throw new IllegalArgumentException("Nombre obligatorio");

        if (u.getApellido() == null || u.getApellido().trim().isEmpty())
            throw new IllegalArgumentException("Apellido obligatorio");

        if (u.getMail() == null || u.getMail().trim().isEmpty())
            throw new IllegalArgumentException("Mail obligatorio");

        if (u.getContraseña() == null || u.getContraseña().trim().isEmpty())
            throw new IllegalArgumentException("Contraseña obligatoria");

        usuarioDAO.guardar(u);
    }

    // ---------------- LISTAR ----------------
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioDAO.listar();
    }

    // ---------------- BUSCAR ----------------
    public Usuario buscarPorId(Long id) throws Exception {
        return usuarioDAO.buscarPorId(id);
    }

    // ---------------- ACTUALIZAR ----------------
    public void actualizarUsuario(Usuario u) throws Exception {
        usuarioDAO.actualizar(u);
    }

    // ---------------- ELIMINAR ----------------
    public void eliminarUsuario(Long id) throws Exception {
        usuarioDAO.eliminarLogico(id);
    }

    // ---------------- LOGIN ----------------
    public Usuario login(Long id, String contraseña) throws Exception {

    Usuario usuario = usuarioDAO.buscarPorId(id);

    if (usuario == null) {
        return null;
    }

    if (!usuario.getContraseña().equals(contraseña)) {
        return null;
    }

    return usuario;
}
}