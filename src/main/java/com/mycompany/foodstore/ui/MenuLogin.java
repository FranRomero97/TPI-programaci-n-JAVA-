package com.mycompany.foodstore.ui;

import com.mycompany.foodstore.entities.Rol;
import com.mycompany.foodstore.entities.Usuario;
import com.mycompany.foodstore.service.UsuarioService;

import java.time.LocalDateTime;

public class MenuLogin extends MenuBase {

    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    public void iniciar() {

        boolean activo = true;

        while (activo) {

            limpiarConsola();

            System.out.println("=== FOOD STORE ===");
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Registrarse");
            System.out.println("0. Salir");

            try {

                int op = leerOpcion(0, 2);

                switch (op) {

                    case 1 -> login();

                    case 2 -> registrarse();

                    case 0 -> activo = false;
                }

            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());
                pausar();
            }
        }
    }

    private void login() {

        try {

            Long id = Long.parseLong(pedirTexto("ID Usuario"));
            String contraseña = pedirTexto("Contraseña");

            Usuario usuario = usuarioService.login(id, contraseña);

            if (usuario == null) {

                System.out.println("Usuario o contraseña incorrectos.");
                pausar();
                return;
            }

            MenuPrincipal menu = new MenuPrincipal(usuario);
            menu.iniciar();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
            pausar();
        }
    }

    private void registrarse() {

        try {

            Usuario usuario = new Usuario();

            usuario.setNombre(pedirTexto("Nombre"));
            usuario.setApellido(pedirTexto("Apellido"));
            usuario.setMail(pedirTexto("Mail"));
            usuario.setCelular(pedirTexto("Celular"));
            usuario.setContraseña(pedirTexto("Contraseña"));

            usuario.setRol(Rol.USUARIO);

            usuario.setEliminado(false);
            usuario.setCreatedAt(LocalDateTime.now());

            usuarioService.guardarUsuario(usuario);

            System.out.println("\nUsuario creado correctamente.");
            System.out.println("Su ID es: " + usuario.getId());

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }
}