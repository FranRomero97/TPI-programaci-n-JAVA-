/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstore.ui;
import java.util.Scanner;

public abstract class MenuBase {
    protected static final Scanner scanner = new Scanner(System.in);
    public abstract void iniciar();

    protected int leerOpcion(int min, int max) throws InvalidInputException {
        String entrada = scanner.nextLine();
        try {
            int opcion = Integer.parseInt(entrada);
            if (opcion < min || opcion > max) {
                throw new InvalidInputException("La opcion debe estar entre " + min + " y " + max + ".");
            }
            return opcion;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Entrada invalida: '" + entrada + "' no es un numero.");
        }
    }

    
    protected String pedirTexto(String mensaje) throws InvalidInputException {
        System.out.print(mensaje + ": ");
        String entrada = scanner.nextLine().trim(); 
    
        if (entrada.isEmpty()) {
            throw new InvalidInputException("La entrada no puede estar vacia.");
        }
        return entrada;
    }
    
    protected void pausar() {
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    protected void limpiarConsola() {
    for (int i = 0; i < 50; i++) {
        System.out.println();
    }
    System.out.flush();
}
}