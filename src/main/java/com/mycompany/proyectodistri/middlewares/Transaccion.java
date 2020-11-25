/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fede1
 */
public class Transaccion extends Thread implements Serializable{
    
    private List<Peticiones> p;
    private GCC gcc;
    private  int ips;

    public Transaccion(List<Peticiones> p, GCC gcc, int ips) {
        
        this.p=p;
        this.gcc = gcc;
        this.ips=ips;
        
    }
    
    public void run(){  
        
        Int stub = Client.conectarEPS();
        System.out.println("Ejecutando Hilo: " + this.getName());
        try {
            
            while(!gcc.distribuir(this)) {
                System.out.println("¡TRANSACCION ABORTADA!");
                stub.sentConfirm("TRANSACCION ABORTADA");
            }
            
            System.out.println("TRANSACCION EXITOSA");
            //Int stub = Client.conectarEPS();
            stub.sentConfirm("TRANSACCION EXITOSA");
        
        } catch (IOException ex) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIps() {
        return ips;
    }

    public void setIps(int ips) {
        this.ips = ips;
    }

   

    public static int getMIN_PRIORITY() {
        return MIN_PRIORITY;
    }

    public static int getNORM_PRIORITY() {
        return NORM_PRIORITY;
    }

    public static int getMAX_PRIORITY() {
        return MAX_PRIORITY;
    }

   

    public List<Peticiones> getP() {
        return p;
    }

    public void setP(List<Peticiones> p) {
        this.p = p;
    }
    
    
    
    
}