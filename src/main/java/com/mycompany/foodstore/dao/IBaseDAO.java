package com.mycompany.foodstore.dao;

import java.util.List;

public interface IBaseDAO<T> {
    void guardar(T entidad) throws Exception;
    void actualizar(T entidad) throws Exception;
    void eliminarLogico(Long id) throws Exception;
    T buscarPorId(Long id) throws Exception;
    List<T> listarActivos() throws Exception;
}
