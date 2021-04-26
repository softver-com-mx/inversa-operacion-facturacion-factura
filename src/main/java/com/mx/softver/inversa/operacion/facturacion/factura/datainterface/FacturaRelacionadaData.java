/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.datainterface;

import com.mx.softver.inversa.operacion.facturacion.factura.entity.Factura;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaRelacionada;
import java.util.List;

/**
 *
 * @author jhernandez
 */
public interface FacturaRelacionadaData {
    /**
     * metodo para crear la relacion entre dos facturas
     * @param idUsuario
     * @param entidad
     * @throws Exception 
     */
    public void crear(FacturaRelacionada entidad, int idUsuario) throws Exception;
    
    /**
     * metodo para obtener las opciones de facturas a relacionar
     * @param idEmpresa
     * @param idUsuario
     * @param entidad
     * @return 
     * @throws Exception 
     */
    public List<Factura> obtenerOpciones(int idEmpresa, Factura entidad, int idUsuario) throws Exception;
    
    /**
     * metodo para validar el id de una relacion
     * @param idRelacion
     * @return
     * @throws Exception 
     */
    public boolean verificarId(int idRelacion) throws Exception;
    
    /**
     * metodo para validar el id de alguna de las dos facturas relacionadas
     * @param idEmpresa
     * @param idFactura
     * @return
     * @throws Exception 
     */
    public boolean verificarIdFactura(int idEmpresa, int idFactura) throws Exception;
    
    /**
     * metodo para eliminar la relacion de dos facturas
     * @param entidad
     * @throws Exception 
     */
    public void eliminar(FacturaRelacionada entidad) throws Exception;
    
    /**
     * metodo para obtener el estatus de una factura
     * @param idFactura
     * @return
     * @throws Exception 
     */
    public String obtenerEstatus(int idFactura) throws Exception;
}
