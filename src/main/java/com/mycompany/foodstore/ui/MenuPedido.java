package com.mycompany.foodstore.ui;

import com.mycompany.foodstore.entities.*;
import com.mycompany.foodstore.service.*;

import java.util.List;

public class MenuPedido extends MenuBase {

    private final PedidoService pedidoService = new PedidoService();
    private final UsuarioService usuarioService = new UsuarioService();
    private final ProductoService productoService = new ProductoService();

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
                    case 2 -> listarPedidos();
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

            Long userId = leerLong("ID usuario");

            Usuario usuario = usuarioService.buscarPorId(userId);

            if (usuario == null) {
                System.out.println("Usuario no encontrado.");
                pausar();
                return;
            }

            Pedido pedido = new Pedido();
            pedido.setUsuario(usuario);

            boolean agregar = true;

            while (agregar) {

                Long prodId = leerLong("ID producto");

                Producto producto = productoService.buscarPorId(prodId);

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
    private void listarPedidos() {

        limpiarConsola();
        System.out.println("\n--- LISTAR PEDIDOS ---");

        try {

            List<Pedido> lista = pedidoService.listarPedidos();

            if (lista.isEmpty()) {
                System.out.println("No hay pedidos.");
            } else {

                System.out.println("====================================================");

                for (Pedido p : lista) {

                    System.out.println(
                            "ID: " + p.getId() +
                            " | FECHA: " + p.getFecha() +
                            " | ESTADO: " + p.getEstado() +
                            " | TOTAL: " + p.getTotal() +
                            " | USUARIO: " + (p.getUsuario() != null ? p.getUsuario().getId() : "N/A")
                    );
                }

                System.out.println("====================================================");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- PAGO ----------------
    private void seleccionarMetodoPago() {

        limpiarConsola();
        System.out.println("\n--- METODO DE PAGO ---");
        System.out.println("1. EFECTIVO | 2. DEBITO | 3. CREDITO | 0. VOLVER");

        try {

            int op = leerOpcion(0, 3);

            if (op != 0) {

                System.out.println("Metodo seleccionado: " + op);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- ELIMINAR ----------------
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