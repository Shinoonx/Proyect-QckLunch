package org.example;

import java.util.Scanner;

public class Menu {
    private ContralodorLogin contralodorLogin;
    private Scanner scanner;

    public Menu(ContralodorLogin contralodorLogin) {
        this.contralodorLogin = contralodorLogin;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("1. Registrar");
            System.out.println("2. Login");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de usuario: ");
                    String username = scanner.nextLine();
                    System.out.print("Correo electrónico: ");
                    String email = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String password = scanner.nextLine();

                    if (contralodorLogin.registrar(username, email, password)) {
                        System.out.println("Usuario registrado con éxito.");
                    } else {
                        System.out.println("El correo electrónico ya está en uso.");
                    }
                    break;
                case 2:
                    System.out.print("Correo electrónico: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String loginPassword = scanner.nextLine();

                    Persona usuario = contralodorLogin.login(loginEmail, loginPassword);
                    if (usuario != null) {
                        System.out.println("Bienvenido, " + usuario.getUsername() + "!");
                    } else {
                        System.out.println("Correo electrónico o contraseña incorrectos.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);

        scanner.close();
    }
}