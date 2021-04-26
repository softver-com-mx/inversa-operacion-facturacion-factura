/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import com.softver.comunes.entity.Archivo;

/**
 *
 * @author jorge
 */
public class LogoCompania extends Archivo{
    private String base64;

    public LogoCompania() {
        super();
    }
    
    public LogoCompania(String nombre) {
        super();
        setNombre(nombre);
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
