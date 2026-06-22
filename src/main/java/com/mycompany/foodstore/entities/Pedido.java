package com.mycompany.foodstore.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends Base implements Calculable {

    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;
    private Usuario usuario;

    private List<DetallePedido> listaDetallePedidos;

    // ---------------- CONSTRUCTOR ----------------

    public Pedido() {
        super();
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.listaDetallePedidos = new ArrayList<>();
    }

    public Pedido(LocalDate fecha, Estado estado, double total, FormaPago formaPago) {
        super();
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.formaPago = formaPago;
        this.listaDetallePedidos = new ArrayList<>();
    }

    // ---------------- GETTERS Y SETTERS ----------------

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetallePedido> getListaDetallePedidos() {
        return listaDetallePedidos;
    }

    // ---------------- LÓGICA ----------------

    @Override
    public void calcularTotal() {
        double suma = 0;

        for (DetallePedido detalle : listaDetallePedidos) {
            if (!detalle.isEliminado()) {
                suma += detalle.getSubtotal();
            }
        }

        this.total = suma;
    }

    public void addDetallePedido(int cantidad, Producto producto) {
        if (producto != null) {
            DetallePedido nuevo = new DetallePedido(cantidad, producto);
            this.listaDetallePedidos.add(nuevo);
            calcularTotal();
        }
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto) {

        for (DetallePedido detalle : this.listaDetallePedidos) {
            if (detalle.getProducto().getId().equals(producto.getId())) {
                return detalle;
            }
        }

        System.out.println("No se encontró el producto en el pedido.");
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {

        for (DetallePedido detalle : this.listaDetallePedidos) {
            if (detalle.getProducto().getId().equals(producto.getId())) {
                detalle.setEliminado(true);
            }
        }

        calcularTotal();
    }
}