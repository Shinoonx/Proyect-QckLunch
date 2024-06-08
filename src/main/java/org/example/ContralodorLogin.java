package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContralodorLogin {
    private static final String FILE_PATH = "usuarios.json";
    private List<Persona> usuarios;
    private Gson gson;

    public ContralodorLogin() {
        this.usuarios = new ArrayList<>();
        this.gson = new Gson();
        cargarUsuarios();
    }

    public boolean registrar(String username, String email, String password) {
        if (buscarUsuarioPorEmail(email) == null) {
            usuarios.add(new Persona(username, email, password));
            guardarUsuarios();
            return true;
        }
        return false;
    }

    public Persona login(String email, String password) {
        Persona usuario = buscarUsuarioPorEmail(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null; // Usuario no encontrado o contraseña incorrecta
    }

    private Persona buscarUsuarioPorEmail(String email) {
        for (Persona usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    private void guardarUsuarios() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarUsuarios() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type usuarioListType = new TypeToken<ArrayList<Persona>>() {}.getType();
            usuarios = gson.fromJson(reader, usuarioListType);
            if (usuarios == null) {
                usuarios = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            usuarios = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
