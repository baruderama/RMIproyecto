/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import java.io.Serializable;

/**
 *
 * @author baru
 */
public class Peticiones implements Serializable{
    
    public String tipoVacuna;
    public String cantidadVacuna;

    public String getTipoVacuna() {
        return tipoVacuna;
    }

    public void setTipoVacuna(String tipoVacuna) {
        this.tipoVacuna = tipoVacuna;
    }

    public String getCantidadVacuna() {
        return cantidadVacuna;
    }

    public void setCantidadVacuna(String cantidadVacuna) {
        this.cantidadVacuna = cantidadVacuna;
    }
    
    
    
}
