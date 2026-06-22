package com.mycompany.foodstore;

import com.mycompany.foodstore.ui.MenuLogin;

public class FoodStore {

    public static void main(String[] args) {

        System.out.println("=== Iniciando Sistema FoodStore ===");

        MenuLogin menuLogin = new MenuLogin();
        menuLogin.iniciar();
    }
}