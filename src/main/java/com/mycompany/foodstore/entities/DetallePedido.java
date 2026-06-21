public class DetallePedido extends Base{
    int cantidad;
    double subtotal;
    Producto producto;

    public DetallePedido(int cantidad, double subtotal){
        super();
        setCantidad(cantidad);
        setSubtotal(subtotal);
        setProducto(producto);
    }

    public DetallePedido(){
        super();
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public int getCantidad(){
        return this.cantidad;
    }

    public void setSubtotal(double precio, int cantidad){
        this.subtotal = precio * cantidad;
    }

    public double getSubtotal(){
        return this.subtotal;
    }

    public void setProducto(Producto producto){
        this.producto = producto;
    }

    public Producto getProducto(){
        return this.producto;
    }

}
