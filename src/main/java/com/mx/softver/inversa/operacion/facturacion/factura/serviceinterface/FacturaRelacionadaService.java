/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface;

import com.softver.comunes.entity.InfoAuditoria;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Factura;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaRelacionada;
import java.util.List;

/**
 *
 * @author jhernandez
 */
public interface FacturaRelacionadaService {
    /**
     * metodo para crear la relacion entre dos facturas
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void crear(InfoAuditoria info, FacturaRelacionada entidad) throws Exception;
    
    /**
     * metodo para crear la relacion entre dos facturas
     * @param info
     * @param filtro
     * @return 
     * @throws Exception 
     */
    public List<Factura> obtenerOpciones(InfoAuditoria info, Factura filtro) throws Exception;
    
    /**
     * metodo para eliminar la relacion entre dos facturas
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void eliminar(InfoAuditoria info, FacturaRelacionada entidad) throws Exception;
}
