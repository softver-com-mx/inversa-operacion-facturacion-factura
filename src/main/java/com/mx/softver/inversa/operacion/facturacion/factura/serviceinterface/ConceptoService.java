/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface;

import com.mx.softver.inversa.operacion.facturacion.factura.entity.Concepto;
import com.softver.comunes.entity.InfoAuditoria;

/**
 *
 * @author jhernandez
 */
public interface ConceptoService {
    /**
     * metodo para crear un concepto
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void crear(InfoAuditoria info, Concepto entidad) throws Exception;
    
    /**
     * metodo para obtener un concepto por id
     * @param info
     * @param filtro
     * @return
     * @throws Exception 
     */
    public Concepto obtenerPorId(InfoAuditoria info, Concepto filtro) throws Exception;
    
    /**
     * metodo para actualizar un concepto
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void actualizar(InfoAuditoria info, Concepto entidad) throws Exception;
    
    /**
     * metodo para eliminar un concepto
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void eliminar(InfoAuditoria info, Concepto entidad) throws Exception;
}
