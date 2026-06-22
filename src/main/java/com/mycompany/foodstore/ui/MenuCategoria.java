package com.mycompany.foodstore.ui;

import com.mycompany.foodstore.entities.Categoria;
import com.mycompany.foodstore.service.CategoriaService;

import java.util.List;

public class MenuCategoria extends MenuBase {

    private final CategoriaService categoriaService = new CategoriaService();

    @Override
    public void iniciar() {
        boolean activo = true;

        while (activo) {
            limpiarConsola();
            System.out.println("\n--- GESTION DE CATEGORIAS ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            try {
                int op = leerOpcion(0, 4);

                switch (op) {
                    case 1 -> listarCategorias();
                    case 2 -> crearCategoria();
                    case 3 -> editarCategoria();
                    case 4 -> eliminarCategoria();
                    case 0 -> activo = false;
                }

            } catch (InvalidInputException e) {
                limpiarConsola();
                System.out.println("Error: " + e.getMessage());
                pausar();

            } catch (Exception e) {
                limpiarConsola();
                System.out.println("Error inesperado: " + e.getMessage());
                pausar();
            }
        }
    }

    // ---------------- CREAR ----------------
    private void crearCategoria() {
        limpiarConsola();
        System.out.println("\n--- CREAR CATEGORIA ---");

        try {
            String nombre = pedirTexto("Nombre");
            String descripcion = pedirTexto("Descripcion");

            Categoria c = new Categoria();
            c.setNombre(nombre);
            c.setDescripcion(descripcion);

            categoriaService.guardarCategoria(c);

            System.out.println("Categoria creada correctamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- LISTAR ----------------
    private void listarCategorias() {
        limpiarConsola();
        System.out.println("\n--- LISTADO DE CATEGORIAS ---");

        try {
            List<Categoria> lista = categoriaService.listarCategorias();

            if (lista.isEmpty()) {
                System.out.println("No hay categorias.");
            } else {
                System.out.printf("%-5s | %-20s | %-30s\n", "ID", "NOMBRE", "DESCRIPCION");
                System.out.println("------------------------------------------------------");

                for (Categoria c : lista) {
                    System.out.printf("%-5d | %-20s | %-30s\n",
                            c.getId(),
                            c.getNombre(),
                            c.getDescripcion());
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- EDITAR ----------------
    private void editarCategoria() {
        limpiarConsola();
        System.out.println("\n--- EDITAR CATEGORIA ---");

        try {
            Long id = leerLong("ID de la categoria");

            Categoria c = categoriaService.buscarPorId(id);

            String nuevoNombre = pedirTexto("Nuevo nombre");
            String nuevaDescripcion = pedirTexto("Nueva descripcion");

            c.setNombre(nuevoNombre);
            c.setDescripcion(nuevaDescripcion);

            categoriaService.actualizarCategoria(c);

            System.out.println("Categoria actualizada.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- ELIMINAR ----------------
    private void eliminarCategoria() {
        limpiarConsola();
        System.out.println("\n--- ELIMINAR CATEGORIA ---");

        try {
            Long id = leerLong("ID de la categoria");

            categoriaService.eliminarCategoria(id);

            System.out.println("Categoria eliminada.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }
}