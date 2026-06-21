package com.mycompany.foodstore.ui;

import com.mycompany.foodstore.entities.Categoria;
import com.mycompany.foodstore.service.CategoriaService;
import java.util.List;

public class MenuCategoria extends MenuBase {
    
    // 1. Instanciamos el servicio para poder usar la base de datos
    private final CategoriaService categoriaService = new CategoriaService();

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
                    case 2 -> crearCategoria(); 
                    case 3 -> editarCategoria();
                    case 4 -> eliminarCategoria();
                    case 0 -> activo = false;
                }
            } catch (InvalidInputException e) { limpiarConsola(); System.out.println("Error: " + e.getMessage()); pausar(); }
        }
    }

    // 2. CONECTADO: Método para insertar en la Base de Datos
    private void crearCategoria() throws InvalidInputException {
        limpiarConsola();
        System.out.println("\n--- CREAR NUEVA CATEGORÍA ---");
        
        String nombre = pedirTexto("Ingrese el nombre de la categoria");
        String descripcion = pedirTexto("Ingrese la descripcion de la categoria");
        
        try {
            Categoria nuevaCategoria = new Categoria();
            nuevaCategoria.setNombre(nombre);
            nuevaCategoria.setDescripcion(descripcion);
            
            // Llamamos al servicio para impactar en MySQL
            categoriaService.guardarCategoria(nuevaCategoria);
            
            System.out.println("\n¡Categoría guardada con éxito en la Base de Datos! ID generado: " + nuevaCategoria.getId());
        } catch (Exception e) {
            System.out.println("\nError al guardar en la base de datos: " + e.getMessage());
        }
        pausar();
    }

    // 3. CONECTADO: Método para traer y mostrar los registros reales
    private void listarCategorias() {
        limpiarConsola();
        System.out.println("\n--- LISTAR CATEGORIAS ---");
        System.out.println("1. Ver todas | 2. Buscar por nombre (Simulado) | 0. Volver");
        try {
            int subOp = leerOpcion(0, 2);
            if (subOp == 1) {
                limpiarConsola();
                System.out.println("=== LISTADO DE CATEGORÍAS REALES ===");
                
                // Llamamos al servicio para traer la lista desde MySQL
                List<Categoria> categorias = categoriaService.listarCategorias();
                
                if (categorias.isEmpty()) {
                    System.out.println("No hay ninguna categoría cargada en la base de datos.");
                } else {
                    // Iteramos la lista e imprimimos los datos en una tablita prolija
                    System.out.printf("%-5s | %-20s | %-30s\n", "ID", "NOMBRE", "DESCRIPCIÓN");
                    System.out.println("------------------------------------------------------------------");
                    for (Categoria cat : categorias) {
                        System.out.printf("%-5d | %-20s | %-30s\n", cat.getId(), cat.getNombre(), cat.getDescripcion());
                    }
                }
                pausar();
            } else if (subOp == 2) {
                String nombre = pedirTexto("Ingrese el nombre de la categoria");
                System.out.println("Buscando categorias con nombre: " + nombre);
                pausar();
            }
        } catch (Exception e) {
            limpiarConsola();
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
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

    // 4. CONECTADO: Eliminación lógica armada
    private void eliminarCategoria() {
        limpiarConsola();
        System.out.println("\n--- ELIMINAR CATEGORÍA ---");
        try {
            // Suponiendo que leerOpcion o una función similar te pide un entero largo
            System.out.print("Ingrese el ID de la categoría a eliminar: ");
            long id = new java.util.Scanner(System.in).nextLong();
            
            categoriaService.eliminarCategoria(id);
            System.out.println("\n¡Categoría marcada como eliminada con éxito!");
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
        pausar();
    }
}