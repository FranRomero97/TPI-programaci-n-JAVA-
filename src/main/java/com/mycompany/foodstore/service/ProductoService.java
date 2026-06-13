package com.mycompany.foodstore.service;

import com.mycompany.foodstore.dao.ProductoDAO;
import com.mycompany.foodstore.entities.Producto;
import java.util.List;

public class ProductoService {
    private final ProductoDAO productoDAO = new ProductoDAO();

    public void guardarProducto(Producto producto) throws Exception {
        // Reglas de negocio de la cátedra
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser menor a cero.");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock inicial no puede ser menor a cero.");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        
        productoDAO.guardar(producto);
    }

    public void actualizarProducto(Producto producto) throws Exception {
        // Mismas reglas para las modificaciones
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser menor a cero.");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser menor a cero.");
        }
        
        productoDAO.actualizar(producto);
    }

    public void eliminarProducto(Long id) throws Exception {
        productoDAO.eliminarLogico(id);
    }

    public Producto buscarPorId(Long id) throws Exception {
        return productoDAO.buscarPorId(id);
    }

    public List<Producto> listarProductos() throws Exception {
        return productoDAO.listarActivos();
    }
}