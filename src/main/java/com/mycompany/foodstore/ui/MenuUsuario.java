/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstore.ui;

public class MenuUsuario extends MenuBase {
    @Override
    public void iniciar() {
        boolean activo = true;
        while (activo) {
            limpiarConsola();
            System.out.println("\n--- GESTION DE USUARIOS ---");
            System.out.println("1. Listar | 2. Crear | 3. Modificar | 4. Eliminar | 0. Volver");
            try {
                int op = leerOpcion(0, 4);
                switch (op) {
                    case 1 -> listarUsuarios();
                    case 2 -> { limpiarConsola(); System.out.println("Creando usuario..."); pausar(); }
                    case 3 -> editarUsuario();
                    case 4 -> { limpiarConsola(); System.out.println("Eliminando usuario..."); pausar(); }
                    case 0 -> activo = false;
                }
            } catch (InvalidInputException e) { limpiarConsola(); System.out.println("Error: " + e.getMessage()); pausar(); }
        }
    }

    private void listarUsuarios() {
        limpiarConsola();
        System.out.println("\n--- LISTAR USUARIOS ---");
        System.out.println("1. Ver todos | 2. Buscar por nombre | 0. Volver");
        try {
            int subOp = leerOpcion(0, 2);
            if (subOp == 1) {
                // --- INSTRUCCION PARA COMPAÑEROS ---
                // 1. Llamar a: usuarioService.listarTodo();
                // 2. Iterar la lista obtenida e imprimir cada objeto.
                System.out.println("Mostrando todos los usuarios...");
                pausar();
            } else if (subOp == 2) {
                String nombre = pedirTexto("Ingrese el nombre del usuario");
                // --- INSTRUCCION PARA COMPAÑEROS ---
                // 1. Llamar a: usuarioService.buscarPorNombre(nombre);
                // 2. Iterar la lista obtenida e imprimir cada resultado.
                System.out.println("Buscando usuarios con nombre: " + nombre);
                pausar();
            }
        } catch (InvalidInputException e) {
            limpiarConsola();
            System.out.println("Error: " + e.getMessage());
            pausar();
        }
    }

    private void editarUsuario() {
        boolean editando = true;
        while (editando) {
            limpiarConsola();
            System.out.println("\n--- EDITAR USUARIO ---");
            System.out.println("1. Nombre | 2. Email | 0. Guardar");
            try {
                int op = leerOpcion(0, 2);
                if (op == 0) editando = false;
                else { limpiarConsola(); System.out.println("Editando atributo " + op + "..."); pausar(); }
            } catch (InvalidInputException e) { limpiarConsola(); System.out.println("Error: " + e.getMessage()); pausar(); }
        }
    }
}