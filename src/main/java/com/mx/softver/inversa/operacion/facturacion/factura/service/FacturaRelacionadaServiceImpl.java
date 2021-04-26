/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.service;

import com.mx.softver.inversa.operacion.facturacion.factura.datainterface.FacturaRelacionadaData;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Factura;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaRelacionada;
import com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface.FacturaRelacionadaService;
import com.softver.comunes.entity.InfoAuditoria;
import com.softver.comunes.exception.OperationNotPermittedSoftverException;
import java.util.List;

/**
 *
 * @author jhernandez
 */
public class FacturaRelacionadaServiceImpl implements FacturaRelacionadaService{
    private FacturaRelacionadaData facturaRelacionadaData;

    /**
     * constructor
     * @param facturaRelacionadaData 
     */
    public FacturaRelacionadaServiceImpl(FacturaRelacionadaData facturaRelacionadaData) {
        this.facturaRelacionadaData = facturaRelacionadaData;
    }

    @Override
    public void crear(InfoAuditoria info, FacturaRelacionada entidad)
        throws Exception {
        validarDatosAuditoria(info);
        validarDatosFacturaRelacionada(info, entidad);
        String estatus = facturaRelacionadaData.obtenerEstatus(entidad.getIdFactura());
        if (!estatus.equals("1") && !estatus.equals("2")) {
            throw new OperationNotPermittedSoftverException(
                "Solo se pueden actualizar conceptos si la factura no está timbrada"
            );
        }
        facturaRelacionadaData.crear(entidad, info.getIdUsuario());
    }
    
    @Override
    public List<Factura> obtenerOpciones(InfoAuditoria info
        , Factura filtro) throws Exception {
        validarDatosAuditoria(info);
        if (filtro == null) {
            throw new OperationNotPermittedSoftverException(
                "El filtro es requerido"
            );
        }
        if (filtro.getIdCliente() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id del cliente es requerido"
            );
        }
        return facturaRelacionadaData.obtenerOpciones(info.getIdEmpresa(), filtro, info.getIdUsuario());
    }

    @Override
    public void eliminar(InfoAuditoria info, FacturaRelacionada entidad)
        throws Exception {
        validarDatosAuditoria(info);
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id de la relacion es requerido"
            );
        }
        if (!facturaRelacionadaData.verificarId(entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id de la relacion ingresado no existe"
            );
        }
        facturaRelacionadaData.eliminar(entidad);
    }
    
    /**
     * metodo para validar los datos de auditoria
     * @param info
     * @throws Exception 
     */
    private void validarDatosAuditoria(InfoAuditoria info) throws Exception{
        if (info == null) {
            throw new OperationNotPermittedSoftverException(
                "Los datos de auditoria son requeridos."
            );
        }
        if (info.getIdEmpresa() < 1) {
            throw new OperationNotPermittedSoftverException(
                "Los datos de auditoria son requeridos."
            );
        }
        if (info.getIdUsuario() < 1) {
            throw new OperationNotPermittedSoftverException(
                "Los datos de auditoria son requeridos."
            );
        }
    }
    
    /**
     * metodo para validar los datos de una factura relacionada
     * @param entidad
     * @throws Exception 
     */
    private void validarDatosFacturaRelacionada(InfoAuditoria info
        , FacturaRelacionada entidad)throws Exception{
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getIdFactura() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura padre es requerido"
            );
        }
        if (!facturaRelacionadaData.verificarIdFactura(info.getIdEmpresa()
            , entidad.getIdFactura())) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura padre ingresado no existe"
            );
        }
        if (entidad.getIdFacturaRelacionada() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura relacionada es requerido"
            );
        }
        if (!facturaRelacionadaData.verificarIdFactura(info.getIdEmpresa(),
            entidad.getIdFacturaRelacionada())) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura relacionada ingresado no existe"
            );
        }
    }
}
