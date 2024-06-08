package org.example;

public class Main {
    public static void main(String[] args) {
        ContralodorLogin contralodorLogin = new ContralodorLogin();
        Menu menu = new Menu(contralodorLogin);
        menu.mostrarMenu();
    }
}