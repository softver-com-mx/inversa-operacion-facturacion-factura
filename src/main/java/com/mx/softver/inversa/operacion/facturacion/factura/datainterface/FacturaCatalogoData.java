/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.datainterface;

/**
 *
 * @author jhernandez
 */
public interface FacturaCatalogoData {
    /**
     * metodo para verificar la existencia de una clave de forma pago
     * @param claveFormaPago
     * @return
     * @throws Exception 
     */
    public boolean verificarClaveFormaPago(String claveFormaPago) throws Exception;
    
    /**
     * metodo para verificar la existencia de una clave de moneda
     * @param claveMoneda
     * @return
     * @throws Exception 
     */
    public boolean verificarClaveMoneda(String claveMoneda) throws Exception;
    
    /**
     * metodo para verificar la existencia de una clave de uso del cfdi
     * @param claveUsoCfdi
     * @return
     * @throws Exception 
     */
    public boolean verificarClaveUsoCfdi(String claveUsoCfdi) throws Exception;
    
    /**
     * metodo para verificar la existencia de una clave de tipo de relacion
     * @param claveTipoRelacion
     * @return
     * @throws Exception 
     */
    public boolean verificarClaveTipoRelacion(String claveTipoRelacion) throws Exception;
}
