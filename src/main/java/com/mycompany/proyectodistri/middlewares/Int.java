/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author baru
 */
public interface Int extends Remote{
    public String sayHello() throws RemoteException;
    
    public Pruebita sacarPrueba(String nombre,int num) throws RemoteException;
    
    public List<Pruebita> buscarPruebita()throws RemoteException;
    
    public void setLista(List<Pruebita> lista)throws RemoteException;
           
    
    
    
}
