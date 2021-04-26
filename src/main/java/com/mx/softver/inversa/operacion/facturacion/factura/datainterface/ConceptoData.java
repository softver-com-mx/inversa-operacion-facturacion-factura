/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.datainterface;


import com.mx.softver.inversa.operacion.facturacion.factura.entity.Concepto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Impuesto;
import java.util.List;

/**
 *
 * @author jhernandez
 */
public interface ConceptoData {
    /**
     * metodo para crear un registro
     * @param idFactura
     * @param entidad
     * @param idUsuario
     * @throws Exception 
     */
    public void crear(int idFactura, Concepto entidad, int idUsuario) throws Exception;
    
    /**
     * metodo para obtener un concepto por id
     * @param idFactura
     * @param idConcepto
     * @return
     * @throws Exception 
     */
    public Concepto obtenerPorId(int idFactura, int idConcepto) throws Exception;
    
    /**
     * metodo para obtener los impuestos de un concepto
     * @param idConcepto
     * @return
     * @throws Exception 
     */
    public List<Impuesto> obtenerImpuestos(int idConcepto) throws Exception;
    
    /**
     * metodo para crear el impuesto de un concepto
     * @param idConcepto
     * @param entidad
     * @param idUsuario
     * @throws Exception 
     */
    public void crearImpuesto(int idConcepto, Impuesto entidad, int idUsuario) throws Exception;
    
    /**
     * metodo para verificar la existencia de un id
     * @param idFactura
     * @param idConcepto
     * @return
     * @throws Exception 
     */
    public boolean verificarId(int idFactura, int idConcepto) throws Exception;
    
    /**
     * metodo para verificar el id de un impuesto
     * @param idConcepto
     * @param idImpuesto
     * @return
     * @throws Exception 
     */
    public boolean verificarIdImpuesto(int idConcepto, int idImpuesto) throws Exception;
    
    /**
     * metodo para validar el id de la factura al que pertenece un concepto
     * @param idEmpresa
     * @param idFactura
     * @return
     * @throws Exception 
     */
    public boolean verificarIdFactura(int idEmpresa, int idFactura) throws Exception;
    
    /**
     * metodo para actualizar una factura
     * @param idFactura
     * @param entidad
     * @param idUsuario
     * @throws Exception 
     */
    public void actualizar(int idFactura, Concepto entidad, int idUsuario) throws Exception;
    
    /**
     * metodo para actualizar un impuesto
     * @param idConcepto
     * @param entidad
     * @param idUsuario
     * @throws Exception 
     */
    public void actualizarImpuesto(int idConcepto, Impuesto entidad, int idUsuario) throws Exception;
    
    /**
     * metodo para eliminar un concepto
     * @param idFactura
     * @param idConcepto
     * @throws Exception 
     */
    public void eliminar(int idFactura, int idConcepto) throws Exception;
    
    /**
     * metodo para obtener el estatus de una factura
     * @param idFactura
     * @return
     * @throws Exception 
     */
    public String obtenerEstatus(int idFactura) throws Exception;
}
