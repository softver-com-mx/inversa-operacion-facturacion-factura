/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import java.util.Date;

/**
 *
 * @author softv
 */
public class PagoFacturaDetalle {
    private int id;
    private int idPagoFactura;
    private double monto;
    private Date fechaAbono;

    public PagoFacturaDetalle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPagoFactura() {
        return idPagoFactura;
    }

    public void setIdPagoFactura(int idPagoFactura) {
        this.idPagoFactura = idPagoFactura;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(Date fechaAbono) {
        this.fechaAbono = fechaAbono;
    }
    
    
}
