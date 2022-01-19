/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author jhernandez
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Cfdi {
    
    private int id;
    private String tipo;
    private String uuid;
    private String rfcEmisor;
    private String rfcReceptor;
    private String rfcPac;
    private String fechaEmision;
    private String fechaTimbrado;
    private boolean cancelado;
    private String xml;
    private String total;
    private String estadoCancelacion;
    private String acuseCancelacion;
    private String fechaCancelacion;
    private String motivoCancelacion;
    private String folioSustitucion;

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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the rfcEmisor
     */
    public String getRfcEmisor() {
        return rfcEmisor;
    }

    /**
     * @param rfcEmisor the rfcEmisor to set
     */
    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }

    /**
     * @return the rfcReceptor
     */
    public String getRfcReceptor() {
        return rfcReceptor;
    }

    /**
     * @param rfcReceptor the rfcReceptor to set
     */
    public void setRfcReceptor(String rfcReceptor) {
        this.rfcReceptor = rfcReceptor;
    }

    /**
     * @return the rfcPac
     */
    public String getRfcPac() {
        return rfcPac;
    }

    /**
     * @param rfcPac the rfcPac to set
     */
    public void setRfcPac(String rfcPac) {
        this.rfcPac = rfcPac;
    }

    /**
     * @return the fechaEmision
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the fechaTimbrado
     */
    public String getFechaTimbrado() {
        return fechaTimbrado;
    }

    /**
     * @param fechaTimbrado the fechaTimbrado to set
     */
    public void setFechaTimbrado(String fechaTimbrado) {
        this.fechaTimbrado = fechaTimbrado;
    }
    
    /**
     * @return the cancelado
     */
    public boolean getCancelado() {
        return cancelado;
    }

    /**
     * @param cancelado the cancelado to set
     */
    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    /**
     * @return the xml
     */
    public String getXml() {
        return xml;
    }

    /**
     * @param xml the xml to set
     */
    public void setXml(String xml) {
        this.xml = xml;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the estado de cancelacion
     */
    public String getEstadoCancelacion() {
        return estadoCancelacion;
    }

    /**
     * @param estadoCancelacion the estado de cancelacion to set
     */
    public void setEstadoCancelacion(String estadoCancelacion) {
        this.estadoCancelacion = estadoCancelacion;
    }

    /**
     * @return the acuseCancelacion
     */
    public String getAcuseCancelacion() {
        return acuseCancelacion;
    }

    /**
     * @param acuseCancelacion the acuseCancelacion to set
     */
    public void setAcuseCancelacion(String acuseCancelacion) {
        this.acuseCancelacion = acuseCancelacion;
    }

    /**
     * @return the fechaCancelacion
     */
    public String getFechaCancelacion() {
        return fechaCancelacion;
    }

    /**
     * @param fechaCancelacion the fechaCancelacion to set
     */
    public void setFechaCancelacion(String fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public String getFolioSustitucion() {
        return folioSustitucion;
    }

    public void setFolioSustitucion(String folioSustitucion) {
        this.folioSustitucion = folioSustitucion;
    }
}
