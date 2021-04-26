/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.datainterface;

import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.ConceptoComprobante;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.EmisorComprobante;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.ReceptorComprobante;
import java.util.List;

/**
 *
 * @author jhernandez
 */
public interface ComprobanteData {
    /**
     * metodo para obtener el emisor de un comprobante
     * @param idEmpresa
     * @return
     * @throws Exception 
     */
    public EmisorComprobante obtenerEmisor(int idEmpresa) throws Exception;
    
    /**
     * metodo para obtener el Receptor del comprobante fiscal
     * @param idFactura
     * @return
     * @throws Exception 
     */
    public ReceptorComprobante obtenerReceptor(int idFactura) throws Exception;
    
    /**
     * metodo para obtener los conceptos de un comprobante
     * @param idFactura
     * @return 
     */
    public List<ConceptoComprobante> obtenerConceptos(int idFactura) throws Exception; 
    
    /**
     * metodo para obtener el lugar de expedicion de un comprobante
     * @param idEmpresa
     * @return
     * @throws Exception 
     */
    public String obtenerLugarExpedicion(int idEmpresa) throws Exception;
}
