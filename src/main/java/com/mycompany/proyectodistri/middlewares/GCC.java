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

    public GCC() {
        this.peticiones = new Stack<>();
    }

    public boolean distribuir(Transaccion transaccion) throws IOException, InterruptedException, ExecutionException, Exception {

        //int ID = (int) transaccion.getId();
        //System.out.println(2);
        //String nombre = null;
        Int stub;
        System.out.println("BALANCEADOR DE CARGA "+transaccion.getName()+":");
        if (transaccion.getIps() == 0) {
            stub = Client.conectar();
            System.out.println("Enviando transaccion a IPS 1... "+transaccion.getName());
            stub.buscarVacuna(transaccion);
        } else {
            stub = Client.conectarIPS2();
            System.out.println("Enviando transaccion a IPS 2... "+transaccion.getName());
            stub.buscarVacuna(transaccion);
        }

        Bloqueo.adquiere(transaccion, TipoBloqueo.ESCRITURA);
        System.out.println("Se bloquea la IPS de destino... "+transaccion.getName());
        List<Peticiones> peticionesNuevas = new ArrayList<>();

        if (peticiones.empty()) {

            Peticiones peticion = new Peticiones();
            peticion.setCantidadVacuna(String.valueOf(stub.getVacAux1()));
            peticion.setTipoVacuna("1");
            peticionesNuevas.add(peticion);
            //System.out.println("lista" + peticionesNuevas.get(0).getTipoVacuna()+' '+transaccion.getName());

            Peticiones peticion2 = new Peticiones();
            peticion2.setCantidadVacuna(String.valueOf(stub.getVacAux2()));
            peticion2.setTipoVacuna("2");
            peticionesNuevas.add(peticion2);
            //System.out.println("lista" + peticionesNuevas.get(1).getTipoVacuna()+""+transaccion.getName());

            Peticiones peticion3 = new Peticiones();
            peticion3.setCantidadVacuna(String.valueOf(stub.getVacAux3()));
            peticion3.setTipoVacuna("3");
            peticionesNuevas.add(peticion3);
            //System.out.println("lista" + peticionesNuevas.get(2).getTipoVacuna()+""+transaccion.getName());

        } else {
            System.out.println("VERIFICACIÓN HACIA ATRÁS " + transaccion.getName() + ":");
            for (Peticiones p : peticiones.peek()) {
                
                if (p.getTipoVacuna().equals("1")) {
                
                    if (Integer.parseInt(p.getCantidadVacuna()) == stub.getVac1()) {
                        System.out.println("Vacuna 1 TRUE");
                        Peticiones peticion = new Peticiones();
                        peticion.setCantidadVacuna(String.valueOf(stub.getVacAux1()));
                        peticion.setTipoVacuna("1");
                        peticionesNuevas.add(peticion);
                    } else {
                        System.err.println("Vacuna 1 FALSE "+transaccion.getName());
                        return false;
                    }
                }
                if (p.getTipoVacuna().equals("2")) {
                    if (Integer.parseInt(p.getCantidadVacuna()) == stub.getVac2()) {
                        System.out.println("Vacuna 2 TRUE");
                        Peticiones peticion = new Peticiones();
                        peticion.setCantidadVacuna(String.valueOf(stub.getVacAux2()));
                        peticion.setTipoVacuna("2");
                        peticionesNuevas.add(peticion);
                    } else {
                        System.err.println("Vacuna 2 FALSE "+transaccion.getName());
                        return false;
                    }
                }
                if (p.getTipoVacuna().equals("3")) {
                    if (Integer.parseInt(p.getCantidadVacuna()) == stub.getVac3()) {
                        System.out.println("Vacuna 3 TRUE");
                        Peticiones peticion = new Peticiones();
                        peticion.setCantidadVacuna(String.valueOf(stub.getVacAux3()));
                        peticion.setTipoVacuna("3");
                        peticionesNuevas.add(peticion);
                    } else {
                        System.err.println("Vacuna 3 FALSE "+transaccion.getName());
                        return false;
                    }
                }

            }
        }
        peticiones.push(peticionesNuevas);
        actualizarBD(peticionesNuevas);
        System.out.println("COMMIT EXITOSO "+transaccion.getName());

        return true;
    }

    public static void actualizarBD(List<Peticiones> peticionesNuevas) throws IOException, Exception {

        for (Peticiones p : peticionesNuevas) {
            if (p.getTipoVacuna().equals("1")) {
                Map<String, Object> data = new HashMap<>();
                data.put("cantidad", p.getCantidadVacuna());
                data.put("tipo", p.getTipoVacuna());
                String uid = "VAC1";
                FirebaseConnection.insertarDatos("vacuna1", uid, data);
            }
            if (p.getTipoVacuna().equals("2")) {
                Map<String, Object> data = new HashMap<>();
                data.put("cantidad", p.getCantidadVacuna());
                data.put("tipo", p.getTipoVacuna());
                String uid = "VAC2";
                FirebaseConnection.insertarDatos("vacuna2", uid, data);
            }
            if (p.getTipoVacuna().equals("3")) {
                Map<String, Object> data = new HashMap<>();
                data.put("cantidad", p.getCantidadVacuna());
                data.put("tipo", p.getTipoVacuna());
                String uid = "VAC3";
                FirebaseConnection.insertarDatos("vacuna3", uid, data);
            }
        }
    }
}
