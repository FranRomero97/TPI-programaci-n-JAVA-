/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstore.ui;

public class MenuProducto extends MenuBase {
    @Override
    public void iniciar() {
        boolean activo = true;
        while (activo) {
            limpiarConsola();
            System.out.println("\n--- GESTION DE PRODUCTOS ---");
            System.out.println("1. Listar | 2. Crear | 3. Modificar | 4. Stock | 5. Eliminar | 0. Volver");
            try {
                int op = leerOpcion(0, 5);
                switch (op) {
                    case 1 -> listarProductos();
                    case 2 -> { limpiarConsola(); System.out.println("Creando producto..."); pausar(); }
                    case 3 -> editarProducto();
                    case 4 -> gestionarStock();
                    case 5 -> { limpiarConsola(); System.out.println("Eliminando producto..."); pausar(); }
                    case 0 -> activo = false;
                }
            } catch (InvalidInputException e) { limpiarConsola(); System.out.println("Error: " + e.getMessage()); pausar(); }
        }
    }

    private void listarProductos() {
        limpiarConsola();
        System.out.println("\n--- LISTAR PRODUCTOS ---");
        System.out.println("1. Ver todos | 2. Buscar por nombre | 0. Volver");
        try {
            int subOp = leerOpcion(0, 2);
            if (subOp == 1) {
                // --- INSTRUCCION PARA COMPAÑEROS ---
                // 1. Llamar a: productoService.listarTodo();
                // 2. Iterar la lista obtenida e imprimir cada objeto.
                System.out.println("Mostrando todos los productos...");
                pausar();
            } else if (subOp == 2) {
                String nombre = pedirTexto("Ingrese el nombre del producto");
                // --- INSTRUCCION PARA COMPAÑEROS ---
                // 1. Llamar a: productoService.buscarPorNombre(nombre);
                // 2. Iterar la lista obtenida e imprimir cada resultado.
                System.out.println("Buscando productos con nombre: " + nombre);
                pausar();
            }
        } catch (InvalidInputException e) {
            limpiarConsola();
            System.out.println("Error: " + e.getMessage());
            pausar();
        }
    }

    private void editarProducto() {
        boolean editando = true;
        while (editando) {
            limpiarConsola();
            System.out.println("\n--- EDITAR PRODUCTO ---");
            System.out.println("1. Nombre | 2. Precio | 3. Categoria | 0. Guardar");
            try {
                int op = leerOpcion(0, 3);
                if (op == 0) editando = false;
                else { limpiarConsola(); System.out.println("Editando atributo " + op + "..."); pausar(); }
            } catch (InvalidInputException e) { limpiarConsola(); System.out.println("Error: " + e.getMessage()); pausar(); }
        }
    }

    private void gestionarStock() {
        limpiarConsola();
        System.out.println("\n--- GESTION DE STOCK ---");
        System.out.println("1. Sumar | 2. Restar | 0. Volver");
        try {
            int op = leerOpcion(0, 2);
            if (op != 0) { limpiarConsola(); System.out.println("Accion " + op + " seleccionada..."); pausar(); }
        } catch (InvalidInputException e) { limpiarConsola(); System.out.println("Error: " + e.getMessage()); pausar(); }
    }
}