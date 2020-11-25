/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;

/**
 *
 * @author baru
 */
public class Eps implements Serializable{
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
    
    
    public static void obtenerPeticiones(List<Peticiones> p) throws InterruptedException, ExecutionException, RemoteException, Exception{
        System.out.println("Se leen las vacunas del documento");
       Peticiones peCopia= new Peticiones();
       int cantidad1=0;
       int cantidad2=0;
       int cantidad3=0;
        for(Peticiones s:p){
            if(s.getTipoVacuna().equals("1"))
                cantidad1=Integer.parseInt(s.getCantidadVacuna())+cantidad1;
            if(s.getTipoVacuna().equals("2"))
                cantidad2=Integer.parseInt(s.getCantidadVacuna())+cantidad2;
            if(s.getTipoVacuna().equals("3"))
                cantidad3=Integer.parseInt(s.getCantidadVacuna())+cantidad3;
            //System.out.println("Typo"+s.getTipoVacuna());
            
            //System.out.println("Cantidad"+s.getCantidadVacuna());
            
            
            
        }
        armartrans(cantidad1,cantidad2,cantidad3);
    }
    
    public static void armartrans(int cant1,int cant2, int cant3) throws InterruptedException, ExecutionException, RemoteException, Exception{
        boolean conf1=false,conf2=false,conf3=false;
        List<Peticiones> transaccion=new ArrayList<Peticiones>();
         try{
             //if(FirebaseConnection.bd==null)
           //FirebaseConnection.conectar();
        }catch(Exception e){
            
        }
         
         Peticiones Vac1= FirebaseConnection.buscarVac1();
         int cant1Bd=Integer.parseInt(Vac1.getCantidadVacuna());
         Peticiones Vac2= FirebaseConnection.buscarVac2();
         int cant2Bd=Integer.parseInt(Vac2.getCantidadVacuna());
         Peticiones Vac3= FirebaseConnection.buscarVac3();
         int cant3Bd=Integer.parseInt(Vac3.getCantidadVacuna());
         
         //System.out.println("valor de la bd"+Vac1.getCantidadVacuna());
         
         if(cant1<=cant1Bd)
             conf1=true;
         if(cant1<=cant2Bd)
             conf2=true;
         if(cant1<=cant3Bd)
             conf3=true;
         
         String cant1String=String.valueOf(cant1);
         String cant2String=String.valueOf(cant2);
         String cant3String=String.valueOf(cant3);
         if(conf1){
             Peticiones pAux=new Peticiones();
             pAux.setCantidadVacuna(cant1String);
             pAux.setTipoVacuna("1");
             transaccion.add(pAux);
             //System.out.println("si llena");
         }else{
             System.out.println("FALTA DE EXISTENCIAS, SE INICIALIZA EN CERO");
             Peticiones pAux=new Peticiones();
             pAux.setCantidadVacuna("0");
             pAux.setTipoVacuna("1");
             transaccion.add(pAux);
             
         }
         
         if(conf2){
             Peticiones pAux=new Peticiones();
             pAux.setCantidadVacuna(cant2String);
             pAux.setTipoVacuna("2");
             transaccion.add(pAux);
         }else{
             System.out.println("FALTA DE EXISTENCIAS, SE INICIALIZA EN CERO");
             Peticiones pAux=new Peticiones();
             pAux.setCantidadVacuna("0");
             pAux.setTipoVacuna("2");
             transaccion.add(pAux);
         }
         
         if(conf3){
             Peticiones pAux=new Peticiones();
             pAux.setCantidadVacuna(cant3String);
             pAux.setTipoVacuna("3");
             transaccion.add(pAux);
         }else{
             System.out.println("FALTA DE EXISTENCIAS, SE INICIALIZA EN CERO");
             Peticiones pAux=new Peticiones();
             pAux.setCantidadVacuna("0");
             pAux.setTipoVacuna("3");
             transaccion.add(pAux);
         }
         
         for(Peticiones s:transaccion){
             System.out.println("cantidad "+s.getCantidadVacuna());
             System.out.println("tipo "+s.getTipoVacuna());

         }
         
         //FirebaseConnection.bd.close();
         
         Int stub= Client.conectarGcc();
         System.out.println("Se envia la transaccion al Gcc");
         stub.enviarTransaccion(transaccion);
         
         }
    
    public static void confirmacion(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
         
}
