package com.mycompany.foodstore.ui;

import java.util.Scanner;

public abstract class MenuBase {

    protected static final Scanner scanner = new Scanner(System.in);

    public abstract void iniciar();

    // ---------------- LEER OPCION ----------------
    protected int leerOpcion(int min, int max) throws InvalidInputException {
        String entrada = scanner.nextLine();

        try {
            int opcion = Integer.parseInt(entrada);

            if (opcion < min || opcion > max) {
                throw new InvalidInputException(
                        "La opcion debe estar entre " + min + " y " + max + "."
                );
            }

            return opcion;

        } catch (NumberFormatException e) {
            throw new InvalidInputException(
                    "Entrada invalida: '" + entrada + "' no es un numero."
            );
        }
    }

    // ---------------- PEDIR TEXTO ----------------
    protected String pedirTexto(String mensaje) throws InvalidInputException {
        System.out.print(mensaje + ": ");
        String entrada = scanner.nextLine().trim();

        if (entrada.isEmpty()) {
            throw new InvalidInputException("La entrada no puede estar vacia.");
        }

        return entrada;
    }

    // ---------------- LEER LONG (NUEVO) ----------------
    protected Long leerLong(String mensaje) throws InvalidInputException {
        System.out.print(mensaje + ": ");
        String entrada = scanner.nextLine();

        try {
            long valor = Long.parseLong(entrada);

            if (valor <= 0) {
                throw new InvalidInputException("El ID debe ser mayor a 0.");
            }

            return valor;

        } catch (NumberFormatException e) {
            throw new InvalidInputException(
                    "Entrada invalida: '" + entrada + "' no es un numero valido."
            );
        }
    }

    // ---------------- PAUSAR ----------------
    protected void pausar() {
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    // ---------------- LIMPIAR CONSOLA ----------------
    protected void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        System.out.flush();
    }

    protected Long leerLongOpcional(String mensaje) throws InvalidInputException {
    System.out.print(mensaje + ": ");
    String entrada = scanner.nextLine();

    try {
        long valor = Long.parseLong(entrada);

        if (valor < 0) {
            throw new InvalidInputException("El ID no puede ser negativo.");
        }

        return valor; // permite 0

    } catch (NumberFormatException e) {
        throw new InvalidInputException("Debe ingresar un numero valido.");
    }
}
}