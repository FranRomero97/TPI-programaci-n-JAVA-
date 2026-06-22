package com.mycompany.foodstore.service;

import com.mycompany.foodstore.dao.PedidoDAO;
import com.mycompany.foodstore.entities.Pedido;

import java.util.List;

public class PedidoService {

    private final PedidoDAO pedidoDAO = new PedidoDAO();

    public void guardarPedido(Pedido p) throws Exception {

        if (p.getListaDetallePedidos().isEmpty()) {
            throw new IllegalArgumentException("El pedido no tiene productos");
        }

        p.calcularTotal();
        pedidoDAO.guardar(p);
    }

    public List<Pedido> listarPedidos() throws Exception {
        return pedidoDAO.listar();
    }

    public void eliminarPedido(Long id) throws Exception {
        pedidoDAO.eliminarLogico(id);
    }
}