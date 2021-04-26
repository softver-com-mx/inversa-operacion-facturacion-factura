/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface;

import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaConcepto;
import com.softver.comunes.entity.InfoAuditoria;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.Comprobante;

/**
 *
 * @author jhernandez
 */
public interface ComprobanteService {
    /**
     * metodo para llenar el objeto comprobante
     * @param info
     * @param entidad
     * @return
     * @throws Exception 
     */
    public Comprobante procesar(InfoAuditoria info, FacturaConcepto entidad) throws Exception;
}
