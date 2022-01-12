/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface;

import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaFiltro;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.PagoFactura;
import com.softver.comunes.entity.Archivo;
import com.softver.comunes.entity.InfoAuditoria;
import java.util.List;

/**
 *
 * @author Luis Avbax
 */
public interface PagoFacturaService {
    
    /**
     * Crear un PagoFactura para la factura junto con un abono, ya sea total o 
     * parcial
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void crear(InfoAuditoria info ,PagoFactura entidad) throws Exception;
    
    /**
     * Actualiza el pagoFactura añadiendole un abono
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void actualizar(InfoAuditoria info, PagoFactura entidad) throws Exception;
    
    /**
     * Obtiene todas las facturas 
     * @param info
     * @param filtro
     * @return
     * @throws Exception 
     */
    public List<PagoFactura> obtenerFacturas (InfoAuditoria info, FacturaFiltro filtro) throws Exception;
    
    /**
     * obtiene la lista de pagos facturas
     * @param filtro
     * @param info
     * @return
     * @throws Exception 
     */
    public List<PagoFactura> obtener (InfoAuditoria info, FacturaFiltro filtro) throws Exception;
    
    /**
     * Obtiene la lista de abonos del pagoFactura
     * @param filtro
     * @return La entidad con sus abonos
     * @throws Exception 
     */
    public PagoFactura obtenerAbonos (PagoFactura filtro) throws Exception;
    
    /**
     * Elimina  el abono seleccionado del pagoFactura
     * @param info
     * @param filtro
     * @throws Exception 
     */
    public void eliminarAbono(InfoAuditoria info, PagoFactura filtro)throws Exception;
    
    /**
     * Descarga excel con el reporte de pagos
     * @param info
     * @param filtro
     * @return
     * @throws Exception 
     */
    public Archivo descargarReporte(InfoAuditoria info, FacturaFiltro filtro) throws Exception;
}
