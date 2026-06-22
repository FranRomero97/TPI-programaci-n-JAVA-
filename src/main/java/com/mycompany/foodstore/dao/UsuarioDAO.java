package com.mycompany.foodstore.dao;

import com.mycompany.foodstore.config.ConexionDB;
import com.mycompany.foodstore.entities.Usuario;
import com.mycompany.foodstore.entities.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // ---------------- GUARDAR ----------------
    public void guardar(Usuario u) throws Exception {

        String sql = "INSERT INTO usuarios (nombre, apellido, mail, celular, contraseña, rol, eliminado, created_at) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getMail());
            ps.setString(4, u.getCelular());
            ps.setString(5, u.getContraseña());
            ps.setString(6, u.getRol().name());
            ps.setBoolean(7, u.isEliminado());
            ps.setTimestamp(8, Timestamp.valueOf(u.getCreatedAt()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setId(rs.getLong(1));
                }
            }
        }
    }

    // ---------------- LISTAR ----------------
    public List<Usuario> listar() throws Exception {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios WHERE eliminado = false";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getLong("id"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setMail(rs.getString("mail"));
                u.setCelular(rs.getString("celular"));
                u.setContraseña(rs.getString("contraseña"));

                u.setRol(Rol.valueOf(rs.getString("rol")));

                u.setEliminado(rs.getBoolean("eliminado"));
                u.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                lista.add(u);
            }
        }

        return lista;
    }

    // ---------------- BUSCAR POR ID ----------------
    public Usuario buscarPorId(Long id) throws Exception {

        String sql = "SELECT * FROM usuarios WHERE id = ? AND eliminado = false";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Usuario u = new Usuario();

                    u.setId(rs.getLong("id"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApellido(rs.getString("apellido"));
                    u.setMail(rs.getString("mail"));
                    u.setCelular(rs.getString("celular"));
                    u.setContraseña(rs.getString("contraseña"));

                    u.setRol(Rol.valueOf(rs.getString("rol")));

                    u.setEliminado(rs.getBoolean("eliminado"));
                    u.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                    return u;
                }
            }
        }

        return null;
    }

    // ---------------- ACTUALIZAR ----------------
    public void actualizar(Usuario u) throws Exception {

        String sql = "UPDATE usuarios SET nombre=?, apellido=?, mail=?, celular=?, contraseña=?, rol=? "
                   + "WHERE id=? AND eliminado=false";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getMail());
            ps.setString(4, u.getCelular());
            ps.setString(5, u.getContraseña());
            ps.setString(6, u.getRol().name());
            ps.setLong(7, u.getId());

            ps.executeUpdate();
        }
    }

    // ---------------- ELIMINAR LÓGICO ----------------
    public void eliminarLogico(Long id) throws Exception {

        String sql = "UPDATE usuarios SET eliminado = true WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();
        }
    }
}