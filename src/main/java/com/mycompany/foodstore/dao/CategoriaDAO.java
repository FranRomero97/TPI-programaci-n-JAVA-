package com.mycompany.foodstore.dao;

import com.mycompany.foodstore.config.ConexionDB;
import com.mycompany.foodstore.entities.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements IBaseDAO<Categoria> {

    @Override
    public void guardar(Categoria categoria) throws Exception {
      
        String sql = "INSERT INTO categorias (nombre, descripcion, eliminado, created_at) VALUES (?, ?, ?, ?)";
        
        // El Try-with-resources asegura que la conexión y el statement se cierren solos al terminar
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setBoolean(3, categoria.isEliminado());
            ps.setTimestamp(4, Timestamp.valueOf(categoria.getCreatedAt()));
            
            ps.executeUpdate();
            
            // Esto sirve para recuperar el ID que autogeneró MySQL y asignárselo a nuestro objeto Java
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public void actualizar(Categoria categoria) throws Exception {
       
        String sql = "UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ? AND eliminado = false";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setLong(3, categoria.getId());
            
            ps.executeUpdate();
        }
    }

    
    @Override
    public void eliminarLogico(Long id) throws Exception {
        // En lugar de borrar el registro, lo marcamos como eliminado
  
        String sql = "UPDATE categorias SET eliminado = true WHERE id = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Categoria buscarPorId(Long id) throws Exception {

    String sql = "SELECT * FROM categorias WHERE id = ? AND eliminado = false";

    try (Connection con = ConexionDB.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setLong(1, id);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getLong("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                return c;
            }
        }
    }

    return null;
}

    @Override
    public List<Categoria> listarActivos() throws Exception {
        List<Categoria> lista = new ArrayList<>();
     
        String sql = "SELECT * FROM categorias WHERE eliminado = false";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getLong("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setEliminado(rs.getBoolean("eliminado"));
                c.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                lista.add(c);
            }
        }
        return lista;
    }
}