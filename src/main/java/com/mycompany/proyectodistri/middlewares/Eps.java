/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

/**
 *
 * @author baru
 */
public class Eps {
    public String nombreDeusuario;
    public String contraseña;

    public Eps(String nombreDeusuario, String contraseña) {
        this.nombreDeusuario = nombreDeusuario;
        this.contraseña = contraseña;
    }

    public String getNombreDeusuario() {
        return nombreDeusuario;
    }

    public void setNombreDeusuario(String nombreDeusuario) {
        this.nombreDeusuario = nombreDeusuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
}
