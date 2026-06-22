package com.mycompany.foodstore.ui;

import com.mycompany.foodstore.entities.Categoria;
import com.mycompany.foodstore.entities.Producto;
import com.mycompany.foodstore.service.CategoriaService;
import com.mycompany.foodstore.service.ProductoService;

import java.util.List;

public class MenuCategoria extends MenuBase {

    private final CategoriaService categoriaService = new CategoriaService();
    private final ProductoService productoService = new ProductoService();

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

            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);

            categoriaService.guardarCategoria(categoria);

            System.out.println("\nCategoria creada correctamente.");
            System.out.println("ID generado: " + categoria.getId());

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

            List<Categoria> categorias = categoriaService.listarCategorias();
            List<Producto> productos = productoService.listarProductos();

            if (categorias.isEmpty()) {

                System.out.println("No hay categorias cargadas.");

            } else {

                for (Categoria categoria : categorias) {

                    System.out.println("==================================================");
                    System.out.println("ID: " + categoria.getId());
                    System.out.println("NOMBRE: " + categoria.getNombre());
                    System.out.println("DESCRIPCION: " + categoria.getDescripcion());

                    System.out.println("\nPRODUCTOS:");

                    boolean tieneProductos = false;

                    for (Producto producto : productos) {

                        if (producto.getCategoria() != null
                                && producto.getCategoria().getId().equals(categoria.getId())) {

                            System.out.println(
                                    " - ID: " + producto.getId()
                                    + " | " + producto.getNombre()
                                    + " | $" + producto.getPrecio()
                                    + " | Stock: " + producto.getStock()
                            );

                            tieneProductos = true;
                        }
                    }

                    if (!tieneProductos) {
                        System.out.println(" Sin productos asignados.");
                    }

                    System.out.println("==================================================");
                    System.out.println();
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

            Categoria categoria = categoriaService.buscarPorId(id);

            if (categoria == null) {

                System.out.println("La categoria no existe.");
                pausar();
                return;
            }

            String nuevoNombre = pedirTexto("Nuevo nombre");
            String nuevaDescripcion = pedirTexto("Nueva descripcion");

            categoria.setNombre(nuevoNombre);
            categoria.setDescripcion(nuevaDescripcion);

            categoriaService.actualizarCategoria(categoria);

            System.out.println("Categoria actualizada correctamente.");

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

            System.out.println("Categoria eliminada correctamente.");

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }
}