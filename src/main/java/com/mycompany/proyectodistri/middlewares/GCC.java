/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import static com.mycompany.proyectodistri.middlewares.JFramePaciente.secretkey;
import java.io.IOException;
import java.io.Serializable;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author federico
 */
public class GCC implements Serializable {

    Stack<List<Peticiones>> peticiones;
    IPS ips;
    int rand;

    public GCC() {
        this.peticiones = new Stack<>();
    }

    public boolean distribuir(Transaccion transaccion) throws IOException, InterruptedException, ExecutionException {

        /*Transaccion transaccion = transacciones.poll();
        
        if(transaccion==null)
            System.out.println("No hay transacciones en la cola!");*/
        rand = (int) Math.random() * 2;
        String nombre = null;

        if (rand == 0) {
            nombre = "ips1";
        }
        if (rand == 1) {
            nombre = "ips2";
        }
        /*if(nombre!=null && transaccion!=null)
            ips = transaccion.getPaciente().buscarIPS(nombre);
        else
            System.out.println("Error al buscar la IPS");*/
        //Int stub=Client.conectar();
        //stub.buscarVacuna(transaccion);
        Int stub=Client.conectar();
        System.out.println("hey");
        stub.buscarVacuna(transaccion);
        //ips.buscarVacuna(transaccion);
        Bloqueo.adquiere(transaccion, TipoBloqueo.ESCRITURA);
        /*
        if(peticiones.empty()){
            System.out.println("emty");
            peticiones.push(transaccion.getP());
            actualizarBD(transaccion.getP());
            return true;
        }
         */

        List<Peticiones> peticionesNuevas = new ArrayList<>();

        if (peticiones.empty()) {
            
            Peticiones peticion = new Peticiones();
            peticion.setCantidadVacuna(String.valueOf(stub.getVacAux1()));
            peticion.setTipoVacuna("1");
            peticionesNuevas.add(peticion);
            System.out.println("lista"+ peticionesNuevas.get(0).getTipoVacuna());
            Peticiones peticion2 = new Peticiones();
            peticion2.setCantidadVacuna(String.valueOf(stub.getVacAux2()));
            peticion2.setTipoVacuna("2");
            peticionesNuevas.add(peticion2);
            System.out.println("lista"+ peticionesNuevas.get(1).getTipoVacuna());
            Peticiones peticion3 = new Peticiones();
            peticion3.setCantidadVacuna(String.valueOf(stub.getVacAux3()));
            peticion3.setTipoVacuna("3");
            peticionesNuevas.add(peticion3);
            
            System.out.println("lista"+ peticionesNuevas.get(2).getTipoVacuna());
            
        } else {
        
            for (Peticiones p : peticiones.peek()) {
                if (p.getTipoVacuna().equals("1")) {
                    if (Integer.parseInt(p.getCantidadVacuna()) == stub.getVac1()) {
                        System.out.println("vacuna1");
                        Peticiones peticion = new Peticiones();
                        peticion.setCantidadVacuna(String.valueOf(stub.getVacAux1()));
                        peticion.setTipoVacuna("1");
                        peticionesNuevas.add(peticion);
                    } else {
                        return false;
                    }
                }
                if (p.getTipoVacuna().equals("2")) {
                    if (Integer.parseInt(p.getCantidadVacuna()) == stub.getVac2()) {
                        Peticiones peticion = new Peticiones();
                        peticion.setCantidadVacuna(String.valueOf(stub.getVacAux2()));
                        peticion.setTipoVacuna("2");
                        peticionesNuevas.add(peticion);
                    } else {
                        return false;
                    }
                }
                if (p.getTipoVacuna().equals("3")) {
                    if (Integer.parseInt(p.getCantidadVacuna()) == stub.getVac3()) {
                        Peticiones peticion = new Peticiones();
                        peticion.setCantidadVacuna(String.valueOf(stub.getVacAux3()));
                        peticion.setTipoVacuna("3");
                        peticionesNuevas.add(peticion);
                    } else {
                        return false;
                    }
                }

            }
        }
            peticiones.push(peticionesNuevas);
            //Transaccion transNueva = new Transaccion(peticionesNuevas, this);
            //FirebaseConnection.conectar();

            actualizarBD(peticionesNuevas);

            System.out.println("Éxito");
            return true;
        }
    

    public static void actualizarBD(List<Peticiones> peticionesNuevas) {
        //FirebaseConnection.conectar();
        
         
        for (Peticiones p : peticionesNuevas) {
            System.out.println("tipo VAC"+p.getTipoVacuna());
            if (p.getTipoVacuna().equals("1")) {
                Map<String, Object> data = new HashMap<>();
                data.put("cantidad", p.getCantidadVacuna());
                data.put("tipo", p.getTipoVacuna());
                String uid = "VAC1";
                System.out.println("que paso34"+p.getCantidadVacuna());
                FirebaseConnection.insertarDatos("vacuna1", uid, data);
            }
            if (p.getTipoVacuna().equals("2")) {
                Map<String, Object> data = new HashMap<>();
                data.put("cantidad", p.getCantidadVacuna());
                data.put("tipo", p.getTipoVacuna());
                String uid = "VAC2";
                System.out.println("que paso?");
                FirebaseConnection.insertarDatos("vacuna2", uid, data);
            }
            if (p.getTipoVacuna().equals("3")) {
                Map<String, Object> data = new HashMap<>();
                data.put("cantidad", p.getCantidadVacuna());
                data.put("tipo", p.getTipoVacuna());
                String uid = "VAC3";
                System.out.println("que paso? 3");
                FirebaseConnection.insertarDatos("vacuna3", uid, data);
            }
        }
    }
}
