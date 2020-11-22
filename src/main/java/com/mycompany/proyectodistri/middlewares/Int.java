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
    
    public void setListaEps(List<Eps> eps)throws RemoteException;
    
    public List<Eps> getlistaEps() throws RemoteException;
    
    public void entregarListaPeticiones(List<Peticiones> p)throws RemoteException;
    
    public void enviarTransaccion(List<Peticiones> p)throws RemoteException;
    
    public void buscarVacuna(Transaccion transaccion)throws RemoteException;
    
    //public boolean pacienteGuardar(Paciente paciente);
    
    
    
           
    
    
    
}
