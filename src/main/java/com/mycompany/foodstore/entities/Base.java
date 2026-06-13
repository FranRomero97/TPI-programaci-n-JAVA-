package com.mycompany.foodstore.entities;

import java.time.LocalDateTime;
public class Base {
    private Long id;
    private boolean eliminado = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public Base(){}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public boolean isEliminado(){
        return eliminado;
    }
    
    public void setEliminado(boolean eliminado){
        this.eliminado = eliminado;
    }
    
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
 
