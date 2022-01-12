/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface;

import com.mx.softver.inversa.operacion.facturacion.factura.entity.PagoFacturaDetalle;
import com.softver.comunes.entity.InfoAuditoria;
import java.util.List;

/**
 *
 * @author Luis Avbax
 */
public interface PagoFacturaDetalleService {
        
    /**
     * Crea el detalle del pago de la factura
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void crear(InfoAuditoria info, PagoFacturaDetalle entidad) throws Exception;
    
    /**
     * Elimina el detalle el pago de la factura
     * @param info
     * @param entidad
     * @throws Exception 
     */
    public void eliminar(InfoAuditoria info, PagoFacturaDetalle entidad)throws Exception;
    
    /**
     * Se obtienen los detalles de un pago de la factura
     * @param idPagoFactura
     * @return
     * @throws Exception 
     */
    public List<PagoFacturaDetalle> obtener(int idPagoFactura) throws Exception;
}
