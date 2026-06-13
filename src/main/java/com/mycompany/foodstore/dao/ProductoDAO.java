package com.mycompany.foodstore.dao;

import com.mycompany.foodstore.config.ConexionDB;
import com.mycompany.foodstore.entities.Categoria;
import com.mycompany.foodstore.entities.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO implements IBaseDAO<Producto> {

    @Override
    public void guardar(Producto producto) throws Exception {
        String sql = "INSERT INTO producto (nombre, precio, descripcion, stock, imagen, disponible, categoria_id, eliminado, createdAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getImagen());
            ps.setBoolean(6, producto.isDisponible());
            
            // Pasamos el ID de la categoría asociada
            if (producto.getCategoria() != null) {
                ps.setLong(7, producto.getCategoria().getId());
            } else {
                ps.setNull(7, Types.BIGINT);
            }
            
            ps.setBoolean(8, producto.isEliminado());
            ps.setTimestamp(9, Timestamp.valueOf(producto.getCreatedAt()));
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    producto.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public void actualizar(Producto producto) throws Exception {
        String sql = "UPDATE producto SET nombre = ?, precio = ?, descripcion = ?, stock = ?, imagen = ?, disponible = ?, categoria_id = ? WHERE id = ? AND eliminado = false";
        
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

    @Override
    public void eliminarLogico(Long id) throws Exception {
        String sql = "UPDATE producto SET eliminado = true WHERE id = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Producto buscarPorId(Long id) throws Exception {
        String sql = "SELECT * FROM producto WHERE id = ? AND eliminado = false";
        Producto producto = null;
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setId(rs.getLong("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setStock(rs.getShort("stock"));
                    producto.setImagen(rs.getString("imagen"));
                    producto.setDisponible(rs.getBoolean("disponible"));
                    producto.setEliminado(rs.getBoolean("eliminado"));
                    producto.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                    
                    // Reconstruimos el objeto Categoria básico con el ID para la asociación
                    long catId = rs.getLong("categoria_id");
                    if (!rs.wasNull()) {
                        Categoria cat = new Categoria();
                        cat.setId(catId);
                        producto.setCategoria(cat);
                    }
                }
            }
        }
        return producto;
    }

    @Override
    public List<Producto> listarActivos() throws Exception {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE eliminado = false";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getLong("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setStock(rs.getInt("stock"));
                p.setImagen(rs.getString("imagen"));
                p.setDisponible(rs.getBoolean("disponible"));
                p.setEliminado(rs.getBoolean("eliminado"));
                p.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                
                long catId = rs.getLong("categoria_id");
                if (!rs.wasNull()) {
                    Categoria cat = new Categoria();
                    cat.setId(catId);
                    p.setCategoria(cat);
                }
                
                lista.add(p);
            }
        }
        return lista;
    }
}

