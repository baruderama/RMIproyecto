/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author federico
 */
public class Paciente {
    private String usuario;
    private char[] contrasena;
    private ArrayList<IPS> instituciones;

    public Paciente(String usuario, char[] contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.instituciones = new ArrayList<IPS>();
        this.instituciones.add(new IPS("ips1"));
        this.instituciones.add(new IPS("ips2"));
    }
    
    public void crearTransaccion(int vacunas, String laboratorio, GCC gcc) {
        try {
            Transaccion transaccion = new Transaccion(this, vacunas, laboratorio, gcc);
            transaccion.start();
            transaccion.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public IPS buscarIPS(String nombre){
        for(IPS ips: instituciones){
            if(nombre==ips.getNombre())
                return ips;
        }
        return null;       
    }
    
    public String getUsuario() {
        return usuario;
    }

    public char[] getContrasena() {
        return contrasena;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(char[] contrasena) {
        this.contrasena = contrasena;
    }
    
}
