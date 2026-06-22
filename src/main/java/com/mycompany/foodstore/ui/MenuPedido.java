package com.mycompany.foodstore.ui;

import com.mycompany.foodstore.entities.*;
import com.mycompany.foodstore.service.*;

import java.util.List;

public class MenuPedido extends MenuBase {

    private Usuario usuarioLogueado;
    private final PedidoService pedidoService = new PedidoService();
    private final ProductoService productoService = new ProductoService();

    public MenuPedido(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    @Override
    public void iniciar() {

        boolean activo = true;

        while (activo) {

            limpiarConsola();
            System.out.println("\n--- GESTION DE PEDIDOS ---");
            System.out.println("1. Crear | 2. Listar | 3. Pago | 4. Eliminar | 0. Volver");

            try {
                int op = leerOpcion(0, 4);

                switch (op) {
                    case 1 -> crearPedido();
                    case 2 -> listarPedidosPorUsuario();
                    case 3 -> seleccionarMetodoPago();
                    case 4 -> eliminarPedido();
                    case 0 -> activo = false;
                }

            } catch (Exception e) {
                limpiarConsola();
                System.out.println("Error: " + e.getMessage());
                pausar();
            }
        }
    }

    // ---------------- CREAR PEDIDO ----------------
    private void crearPedido() {

        limpiarConsola();
        System.out.println("\n--- CREAR PEDIDO ---");

        try {

            Pedido pedido = new Pedido();

            //  USUARIO LOGUEADO
            pedido.setUsuario(usuarioLogueado);

            boolean agregar = true;

            System.out.println("\n--- PRODUCTOS DISPONIBLES ---");

            List<Producto> productos = productoService.listarProductos();

            for (Producto p : productos) {
            System.out.println(
                "ID: " + p.getId() +
                " | NOMBRE: " + p.getNombre() +
                " | PRECIO: " + p.getPrecio() +
                " | STOCK: " + p.getStock()
            );
                }

            while (agregar) {

                String nombreProducto = pedirTexto("Nombre del producto");

                Producto producto = productoService.buscarPorNombre(nombreProducto);

                if (producto == null) {
                    System.out.println("Producto no encontrado.");
                    continue;
                }

                int cantidad = Integer.parseInt(pedirTexto("Cantidad"));

                pedido.addDetallePedido(cantidad, producto);

                System.out.println("Producto agregado.");

                String r = pedirTexto("Agregar otro? (s/n)");

                if (r.equalsIgnoreCase("n")) {
                    agregar = false;
                }
            }

            pedido.calcularTotal();

            System.out.println("\nTOTAL: " + pedido.getTotal());

            pedidoService.guardarPedido(pedido);

            System.out.println("Pedido creado con ID: " + pedido.getId());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- LISTAR PEDIDOS ----------------
    private void listarPedidosPorUsuario() {

        limpiarConsola();
        System.out.println("\n--- PEDIDOS DEL USUARIO ---");

        try {

            List<Pedido> lista = pedidoService.listarPorUsuario(usuarioLogueado.getId());

            if (lista.isEmpty()) {
                System.out.println("No hay pedidos.");
            } else {

                System.out.println("======================================");

                for (Pedido p : lista) {

                    System.out.println(
                            "ID: " + p.getId() +
                            " | FECHA: " + p.getFecha() +
                            " | ESTADO: " + p.getEstado() +
                            " | TOTAL: " + p.getTotal()
                    );
                }

                System.out.println("======================================");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- METODO DE PAGO ----------------
    private void seleccionarMetodoPago() {

        limpiarConsola();
        System.out.println("\n--- METODO DE PAGO ---");
        System.out.println("1. EFECTIVO | 2. DEBITO | 3. CREDITO | 0. VOLVER");

        try {

            int op = leerOpcion(0, 3);

            FormaPago fp = switch (op) {
                case 1 -> FormaPago.EFECTIVO;
                case 2 -> FormaPago.TARJETA;
                case 3 -> FormaPago.TRANSFERENCIA;
                default -> null;
            };

            if (fp != null) {
                System.out.println("Metodo seleccionado: " + fp);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- ELIMINAR PEDIDO ----------------
    private void eliminarPedido() {

        limpiarConsola();
        System.out.println("\n--- ELIMINAR PEDIDO ---");

        try {

            Long id = leerLong("ID pedido");

            pedidoService.eliminarPedido(id);

            System.out.println("Pedido eliminado.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }
}