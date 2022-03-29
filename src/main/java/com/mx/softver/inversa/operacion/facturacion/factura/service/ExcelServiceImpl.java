/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.service;

import com.mx.softver.inversa.operacion.facturacion.factura.entity.PagoFactura;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.PagoFacturaDetalle;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.ReporteFactura;
import com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface.ExcelService;
import java.util.List;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Luis Avbax
 */
public class ExcelServiceImpl  implements ExcelService{
    
    private static final String[] COLUMNAS_REPORTE_FACTURAS = {"FECHA", "FOLIO", "CANTIDAD",
        "SERIE", "RFC", "RAZON SOCIAL", "ENTIDAD FEDERATIVA","CONCEPTO","OBSERVACIONES",
        "FORMA DE PAGO","METODO DE PAGO", "TOTAL SIN IVA", "TOTAL CON IVA","DESCUENTO", "ESTADO", "UUID"};
    
    private static final String[] COLUMNAS_REPORTE_PAGOS_FACTURA = {"FECHA EXPEDICIÓN", "FOLIO",
        "SERIE", "RFC", "RAZON SOCIAL", "FORMA DE PAGO", "FOLIO-COTIZACIÓN", "SUBTOTAL", "IVA", "TOTAL CON IVA", "ESTADO",
        "SALDO A PAGAR", "ESTADO DE PAGO", "NO. PAGOS"};    
     
    public XSSFWorkbook crearExcelVentasDiarias(List<ReporteFactura> listaFacturas) throws Exception {
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet("Facturas");
        CreationHelper helper = libro.getCreationHelper();
        XSSFCellStyle estiloCeldaFecha = crearEstiloCeldaFecha(libro, helper);
        XSSFCellStyle estiloCeldaPrecio = crearEstiloCeldaPrecio(libro);
        crearCabecera(libro, hoja, COLUMNAS_REPORTE_FACTURAS);
        XSSFCellStyle estiloCentradoCelda = libro.createCellStyle();
        centrarContenido(estiloCentradoCelda);
        
        int numeroFila = 1;
        for (ReporteFactura factura : listaFacturas) {
            XSSFRow fila = hoja.createRow(numeroFila++);
            
            XSSFCell celdaFechaExpedicion = crearCelda(fila,0, estiloCeldaFecha);
            celdaFechaExpedicion.setCellValue(factura.getFechaExpedicion());
            XSSFCell celdaFolio = crearCelda(fila,1,estiloCentradoCelda);
            celdaFolio.setCellValue(factura.getFolio());
            XSSFCell celdaCantidad = crearCelda(fila,2,estiloCentradoCelda);
            celdaCantidad.setCellValue(factura.getCantidad());
            XSSFCell celdaProducto = crearCelda(fila,3, estiloCentradoCelda);
            celdaProducto.setCellValue(factura.getSerie());
            XSSFCell celdaRfc = crearCelda(fila,4, estiloCentradoCelda);
            celdaRfc.setCellValue(factura.getRfcReceptor());
            XSSFCell celdaRazonSocial = crearCelda(fila, 5, estiloCentradoCelda);
            celdaRazonSocial.setCellValue(factura.getNombreReceptor());   
            XSSFCell celdaEntidadFed = crearCelda(fila, 6, estiloCentradoCelda);
            celdaEntidadFed.setCellValue(factura.getNombreEntidadFederativa());         
            XSSFCell celdaConcepto = crearCelda(fila, 7, estiloCentradoCelda);
            celdaConcepto.setCellValue(factura.getConcepto());
            XSSFCell celdaObservacion = crearCelda(fila, 8, estiloCentradoCelda);
            celdaObservacion.setCellValue(factura.getObservacion());           
            XSSFCell celdaFormaPago = crearCelda(fila, 9, estiloCentradoCelda);
            celdaFormaPago.setCellValue(factura.getFormaPago());           
            XSSFCell celdaMetodoPago = crearCelda(fila, 10, estiloCentradoCelda);
            celdaMetodoPago.setCellValue(factura.getClaveMetodoPago());       
            XSSFCell celdaSubtotal = crearCelda(fila, 11, estiloCeldaPrecio);
            celdaSubtotal.setCellValue(factura.getSubtotal());      
            XSSFCell celdaTotal = crearCelda(fila, 12, estiloCeldaPrecio);
            celdaTotal.setCellValue(factura.getTotal());  
            XSSFCell celdaDescuento = crearCelda(fila, 13, estiloCeldaPrecio);
            celdaDescuento.setCellValue(factura.getDescuento());  
            XSSFCell celdaEstatus = crearCelda(fila, 14, estiloCeldaPrecio);
            celdaEstatus.setCellValue(factura.getEstatus());
            XSSFCell celdaUuid = crearCelda(fila, 15, estiloCentradoCelda);
            celdaUuid.setCellValue(factura.getUuid());
        }
        ajustarColumnas(COLUMNAS_REPORTE_FACTURAS, hoja);
        return libro;
    }
    
    @Override
    public XSSFWorkbook crearReportePagosFacturas(List<PagoFactura> listaPagosFacturas) throws Exception {
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet("Pago de Facturas");
        CreationHelper helper = libro.getCreationHelper();
        XSSFCellStyle estiloCeldaFecha = crearEstiloCeldaFecha(libro, helper);
        XSSFCellStyle estiloCeldaPrecio = crearEstiloCeldaPrecio(libro);
        crearCabecera(libro, hoja, COLUMNAS_REPORTE_PAGOS_FACTURA);
        XSSFCellStyle estiloCentradoCelda = libro.createCellStyle();
        centrarContenido(estiloCentradoCelda);
        
        int numeroFila = 1;
        for (PagoFactura factura : listaPagosFacturas) {
            XSSFRow fila = hoja.createRow(numeroFila++);
            
            XSSFCell celdaFechaExpedicion = crearCelda(fila,0, estiloCeldaFecha);
            celdaFechaExpedicion.setCellValue(factura.getFechaExpedicion());
            XSSFCell celdaFolio = crearCelda(fila,1,estiloCentradoCelda);
            celdaFolio.setCellValue(factura.getFolio());            
            XSSFCell celdaProducto = crearCelda(fila,2, estiloCentradoCelda);
            celdaProducto.setCellValue(factura.getSerie());            
            XSSFCell celdaRfc = crearCelda(fila,3, estiloCentradoCelda);
            celdaRfc.setCellValue(factura.getRfc());            
            XSSFCell celdaRazonSocial = crearCelda(fila, 4, estiloCentradoCelda);
            celdaRazonSocial.setCellValue(factura.getNombreCliente());                       
            XSSFCell celdaFormaPago = crearCelda(fila, 5, estiloCentradoCelda);
            celdaFormaPago.setCellValue(factura.getFormaPago());
            XSSFCell celdaFolioCotizacion = crearCelda(fila,6,estiloCentradoCelda);
            celdaFolioCotizacion.setCellValue(factura.getFolioCotizacion());                        
            XSSFCell celdaSubtotal = crearCelda(fila, 7, estiloCeldaPrecio);
            celdaSubtotal.setCellValue(factura.getSubtotal());             
            XSSFCell celdaIva = crearCelda(fila, 8, estiloCentradoCelda);
            celdaIva.setCellValue(factura.getTotal() - factura.getSubtotal());                           
            XSSFCell celdaTotal = crearCelda(fila, 9, estiloCeldaPrecio);
            celdaTotal.setCellValue(factura.getTotal());                            
            XSSFCell celdaEstatus = crearCelda(fila, 10, estiloCeldaPrecio);
            celdaEstatus.setCellValue(asignarEstadoFactura(factura.getEstatus()));                        
            XSSFCell celdaSaldoInsoluto = crearCelda(fila, 11, estiloCeldaPrecio);
            celdaSaldoInsoluto.setCellValue(factura.getSaldoInsoluto());                       
            XSSFCell celdaEstadoPago = crearCelda(fila, 12, estiloCeldaPrecio);
            celdaEstadoPago.setCellValue(asignarEstadoPagoFacturas(factura.getEstado()));
            XSSFCell celdaNumPagos = crearCelda(fila, 13, estiloCentradoCelda);
            celdaNumPagos.setCellValue(asignarNumeroPagos(factura.getListaPagos()));
        }
        ajustarColumnas(COLUMNAS_REPORTE_PAGOS_FACTURA, hoja);
        return libro;
    }
    
    private void crearCabecera(XSSFWorkbook libro, XSSFSheet hoja, String[] columnas) {
        XSSFCellStyle estiloCabecera = crearEstiloCabecera(libro);
        XSSFRow header = hoja.createRow(0);

        for (int i = 0; i < columnas.length; i++) {
            XSSFCell celda = header.createCell(i);
            celda.setCellValue(columnas[i]);
            celda.setCellStyle(estiloCabecera);
        }
    }
    
    private XSSFCell crearCelda(XSSFRow fila, int indice, XSSFCellStyle estiloCelda){
        XSSFCell celda = fila.createCell(indice); 
        celda.setCellStyle(estiloCelda);
        return celda;
    }
    
    private void ajustarColumnas(String[] columnas, XSSFSheet hoja) {
        for (int i = 0; i < columnas.length; i++) {
            hoja.autoSizeColumn(i);
        }
    }
    
    private void centrarContenido(XSSFCellStyle estilo){
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    private XSSFCellStyle crearEstiloCeldaFecha(XSSFWorkbook libro, CreationHelper helper) {
        XSSFCellStyle celdaFecha = libro.createCellStyle();
        celdaFecha.setDataFormat(helper.createDataFormat().getFormat("DD/MM/YYYY HH:mm"));
        centrarContenido(celdaFecha);
        return celdaFecha;
    }
    
    private XSSFCellStyle crearEstiloCeldaPrecio(XSSFWorkbook libro){
        XSSFCellStyle estiloCeldaPrecio = libro.createCellStyle();
        estiloCeldaPrecio.setDataFormat((short) 7);
        centrarContenido(estiloCeldaPrecio);
        return estiloCeldaPrecio;
    }
    
    private XSSFCellStyle crearEstiloCabecera(XSSFWorkbook libro){
        XSSFFont fuenteCabecera = libro.createFont();
        fuenteCabecera.setBold(true);
        XSSFCellStyle estiloCabecera = libro.createCellStyle();
        estiloCabecera.setFont(fuenteCabecera);
        centrarContenido(estiloCabecera);
        return estiloCabecera;
    }
    
   private String asignarEstadoPagoFacturas(String estado){
       if(estado != null){
            if(estado.equals("1")){
                return "PAGADO";
            }
            if(estado.equals("3")){
                return "PARCIALMENTE PAGADO";
            }
       }
       return "PENDIENTE";
   }
   
   private String asignarEstadoFactura(String estado){
       if(estado.equals("4")){
           return "TIMBRADO";
       }
       return "";
   }
   
   private int asignarNumeroPagos(List<PagoFacturaDetalle> listaPagos){
       if(listaPagos != null){
           return listaPagos.size();
       }
       return 0;
   }
    
}
