/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import java.util.List;

/**
 *
 * @author Jorge
 */
public class FacturaVistaPrevia extends Factura{
    private String nombreEmisor;
    private String rfcEmisor;
    private String direccionEmisor;
    private String claveRegimenFiscalEmisor;
    private String regimenFiscalEmisor;
    private String codigoPostal;
    private String nombreReceptor;
    private String rfcReceptor;
    private String direccionReceptor;
    private String formaPago;
    private String usoCfdi;
    private String tipoRelacion;
    private String totalEnLetra;
    private Double totalImpFedTrasladados;
    private Double totalImpFedRetenidos;
    private Double totalImpLocTrasladados;
    private Double totalImpLocRetenidos;
    private LogoCompania logo;
    private String qrBase64;
    
    private List<ConceptoCompleto> conceptos;
    private List<FacturaRelacionada> facturasRelacionadas;

    /**
     * constructor
     */
    public FacturaVistaPrevia() {
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

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getUsoCfdi() {
        return usoCfdi;
    }

    public void setUsoCfdi(String usoCfdi) {
        this.usoCfdi = usoCfdi;
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public String getTotalEnLetra() {
        return totalEnLetra;
    }

    public void setTotalEnLetra(String totalEnLetra) {
        this.totalEnLetra = totalEnLetra;
    }

    public Double getTotalImpFedTrasladados() {
        return totalImpFedTrasladados;
    }

    public void setTotalImpFedTrasladados(Double totalImpFedTrasladados) {
        this.totalImpFedTrasladados = totalImpFedTrasladados;
    }

    public Double getTotalImpFedRetenidos() {
        return totalImpFedRetenidos;
    }

    public void setTotalImpFedRetenidos(Double totalImpFedRetenidos) {
        this.totalImpFedRetenidos = totalImpFedRetenidos;
    }

    public Double getTotalImpLocTrasladados() {
        return totalImpLocTrasladados;
    }

    public void setTotalImpLocTrasladados(Double totalImpLocTrasladados) {
        this.totalImpLocTrasladados = totalImpLocTrasladados;
    }

    public Double getTotalImpLocRetenidos() {
        return totalImpLocRetenidos;
    }

    public void setTotalImpLocRetenidos(Double totalImpLocRetenidos) {
        this.totalImpLocRetenidos = totalImpLocRetenidos;
    }

    public List<ConceptoCompleto> getConceptos() {
        return conceptos;
    }

    public void setConceptos(List<ConceptoCompleto> conceptos) {
        this.conceptos = conceptos;
    }

    public List<FacturaRelacionada> getFacturasRelacionadas() {
        return facturasRelacionadas;
    }

    public void setFacturasRelacionadas(List<FacturaRelacionada> facturasRelacionadas) {
        this.facturasRelacionadas = facturasRelacionadas;
    }

    public LogoCompania getLogo() {
        return logo;
    }

    public void setLogo(LogoCompania logo) {
        this.logo = logo;
    }

    public String getQrBase64() {
        return qrBase64;
    }

    public void setQrBase64(String qrBase64) {
        this.qrBase64 = qrBase64;
    }
}
