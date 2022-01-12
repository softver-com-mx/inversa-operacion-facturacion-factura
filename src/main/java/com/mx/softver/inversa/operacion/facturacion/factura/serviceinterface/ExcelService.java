/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.PagoFactura;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.ReporteFactura;
import java.util.List;

/**
 *
 * @author Luis Avbax
 */
public interface ExcelService {
        public XSSFWorkbook crearExcelVentasDiarias(List<ReporteFactura> listaFacturas) throws Exception;
    
    public XSSFWorkbook crearReportePagosFacturas(List<PagoFactura> listaPagosFacturas) throws Exception;
    
}
