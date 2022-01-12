/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import java.util.List;

/**
 *
 * @author softv
 */
public class PagoFactura extends Factura{
    private int idPagoFactura;
    private int idFactura;
    private int idEmpresa;
    private List<PagoFacturaDetalle> listaPagos;
    private String folioCotizacion;
    private double saldoInsoluto;
    private String estado;
    private String rfc;
    private String formaPago;

    public PagoFactura() {
    }

    public int getIdPagoFactura() {
        return idPagoFactura;
    }

    public void setIdPagoFactura(int idPagoFactura) {
        this.idPagoFactura = idPagoFactura;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
        

    public List<PagoFacturaDetalle> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(List<PagoFacturaDetalle> listaPagos) {
        this.listaPagos = listaPagos;
    }


    public String getFolioCotizacion() {
        return folioCotizacion;
    }

    public void setFolioCotizacion(String folioCotizacion) {
        this.folioCotizacion = folioCotizacion;
    }

    public double getSaldoInsoluto() {
        return saldoInsoluto;
    }

    public void setSaldoInsoluto(double saldoInsoluto) {
        this.saldoInsoluto = saldoInsoluto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
        
}
