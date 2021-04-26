/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.datainterface;


import com.mx.softver.inversa.operacion.facturacion.factura.entity.Concepto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.ConceptoCompleto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Factura;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaConcepto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaFiltro;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaRelacionada;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaVistaPrevia;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Impuesto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.UuidConceptos;
import java.util.List;

/**
 *
 * @author jhernandez
 */
public interface FacturaData {
    /**
     * metodo para crear un registro
     * @param idEmpresa
     * @param entidad
     * @param idUsuario
     * @throws Exception 
     */
    public void crear(int idEmpresa, Factura entidad, int idUsuario) throws Exception;
    
    /**
     * metodo para obtener el listado de facturas
     * @param idEmpresa
     * @param filtro
     * @return listado de facturas
     * @throws Exception 
     */
    public List<Factura> obtener(int idEmpresa, FacturaFiltro filtro) throws Exception;
    
    /**
     * metodo para obtener las facturas timbradas con su saldo a pagar
     * @param idEmpresa
     * @param entidad
     * @return
     * @throws Exception 
     */
    public List<Factura> obtenerFacturasConSaldo(int idEmpresa, FacturaFiltro entidad) throws Exception;
    
    /**
     * metodo para obtener los conceptos de una factura
     * @param idFactura
     * @return
     * @throws Exception 
     */
    public List<Concepto> obtenerConceptos(int idFactura) throws Exception;
    
    /**
     * metodo para obtener el listado de impuestos de un concepto
     * @param idConcepto
     * @return
     * @throws Exception 
     */
    public List<Impuesto> obtenerImpuestos(int idConcepto) throws Exception;
    
    /**
     * metodo para obtener las facturas relacionadas a una factura padre
     * @param idFacturaPadre
     * @return
     * @throws Exception 
     */
    public List<FacturaRelacionada> obtenerFacturasRelacionadas(int idFacturaPadre) throws Exception;
    
    /**
     * metodo para obtener una factura con detalles
     * @param idEmpresa
     * @param filtro
     * @return factura detallada
     * @throws Exception 
     */
    public FacturaConcepto obtenerPorId(int idEmpresa, Factura filtro) throws Exception;
    
    /**
     * metodo para verificar la existencia de un id
     * @param idEmpresa
     * @param idFactura
     * @return
     * @throws Exception 
     */
    public boolean verificarId(int idEmpresa, int idFactura) throws Exception;
    
    /**
     * metodo para verificar el timbrado de una factura
     * @param idEmpresa
     * @param idFactura
     * @return
     * @throws Exception 
     */
    public String verificarTimbrado(int idEmpresa, int idFactura) throws Exception;
    
    /**
     * metodo para verificar la exitencia de un uuid
     * @param idFactura
     * @param uuid
     * @return
     * @throws Exception 
     */
    public boolean verificarUuid(int idFactura, String uuid) throws Exception;
    
    /**
     * metodo para actualizar una factura
     * @param idEmpresa
     * @param entidad
     * @param idUsuario
     * @throws Exception 
     */
    public void actualizar(int idEmpresa, Factura entidad, int idUsuario) throws Exception;
    
    /**
     * metodo para actualizar el estatus de una factura
     * @param idEmpresa
     * @param idFactura
     * @param estatus
     * @param idUsuario
     * @throws Exception 
     */
    public void actualizarEstatus(int idEmpresa, int idFactura, String estatus, int idUsuario) throws Exception;
    
    /**
     * metodo para obtener el estatus de una factura
     * @param idFactura
     * @return
     * @throws Exception 
     */
    public String obtenerEstatus(int idFactura) throws Exception;
    
    /**
     * metodo para obtener la ruta de la api de timbrado
     * @return
     * @throws Exception 
     */
    public String obtenerUrlTimbrado() throws Exception;
    
    /**
     * metodo para obtener la ruta de la api de descargas de xml y pdf
     * @return
     * @throws Exception 
     */
    public String obtenerUrlDescargas() throws Exception;
    
    /**
     * metodo para obtener el token para la api de servicios
     * @return
     * @throws Exception 
     */
    public String obtenerTokenServicio(int idEmpresa) throws Exception;
    
    /**
     * metodo para registrar el timbrado de una factura
     * @param idEmpresa
     * @param idFactura
     * @param uuid
     * @param idUsuario
     * @throws Exception 
     */
    public void timbrar(int idEmpresa, int idFactura, String uuid, int idUsuario) throws Exception;
    
    /**
     * metodo para obtener los datos para la vista previa de una factura
     * @param idEmpresa
     * @param entidad
     * @throws Exception 
     */
    public void obtenerVistaPrevia(int idEmpresa, FacturaVistaPrevia entidad) throws Exception;
    
    /**
     * metodo para obtener los datos faltantes de un concepto
     * @param entidad
     * @throws Exception 
     */
    public void obtenerConceptoCompleto(ConceptoCompleto entidad) throws Exception;
    
    /**
     * metodo para obtener los datos faltantes de un concepto
     * @param idFactura
     * @return 
     * @throws Exception 
     */
    public List<ConceptoCompleto> obtenerConceptosCompletos(int idFactura) throws Exception;
    
    /**
     * metodo para obtener la ruta base configurada en la empresa
     * @param idEmpresa
     * @return
     * @throws Exception 
     */
    public String obtenerRutaBase(int idEmpresa) throws Exception;
    
    /**
     * metodo para obtener los datos generales del encabezado de una factura impresa
     * @param idEmpresa
     * @param idFactura
     * @param entidad
     * @throws Exception 
     */
    public void obtenerDatosEncabezado(int idEmpresa, int idFactura, UuidConceptos entidad) throws Exception;
    
    /**
     * metodo para crear un registro
     * @param idFactura
     * @param entidad
     * @param idUsuario
     * @throws Exception 
     */
    public void crearConcepto(int idFactura, Concepto entidad, int idUsuario) throws Exception;
    
    /**
     * metodo para crear el impuesto de un concepto
     * @param idConcepto
     * @param entidad
     * @param idUsuario
     * @throws Exception 
     */
    public void crearImpuesto(int idConcepto, Impuesto entidad, int idUsuario) throws Exception;
    
    /**
     * metodo para crear la relacion entre dos facturas
     * @param idFactura
     * @param idUsuario
     * @param entidad
     * @throws Exception 
     */
    public void crearFacturaRelacionada(int idFactura, FacturaRelacionada entidad, int idUsuario) throws Exception;
}
