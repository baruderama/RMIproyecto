/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baru
 */
public class Server implements Int {
    
    public static List<Pruebita> listaNombresPruebas = new ArrayList<Pruebita>();
    public static List<Eps> listaEpsActivas= new ArrayList<Eps>();
    public static String actualEps;
    public static int vac1;
    public static int vac2;
    public static int vac3;
    public static int vacAux1;
    public static int vacAux2;
    public static int vacAux3;
     public Server() {}

     @Override
    public String sayHello() {
        return "Hello, world!";
    }
    
        
    public static void main(String args[]) {
        
        try {
            Server obj = new Server();
            Int stub = (Int) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1083);
            
            registry.bind("Hello", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public Pruebita sacarPrueba(String nombre,int num) throws RemoteException {
       Pruebita p= new Pruebita(nombre,num);
       return p;
    }

    @Override
    public List<Pruebita> buscarPruebita() throws RemoteException {
        
        //listaNombresPruebas=FirebaseConnection.listaNombresPruebas;
        
        if(listaNombresPruebas!=null){
            System.out.println(listaNombresPruebas.get(0).getNombre());
        listaNombresPruebas.forEach(d -> {
            System.out.println("bro"+d.getNombre());
        });
        }
        //return listaNombresPruebas;
         return listaNombresPruebas;
    }
    
    
    //return listaNombresPruebas;

    @Override
    public void setLista(List<Pruebita> lista) throws RemoteException {
        listaNombresPruebas=lista;
    }
    


    @Override
    public void setListaEps(List<Eps> eps) throws RemoteException {
        listaEpsActivas=eps;
    }

    @Override
    public List<Eps> getlistaEps() {
        return listaEpsActivas; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void entregarListaPeticiones(List<Peticiones> p) throws RemoteException {
        try {
            Eps.obtenerPeticiones(p);
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("entra4");
    }

    @Override
    public void enviarTransaccion(List<Peticiones> peticiones) throws RemoteException {
        GCC gcc = new GCC();
        Transaccion transaccion = new Transaccion(peticiones, gcc);
        transaccion.start();
        try {
            transaccion.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Ejecutando hilo: "+transaccion.getName());
        
        
    }

    @Override
    public void buscarVacuna(Transaccion t) throws RemoteException {
        try {
            IPS.buscarVacuna(t);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    

    
    
    
}
