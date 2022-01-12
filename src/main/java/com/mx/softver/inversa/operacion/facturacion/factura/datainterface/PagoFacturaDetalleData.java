/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.datainterface;

import com.mx.softver.inversa.operacion.facturacion.factura.entity.PagoFacturaDetalle;
import com.softver.comunes.entity.InfoAuditoria;
import java.util.List;

/**
 *
 * @author softv
 */
public interface PagoFacturaDetalleData {
    
    public void crear(InfoAuditoria info, PagoFacturaDetalle entidad) throws Exception;
    
     public void eliminar(InfoAuditoria info, PagoFacturaDetalle entidad)throws Exception;
    
    public List<PagoFacturaDetalle> obtener(int idPagoFactura) throws Exception;
}
