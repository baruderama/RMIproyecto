/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fede1
 */
public class Transaccion extends Thread{
    
    private List<Peticiones> p;
    private GCC gcc;
    private int rand;

    public Transaccion(List<Peticiones> p, GCC gcc) {
        
        this.p=p;
        this.gcc = gcc;
    }
    
    public void run(){  
        System.out.println("Hilo2");
        try {
            
            while(!gcc.distribuir(this)) {
                System.out.println("Transacción abortada!!");
            }
            
            System.out.println("Transaccion exitosa.");
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Inicia el hilo de la transaccion");
    }

   


    public double getRand() {
        return rand;
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

   


    public void setRand(int rand) {
        this.rand = rand;
    }

    public List<Peticiones> getP() {
        return p;
    }

    public void setP(List<Peticiones> p) {
        this.p = p;
    }
    
    
    
    
}
