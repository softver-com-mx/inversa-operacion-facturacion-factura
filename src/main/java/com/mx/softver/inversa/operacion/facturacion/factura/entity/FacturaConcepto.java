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
public class FacturaConcepto extends Factura{
    private List<Concepto> conceptos;
    private List<FacturaRelacionada> facturasRelacionadas;

    /**
     * constructor
     */
    public FacturaConcepto() {}

    /**
     * @return the conceptos
     */
    public List<Concepto> getConceptos() {
        return conceptos;
    }

    /**
     * @param conceptos the conceptos to set
     */
    public void setConceptos(List<Concepto> conceptos) {
        this.conceptos = conceptos;
    }

    /**
     * @return the facturasRelacionadas
     */
    public List<FacturaRelacionada> getFacturasRelacionadas() {
        return facturasRelacionadas;
    }

    /**
     * @param facturasRelacionadas the facturasRelacionadas to set
     */
    public void setFacturasRelacionadas(List<FacturaRelacionada> facturasRelacionadas) {
        this.facturasRelacionadas = facturasRelacionadas;
    }
}
