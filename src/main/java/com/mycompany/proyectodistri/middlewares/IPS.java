/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author federico
 */
public class IPS {
    String nombre;
    static int vac1;
    static int vac2;
    static int vac3;
    static int vacAux1;
    static int vacAux2;
    static int vacAux3;
    
    public IPS(String nombre) {
        this.nombre = nombre;
    }
    
    public static void buscarVacuna(Transaccion transaccion) throws IOException, InterruptedException, ExecutionException {
        //FirebaseConnection.conectar();
        
        vac1=Integer.parseInt(FirebaseConnection.buscarVac1().getCantidadVacuna());
        vac2=Integer.parseInt(FirebaseConnection.buscarVac2().getCantidadVacuna());
        vac3=Integer.parseInt(FirebaseConnection.buscarVac3().getCantidadVacuna());

        
        for(Peticiones p: transaccion.getP() ) {
            System.out.println("es "+p.getTipoVacuna());
            if(p.getTipoVacuna().equals("1"))
                vacAux1 = vac1 - Integer.parseInt(p.getCantidadVacuna());
            if(p.getTipoVacuna().equals("2"))
                vacAux2 = vac2 - Integer.parseInt(p.getCantidadVacuna());
            if(p.getTipoVacuna().equals("3"))
                vacAux3 = vac3 - Integer.parseInt(p.getCantidadVacuna());    
        }
        
        
        
        
        //return null;    
        
        
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    private int Integer(String cantidadVacuna) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
