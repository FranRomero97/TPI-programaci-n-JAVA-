package com.mycompany.foodstore.ui;

import com.mycompany.foodstore.entities.Usuario;
import com.mycompany.foodstore.entities.Rol;
import com.mycompany.foodstore.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.List;

public class MenuUsuario extends MenuBase {

    private final UsuarioService usuarioService = new UsuarioService();

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
                    case 2 -> crearUsuario();
                    case 3 -> editarUsuario();
                    case 4 -> eliminarUsuario();
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
    private void crearUsuario() {

        limpiarConsola();
        System.out.println("\n--- CREAR USUARIO ---");

        try {

            String nombre = pedirTexto("Nombre");
            String apellido = pedirTexto("Apellido");
            String mail = pedirTexto("Mail");
            String celular = pedirTexto("Celular");
            String contraseña = pedirTexto("Contraseña");

            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setApellido(apellido);
            u.setMail(mail);
            u.setCelular(celular);
            u.setContraseña(contraseña);
            u.setRol(Rol.USUARIO); // por defecto
            u.setCreatedAt(LocalDateTime.now());

            usuarioService.guardarUsuario(u);

            System.out.println("Usuario creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- LISTAR ----------------
    private void listarUsuarios() {

        limpiarConsola();
        System.out.println("\n--- LISTAR USUARIOS ---");

        try {

            List<Usuario> lista = usuarioService.listarUsuarios();

            if (lista.isEmpty()) {
                System.out.println("No hay usuarios cargados.");
            } else {

                System.out.println("====================================================");

                for (Usuario u : lista) {
                    System.out.println(
                            "ID: " + u.getId() +
                            " | NOMBRE: " + u.getNombre() +
                            " | APELLIDO: " + u.getApellido() +
                            " | MAIL: " + u.getMail() +
                            " | CELULAR: " + u.getCelular() +
                            " | ROL: " + u.getRol()
                    );
                }

                System.out.println("====================================================");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- EDITAR ----------------
    private void editarUsuario() {

        limpiarConsola();
        System.out.println("\n--- EDITAR USUARIO ---");

        try {

            Long id = leerLong("ID usuario");

            Usuario u = usuarioService.buscarPorId(id);

            if (u == null) {
                System.out.println("Usuario no encontrado.");
                pausar();
                return;
            }

            String nombre = pedirTexto("Nuevo nombre");
            String apellido = pedirTexto("Nuevo apellido");
            String mail = pedirTexto("Nuevo mail");
            String celular = pedirTexto("Nuevo celular");
            String contraseña = pedirTexto("Nueva contraseña");

            u.setNombre(nombre);
            u.setApellido(apellido);
            u.setMail(mail);
            u.setCelular(celular);
            u.setContraseña(contraseña);

            usuarioService.actualizarUsuario(u);

            System.out.println("Usuario actualizado.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }

    // ---------------- ELIMINAR ----------------
    private void eliminarUsuario() {

        limpiarConsola();
        System.out.println("\n--- ELIMINAR USUARIO ---");

        try {

            Long id = leerLong("ID usuario");

            usuarioService.eliminarUsuario(id);

            System.out.println("Usuario eliminado.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        pausar();
    }
}