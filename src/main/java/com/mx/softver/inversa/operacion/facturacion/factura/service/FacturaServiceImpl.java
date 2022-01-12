/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mx.softver.inversa.operacion.facturacion.factura.data.ClienteApiWSData;
import com.mx.softver.inversa.operacion.facturacion.factura.data.NumerosALetras;
import com.mx.softver.inversa.operacion.facturacion.factura.datainterface.FacturaCatalogoData;
import com.mx.softver.inversa.operacion.facturacion.factura.datainterface.FacturaData;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Cfdi;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Concepto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.ConceptoCompleto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Factura;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaConcepto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaFiltro;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaRelacionada;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaVistaPrevia;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.IdCorreo;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Impuesto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.LogoCompania;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.ReporteFactura;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.UuidConceptos;
import com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface.ComprobanteService;
import com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface.FacturaService;
import com.softver.comunes.datainterface.TransactionData;
import com.softver.comunes.entity.Archivo;
import com.softver.comunes.entity.Id;
import com.softver.comunes.entity.InfoAuditoria;
import com.softver.comunes.entity.RespuestaBase;
import com.softver.comunes.exception.OperationNotPermittedSoftverException;
import com.softver.comunes.mensajeria.entity.Mensaje;
import com.softver.comunes.mensajeria.serviceinterface.MensajeriaService;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.Comprobante;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author jhernandez
 */
public class FacturaServiceImpl implements FacturaService{
    private FacturaData facturaData;
    private FacturaCatalogoData facturaCatalogoData;
    private TransactionData transactionData;
    private ComprobanteService comprobanteService;
    private MensajeriaService mensajeriaService;

    /**
     * constructor
     * @param facturaData 
     */
    public FacturaServiceImpl(FacturaData facturaData) {
        this.facturaData = facturaData;
    }

    /**
     * constructor
     * @param facturaData
     * @param facturaCatalogoData 
     */
    public FacturaServiceImpl(FacturaData facturaData
        , FacturaCatalogoData facturaCatalogoData) {
        this.facturaData = facturaData;
        this.facturaCatalogoData = facturaCatalogoData;
    }
    
    /**
     * constructor
     * @param facturaData
     * @param mensajeriaService 
     */
    public FacturaServiceImpl(FacturaData facturaData
        , MensajeriaService mensajeriaService) {
        this.facturaData = facturaData;
        this.mensajeriaService = mensajeriaService;
    }

    /**
     * constructor
     * @param facturaData
     * @param facturaCatalogoData
     * @param transactionData 
     */
    public FacturaServiceImpl(FacturaData facturaData
        , FacturaCatalogoData facturaCatalogoData
        , TransactionData transactionData) {
        this.facturaData = facturaData;
        this.facturaCatalogoData = facturaCatalogoData;
        this.transactionData = transactionData;
    }

    /**
     * constructor
     * @param facturaData
     * @param comprobanteService 
     */
    public FacturaServiceImpl(FacturaData facturaData, ComprobanteService comprobanteService) {
        this.facturaData = facturaData;
        this.comprobanteService = comprobanteService;
    }

    @Override
    public void crear(InfoAuditoria info, FacturaConcepto entidad)
        throws Exception {
        validarDatosAuditoria(info);
        validarDatosFactura(entidad);
        if (entidad.getConceptos() != null) {
            validarDatosConceptos(entidad.getConceptos());
        }
        if (entidad.getIdSerie() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id de la serie es requerida"
            );
        }
        try {
            transactionData.begin();
            // creacion de los datos generales de la factura
            facturaData.crear(info.getIdEmpresa(), entidad, info.getIdUsuario());
            // creacion de los conceptos
            for (Concepto concepto : entidad.getConceptos()) {
                facturaData.crearConcepto(
                    entidad.getId()
                    , concepto
                    , info.getIdUsuario()
                );
                // creacion de los impuestos
                for (Impuesto impuesto : concepto.getImpuestos()) {
                    facturaData.crearImpuesto(
                        concepto.getId()
                        , impuesto
                        , info.getIdUsuario()
                    );
                }
            }
            // creacion de las facturas relacionadas
            if (entidad.getFacturasRelacionadas() != null) {
                validarDatosFacturasRelacionadas(
                    info
                    , entidad.getFacturasRelacionadas()
                );
                for (FacturaRelacionada relacion : entidad.getFacturasRelacionadas()) {
                    facturaData.crearFacturaRelacionada(
                        entidad.getId()
                        , relacion
                        , info.getIdUsuario()
                    );
                }
            }
            transactionData.end(true);
        } catch(Exception e) {
            transactionData.end(false);
            throw e;
        }
    }

    @Override
    public List<Factura> obtener(InfoAuditoria info, FacturaFiltro filtro)
        throws Exception {
        validarDatosAuditoria(info);
        if (filtro == null) {
            throw new OperationNotPermittedSoftverException(
                "El filtro es requerido"
            );
        }
        if (filtro.getEstatus() == null) {
            throw new OperationNotPermittedSoftverException(
                "El estatus es requerido"
            );
        }
        if (filtro.getEstatus().equals("7")) {
            return facturaData.obtenerFacturasConSaldo(info.getIdEmpresa(), filtro);
        } else {
            return facturaData.obtener(info.getIdEmpresa(), filtro);
        }
    }

    @Override
    public FacturaConcepto obtenerPorId(InfoAuditoria info, Factura entidad)
        throws Exception {
        validarDatosAuditoria(info);
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura es requerida"
            );
        }
        if (!facturaData.verificarId(info.getIdEmpresa(), entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura ingresada no existe"
            );
        }
        FacturaConcepto factura = facturaData.obtenerPorId(
            info.getIdEmpresa(), entidad
        );
        factura.setConceptos(facturaData.obtenerConceptos(factura.getId()));
        for (Concepto concepto : factura.getConceptos()) {
            concepto.setImpuestos(facturaData.obtenerImpuestos(concepto.getId()));
        }
        factura.setFacturasRelacionadas(
            facturaData.obtenerFacturasRelacionadas(factura.getId())
        );
        return factura;
    }

    @Override
    public void actualizar(InfoAuditoria info, Factura entidad)
        throws Exception {
        validarDatosAuditoria(info);
        validarDatosFactura(entidad);
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura es requerida"
            );
        }
        if (!facturaData.verificarId(info.getIdEmpresa(), entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id de la factura ingresada no existe"
            );
        }
        if (entidad.getEstatus() == null) {
            throw new OperationNotPermittedSoftverException(
                "El estatus es requerido"
            );
        }
        String estatus = facturaData.obtenerEstatus(entidad.getId());
        if (!estatus.equals("1") && !estatus.equals("2")) {
            throw new OperationNotPermittedSoftverException(
                "Solo se puede actualizar la factura si no está timbrada"
            );
        }
        facturaData.actualizar(
            info.getIdEmpresa()
            , entidad
            , info.getIdUsuario()
        );
    }
    
    @Override
    public FacturaConcepto timbrarFactura(InfoAuditoria info, Id filtro) throws Exception {
        if (filtro == null) {
            throw new OperationNotPermittedSoftverException(
                "El filtro es requerido"
            );
        }
        if (filtro.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El filtro es requerido"
            );
        }
        if (!facturaData.verificarId(info.getIdEmpresa(), filtro.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id ingresado no existe"
            );
        }
        Factura factura = new Factura();
        factura.setId(filtro.getId());
        FacturaConcepto facturaConcepto = obtenerPorId(info, factura);
        if (facturaConcepto.getConceptos().isEmpty()) {
            throw new OperationNotPermittedSoftverException(
                "No se puede timbrar una factura sin conceptos."
            );
        }
        // validar los estados de la factura
        switch(facturaConcepto.getEstatus()) {
            case "1":
                // se cambia el estado a 3
                facturaData.actualizarEstatus(
                    info.getIdEmpresa()
                    , facturaConcepto.getId()
                    , "3"
                    , info.getIdUsuario()
                );
                facturaData.reajustarFolio(
                    info.getIdEmpresa()
                    , facturaConcepto.getSerie()
                    , facturaConcepto.getFolio()
                    , facturaConcepto.getId()
                );
            break;
            case "3":
                // se comprueba que ya este timbrado, si es asi se regresa el uuid y se cambia el estado a 4
                String uuid = facturaData.verificarTimbrado(
                    info.getIdEmpresa()
                    , facturaConcepto.getId()
                );
                if (uuid != null) {
                    facturaData.actualizarEstatus(
                        info.getIdEmpresa()
                        , facturaConcepto.getId()
                        , "4"
                        , info.getIdUsuario()
                    );
                    facturaConcepto = obtenerPorId(info, factura);
                    return facturaConcepto;
                } else {
                    throw new OperationNotPermittedSoftverException(
                        "Procesando timbrado, si el mensaje persiste, comunicarse con soporte técnico."
                    );
                }
            default:
                throw new OperationNotPermittedSoftverException(
                    "Una factura inactiva, timbrada previamente, en proceso de cancelación o cancelada, no puede timbrarse."
                );
        }
        facturaConcepto = obtenerPorId(info, factura);
        // validacion de fechas de emision
        validarFechaEmision(
            facturaConcepto
            , info.getIdEmpresa()
            , info.getIdUsuario()
        );
        // validar los datos de cotizacion (total, timbrados, etc)
        
        // se obtiene el comprobante procesado
        Comprobante comprobante = comprobanteService.procesar(info, facturaConcepto);
        // peticion  a la api
        ClienteApiWSData clienteApi = new ClienteApiWSData(
            facturaData.obtenerTokenServicio(info.getIdEmpresa())
            , info.getRfcEmpresa()
        );
        String urlTimbrado = facturaData.obtenerUrlTimbrado();
        RespuestaBase respuestaBase = clienteApi.post(
            urlTimbrado
            , "firmarytimbrar"
            , new TypeReference<RespuestaBase<Comprobante>>(){}
            , comprobante
        );
        if (respuestaBase.isError()) {
            facturaData.actualizarEstatus(
                info.getIdEmpresa()
                , facturaConcepto.getId()
                , "1"
                , info.getIdUsuario()
            );
            throw new OperationNotPermittedSoftverException(
                respuestaBase.getMensaje() + (respuestaBase.getMensajeDetalle() == null
                ? ""
                : " - " + respuestaBase.getMensajeDetalle())
            );
        } else {
            // si la respuesta es exitosa se modifica el estado y se guarda el uuid
            comprobante = (Comprobante) respuestaBase.getEntidad();
            facturaData.timbrar(
                info.getIdEmpresa()
                , facturaConcepto.getId()
                , comprobante.getComplemento().getTimbreFiscalDigital().getUUID()
                , info.getIdUsuario()
            );
            facturaConcepto.setUuid(comprobante.getComplemento().getTimbreFiscalDigital().getUUID());
        }
        return facturaConcepto;
    }
    
    @Override
    public Cfdi cancelarFactura(InfoAuditoria info, Factura entidad)
        throws Exception {
        validarDatosAuditoria(info);
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id es requerido"
            );
        }
        if (!facturaData.verificarId(info.getIdEmpresa(), entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id ingresado no existe"
            );
        }
        if (entidad.getUuid() == null) {
            throw new OperationNotPermittedSoftverException(
                "El uuid es requerido"
            );
        }
        if (entidad.getUuid().isEmpty()) {
            throw new OperationNotPermittedSoftverException(
                "El uuid es requerido"
            );
        }
        if (!facturaData.verificarUuid(entidad.getId(), entidad.getUuid())) {
            throw new OperationNotPermittedSoftverException(
                "El uuid ingresado no existe"
            );
        }
        String estatus = facturaData.obtenerEstatus(entidad.getId());
        if (!estatus.equals("4")) {
            throw new OperationNotPermittedSoftverException(
                "Solo se puede cancelar una factura timbrada activa"
            );
        }
        Cfdi cfdi = new Cfdi();
        cfdi.setUuid(entidad.getUuid());
        cfdi.setRfcEmisor(info.getRfcEmpresa());
        ClienteApiWSData clienteApi = new ClienteApiWSData(
            facturaData.obtenerTokenServicio(info.getIdEmpresa())
            , info.getRfcEmpresa()
        );
        String urlTimbrado = facturaData.obtenerUrlTimbrado();
        RespuestaBase respuestaBase = clienteApi.post(
            urlTimbrado
            , "cancelar"
            , new TypeReference<RespuestaBase<Cfdi>>(){}
            , cfdi  
        );
        if (respuestaBase.isError()) {
            throw new OperationNotPermittedSoftverException(
                respuestaBase.getMensaje() + (respuestaBase.getMensajeDetalle() == null
                ? ""
                : " - " + respuestaBase.getMensajeDetalle())
            );
        }
        cfdi = (Cfdi) respuestaBase.getEntidad();
        if (cfdi.getCancelado()) {
            // se actualiza el estatus a cancelado
            facturaData.actualizarEstatus(
                info.getIdEmpresa()
                , entidad.getId()
                , "6"
                , info.getIdUsuario()
            );
        } else {
            // se actualiza el estatus a en proceso de cancelado
            facturaData.actualizarEstatus(
                info.getIdEmpresa()
                , entidad.getId()
                , "5"
                , info.getIdUsuario()
            );
        }
        return cfdi;
    }
    
    @Override
    public Cfdi comprobarCancelacion(InfoAuditoria info, Factura entidad) throws Exception {
        validarDatosAuditoria(info);
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id es requerido"
            );
        }
        if (!facturaData.verificarId(info.getIdEmpresa(), entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id ingresado no existe"
            );
        }
        if (entidad.getUuid() == null) {
            throw new OperationNotPermittedSoftverException(
                "El uuid es requerido"
            );
        }
        if (entidad.getUuid().isEmpty()) {
            throw new OperationNotPermittedSoftverException(
                "El uuid es requerido"
            );
        }
        if (!facturaData.verificarUuid(entidad.getId(), entidad.getUuid())) {
            throw new OperationNotPermittedSoftverException(
                "El uuid ingresado no existe"
            );
        }
        String estatus = facturaData.obtenerEstatus(entidad.getId());
        if (!estatus.equals("5")) {
            throw new OperationNotPermittedSoftverException(
                "No se puede comprobar la cancelacion de una factura que no haya iniciado su proceso de cancelacion"
            );
        }
        Cfdi cfdi = new Cfdi();
        cfdi.setUuid(entidad.getUuid());
        cfdi.setRfcEmisor(info.getRfcEmpresa());
        ClienteApiWSData clienteApi = new ClienteApiWSData(
            facturaData.obtenerTokenServicio(info.getIdEmpresa())
            , info.getRfcEmpresa()
        );
        String urlTimbrado = facturaData.obtenerUrlTimbrado();
        RespuestaBase respuestaBase = clienteApi.post(
            urlTimbrado
            , "estado/obtener"
            , new TypeReference<RespuestaBase<Cfdi>>(){}
            , cfdi  
        );
        if (respuestaBase.isError()) {
            throw new OperationNotPermittedSoftverException(
                respuestaBase.getMensaje() + (respuestaBase.getMensajeDetalle() == null
                ? ""
                : " - " + respuestaBase.getMensajeDetalle())
            );
        }
        cfdi = (Cfdi) respuestaBase.getEntidad();
        if (cfdi.getCancelado()) {
            // se actualiza el estatus a cancelado
            facturaData.actualizarEstatus(
                info.getIdEmpresa()
                , entidad.getId()
                , "6"
                , info.getIdUsuario()
            );
        }
        
        return cfdi;
    }
    
    @Override
    public Archivo descargarXml(InfoAuditoria info, Factura entidad)
        throws Exception {
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id es requerido"
            );
        }
        if (!facturaData.verificarId(info.getIdEmpresa(), entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id ingresado no existe"
            );
        }
        if (entidad.getUuid() == null) {
            throw new OperationNotPermittedSoftverException(
                "El uuid es requerido"
            );
        }
        if (entidad.getUuid().isEmpty()) {
            throw new OperationNotPermittedSoftverException(
                "El uuid es requerido"
            );
        }
        if (!facturaData.verificarUuid(entidad.getId(), entidad.getUuid())) {
            throw new OperationNotPermittedSoftverException(
                "El uuid ingresado no existe"
            );
        }
        
        Cfdi cfdi = new Cfdi();
        cfdi.setUuid(entidad.getUuid());
        ClienteApiWSData clienteApi = new ClienteApiWSData(
            facturaData.obtenerTokenServicio(info.getIdEmpresa())
            , info.getRfcEmpresa()
        );
        String urlDescargas = facturaData.obtenerUrlDescargas();
        RespuestaBase respuestaBase = clienteApi.post(
            urlDescargas
            , "factura/cfdi/uuid/xml/obtener"
            , new TypeReference<RespuestaBase<Archivo>>(){}
            , cfdi
        );
        if (respuestaBase.isError()) {
            throw new OperationNotPermittedSoftverException(
                respuestaBase.getMensaje() + (respuestaBase.getMensajeDetalle() == null
                ? ""
                : " - " + respuestaBase.getMensajeDetalle())
            );
        }
        return (Archivo) respuestaBase.getEntidad();
    }

    @Override
    public Archivo descargarPdf(InfoAuditoria info, Factura entidad)
        throws Exception {
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id es requerido"
            );
        }
        if (!facturaData.verificarId(info.getIdEmpresa(), entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id ingresado no existe"
            );
        }
        if (entidad.getUuid() == null) {
            throw new OperationNotPermittedSoftverException(
                "El uuid es requerido"
            );
        }
        if (entidad.getUuid().isEmpty()) {
            throw new OperationNotPermittedSoftverException(
                "El uuid es requerido"
            );
        }
        if (!facturaData.verificarUuid(entidad.getId(), entidad.getUuid())) {
            throw new OperationNotPermittedSoftverException(
                "El uuid ingresado no existe"
            );
        }
        UuidConceptos objeto = new UuidConceptos();
        objeto.setUuid(entidad.getUuid());
        facturaData.obtenerDatosEncabezado(
            info.getIdEmpresa()
            , entidad.getId()
            , objeto
        );
        objeto.setConceptos(
            facturaData.obtenerConceptosCompletos(entidad.getId())
        );
        if (objeto.getLogo() != null) {
            generarLogo(info.getIdEmpresa(), objeto.getLogo());
        }
        ClienteApiWSData clienteApi = new ClienteApiWSData(
            facturaData.obtenerTokenServicio(info.getIdEmpresa())
            , info.getRfcEmpresa()
        );
        String urlDescargas = facturaData.obtenerUrlDescargas();
        RespuestaBase respuestaBase = clienteApi.post(
            urlDescargas
            , "factura/cfdi/uuid/pdf/obtener"
            , new TypeReference<RespuestaBase<Archivo>>(){}
            , objeto
        );
        if (respuestaBase.isError()) {
            throw new OperationNotPermittedSoftverException(
                respuestaBase.getMensaje() + (respuestaBase.getMensajeDetalle() == null
                ? ""
                : " - " + respuestaBase.getMensajeDetalle())
            );
        }
        return (Archivo) respuestaBase.getEntidad();
    }
    
    @Override
    public void enviarCorreo(InfoAuditoria info, IdCorreo entidad)
        throws Exception {
        validarDatosAuditoria(info);
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id es requerido"
            );
        }
        if (!facturaData.verificarId(info.getIdEmpresa(), entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id ingresado no existe"
            );
        }
        if (entidad.getUuid() == null) {
            throw new OperationNotPermittedSoftverException(
                "El uuid es requerido"
            );
        }
        if (entidad.getUuid().isEmpty()) {
            throw new OperationNotPermittedSoftverException(
                "El uuid es requerido"
            );
        }
        if (!facturaData.verificarUuid(entidad.getId(), entidad.getUuid())) {
            throw new OperationNotPermittedSoftverException(
                "El uuid ingresado no existe"
            );
        }
        if (entidad.getCorreo() == null) {
            throw new OperationNotPermittedSoftverException(
                "El correo es requerido"
            );
        }
        List<Archivo> archivos = new ArrayList<>();
        Factura factura = new Factura();
        factura.setId(entidad.getId());
        factura.setUuid(entidad.getUuid());
        archivos.add(descargarXml(info, factura));
        archivos.add(descargarPdf(info, factura));
        
        Mensaje mensaje = new Mensaje();
        mensaje.setDestinatario(entidad.getCorreo());
        mensaje.setAsunto("Comprobante Fiscal Digital");
        mensaje.setMensaje("Envío de PDF y XML del comprobante: " + entidad.getUuid());
        
        mensajeriaService.enviar(mensaje, archivos);
    }
    
    @Override
    public FacturaVistaPrevia obtenerVistaPrevia(InfoAuditoria info, Id entidad)
        throws Exception {
        validarDatosAuditoria(info);
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id es requerido"
            );
        }
        if (!facturaData.verificarId(info.getIdEmpresa(), entidad.getId())) {
            throw new OperationNotPermittedSoftverException(
                "El id ingresado no existe"
            );
        }
        FacturaVistaPrevia factura = new FacturaVistaPrevia();
        ObjectMapper mapper = new ObjectMapper();
        factura.setId(entidad.getId());
        FacturaConcepto facturaConcepto = obtenerPorId(info, factura);
        String facturaJSON = mapper.writeValueAsString(facturaConcepto);
        factura = mapper.readValue(facturaJSON, FacturaVistaPrevia.class);
        facturaData.obtenerVistaPrevia(info.getIdEmpresa(), factura);
        factura.setTotalEnLetra(NumerosALetras.Convertir(factura.getTotal().toString(), true));
        factura.setTotalImpFedTrasladados(0.0);
        factura.setTotalImpFedRetenidos(0.0);
        factura.setTotalImpLocTrasladados(0.0);
        factura.setTotalImpLocRetenidos(0.0);
        //se llenan los datos faltantes de los conceptos para la vista previa
        for (ConceptoCompleto concepto : factura.getConceptos()) {
            facturaData.obtenerConceptoCompleto(concepto);
            for (Impuesto impuesto : concepto.getImpuestos()) {
                // se busca si es un impuesto federal
                if (impuesto.getOrigen().equals("F")) {
                    // se verifica si es trasladado
                    if (impuesto.getTipoImpuesto().equals("T")) {
                        if (factura.getTotalImpFedTrasladados() == 0) {
                            factura.setTotalImpFedTrasladados(
                                redondear(impuesto.getTotalImpuesto(), 2)
                            );
                        } else {
                            factura.setTotalImpFedTrasladados(
                                redondear(
                                    factura.getTotalImpFedTrasladados() 
                                    + impuesto.getTotalImpuesto()
                                , 2)
                            );
                        }
                    } else {
                        // si es un federal retenido cae aqui
                        if (factura.getTotalImpFedRetenidos() == 0) {
                            factura.setTotalImpFedRetenidos(
                                redondear(impuesto.getTotalImpuesto(), 2)
                            );
                        } else {
                            factura.setTotalImpFedRetenidos(
                                redondear(
                                    factura.getTotalImpFedRetenidos() 
                                    + impuesto.getTotalImpuesto()
                                , 2)
                            );
                        }
                    }
                } else {
                    // si es un impuesto local entra aqui
                    if (impuesto.getTipoImpuesto().equals("T")) {
                        if (factura.getTotalImpLocTrasladados() == 0) {
                            factura.setTotalImpLocTrasladados(
                                redondear(impuesto.getTotalImpuesto(), 2)
                            );
                        } else {
                            factura.setTotalImpLocTrasladados(
                                redondear(
                                    factura.getTotalImpLocTrasladados() 
                                    + impuesto.getTotalImpuesto()
                                , 2)
                            );
                        }
                    } else {
                        // si es un federal retenido cae aqui
                        if (factura.getTotalImpLocRetenidos() == 0) {
                            factura.setTotalImpLocRetenidos(
                                redondear(impuesto.getTotalImpuesto(), 2)
                            );
                        } else {
                            factura.setTotalImpLocRetenidos(
                                redondear(
                                    factura.getTotalImpLocRetenidos() 
                                    + impuesto.getTotalImpuesto()
                                , 2)
                            );
                        }
                    }
                }
            }
        }
        if (factura.getLogo() != null) {
            generarLogo(info.getIdEmpresa(), factura.getLogo());
        }
        factura.setQrBase64(generarQr());
        
        return factura;
    }
    
    
    @Override
    public FacturaConcepto obtenerFacturaClonada(InfoAuditoria info
        , Factura entidad) throws Exception {
        FacturaConcepto factura = obtenerPorId(info, entidad);
        factura.setId(0);
        factura.setFolio(0);
        factura.setUuid(null);
        factura.setEstatus("1");
        factura.setFacturasRelacionadas(null);
        int posicion = 0;
        for (Concepto concepto : factura.getConceptos()) {
            posicion = posicion + 1;
            concepto.setId(posicion);
            // se recalculan los impuestos si la facturacion es por cotizacion y el concepto tiene un descuento
            if (factura.getTipoFacturacion() != null
                && factura.getTipoFacturacion().equals("1")
                && concepto.getDescuento() > 0) {
                concepto.setDescuento(0.0);
                for (Impuesto impuesto : concepto.getImpuestos()) {
                    impuesto.setId(0);
                    impuesto.setIdConcepto(0);
                    impuesto.setTotalImpuesto(
                        redondear(
                            concepto.getImporte() * impuesto.getTasaOCuota()
                            , 6
                        )
                    );
                }
            } else {
                for (Impuesto impuesto : concepto.getImpuestos()) {
                    impuesto.setId(0);
                    impuesto.setIdConcepto(0);
                }
            }
        }
        factura.setTipoFacturacion(null);
        
        return factura;
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
     * metodo para validar los datos de una factura
     * @param entidad
     * @throws Exception 
     */
    private void validarDatosFactura(Factura entidad) throws Exception {
        if (entidad == null) {
            throw new OperationNotPermittedSoftverException(
                "La entidad es requerida"
            );
        }
        if (entidad.getIdCliente() < 1) {
            throw new OperationNotPermittedSoftverException(
                "El id del cliente es requerido"
            );
        }
        if (entidad.getTipoComprobante() == null) {
            throw new OperationNotPermittedSoftverException(
                "El tipo de comprobante es requerido"
            );
        }
        if (entidad.getSerie() == null) {
            throw new OperationNotPermittedSoftverException(
                "La serie es requerida"
            );
        }
        if (entidad.getFechaExpedicion() == null) {
            throw new OperationNotPermittedSoftverException(
                "La fecha de expedicion es requerida"
            );
        }
        if (entidad.getClaveFormaPago() == null) {
            throw new OperationNotPermittedSoftverException(
                "La clave de forma de pago es requerida"
            );
        }
        if (!facturaCatalogoData.verificarClaveFormaPago(entidad.getClaveFormaPago())) {
            throw new OperationNotPermittedSoftverException(
                "La clave de forma de pago ingresada no existe"
            );
        }
        if (entidad.getClaveMetodoPago() == null) {
            throw new OperationNotPermittedSoftverException(
                "El metodo de pago es requerido"
            );
        }
        if (entidad.getClaveMoneda() == null) {
            throw new OperationNotPermittedSoftverException(
                "La clave de la moneda es requerida"
            );
        }
        if (!facturaCatalogoData.verificarClaveMoneda(entidad.getClaveMoneda())) {
            throw new OperationNotPermittedSoftverException(
                "La clave para moneda ingresada, no existe"
            );
        }
        if (entidad.getTipoCambio() == null) {
            throw new OperationNotPermittedSoftverException(
                "El tipo de cambio es requerido"
            );
        }
        if (entidad.getClaveUsoCfdi() == null) {
            throw new OperationNotPermittedSoftverException(
                "La clave del uso del cfdi es requerida"
            );
        }
        if (!facturaCatalogoData.verificarClaveUsoCfdi(entidad.getClaveUsoCfdi())) {
            throw new OperationNotPermittedSoftverException(
                "La clave de uso del cfdi ingresada no existe"
            );
        }
        if (entidad.getSubtotal() == null) {
            throw new OperationNotPermittedSoftverException(
                "El subtotal es requerido"
            );
        }
        if (entidad.getDescuento() == null) {
            throw new OperationNotPermittedSoftverException(
                "El descuento es requerido"
            );
        }
        if (entidad.getTotal() == null) {
            throw new OperationNotPermittedSoftverException(
                "El total es requerido"
            );
        }
        if (entidad.getVersion() == null) {
            throw new OperationNotPermittedSoftverException(
                "La version del cfdi es requerida"
            );
        }
    }
    
    /**
     * metodo para validar el listado de conceptos y sus datos generales
     * @param conceptos
     * @throws Exception 
     */
    private void validarDatosConceptos(List<Concepto> conceptos) throws Exception{
        for (Concepto entidad : conceptos) {
            if (entidad == null) {
                throw new OperationNotPermittedSoftverException(
                    "La entidad es requerida"
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
    
    /**
     * metodo para validar los datos de las facturas relacionadas
     * @param info
     * @param lista
     * @throws Exception 
     */
    private void validarDatosFacturasRelacionadas(InfoAuditoria info
        , List<FacturaRelacionada> lista) throws Exception{
        for (FacturaRelacionada entidad : lista) {
            if (entidad == null) {
                throw new OperationNotPermittedSoftverException(
                    "La entidad es requerida"
                );
            }
            if (entidad.getIdFacturaRelacionada() < 1) {
                throw new OperationNotPermittedSoftverException(
                    "El id de la factura relacionada es requerido"
                );
            }
            if (!facturaData.verificarId(info.getIdEmpresa(),
                entidad.getIdFacturaRelacionada())) {
                throw new OperationNotPermittedSoftverException(
                    "El id de la factura relacionada ingresado no existe"
                );
            }
        }
    }
    
    /**
    * metodo para validar que la fecha de elaboracion de un comprobante entre en los rangos permitidos
    * @param fechaEmision
    * @param entidad
    * @param idEmpresa
    * @param idUsuario
    * @throws Exception 
    */
    private void validarFechaEmision(FacturaConcepto entidad, int idEmpresa
        , int idUsuario) throws Exception{
        Date fechaElaboracion = entidad.getFechaExpedicion();
        Date fechaActual = new Date();
        Date fechaRestada = restarHoras(fechaActual, -72);

        if (fechaElaboracion.compareTo(fechaRestada) < 0) {
            facturaData.actualizarEstatus(
                idEmpresa
                , entidad.getId()
                , "1"
                , idUsuario
            );
            throw new OperationNotPermittedSoftverException(
                "No se pueden timbrar facturas elaboradas hace más de 3 días."
            );
        } else {
            if (fechaElaboracion.compareTo(fechaActual) > 0) {
                facturaData.actualizarEstatus(
                    idEmpresa
                    , entidad.getId()
                    , "1"
                    , idUsuario
                );
                throw new OperationNotPermittedSoftverException(
                    "No se pueden timbrar facturas con fechas futuras."
                );
            }
        }
    }
    
    /**
     * metodo para restar horas a una fecha dada
     * @param fecha
     * @param horas
     * @return 
     */
    public Date restarHoras(Date fecha, int horas) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.HOUR, horas);
        return calendar.getTime();
    }
    
    /**
     * metodo para redondear un numero
     * @param numero
     * @param numeroDecimales
     * @return 
     */
    private Double redondear(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }
    
    /**
     * metodo para generar el archivo de un logo a partir de una ruta
     * @param entidad
     * @throws Exception 
     */
    private void generarLogo(int idEmpresa, LogoCompania entidad) throws Exception{
        String ruta = facturaData.obtenerRutaBase(idEmpresa);
        File archivo = new File(ruta + entidad.getNombre());
        if(!archivo.exists()){
            throw new OperationNotPermittedSoftverException(
                "el archivo no existe"
            );
        }
        entidad.setNombre(archivo.getName());

        byte[] bArray = new byte[(int) archivo.length()];
        FileInputStream fis = new FileInputStream(archivo);
        fis.read(bArray);
        fis.close();
        entidad.setBase64(new String( Base64.getEncoder().encode(bArray), "UTF-8") );
    }
    
    private String generarQr() throws Exception{
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        // Se genera el codigo QR
        String texto = "Este es un codigo QR de muestra, el QR oficial aparecera al timbrarse el CFDI.";
        int width = 300;
        int height = 300;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = 
            qrCodeWriter.encode(
                texto
                , BarcodeFormat.QR_CODE
                , width
                , height
            );
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] bArray = pngOutputStream.toByteArray();
        return new String(Base64.getEncoder().encode(bArray), "UTF-8");
    }

    @Override
    public Archivo descargarReporte(InfoAuditoria info, FacturaFiltro filtro) throws Exception {

  DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
        Date fecha = new Date();
        
        validarDatosAuditoria(info);
        
        if(filtro == null){
            throw new OperationNotPermittedSoftverException("La entidad es requerida");
        }
        
        List<ReporteFactura> listaFacturas = facturaData.obtenerDatosReporteDescargar(info.getIdEmpresa(), filtro);
        if(listaFacturas.size() < 1){
            throw new OperationNotPermittedSoftverException("No se encontraron resultados con "
                    + "los filtros ingresados");
        }
        ExcelServiceImpl excelServiceImpl = new ExcelServiceImpl(); 
     
        XSSFWorkbook reporte = excelServiceImpl.crearExcelVentasDiarias(listaFacturas);
        byte[] reporteEnBytes = convertirDocumentoABytes(reporte);
        return new Archivo("Reporte de Facturas "+dtf.format(fecha), "xlsx","", reporteEnBytes);
    }
    
    private byte[] convertirDocumentoABytes(XSSFWorkbook documentoExcel) throws Exception{
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        try{
            documentoExcel.write(byteArray);
        }catch(IOException ex){
            throw new OperationNotPermittedSoftverException("Error al convertir el documento a bytes");
        }
        return byteArray.toByteArray(); 
    }
}
