package com.mycompany.foodstore.dao;

import com.mycompany.foodstore.config.ConexionDB;
import com.mycompany.foodstore.entities.Categoria;
import com.mycompany.foodstore.entities.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements IBaseDAO<Producto> {

    // ---------------- GUARDAR ----------------
    @Override
    public void guardar(Producto producto) throws Exception {

        String sql = "INSERT INTO productos "
                + "(nombre, precio, descripcion, stock, imagen, disponible, categoria_id, eliminado, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getImagen());
            ps.setBoolean(6, producto.isDisponible());

            if (producto.getCategoria() != null) {
                ps.setLong(7, producto.getCategoria().getId());
            } else {
                ps.setNull(7, Types.BIGINT);
            }

            ps.setBoolean(8, producto.isEliminado());

            if (producto.getCreatedAt() != null) {
                ps.setTimestamp(9, Timestamp.valueOf(producto.getCreatedAt()));
            } else {
                ps.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    producto.setId(rs.getLong(1));
                }
            }
        }
    }

    // ---------------- ACTUALIZAR ----------------
    @Override
    public void actualizar(Producto producto) throws Exception {

        String sql = "UPDATE productos SET "
                + "nombre=?, precio=?, descripcion=?, stock=?, imagen=?, disponible=?, categoria_id=? "
                + "WHERE id=? AND eliminado=false";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getImagen());
            ps.setBoolean(6, producto.isDisponible());

            if (producto.getCategoria() != null) {
                ps.setLong(7, producto.getCategoria().getId());
            } else {
                ps.setNull(7, Types.BIGINT);
            }

            ps.setLong(8, producto.getId());

            ps.executeUpdate();
        }
    }

    // ---------------- ELIMINAR ----------------
    @Override
    public void eliminarLogico(Long id) throws Exception {

        String sql = "UPDATE productos SET eliminado = true WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    // ---------------- BUSCAR POR ID ----------------
    @Override
    public Producto buscarPorId(Long id) throws Exception {

        String sql = "SELECT * FROM productos WHERE id = ? AND eliminado = false";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapProducto(rs);
                }
            }
        }

        return null;
    }

    // ---------------- BUSCAR POR NOMBRE ----------------
    public Producto buscarPorNombre(String nombre) throws Exception {

        String sql = "SELECT * FROM productos WHERE nombre LIKE ? AND eliminado = false";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapProducto(rs);
                }
            }
        }

        return null;
    }

    // ---------------- LISTAR ----------------
    @Override
    public List<Producto> listarActivos() throws Exception {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM productos WHERE eliminado = false";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapProducto(rs));
            }
        }

        return lista;
    }

    // ---------------- MAPPER (IMPORTANTE) ----------------
    private Producto mapProducto(ResultSet rs) throws Exception {

        Producto p = new Producto();

        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getDouble("precio"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setStock(rs.getInt("stock"));
        p.setImagen(rs.getString("imagen"));
        p.setDisponible(rs.getBoolean("disponible"));
        p.setEliminado(rs.getBoolean("eliminado"));

        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) {
            p.setCreatedAt(ts.toLocalDateTime());
        }

        long catId = rs.getLong("categoria_id");

        if (!rs.wasNull()) {
            Categoria cat = new Categoria();
            cat.setId(catId);
            p.setCategoria(cat);
        }

        return p;
    }
}