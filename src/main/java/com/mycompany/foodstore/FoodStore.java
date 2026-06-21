package com.mycompany.foodstore;

import com.mycompany.foodstore.ui.MenuPrincipal;

public class FoodStore {

    public static void main(String[] args) {
        System.out.println("=== Iniciando Sistema FoodStore ===");

        MenuPrincipal menu = new MenuPrincipal();

        menu.iniciar(); 
    }
}