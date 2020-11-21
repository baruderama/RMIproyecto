/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

/**
 *
 * @author fede1
 */
public class Transaccion extends Thread{
    private Paciente paciente;
    private int vacunas;
    private String laboratorio;
    private GCC gcc;
    private int rand;

    public Transaccion(Paciente paciente, int vacunas, String laboratorio, GCC gcc) {
        this.paciente = paciente;
        this.vacunas = vacunas;
        this.laboratorio = laboratorio;
        this.gcc = gcc;
    }
    
    public void run(){       
        gcc.distribuir();
        System.out.println("Inicia el hilo de la transaccion");
    }

    public int getVacunas() {
        return vacunas;
    }

    public void setVacunas(int vacunas) {
        this.vacunas = vacunas;
    }    

    public Paciente getPaciente() {
        return paciente;
    }

    public String getLaboratorio() {
        return laboratorio;
    }


    public double getRand() {
        return rand;
    }

    public static int getMIN_PRIORITY() {
        return MIN_PRIORITY;
    }

    public static int getNORM_PRIORITY() {
        return NORM_PRIORITY;
    }

    public static int getMAX_PRIORITY() {
        return MAX_PRIORITY;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }


    public void setRand(int rand) {
        this.rand = rand;
    }
    
    
}
