/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstore.ui;

import com.mycompany.foodstore.entities.Producto;
import com.mycompany.foodstore.entities.Categoria;
import com.mycompany.foodstore.service.ProductoService;

import java.util.List;

public class MenuProducto extends MenuBase {

    private final ProductoService productoService = new ProductoService();

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
                    case 2 -> crearProducto();   // 🔥 FIX
                    case 3 -> editarProducto();
                    case 4 -> gestionarStock();
                    case 5 -> eliminarProducto(); // 🔥 FIX
                    case 0 -> activo = false;
                }

            } catch (Exception e) {
                limpiarConsola();
                System.out.println("Error: " + e.getMessage());
                pausar();
            }
        }
    }

    // ---------------- CREAR ----------------
    private void crearProducto() {
        limpiarConsola();
        System.out.println("\n--- CREAR PRODUCTO ---");

        try {
            String nombre = pedirTexto("Nombre");
            String descripcion = pedirTexto("Descripcion");

            double precio = Double.parseDouble(pedirTexto("Precio"));
            int stock = Integer.parseInt(pedirTexto("Stock"));
            String imagen = pedirTexto("Imagen");

            Long catId = leerLongOpcional("ID categoria (0 si no tiene)");

            Categoria cat = null;
            if (catId != 0) {
                cat = new Categoria();
                cat.setId(catId);
            }

            Producto p = new Producto();
            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);
            p.setStock(stock);
            p.setImagen(imagen);
            p.setDisponible(true);
            p.setEliminado(false);
            p.setCreatedAt(java.time.LocalDateTime.now());
            p.setCategoria(cat);

            productoService.guardarProducto(p);

            System.out.println("Producto creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    private void listarProductos() {

    limpiarConsola();
    System.out.println("\n--- LISTAR PRODUCTOS ---");

    try {

        List<Producto> lista = productoService.listarProductos();

        System.out.println("====================================================");

        for (Producto p : lista) {

            System.out.println(
                    "ID: " + p.getId() +
                    " | NOMBRE: " + p.getNombre() +
                    " | DESCRIPCION: " + p.getDescripcion() +
                    " | PRECIO: " + p.getPrecio() +
                    " | STOCK: " + p.getStock() +
                    " | CATEGORIA_ID: " +
                    (p.getCategoria() != null ? p.getCategoria().getId() : "SIN CATEGORIA")
            );
        }

        System.out.println("====================================================");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    pausar();
}


    // ---------------- ELIMINAR ----------------
    private void eliminarProducto() {
        limpiarConsola();
        System.out.println("\n--- ELIMINAR PRODUCTO ---");

        try {
            Long id = leerLong("ID producto");

            productoService.eliminarProducto(id);

            System.out.println("Producto eliminado.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

//------------------- EDITAR ----------------   
    private void editarProducto() {
    limpiarConsola();
    System.out.println("\n--- EDITAR PRODUCTO ---");

    try {
        Long id = leerLong("ID del producto");

        Producto p = productoService.buscarPorId(id);

        if (p == null) {
            System.out.println("Producto no encontrado.");
            pausar();
            return;
        }

        System.out.println("\nProducto actual:");
        System.out.println("Nombre: " + p.getNombre());
        System.out.println("Descripcion: " + p.getDescripcion());
        System.out.println("Precio: " + p.getPrecio());
        System.out.println("Stock: " + p.getStock());
        System.out.println("Imagen: " + p.getImagen());

        System.out.println("\n--- NUEVOS DATOS ---");

        String nombre = pedirTexto("Nuevo nombre");
        String descripcion = pedirTexto("Nueva descripcion");
        double precio = Double.parseDouble(pedirTexto("Nuevo precio"));
        int stock = Integer.parseInt(pedirTexto("Nuevo stock"));
        String imagen = pedirTexto("Nueva imagen");

        Long catId = leerLongOpcional("Nueva categoria (0 para mantener)");

        if (catId != 0) {
            Categoria cat = new Categoria();
            cat.setId(catId);
            p.setCategoria(cat);
        }

        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setImagen(imagen);

        productoService.actualizarProducto(p);

        System.out.println("\nProducto actualizado correctamente.");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    pausar();
}

   private void gestionarStock() {
    limpiarConsola();
    System.out.println("\n--- GESTION DE STOCK ---");

    try {
        Long id = leerLong("ID del producto");

        Producto p = productoService.buscarPorId(id);

        if (p == null) {
            System.out.println("Producto no encontrado.");
            pausar();
            return;
        }

        System.out.println("\nProducto: " + p.getNombre());
        System.out.println("Stock actual: " + p.getStock());

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    pausar();
        }

}