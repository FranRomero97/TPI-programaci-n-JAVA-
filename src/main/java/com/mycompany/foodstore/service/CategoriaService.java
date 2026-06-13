package com.mycompany.foodstore.service;

import com.mycompany.foodstore.dao.CategoriaDAO;
import com.mycompany.foodstore.entities.Categoria;
import java.util.List;

public class CategoriaService {
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void guardarCategoria(Categoria categoria) throws Exception {
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
        categoriaDAO.guardar(categoria);
    }

    public void actualizarCategoria(Categoria categoria) throws Exception {
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
        categoriaDAO.actualizar(categoria);
    }

    public void eliminarCategoria(Long id) throws Exception {
        categoriaDAO.eliminarLogico(id);
    }

    public Categoria buscarPorId(Long id) throws Exception {
        return categoriaDAO.buscarPorId(id);
    }

    public List<Categoria> listarCategorias() throws Exception {
        return categoriaDAO.listarActivos();
    }
}