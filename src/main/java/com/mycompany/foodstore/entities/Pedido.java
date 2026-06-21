import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Enum.FormaPago;

public class Pedido extends Base implements Calculable{
    LocalDate fecha;
    Estado estado;
    double total;
    FormaPago formaPago;
    List<DetallePedido> ListaDetallePedidos = new ArrayList<>();

    public Pedido(LocalDate fecha, Estado estado, double total, FormaPago formaPago){
        super();
        setFecha(fecha);
        setEstado(estado);
        setTotal(total);
        setFormaPago(formaPago);
    }

    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }

    public void setEstado(Estado estado){
        this.estado = estado;
    }

    public void setTotal(double total){
        this.total = total;
    }

    public void setFormaPago(FormaPago formaPago){
        this.formaPago = formaPago;
    }

    // AÑADIR DETALLE DE PEDIDO SEGUN PRODUCTO, PRECIO Y CANTIDAD -------
    public void addDetallePedido(int cantidad, double precio, Producto producto){
        DetallePedido nuevoDetallePedido = new DetallePedido();
        nuevoDetallePedido.setCantidad(cantidad);
        nuevoDetallePedido.setProducto(producto);
        nuevoDetallePedido.setSubtotal(precio, cantidad);
        this.ListaDetallePedidos.add(nuevoDetallePedido);
    }

    // VER DETALLE POR ID DE PRODUCTO ------------------------------------
    public DetallePedido findeDetallePedidoByProducto(Producto producto){
        for (DetallePedido detalle: this.ListaDetallePedidos){
            if(detalle.getProducto().getId().equals(producto.getId())){
                return detalle;}}
                System.out.println("No se encontró un detalle de pedido con el producto ingresado."); 
                return null;}
        
    
    // ELIMINAR DETALLE PEDIDO POR ID DE PRODUCTO ---------------
    public void deleteDetallePedidoByProducto(Producto producto){
        for (DetallePedido detalleEliminar : this.ListaDetallePedidos) {
            if(detalleEliminar.getProducto().getId().equals(producto.getId())){   
            detalleEliminar.setEliminado(true);}}
        System.out.println("No se encontro un detalle de pedido con el producto que desea eliminar.");
    }
}
