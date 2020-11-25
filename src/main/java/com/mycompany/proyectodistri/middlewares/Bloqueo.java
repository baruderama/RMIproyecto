/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fede1
 */
public class Bloqueo {
    private static IPS ips;
    private static Vector propietarios;
    private static TipoBloqueo tipoBloqueo;
    
    
    public static synchronized void adquiere(Transaccion transaccion, TipoBloqueo tipoBloqueoRecibido) {
        while(tipoBloqueo == TipoBloqueo.ESCRITURA) {
            try {
                System.out.println(transaccion.getName());
                transaccion.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bloqueo.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(propietarios.isEmpty()) {
                propietarios.add(transaccion);
                tipoBloqueo = tipoBloqueoRecibido;
            }
        }
    }
        
    public static synchronized void libera(Transaccion transaccion) {
        propietarios.remove(transaccion);
        transaccion.notify();
    }    
}
