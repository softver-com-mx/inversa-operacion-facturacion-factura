/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface;

import com.softver.comunes.entity.Archivo;
import com.softver.comunes.entity.Id;
import com.softver.comunes.entity.InfoAuditoria;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Cfdi;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Factura;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaConcepto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaFiltro;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaVistaPrevia;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.IdCorreo;
import java.util.List;

/**
 *
 * @author jhernandez
 */
public interface FacturaService {
    /**
     * metodo para crear una factura
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void crear(InfoAuditoria info, FacturaConcepto entidad) throws Exception;
    
    /**
     * metodo para obtener el listado de facturas
     * @param info
     * @param filtro
     * @return
     * @throws Exception 
     */
    public List<Factura> obtener(InfoAuditoria info, FacturaFiltro filtro) throws Exception;
    
    /**
     * metodo para obtener el detalle de una factura
     * @param info
     * @param entidad
     * @return
     * @throws Exception 
     */
    public FacturaConcepto obtenerPorId(InfoAuditoria info, Factura entidad) throws Exception;
    
    /**
     * metodo para actualizar una factura
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void actualizar(InfoAuditoria info, Factura entidad) throws Exception;
    
    /**
     * metodo para timbrar una factura por ID
     * @param info
     * @param filtro
     * @return
     * @throws Exception 
     */
    public FacturaConcepto timbrarFactura(InfoAuditoria info, Id filtro) throws Exception;
    
    /**
     * metodo para cancelar una factura
     * @param info
     * @param entidad
     * @return
     * @throws Exception 
     */
    public Cfdi cancelarFactura(InfoAuditoria info, Factura entidad) throws Exception;
    
    /**
     * metodo para comprobar la cancelacion de un factura
     * @param info
     * @param entidad
     * @return
     * @throws Exception 
     */
    public Cfdi comprobarCancelacion(InfoAuditoria info, Factura entidad) throws Exception;
        
    /**
     * metodo para descargar el reporte de facturas
     * @param info
     * @param filtro
     * @return
     * @throws Exception 
     */
    public Archivo descargarReporte(InfoAuditoria info, FacturaFiltro filtro) throws Exception;
    
    /**
     * metodo para descargar el xml de una factura
     * @param info
     * @param entidad
     * @return
     * @throws Exception 
     */
    
    public Archivo descargarXml(InfoAuditoria info, Factura entidad) throws Exception;
    
    /**
     * metodo para descargar el pdf de un factura
     * @param info
     * @param entidad
     * @return
     * @throws Exception 
     */
    public Archivo descargarPdf(InfoAuditoria info, Factura entidad) throws Exception;
    
    /**
     * metodo del controller para enviar un correo con el xml y el pdf de la factura
     * @param info
     * @param peticion
     * @throws Exception 
     */
    public void enviarCorreo(InfoAuditoria info, IdCorreo peticion) throws Exception;
    
    /**
     * metodo para obtener los datos de la vista previa de una factura
     * @param info
     * @param entidad
     * @return
     * @throws Exception 
     */
    public FacturaVistaPrevia obtenerVistaPrevia(InfoAuditoria info, Id entidad) throws Exception;
    
    /**
     * metodo para obtener los datos clonados de una factura
     * @param info
     * @param entidad
     * @return
     * @throws Exception 
     */
    public FacturaConcepto obtenerFacturaClonada(InfoAuditoria info, Factura entidad) throws Exception;
}
