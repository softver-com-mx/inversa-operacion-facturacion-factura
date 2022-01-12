/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.datainterface;

import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaFiltro;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.PagoFactura;
import com.softver.comunes.entity.InfoAuditoria;
import java.util.List;

/**
 *
 * @author softv
 */
public interface PagoFacturaData {
    
    public void crear(InfoAuditoria info, PagoFactura entidad) throws Exception;
    
    public void actualizar(InfoAuditoria info, PagoFactura entidad) throws Exception;
    
    public List<PagoFactura> obtenerFacturas (InfoAuditoria info, FacturaFiltro filtro) throws Exception;
    
    public List<PagoFactura> obtener (InfoAuditoria info, FacturaFiltro filtro) throws Exception;
}
