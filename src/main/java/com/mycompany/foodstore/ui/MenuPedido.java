/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstore.ui;

public class MenuPedido extends MenuBase {
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
                    case 1 -> { limpiarConsola(); System.out.println("Creando pedido..."); pausar(); }
                    case 2 -> listarPedidos();
                    case 3 -> seleccionarMetodoPago();
                    case 4 -> { limpiarConsola(); System.out.println("Eliminando pedido..."); pausar(); }
                    case 0 -> activo = false;
                }
            } catch (InvalidInputException e) { limpiarConsola(); System.out.println("Error: " + e.getMessage()); pausar(); }
        }
    }

    private void listarPedidos() {
        limpiarConsola();
        System.out.println("\n--- LISTAR PEDIDOS ---");
        System.out.println("1. Ver todos | 2. Buscar por ID/Cliente | 0. Volver");
        try {
            int subOp = leerOpcion(0, 2);
            if (subOp == 1) {
                // --- INSTRUCCION PARA COMPAÑEROS ---
                // 1. Llamar a: pedidoService.listarTodo();
                // 2. Iterar la lista obtenida e imprimir cada pedido.
                System.out.println("Mostrando todos los pedidos...");
                pausar();
            } else if (subOp == 2) {
                String criterio = pedirTexto("Ingrese ID o nombre del cliente");
                // --- INSTRUCCION PARA COMPAÑEROS ---
                // 1. Llamar a: pedidoService.buscarPorCriterio(criterio);
                // 2. Iterar la lista obtenida e imprimir cada resultado.
                System.out.println("Buscando pedidos con: " + criterio);
                pausar();
            }
        } catch (InvalidInputException e) {
            limpiarConsola();
            System.out.println("Error: " + e.getMessage());
            pausar();
        }
    }

    private void seleccionarMetodoPago() {
        limpiarConsola();
        System.out.println("\n--- METODO DE PAGO ---");
        System.out.println("1. Efectivo | 2. Debito | 3. Credito | 0. Volver");
        try {
            int op = leerOpcion(0, 3);
            if (op != 0) { 
                limpiarConsola(); 
                System.out.println("Metodo " + op + " seleccionado."); 
                pausar(); 
            }
        } catch (InvalidInputException e) { limpiarConsola(); System.out.println("Error: " + e.getMessage()); pausar(); }
    }
}