/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import java.util.Date;

/**
 *
 * @author jhernandez
 */
public class FacturaRelacionada {
    private int id;
    private int idFactura;
    private int idFacturaRelacionada;
    private String claveTipoRelacion;
    private String uuid;
    private String serie;
    private int folio;
    private Date fechaCreacion;
    private String tipoComprobante;

    /**
     * constructor
     */
    public FacturaRelacionada() {}

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
     * @return the idFactura
     */
    public int getIdFactura() {
        return idFactura;
    }

    /**
     * @param idFactura the idFactura to set
     */
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    /**
     * @return the idFacturaRelacionada
     */
    public int getIdFacturaRelacionada() {
        return idFacturaRelacionada;
    }

    /**
     * @param idFacturaRelacionada the idFacturaRelacionada to set
     */
    public void setIdFacturaRelacionada(int idFacturaRelacionada) {
        this.idFacturaRelacionada = idFacturaRelacionada;
    }

    public String getClaveTipoRelacion() {
        return claveTipoRelacion;
    }

    public void setClaveTipoRelacion(String claveTipoRelacion) {
        this.claveTipoRelacion = claveTipoRelacion;
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
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the folio
     */
    public int getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the tipoComprobante
     */
    public String getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * @param tipoComprobante the tipoComprobante to set
     */
    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }
}
