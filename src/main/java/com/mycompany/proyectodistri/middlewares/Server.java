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
    private static int ips=0;
    private static GCC gcc = new GCC();
    private static String confir;
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

            System.err.println("Server Ready");
            FirebaseConnection.conectar();
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
        //GCC gcc = new GCC();
        if(ips==0){
            ips=1;
        }else{
            ips=0;
        }
        Transaccion transaccion = new Transaccion(peticiones, gcc, ips);
        transaccion.start();
        try {
            transaccion.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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

    @Override
    public  int getVac1() {
        return vac1;
    }

    @Override
    public  void setVac1(int vac1) {
        Server.vac1 = vac1;
    }

    @Override
    public  int getVac2() {
        return vac2;
    }

    @Override
    public  void setVac2(int vac2) {
        Server.vac2 = vac2;
    }

    @Override
    public int getVac3() {
        return vac3;
    }

    @Override
    public  void setVac3(int vac3) {
        Server.vac3 = vac3;
    }

    @Override
    public  int getVacAux1() {
        return vacAux1;
    }

    @Override
    public void setVacAux1(int vacAux1) {
        Server.vacAux1 = vacAux1;
    }

    @Override
    public  int getVacAux2() {
        return vacAux2;
    }

    @Override
    public  void setVacAux2(int vacAux2) {
        Server.vacAux2 = vacAux2;
    }

    @Override
    public  int getVacAux3() {
        return vacAux3;
    }

    @Override
    public  void setVacAux3(int vacAux3) {
        Server.vacAux3 = vacAux3;
    }

    @Override
    public  String getConfir() {
        return confir;
    }

    @Override
    public  void setConfir(String confir) {
        Server.confir = confir;
    }

    @Override
    public void sentConfirm(String confir) throws RemoteException {
        Eps.confirmacion(confir); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    

    
    
    
}
