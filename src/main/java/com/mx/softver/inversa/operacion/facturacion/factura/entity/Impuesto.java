/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import com.softver.comunes.exception.RequiredFieldSoftverException;

/**
 *
 * @author jhernandez
 */
public class Impuesto {
    private int id;
    private int idConcepto;
    private int idArticuloImpuesto;
    private Double totalImpuesto;
    private String claveImpuestoSat;
    private String nombre;
    private String origen;
    private String baseOAcumulado;
    private String tipoImpuesto;
    private String factor;
    private Double tasaOCuota;

    /**
     * constructor
     */
    public Impuesto() {}

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
     * @return the idConcepto
     */
    public int getIdConcepto() {
        return idConcepto;
    }

    /**
     * @param idConcepto the idConcepto to set
     */
    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    /**
     * @return the idArticuloImpuesto
     */
    public int getIdArticuloImpuesto() {
        return idArticuloImpuesto;
    }

    /**
     * @param idArticuloImpuesto the idArticuloImpuesto to set
     */
    public void setIdArticuloImpuesto(int idArticuloImpuesto) {
        this.idArticuloImpuesto = idArticuloImpuesto;
    }
    
    /**
     * @return the totalImpuesto
     */
    public Double getTotalImpuesto() {
        return totalImpuesto;
    }

    /**
     * @param totalImpuesto the totalImpuesto to set
     * @throws java.lang.Exception
     */
    public void setTotalImpuesto(Double totalImpuesto) throws Exception{
        if (totalImpuesto == null) {
            throw new RequiredFieldSoftverException(
                "El total del impuesto no puede ser nulo"
            );
        }
        if (totalImpuesto < 0) {
            throw new RequiredFieldSoftverException(
                "El total del impuesto no puede ser menor a 0"
            );
        }
        this.totalImpuesto = totalImpuesto;
    }

    /**
     * @return the claveImpuestoSat
     */
    public String getClaveImpuestoSat() {
        return claveImpuestoSat;
    }

    /**
     * @param claveImpuestoSat the claveImpuestoSat to set
     */
    public void setClaveImpuestoSat(String claveImpuestoSat) {
        this.claveImpuestoSat = claveImpuestoSat;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the baseOAcumulado
     */
    public String getBaseOAcumulado() {
        return baseOAcumulado;
    }

    /**
     * @param baseOAcumulado the baseOAcumulado to set
     */
    public void setBaseOAcumulado(String baseOAcumulado) {
        this.baseOAcumulado = baseOAcumulado;
    }

    /**
     * @return the tipoImpuesto
     */
    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    /**
     * @param tipoImpuesto the tipoImpuesto to set
     */
    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    /**
     * @return the factor
     */
    public String getFactor() {
        return factor;
    }

    /**
     * @param factor the factor to set
     */
    public void setFactor(String factor) {
        this.factor = factor;
    }

    /**
     * @return the tasaOCuota
     */
    public Double getTasaOCuota() {
        return tasaOCuota;
    }

    /**
     * @param tasaOCuota the tasaOCuota to set
     */
    public void setTasaOCuota(Double tasaOCuota) {
        this.tasaOCuota = tasaOCuota;
    }
}
