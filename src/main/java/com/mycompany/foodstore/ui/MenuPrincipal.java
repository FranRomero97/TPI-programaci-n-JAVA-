package com.mycompany.foodstore.ui;

public class MenuPrincipal extends MenuBase {
    
    // Instancia de los submenus
    private final MenuCategoria menuCategoria = new MenuCategoria();
    private final MenuProducto menuProducto = new MenuProducto();
    private final MenuUsuario menuUsuario = new MenuUsuario();
    private final MenuPedido menuPedido = new MenuPedido();

    @Override
    public void iniciar() {
        boolean activo = true;
        while (activo) {
            limpiarConsola();
            System.out.println("\n--- SISTEMA DE GESTION FOOD STORE ---");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            
            try {
                int op = leerOpcion(0, 4);
                switch (op) {
                    case 1 -> menuCategoria.iniciar();
                    case 2 -> menuProducto.iniciar();
                    case 3 -> menuUsuario.iniciar();
                    case 4 -> menuPedido.iniciar();
                    case 0 -> {
                        limpiarConsola();
                        System.out.println("Cerrando sistema...");
                        activo = false;
                    }
                }
            } catch (InvalidInputException e) { 
                limpiarConsola(); 
                System.out.println("Error: " + e.getMessage()); 
                pausar(); 
            }
        }
    }
}