/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import java.util.List;

/**
 *
 * @author jhernandez
 */
public class UuidConceptos {
    private String uuid;
    private String nombreEmisor;
    private String rfcEmisor;
    private String direccionEmisor;
    private String claveRegimenFiscalEmisor;
    private String regimenFiscalEmisor;
    private String codigoPostal;
    private String nombreReceptor;
    private String rfcReceptor;
    private String direccionReceptor;
    private LogoCompania logo;
    
    private List<ConceptoCompleto> conceptos;

    /**
     * constructor
     */
    public UuidConceptos() {}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<ConceptoCompleto> getConceptos() {
        return conceptos;
    }

    public void setConceptos(List<ConceptoCompleto> conceptos) {
        this.conceptos = conceptos;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getRfcEmisor() {
        return rfcEmisor;
    }

    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }

    public String getDireccionEmisor() {
        return direccionEmisor;
    }

    public void setDireccionEmisor(String direccionEmisor) {
        this.direccionEmisor = direccionEmisor;
    }

    public String getClaveRegimenFiscalEmisor() {
        return claveRegimenFiscalEmisor;
    }

    public void setClaveRegimenFiscalEmisor(String claveRegimenFiscalEmisor) {
        this.claveRegimenFiscalEmisor = claveRegimenFiscalEmisor;
    }

    public String getRegimenFiscalEmisor() {
        return regimenFiscalEmisor;
    }

    public void setRegimenFiscalEmisor(String regimenFiscalEmisor) {
        this.regimenFiscalEmisor = regimenFiscalEmisor;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public String getRfcReceptor() {
        return rfcReceptor;
    }

    public void setRfcReceptor(String rfcReceptor) {
        this.rfcReceptor = rfcReceptor;
    }

    public String getDireccionReceptor() {
        return direccionReceptor;
    }

    public void setDireccionReceptor(String direccionReceptor) {
        this.direccionReceptor = direccionReceptor;
    }

    public LogoCompania getLogo() {
        return logo;
    }

    public void setLogo(LogoCompania logo) {
        this.logo = logo;
    }
}
