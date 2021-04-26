/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

/**
 *
 * @author Jorge
 */
public class ConceptoCompleto extends Concepto{
    private String claveProdServ;
    private String descripcionClaveProdServ;
    private String claveUnidad;
    private String descripcion;
    private String noIdentificacion;
    private String unidad;

    public ConceptoCompleto() {
    }

    public String getClaveProdServ() {
        return claveProdServ;
    }

    public void setClaveProdServ(String claveProdServ) {
        this.claveProdServ = claveProdServ;
    }
    
    public String getDescripcionClaveProdServ() {
        return descripcionClaveProdServ;
    }

    public void setDescripcionClaveProdServ(String descripcionClaveProdServ) {
        this.descripcionClaveProdServ = descripcionClaveProdServ;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
