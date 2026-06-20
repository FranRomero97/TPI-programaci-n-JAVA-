/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstore.ui;

public class MenuCategoria extends MenuBase {
    @Override
    public void iniciar() {
        boolean activo = true;
        while (activo) {
            limpiarConsola();
            System.out.println("\n--- GESTION DE CATEGORIAS ---");
            System.out.println("1. Listar | 2. Crear | 3. Modificar | 4. Eliminar | 0. Volver");
            try {
                int op = leerOpcion(0, 4);
                switch (op) {
                    case 1 -> listarCategorias();
                    case 2 -> { limpiarConsola(); System.out.println("Creando categoria..."); pausar(); }
                    case 3 -> editarCategoria();
                    case 4 -> { limpiarConsola(); System.out.println("Eliminando categoria..."); pausar(); }
                    case 0 -> activo = false;
                }
            } catch (InvalidInputException e) { limpiarConsola(); System.out.println("Error: " + e.getMessage()); pausar(); }
        }
    }

    private void listarCategorias() {
        limpiarConsola();
        System.out.println("\n--- LISTAR CATEGORIAS ---");
        System.out.println("1. Ver todas | 2. Buscar por nombre | 0. Volver");
        try {
            int subOp = leerOpcion(0, 2);
            if (subOp == 1) {
                // --- INSTRUCCION PARA COMPAÑEROS ---
                // 1. Llamar a: categoriaService.listarTodo();
                // 2. Iterar la lista obtenida e imprimir cada objeto.
                System.out.println("Mostrando todas las categorias...");
                pausar();
            } else if (subOp == 2) {
                String nombre = pedirTexto("Ingrese el nombre de la categoria");
                // --- INSTRUCCION PARA COMPAÑEROS ---
                // 1. Llamar a: categoriaService.buscarPorNombre(nombre);
                // 2. Iterar la lista obtenida e imprimir cada resultado.
                System.out.println("Buscando categorias con nombre: " + nombre);
                pausar();
            }
        } catch (InvalidInputException e) {
            limpiarConsola();
            System.out.println("Error: " + e.getMessage());
            pausar();
        }
    }

    private void editarCategoria() {
        boolean editando = true;
        while (editando) {
            limpiarConsola();
            System.out.println("\n--- EDITAR CATEGORIA ---");
            System.out.println("1. Nombre | 2. Descripcion | 0. Guardar y Salir");
            try {
                int op = leerOpcion(0, 2);
                if (op == 0) editando = false;
                else { limpiarConsola(); System.out.println("Editando atributo " + op + "..."); pausar(); }
            } catch (InvalidInputException e) { limpiarConsola(); System.out.println("Error: " + e.getMessage()); pausar(); }
        }
    }
}