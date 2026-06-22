package com.mycompany.foodstore.service;

import com.mycompany.foodstore.dao.CategoriaDAO;
import com.mycompany.foodstore.entities.Categoria;
import java.util.List;

public class CategoriaService {

    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void guardarCategoria(Categoria categoria) throws Exception {
        validarCategoria(categoria);
        categoriaDAO.guardar(categoria);
    }

    public void actualizarCategoria(Categoria categoria) throws Exception {
        validarCategoria(categoria);
        categoriaDAO.actualizar(categoria);
    }

    public void eliminarCategoria(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        categoriaDAO.eliminarLogico(id);
    }

    public Categoria buscarPorId(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Categoria c = categoriaDAO.buscarPorId(id);

        if (c == null) {
            throw new IllegalArgumentException("Categoria no encontrada.");
        }

        return c;
    }

    public List<Categoria> listarCategorias() throws Exception {
        return categoriaDAO.listarActivos();
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria nula.");
        }

        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
    }
}