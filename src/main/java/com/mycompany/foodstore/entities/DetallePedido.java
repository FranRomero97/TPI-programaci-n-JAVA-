package com.mycompany.foodstore.entities;

import com.mycompany.foodstore.entities.Base;
import com.mycompany.foodstore.entities.Producto;

public class DetallePedido extends Base {
    private int cantidad;
    private double subtotal;
    private Producto producto;

    public DetallePedido(int cantidad, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.producto = producto;
        if (producto != null) {
            this.subtotal = producto.getPrecio() * cantidad;
        }
    }

    public DetallePedido() {
        super();
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setSubtotal(double precio, int cantidad) {
        this.subtotal = precio * cantidad;
    }

    public double getSubtotal() {
        return this.subtotal;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getProducto() {
        return this.producto;
    }
}