/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author baru
 */
public interface Int extends Remote{
    String sayHello() throws RemoteException;
    
}
