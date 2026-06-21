package com.mycompany.foodstore.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.foodstore.entities.Base;
import com.mycompany.foodstore.entities.Producto;

public class Pedido extends Base implements Calculable {
    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;
    private List<DetallePedido> ListaDetallePedidos = new ArrayList<>();

    public Pedido() {
        super();
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
    }

    public Pedido(LocalDate fecha, Estado estado, double total, FormaPago formaPago) {
        super();
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.formaPago = formaPago;
    }

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

    public List<DetallePedido> getListaDetallePedidos() {
        return ListaDetallePedidos;
    }

    @Override
    public void calcularTotal() {
        double suma = 0;
        for (DetallePedido detalle : ListaDetallePedidos) {
            if (!detalle.isEliminado()) {
                suma += detalle.getSubtotal();
            }
        }
        this.total = suma;
    }

    public void addDetallePedido(int cantidad, Producto producto) {
        if (producto != null) {
            DetallePedido nuevoDetallePedido = new DetallePedido(cantidad, producto);
            this.ListaDetallePedidos.add(nuevoDetallePedido);
            calcularTotal();
        }
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalle : this.ListaDetallePedidos) {
            if (detalle.getProducto().getId().equals(producto.getId())) {
                return detalle;
            }
        }
        System.out.println("No se encontró un detalle de pedido con el producto ingresado."); 
        return null;
    }
        
    public void deleteDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalleEliminar : this.ListaDetallePedidos) {
            if (detalleEliminar.getProducto().getId().equals(producto.getId())) {   
                detalleEliminar.setEliminado(true);
            }
        }
        calcularTotal();
    }
}