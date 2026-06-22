package com.mycompany.foodstore.ui;

import com.mycompany.foodstore.entities.Rol;
import com.mycompany.foodstore.entities.Usuario;

public class MenuPrincipal extends MenuBase {

    private Usuario usuarioLogueado;

    private MenuCategoria menuCategoria;
    private MenuProducto menuProducto;
    private MenuUsuario menuUsuario;
    private MenuPedido menuPedido;

    public MenuPrincipal(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;

        //  inicialización correcta con sesión
        this.menuCategoria = new MenuCategoria();
        this.menuProducto = new MenuProducto();
        this.menuUsuario = new MenuUsuario();
        this.menuPedido = new MenuPedido(usuarioLogueado);
    }

    @Override
    public void iniciar() {

        boolean activo = true;

        while (activo) {

            limpiarConsola();

            if (usuarioLogueado.getRol() == Rol.ADMIN) {

                System.out.println("\n--- FOOD STORE (ADMIN) ---");
                System.out.println("1. Categorias");
                System.out.println("2. Productos");
                System.out.println("3. Usuarios");
                System.out.println("4. Pedidos");
                System.out.println("0. Cerrar Sesion");

                try {

                    int op = leerOpcion(0, 4);

                    switch (op) {

                        case 1 -> menuCategoria.iniciar();
                        case 2 -> menuProducto.iniciar();
                        case 3 -> menuUsuario.iniciar();
                        case 4 -> menuPedido.iniciar();
                        case 0 -> activo = false;
                    }

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    pausar();
                }

            } else {

                System.out.println("\n--- FOOD STORE (USUARIO) ---");
                System.out.println("1. Pedidos");
                System.out.println("0. Cerrar Sesion");

                try {

                    int op = leerOpcion(0, 1);

                    switch (op) {

                        case 1 -> menuPedido.iniciar();
                        case 0 -> activo = false;
                    }

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    pausar();
                }
            }
        }
    }
}