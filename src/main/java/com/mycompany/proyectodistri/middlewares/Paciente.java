/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;


import java.util.ArrayList;


import java.io.Serializable;


/**
 *
 * @author federico
 */
public class Paciente implements Serializable{
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
    
    /*public void crearTransaccion(int vacunas, String laboratorio, GCC gcc) {
        //Transaccion transaccion = new Transaccion(this, vacunas, laboratorio, gcc);
        gcc.transacciones.add(transaccion);
        transaccion.start();
    }*/
    
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
