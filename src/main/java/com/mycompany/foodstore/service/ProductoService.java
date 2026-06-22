package com.mycompany.foodstore.service;

import com.mycompany.foodstore.dao.CategoriaDAO;
import com.mycompany.foodstore.dao.ProductoDAO;
import com.mycompany.foodstore.entities.Categoria;
import com.mycompany.foodstore.entities.Producto;

import java.util.List;

public class ProductoService {

    private final ProductoDAO productoDAO = new ProductoDAO();

  
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();


    public Producto buscarPorNombre(String nombre) throws Exception {
    return productoDAO.buscarPorNombre(nombre);
}

    public void guardarProducto(Producto producto) throws Exception {

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

    //  VALIDACIÓN DE CATEGORÍA
    public Categoria validarCategoria(Long id) throws Exception {

        if (id == 0) return null;

        Categoria cat = categoriaDAO.buscarPorId(id);

        if (cat == null) {
            throw new IllegalArgumentException("La categoría no existe");
        }

        return cat;
    }
}