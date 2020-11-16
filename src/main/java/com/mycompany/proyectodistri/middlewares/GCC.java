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
public class GCC {
    Queue<Transaccion> transacciones;
    IPS ips;
    int rand;

    public GCC() {
        this.transacciones = new LinkedList<Transaccion>();
    }
    
    public void distribuir() {
        
        Transaccion transaccion = transacciones.poll();
        
        if(transaccion==null)
            System.out.println("No hay transacciones en la cola!");
        
        rand = (int) Math.random()*2;
        String nombre = null;
        
        if(rand == 0)
            nombre = "ips1";
        if(rand == 1)
            nombre = "ips2";
        if(nombre!=null)
            ips = transaccion.getPaciente().buscarIPS(nombre);
        else
            System.out.println("Error al buscar la IPS");
        
        ips.getTransacciones().add(transaccion);
        System.out.println("Éxito");
    }
}
