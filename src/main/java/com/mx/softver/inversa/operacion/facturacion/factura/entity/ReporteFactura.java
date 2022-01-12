/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

/**
 *
 * @author Luis Avbax
 */
public class ReporteFactura extends FacturaVistaPrevia {
    String nombreEntidadFederativa;
    String concepto;
    String observacion; 

    public ReporteFactura() {
    }

    public String getNombreEntidadFederativa() {
        return nombreEntidadFederativa;
    }

    public void setNombreEntidadFederativa(String nombreEntidadFederativa) {
        this.nombreEntidadFederativa = nombreEntidadFederativa;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
