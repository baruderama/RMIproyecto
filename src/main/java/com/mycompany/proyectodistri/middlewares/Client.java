/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author baru
 */
public class Client {
    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        System.out.println(host);
        try {
            Registry registry;
            registry = LocateRegistry.getRegistry(1083);
            Int stub = (Int) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
}
