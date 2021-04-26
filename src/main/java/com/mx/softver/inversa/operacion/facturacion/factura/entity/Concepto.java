/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import com.softver.comunes.exception.RequiredFieldSoftverException;
import java.util.List;

/**
 *
 * @author jhernandez
 */
public class Concepto {
    private int id;
    private int idFactura;
    private int idVenta;
    private int idArticulo;
    private String nombreArticulo;
    private Double cantidad;
    private Double valorUnitario;
    private Double descuento;
    private Double importe;
    private String observacion;
    
    private List<Impuesto> impuestos;

    /**
     * constructor
     */
    public Concepto() {}
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id factura
     */
    public int getIdFactura() {
        return idFactura;
    }

    /**
     * @param idFactura the id factura to set
     */
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    
    /**
     * @return the idArticulo
     */
    public int getIdArticulo() {
        return idArticulo;
    }

    /**
     * @param idArticulo the idArticulo to set
     */
    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    /**
     * @return the nombreArticulo
     */
    public String getNombreArticulo() {
        return nombreArticulo;
    }

    /**
     * @param nombreArticulo the nombreArticulo to set
     */
    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    
    /**
     * @param cantidad the cantidad to set
     * @throws java.lang.Exception
     */
    public void setCantidad(Double cantidad) throws Exception{
        if (cantidad == null) {
            throw new RequiredFieldSoftverException(
                "La cantidad no puede ser nula"
            );
        }
        if (cantidad <= 0) {
            throw new RequiredFieldSoftverException(
                "La cantidad no puede ser menor o igual a 0"
            );
        }
        this.cantidad = cantidad;
    }

    
    /**
     * @return the valorUnitario
     */
    public Double getValorUnitario() {
        return valorUnitario;
    }

    
    /**
     * @param valorUnitario the valorUnitario to set
     * @throws java.lang.Exception
     */
    public void setValorUnitario(Double valorUnitario) throws Exception{
        if (valorUnitario == null) {
            throw new RequiredFieldSoftverException(
                "El valor unitario no puede ser nulo"
            );
        }
        if (valorUnitario < 0) {
            throw new RequiredFieldSoftverException(
                "El valor unitario no puede ser menor a 0"
            );
        }
        this.valorUnitario = valorUnitario;
    }

    
    /**
     * @return the descuento
     */
    public Double getDescuento() {
        return descuento;
    }

    
    /**
     * @param descuento the descuento to set
     * @throws java.lang.Exception
     */
    public void setDescuento(Double descuento) throws Exception{
        if (descuento == null) {
            throw new RequiredFieldSoftverException(
                "El descuento no puede ser nulo"
            );
        }
        if (descuento < 0) {
            throw new RequiredFieldSoftverException(
                "El descuento no puede ser menor a 0"
            );
        }
        this.descuento = descuento;
    }

    
    /**
     * @return the importe
     */
    public Double getImporte() {
        return importe;
    }

    
    /**
     * @param importe the importe to set
     * @throws java.lang.Exception
     */
    public void setImporte(Double importe) throws Exception{
        if (importe == null) {
            throw new RequiredFieldSoftverException(
                "El importe no puede ser nulo"
            );
        }
        if (importe < 0) {
            throw new RequiredFieldSoftverException(
                "El importe no puede ser menor a 0"
            );
        }
        this.importe = importe;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     * @throws java.lang.Exception
     */
    public void setObservacion(String observacion) throws Exception{
        if (observacion != null) {
            if (observacion.isEmpty()) {
                throw new RequiredFieldSoftverException(
                    "La observacion no puede ser vacia"
                );
            }
            observacion = observacion.trim().toUpperCase();
            if (observacion.length() > 300) {
                throw new RequiredFieldSoftverException(
                    "La observacion no puede contener mas de 300 caracteres"
                );
            }
        }
        this.observacion = observacion;
    }

    /**
     * @return the impuestos
     */
    public List<Impuesto> getImpuestos() {
        return impuestos;
    }

    /**
     * @param impuestos the impuestos to set
     */
    public void setImpuestos(List<Impuesto> impuestos) {
        this.impuestos = impuestos;
    }
}
