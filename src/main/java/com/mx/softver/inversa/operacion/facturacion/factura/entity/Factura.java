/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import com.softver.comunes.exception.MaxLimitExceededSoftverException;
import com.softver.comunes.exception.OperationNotPermittedSoftverException;
import com.softver.comunes.exception.RequiredFieldSoftverException;
import com.softver.comunes.exception.ValueNotAllowedSoftverException;
import java.util.Date;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author jhernandez
 */
public class Factura {
    private int id;
    private int idSerie;
    private int idCliente;
    private String tipoFacturacion;
    private String tipoComprobante;
    private String serie;
    private int folio;
    private Date fechaExpedicion;
    private String claveFormaPago;
    private String claveMetodoPago;
    private String condicionPago;
    private String claveMoneda;
    private Double tipoCambio;
    private String claveUsoCfdi;
    private Double subtotal;
    private Double descuento;
    private Double total;
    private String numeroCuenta;
    private String version;
    private String uuid;
    private String claveTipoRelacion;
    private String estatus;
    private Double saldoAPagar;
    private int parcialidad;
    private String nombreCliente;
    
    private final String[] tiposComprobantes = {"I", "E", "T", "P", "N"};

    /**
     * constructor
     */
    public Factura() {}
    
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
     * @return the idSerie
     */
    public int getIdSerie() {
        return idSerie;
    }

    /**
     * @param idSerie the idSerie to set
     */
    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    /**
     * @return the id del cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the id cliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the tipoFacturacion
     */
    public String getTipoFacturacion() {
        return tipoFacturacion;
    }

    /**
     * @param tipoFacturacion the id tipoFacturacion to set
     * @throws java.lang.Exception
     */
    public void setTipoFacturacion(String tipoFacturacion) throws Exception{
        if (tipoFacturacion != null) {
            if (tipoFacturacion.isEmpty()) {
                throw new RequiredFieldSoftverException(
                    "El tipo de facturacion no debe estar vacío"
                );
            }
            if (!tipoFacturacion.equals("1") && !tipoFacturacion.equals("2")
                && !tipoFacturacion.equals("3") && !tipoFacturacion.equals("4")) {
                throw new OperationNotPermittedSoftverException(
                    "El tipo de facturacion ingresado no es valido"
                );
            }
        }
        this.tipoFacturacion = tipoFacturacion;
    }
    
    /**
     * @return the tipo comprobante
     */
    public String getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * @param tipoComprobante the tipo comprobante to set
     * @throws java.lang.Exception
     */
    public void setTipoComprobante(String tipoComprobante) throws Exception{
        if (tipoComprobante == null) {
            throw new RequiredFieldSoftverException(
                "El tipo de comprobante no debe ser nulo"
            );
        }
        if (tipoComprobante.isEmpty()) {
            throw new RequiredFieldSoftverException(
                "El tipo de comprobante no debe estar vacío"
            );
        }
        tipoComprobante = tipoComprobante.trim().toUpperCase();
        if (!ArrayUtils.contains(tiposComprobantes, tipoComprobante)) {
            throw new ValueNotAllowedSoftverException(
                "El tipo de comprobante ingresado es invalido"
            );
        }
        this.tipoComprobante = tipoComprobante;
    }
    
    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     * @throws java.lang.Exception
     */
    public void setSerie(String serie) throws Exception{
        if (serie == null) {
            throw new RequiredFieldSoftverException(
                "La serie no debe ser nula"
            );
        }
        if (serie.isEmpty()) {
            throw new RequiredFieldSoftverException(
                "La serie no debe estar vacía"
            );
        }
        serie = serie.trim().toUpperCase();
        if (serie.length() > 25) {
            throw new MaxLimitExceededSoftverException(
                "La serie no debe ser mayor a 25 caractéres"
            );
        }
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
     * @return the fechaExpedicion
     */
    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    /**
     * @param fechaExpedicion the fechaExpedicion to set
     * @throws java.lang.Exception
     */
    public void setFechaExpedicion(Date fechaExpedicion) throws Exception{
        if (fechaExpedicion == null) {
            throw new RequiredFieldSoftverException(
                "La fecha de expedicion no puede ser nula"
            );
        }
        this.fechaExpedicion = fechaExpedicion;
    }
    
    /**
     * @return the claveFormaPago
     */
    public String getClaveFormaPago() {
        return claveFormaPago;
    }

    /**
     * @param claveFormaPago the claveFormaPago to set
     * @throws java.lang.Exception
     */
    public void setClaveFormaPago(String claveFormaPago) throws Exception{
        if (claveFormaPago == null) {
            throw new RequiredFieldSoftverException(
                "La forma de pago es requerida"
            );
        }
        if (claveFormaPago.isEmpty()) {
            throw new RequiredFieldSoftverException(
                "La forma de pago no debe estar vacía"
            );
        }
        claveFormaPago = claveFormaPago.trim().toUpperCase();
        if (claveFormaPago.length() > 2) {
            throw new MaxLimitExceededSoftverException(
                "La forma de pago no debe ser mayor a 2 caractéres"
            );
        }
        this.claveFormaPago = claveFormaPago;
    }
    
    /**
     * @return the claveMetodoPago pago
     */
    public String getClaveMetodoPago() {
        return claveMetodoPago;
    }

    /**
     * @param claveMetodoPago the claveMetodoPago to set
     * @throws java.lang.Exception
     */
    public void setClaveMetodoPago(String claveMetodoPago) throws Exception{
        if (claveMetodoPago == null) {
            throw new RequiredFieldSoftverException(
                "El metodo de pago es requerido"
            );
        }
        if (claveMetodoPago.isEmpty()) {
            throw new RequiredFieldSoftverException(
                "El metodo de pago no debe estar vacío"
            );
        }
        claveMetodoPago = claveMetodoPago.trim().toUpperCase();
        if (claveMetodoPago.length() > 3) {
            throw new MaxLimitExceededSoftverException(
                "El metodo de pago no debe ser mayor a 3 caractéres"
            );
        }
        if (!claveMetodoPago.equals("PUE") && !claveMetodoPago.equals("PPD")) {
            throw new OperationNotPermittedSoftverException(
                "El metodo de pago ingresado no es valido"
            );
        }
        this.claveMetodoPago = claveMetodoPago;
    }
    
    /**
     * @return the condicionPago
     */
    public String getCondicionPago() {
        return condicionPago;
    }

    /**
     * @param condicionPago the condicionPago to set
     * @throws java.lang.Exception
     */    
    public void setCondicionPago(String condicionPago) throws Exception{
        if (condicionPago != null) {
            if (condicionPago.isEmpty()) {
                throw new RequiredFieldSoftverException(
                    "La condicion de pago no debe estar vacía"
                );
            }
            condicionPago = condicionPago.trim().toUpperCase();
            if (condicionPago.length() > 1000) {
                throw new MaxLimitExceededSoftverException(
                    "La condicion de pago no debe ser mayor a 300 caractéres"
                );
            }
        }
        this.condicionPago = condicionPago;
    }
    
    /**
     * @return the claveMoneda
     */
    public String getClaveMoneda() {
        return claveMoneda;
    }

    /**
     * @param claveMoneda the claveMoneda to set
     * @throws java.lang.Exception
     */
    public void setClaveMoneda(String claveMoneda) throws Exception{
        if (claveMoneda == null) {
            throw new RequiredFieldSoftverException(
                "La moneda es requerida"
            );
        }
        if (claveMoneda.isEmpty()) {
            throw new RequiredFieldSoftverException(
                "La moneda no debe estar vacía"
            );
        }
        claveMoneda = claveMoneda.trim().toUpperCase();
        if (claveMoneda.length() > 3) {
            throw new MaxLimitExceededSoftverException(
                "La moneda no debe ser mayor a 3 caractéres"
            );
        }
        this.claveMoneda = claveMoneda;
    }
    
    /**
     * @return the tipo cambio
     */
    public Double getTipoCambio() {
        return tipoCambio;
    }

    /**
     * @param tipoCambio the tipo cambio to set
     * @throws java.lang.Exception
     */
    public void setTipoCambio(Double tipoCambio) throws Exception{
        if (tipoCambio == null) {
            throw new RequiredFieldSoftverException(
                "El tipo de cambio no puede ser nulo"
            );
        }
        if (tipoCambio < 0) {
            throw new RequiredFieldSoftverException(
                "El tipo de cambio no puede ser menor a 0"
            );
        }
        this.tipoCambio = tipoCambio;
    }
    
    /**
     * @return the claveUsoCfdi
     */
    public String getClaveUsoCfdi() {
        return claveUsoCfdi;
    }

    /**
     * @param claveUsoCfdi the uso claveUsoCfdi to set
     * @throws java.lang.Exception
     */
    public void setClaveUsoCfdi(String claveUsoCfdi) throws Exception{
        if (claveUsoCfdi == null) {
            throw new RequiredFieldSoftverException(
                "El uso del cfdi es requerido"
            );
        }
        if (claveUsoCfdi.isEmpty()) {
            throw new RequiredFieldSoftverException(
                "El uso del cfdi no debe estar vacío"
            );
        }
        claveUsoCfdi = claveUsoCfdi.trim().toUpperCase();
        if (claveUsoCfdi.length() > 3) {
            throw new MaxLimitExceededSoftverException(
                "El uso del cfdi no debe ser mayor a 3 caractéres"
            );
        }
        this.claveUsoCfdi = claveUsoCfdi;
    }

    /**
     * @return the subtotal
     */
    public Double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     * @throws java.lang.Exception
     */
    public void setSubtotal(Double subtotal) throws Exception{
        if (subtotal == null) {
            throw new RequiredFieldSoftverException(
                "El subtotal no puede ser nulo"
            );
        }
        if (subtotal < 0) {
            throw new RequiredFieldSoftverException(
                "El subtotal de la factura no puede ser menor a 0"
            );
        }
        this.subtotal = subtotal;
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
                "El descuento de la factura no puede ser menor a 0"
            );
        }
        this.descuento = descuento;
    }
    
    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     * @throws java.lang.Exception
     */
    public void setTotal(Double total) throws Exception{
        if (total == null) {
            throw new RequiredFieldSoftverException(
                "El total no puede ser nulo"
            );
        }
        if (total < 0) {
            throw new RequiredFieldSoftverException(
                "El monto total de la factura no puede ser menor a 0"
            );
        }
        this.total = total;
    }
    
    /**
     * @return the numero cuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numero cuenta to set
     * @throws java.lang.Exception
     */
    public void setNumeroCuenta(String numeroCuenta) throws Exception{
        if (numeroCuenta != null) {
            if (numeroCuenta.isEmpty()) {
                throw new RequiredFieldSoftverException(
                    "El numero de cuenta no debe estar vacío"
                );
            }
            numeroCuenta = numeroCuenta.trim().toUpperCase();
            if (numeroCuenta.length() > 18) {
                throw new MaxLimitExceededSoftverException(
                    "El numero de cuenta no debe ser mayor a 4 digitos"
                );
            }
        }
        this.numeroCuenta = numeroCuenta;
    }
    
    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     * @throws java.lang.Exception
     */
    public void setVersion(String version) throws Exception{
        if (version == null) {
            throw new RequiredFieldSoftverException(
                "La version del cfdi es requerida"
            );
        }
        if (version.isEmpty()) {
            throw new RequiredFieldSoftverException(
                "La version del cfdi no debe estar vacía"
            );
        }
        version = version.trim().toUpperCase();
        if (version.length() > 3) {
            throw new MaxLimitExceededSoftverException(
                "La version del cfdi no debe ser mayor a 3 caractéres"
            );
        }
        this.version = version;
    }
    
    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     * @throws java.lang.Exception
     */
    public void setUuid(String uuid) throws Exception{
        this.uuid = uuid;
    }

    /**
     * @return the claveTipoRelacion
     */
    public String getClaveTipoRelacion() {
        return claveTipoRelacion;
    }

    /**
     * @param claveTipoRelacion the claveTipoRelacion to set
     */
    public void setClaveTipoRelacion(String claveTipoRelacion) {
        this.claveTipoRelacion = claveTipoRelacion;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Double getSaldoAPagar() {
        return saldoAPagar;
    }

    public void setSaldoAPagar(Double saldoAPagar) {
        this.saldoAPagar = saldoAPagar;
    }

    public int getParcialidad() {
        return parcialidad;
    }

    public void setParcialidad(int parcialidad) {
        this.parcialidad = parcialidad;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
