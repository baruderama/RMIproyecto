/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author baru
 */
public class Client {
    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        System.out.println(host);
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        try {
            Registry registry;
            registry = LocateRegistry.getRegistry(1083);
            Int stub = (Int) registry.lookup("Hello");
            
            /*
            String response = stub.sayHello();
            System.out.println("pon tu nombre");
            String nombre= myObj.nextLine();
            System.out.println("pon tu numero");
            int  num= Integer.parseInt(myObj.nextLine());
            Pruebita response2= stub.sacarPrueba(nombre,num);
            System.out.println("response: " + response);
            System.out.println("response: " + response2.getNombre()+" "+response2.getNum());
            */

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public static Int conectar(){
        
        try{
            Registry registry;

            //registry = LocateRegistry.getRegistry("192.168.0.16",1083);//REMOTO
            registry = LocateRegistry.getRegistry(1083); //LOCAL
            Int stub = (Int) registry.lookup("Hello");
            return stub;
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<Pruebita> buscarPruebita() throws RemoteException{
        
        Int stub= conectar();
        System.out.println("hola");
        return stub.buscarPruebita();
    }
    
    public static Int conectarGcc(){
        
        try{
            Registry registry;

            registry = LocateRegistry.getRegistry("192.168.0.16",1083);//REMOTO
            //registry = LocateRegistry.getRegistry(1087); //LOCAL
            Int stub = (Int) registry.lookup("Hello");
            return stub;
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }
    
   
    
}
