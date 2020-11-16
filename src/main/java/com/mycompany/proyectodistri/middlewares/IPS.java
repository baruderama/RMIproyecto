/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author fede1
 */
public class IPS {
    String nombre;
    Queue<Transaccion> transacciones;

    public IPS(String nombre) {
        this.nombre = nombre;
        this.transacciones = new LinkedList<Transaccion>();
    }   

    public String getNombre() {
        return nombre;
    }

    public Queue<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTransacciones(Queue<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
    
    
}
