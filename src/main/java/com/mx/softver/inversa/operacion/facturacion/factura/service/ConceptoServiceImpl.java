/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.service;

import com.mx.softver.inversa.operacion.facturacion.factura.datainterface.ConceptoData;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Concepto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Impuesto;
import com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface.ConceptoService;
import com.softver.comunes.datainterface.TransactionData;
import com.softver.comunes.entity.InfoAuditoria;
import com.softver.comunes.exception.OperationNotPermittedSoftverException;

/**
 *
 * @author jhernandez
 */
public class ConceptoServiceImpl implements ConceptoService{
    private ConceptoData conceptoData;
    private TransactionData transactionData;

    /**
     * constructor
     * @param conceptoData 
     */
    public ConceptoServiceImpl(ConceptoData conceptoData) {
        this.conceptoData = conceptoData;
    }

    /**
     * constructor
     * @param conceptoData
     * @param transactionData 
     */
    public ConceptoServiceImpl(ConceptoData conceptoData
        , TransactionData transactionData) {
        this.conceptoData = conceptoData;
        this.transactionData = transactionData;
    }

    @Override
    public void crear(InfoAuditoria info, Concepto entidad) throws Exception {
        validarDatosAuditoria(info);
        validarDatosConcepto(entidad);
        if (!conceptoData.verificarIdFactura(info.getIdEmpresa()
            , entidad.getIdFactura())) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura ingresado no existe"
            );
        }
        String estatus = conceptoData.obtenerEstatus(entidad.getIdFactura());
        if (!estatus.equals("1") && !estatus.equals("2")) {
            throw new OperationNotPermittedSoftverException(
                "Solo se pueden guardar conceptos si la factura no está timbrada"
            );
        }
        try {
            transactionData.begin();
            conceptoData.crear(
                entidad.getIdFactura()
                , entidad
                , info.getIdUsuario()
            );
            for (Impuesto impuesto : entidad.getImpuestos()) {
                conceptoData.crearImpuesto(
                    entidad.getId()
                    , impuesto
                    , info.getIdUsuario()
                );
            }
            transactionData.end(true);
        } catch(Exception e) {
            transactionData.end(false);
            throw e;
        }
    }
    
    @Override
    public Concepto obtenerPorId(InfoAuditoria info, Concepto filtro)
        throws Exception {
        validarDatosAuditoria(info);
        if (filtro == null) {
            throw new OperationNotPermittedSoftverException(
                "El filtro es requerido"
            );
        }
        if (!conceptoData.verificarIdFactura(info.getIdEmpresa()
            , filtro.getIdFactura())) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura ingresado no existe"
            );
        }
        if (filtro.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id es requerido"
            );
        }
        if (!conceptoData.verificarId(filtro.getIdFactura()
            , filtro.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id del concepto ingresado no existe"
            );
        }
        filtro = conceptoData.obtenerPorId(filtro.getIdFactura(), filtro.getId());
        filtro.setImpuestos(conceptoData.obtenerImpuestos(filtro.getId()));
        
        return filtro;
    }

    @Override
    public void actualizar(InfoAuditoria info, Concepto entidad)
        throws Exception {
        validarDatosAuditoria(info);
        validarDatosConcepto(entidad);
        if (!conceptoData.verificarIdFactura(info.getIdEmpresa()
            , entidad.getIdFactura())) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura ingresado no existe"
            );
        }
        String estatus = conceptoData.obtenerEstatus(entidad.getIdFactura());
        if (!estatus.equals("1") && !estatus.equals("2")) {
            throw new OperationNotPermittedSoftverException(
                "Solo se pueden actualizar conceptos si la factura no está timbrada"
            );
        }
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id es requerido"
            );
        }
        if (!conceptoData.verificarId(entidad.getIdFactura()
            , entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id del concepto ingresado no existe"
            );
        }
        for (Impuesto impuesto : entidad.getImpuestos()) {
            if (impuesto.getIdConcepto() < 1) {
                throw new OperationNotPermittedSoftverException(
                    "El id del concepto es requerido"
                );
            }
            if (!conceptoData.verificarIdImpuesto(entidad.getId()
                , impuesto.getId())) {
                throw new OperationNotPermittedSoftverException(
                    "El id del impuesto ingresado no existe"
                );
            }
        }
        try {
            transactionData.begin();
            conceptoData.actualizar(
                entidad.getIdFactura()
                , entidad
                , info.getIdUsuario()
            );
            for (Impuesto impuesto : entidad.getImpuestos()) {
                conceptoData.actualizarImpuesto(
                    entidad.getId()
                    , impuesto
                    , info.getIdUsuario()
                );
            }
            transactionData.end(true);
        } catch(Exception e) {
            transactionData.end(false);
            throw e;
        }
    }
    
    @Override
    public void eliminar(InfoAuditoria info, Concepto entidad)
        throws Exception {
        validarDatosAuditoria(info);
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getIdFactura() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura es requerido"
            );
        }
        if (!conceptoData.verificarIdFactura(info.getIdEmpresa()
            , entidad.getIdFactura())) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura ingresado no existe"
            );
        }
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id es requerido"
            );
        }
        if (!conceptoData.verificarId(entidad.getIdFactura()
            , entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id del concepto ingresado no existe"
            );
        }
        conceptoData.eliminar(entidad.getIdFactura(), entidad.getId());
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
     * metodo para validar los datos de un concepto
     * @param entidad
     * @throws Exception 
     */
    private void validarDatosConcepto(Concepto entidad) throws Exception {
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getIdFactura() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura es requerido"
            );
        }
        if (entidad.getCantidad() == null) {
            throw new OperationNotPermittedSoftverException(
                "La cantidad es requerida"
            );
        }
        if (entidad.getValorUnitario() == null) {
            throw new OperationNotPermittedSoftverException(
                "El valor unitario es requerido"
            );
        }
        if (entidad.getDescuento() == null) {
            throw new OperationNotPermittedSoftverException(
                "El descuento es requerido"
            );
        }
        if (entidad.getImporte() == null) {
            throw new OperationNotPermittedSoftverException(
                "El importe es requerido"
            );
        }
        if (entidad.getImpuestos() == null) {
            throw new OperationNotPermittedSoftverException(
                "Los impuestos de un concepto son requeridos"
            );
        }
        for (Impuesto impuesto : entidad.getImpuestos()) {
            validarDatosImpuesto(impuesto);
        }
    }
    
    /**
     * metodo para validar los datos de un impuesto
     * @param entidad
     * @throws Exception 
     */
    private void validarDatosImpuesto(Impuesto entidad) throws Exception{
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getTotalImpuesto() == null) {
            throw new OperationNotPermittedSoftverException(
                "El total del impuesto es requerido"
            );
        }
    }
}
