package com.mycompany.foodstore.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.foodstore.entities.Base;

public class Usuario extends Base {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contraseña;
    private Rol rol; // Ya no necesita import si Rol está en el mismo paquete
    private List<Pedido> pedidos;

    public Usuario(){
        super();
        this.pedidos = new ArrayList<>();
    }

    public Usuario(String nombre, String apellido, String mail,
                   String celular, String contraseña, Rol rol,
                   Long ID, boolean eliminado, LocalDateTime createdAt) {

        super(); // Llama al constructor vacío de Base
        this.setId(ID); // Setea el ID en la clase Base
        this.setEliminado(eliminado); // Setea el estado eliminado en Base
        this.setCreatedAt(createdAt); // Setea la fecha en Base
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contraseña = contraseña;
        this.rol = rol;
        this.pedidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
}