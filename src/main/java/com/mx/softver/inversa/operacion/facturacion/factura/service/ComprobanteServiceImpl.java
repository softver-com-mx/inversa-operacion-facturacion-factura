/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.service;

import com.mx.softver.inversa.operacion.facturacion.factura.datainterface.ComprobanteData;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Concepto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaConcepto;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.FacturaRelacionada;
import com.mx.softver.inversa.operacion.facturacion.factura.entity.Impuesto;
import com.mx.softver.inversa.operacion.facturacion.factura.serviceinterface.ComprobanteService;
import com.softver.comunes.entity.InfoAuditoria;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.CfdiRelacionado;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.CfdiRelacionados;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.ComplementoComprobante;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.Comprobante;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.ConceptoComprobante;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.ImpuestoComprobante;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.ImpuestoLocal;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.RetencionComprobante;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.RetencionLocal;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.TrasladoComprobante;
import com.softver.erp.comunes.comprobantefiscal.entity.cdfi33.TrasladoLocal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jhernandez
 */
public class ComprobanteServiceImpl implements ComprobanteService{
    private ComprobanteData comprobanteData;

    /**
     * constructor
     * @param comprobanteData 
     */
    public ComprobanteServiceImpl(ComprobanteData comprobanteData) {
        this.comprobanteData = comprobanteData;
    }

    @Override
    public Comprobante procesar(InfoAuditoria info, FacturaConcepto entidad)
        throws Exception {
        Comprobante comprobante = new Comprobante();
        llenarEncabezado(comprobante, entidad, info);
        llenarCfdisRelacionados(comprobante, entidad);
        llenarEmisorReceptor(comprobante, entidad, info);
        llenarConceptos(comprobante, entidad);
        llenarImpuestosConceptos(comprobante, entidad);
        llenarImpuestosComprobante(comprobante);
        return comprobante;
    }
    
    /**
     * metodo para llenar los datos generales del comprobante
     * @param comprobante
     * @param entidad
     * @throws Exception 
     */
    private void llenarEncabezado(Comprobante comprobante
        , FacturaConcepto entidad, InfoAuditoria info) throws Exception{
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formater.format(entidad.getFechaExpedicion());
        
        comprobante.setVersion(entidad.getVersion());
        comprobante.setSerie(entidad.getSerie());
        comprobante.setFolio(String.valueOf(entidad.getFolio()));
        comprobante.setFecha(formater.format(entidad.getFechaExpedicion()));
        comprobante.setFormaPago(entidad.getClaveFormaPago());
        comprobante.setCondicionesDePago(entidad.getCondicionPago());
        comprobante.setMoneda(entidad.getClaveMoneda());
        comprobante.setTipoDeComprobante(entidad.getTipoComprobante());
        comprobante.setMetodoPago(entidad.getClaveMetodoPago());
        if (entidad.getDescuento() > 0) {
            comprobante.setDescuento(entidad.getDescuento());
        }
        comprobante.setSubTotal(entidad.getSubtotal());
        comprobante.setTotal(entidad.getTotal());
        comprobante.setLugarExpedicion(comprobanteData.obtenerLugarExpedicion(info.getIdEmpresa()));
        if (!entidad.getClaveMoneda().equals("MXN") 
            && !entidad.getClaveMoneda().equals("XXX")) {
            comprobante.setTipoCambio(entidad.getTipoCambio());
        }
    }
    
    /**
     * metodo para llenar los cfdis relacionados de un comprobante
     * @param comprobante
     * @param entidad 
     */
    private void llenarCfdisRelacionados(Comprobante comprobante
        , FacturaConcepto entidad) {
        if (entidad.getFacturasRelacionadas().size() > 0) {
            CfdiRelacionados cfdiRelacionados = new CfdiRelacionados();
            cfdiRelacionados.setTipoRelacion(entidad.getClaveTipoRelacion());
            List<CfdiRelacionado> lista = new ArrayList<>();
            for (FacturaRelacionada facturaRelacionada : entidad.getFacturasRelacionadas()) {
                CfdiRelacionado cfdiRelacionado = new CfdiRelacionado();
                cfdiRelacionado.setUuid(facturaRelacionada.getUuid());
                lista.add(cfdiRelacionado);
            }
            cfdiRelacionados.setCfdiRelacionado(lista);
            comprobante.setCfdiRelacionados(cfdiRelacionados);
        }
    }
    
    /**
     * metodo para llenar el emisor y receptor de un comprobante
     * @param comprobante
     * @param entidad 
     */
    private void llenarEmisorReceptor(Comprobante comprobante
        , FacturaConcepto entidad, InfoAuditoria info) throws Exception{
        comprobante.setEmisor(
            comprobanteData.obtenerEmisor(info.getIdEmpresa())
        );
        comprobante.setReceptor(
            comprobanteData.obtenerReceptor(entidad.getId())
        );
    }
    
    /**
     * metodo para llenar los conceptos de un comprobante
     * @param comprobante
     * @param entidad 
     */
    private void llenarConceptos(Comprobante comprobante
        , FacturaConcepto entidad) throws Exception{
        comprobante.setConceptos(
            comprobanteData.obtenerConceptos(entidad.getId())
        );
        for (ConceptoComprobante concepto : comprobante.getConceptos()) {
            if (concepto.getDescuento() != null) {
                concepto.setDescuento(
                    redondear(concepto.getImporte() * concepto.getDescuento(), 6)
                );
            }
        }
    }
    
    private void llenarImpuestosConceptos(Comprobante comprobante
        , FacturaConcepto entidad) {
        List<TrasladoLocal> trasladosLocales = new ArrayList<>();
        List<RetencionLocal> retencionesLocales = new ArrayList<>();
        
        for (int i = 0; i < comprobante.getConceptos().size(); i++) {
            List<TrasladoComprobante> traslados = new ArrayList<>();
            List<RetencionComprobante> retenciones = new ArrayList<>();
            ConceptoComprobante conceptoComprobante = comprobante.getConceptos().get(i);
            Double descuento = 0.0;
            if (conceptoComprobante.getDescuento() != null) {
                descuento = conceptoComprobante.getDescuento();
            }
            Double base = conceptoComprobante.getImporte() - descuento;
            Double acumulado = 0.0;
            
            Concepto concepto = entidad.getConceptos().get(i);
            for (int j = 0; j < concepto.getImpuestos().size(); j++) {
                Impuesto impuesto = concepto.getImpuestos().get(j);
                
                if (impuesto.getBaseOAcumulado().equals("B")) {
                    // impuestos base
                    if (impuesto.getOrigen().equals("F")) {
                        // impuestos federales
                        if (impuesto.getTipoImpuesto().equals("T")) {
                            // impuestos trasladados
                            if (!impuesto.getFactor().equals("E")) {
                                TrasladoComprobante traslado = new TrasladoComprobante();
                                traslado.setTipoFactor(sustituirFactor(impuesto.getFactor()));
                                traslado.setImpuesto(impuesto.getClaveImpuestoSat());
                                traslado.setTasaOCuota(impuesto.getTasaOCuota());
                                traslado.setImporte(impuesto.getTotalImpuesto());
                                traslado.setBase(base);
                                acumulado += traslado.getImporte();
                                traslados.add(traslado);
                            }
                        } else {
                            // impuestos retenidos
                            RetencionComprobante retencion = new RetencionComprobante();
                            retencion.setTipoFactor(sustituirFactor(impuesto.getFactor()));
                            retencion.setImpuesto(impuesto.getClaveImpuestoSat());
                            retencion.setTasaOCuota(impuesto.getTasaOCuota());
                            retencion.setImporte(impuesto.getTotalImpuesto());
                            retencion.setBase(base);
                            acumulado += retencion.getImporte();
                            retenciones.add(retencion);
                        }
                    } else {
                        // impuestos locales
                        if (impuesto.getTipoImpuesto().equals("T")) {
                            // impuestos trasladados
                            TrasladoLocal trasladoLocal = new TrasladoLocal();
                            trasladoLocal.setImpLocTraslado(impuesto.getNombre());
                            trasladoLocal.setTasaDeTraslado(impuesto.getTasaOCuota());
                            trasladoLocal.setImporte(impuesto.getTotalImpuesto());
                            if (impuesto.getFactor().equals("T")) {
                                trasladoLocal.setTasaDeTraslado(
                                    trasladoLocal.getTasaDeTraslado() * 100
                                );
                            }
                            acumulado += trasladoLocal.getImporte();
                            trasladosLocales.add(trasladoLocal);
                        } else {
                            // impuestos retenidos
                            RetencionLocal retencionLocal = new RetencionLocal();
                            retencionLocal.setImpLocRetenido(impuesto.getNombre());
                            retencionLocal.setTasaDeRetencion(impuesto.getTasaOCuota());
                            retencionLocal.setImporte(impuesto.getTotalImpuesto());
                            if (impuesto.getFactor().equals("T")) {
                                retencionLocal.setTasaDeRetencion(
                                    retencionLocal.getTasaDeRetencion() * 100
                                );
                            }
                            acumulado += retencionLocal.getImporte();
                            retencionesLocales.add(retencionLocal);
                        }
                    }
                }
                
                Double baseAcumulada = base + acumulado;
                
                if (impuesto.getBaseOAcumulado().equals("A")) {
                    // impuestos base
                    if (impuesto.getOrigen().equals("F")) {
                        // impuestos federales
                        if (impuesto.getTipoImpuesto().equals("T")) {
                            // impuestos trasladados
                            if (!impuesto.getFactor().equals("E")) {
                                TrasladoComprobante traslado = new TrasladoComprobante();
                                traslado.setTipoFactor(sustituirFactor(impuesto.getFactor()));
                                traslado.setImpuesto(impuesto.getClaveImpuestoSat());
                                traslado.setTasaOCuota(impuesto.getTasaOCuota());
                                traslado.setImporte(impuesto.getTotalImpuesto());
                                traslado.setBase(baseAcumulada);
                                baseAcumulada += traslado.getImporte();
                                traslados.add(traslado);
                            }
                        } else {
                            // impuestos retenidos
                            RetencionComprobante retencion = new RetencionComprobante();
                            retencion.setTipoFactor(sustituirFactor(impuesto.getFactor()));
                            retencion.setImpuesto(impuesto.getClaveImpuestoSat());
                            retencion.setTasaOCuota(impuesto.getTasaOCuota());
                            retencion.setImporte(impuesto.getTotalImpuesto());
                            retencion.setBase(baseAcumulada);
                            baseAcumulada += retencion.getImporte();
                            retenciones.add(retencion);
                        }
                    } else {
                        // impuestos locales
                        if (impuesto.getTipoImpuesto().equals("T")) {
                            // impuestos trasladados
                            TrasladoLocal trasladoLocal = new TrasladoLocal();
                            trasladoLocal.setImpLocTraslado(impuesto.getNombre());
                            trasladoLocal.setTasaDeTraslado(impuesto.getTasaOCuota());
                            trasladoLocal.setImporte(impuesto.getTotalImpuesto());
                            if (impuesto.getFactor().equals("T")) {
                                trasladoLocal.setTasaDeTraslado(
                                    trasladoLocal.getTasaDeTraslado() * 100
                                );
                            }
                            baseAcumulada += trasladoLocal.getImporte();
                            trasladosLocales.add(trasladoLocal);
                        } else {
                            // impuestos retenidos
                            RetencionLocal retencionLocal = new RetencionLocal();
                            retencionLocal.setImpLocRetenido(impuesto.getNombre());
                            retencionLocal.setTasaDeRetencion(impuesto.getTasaOCuota());
                            retencionLocal.setImporte(impuesto.getTotalImpuesto());
                            if (impuesto.getFactor().equals("T")) {
                                retencionLocal.setTasaDeRetencion(
                                    retencionLocal.getTasaDeRetencion() * 100
                                );
                            }
                            baseAcumulada += retencionLocal.getImporte();
                            retencionesLocales.add(retencionLocal);
                        }
                    }
                }
            }
            // se llena el objeto de impuestos y se agrega al concepto
            if (!retenciones.isEmpty() || !traslados.isEmpty()) {
                ImpuestoComprobante impuestoComprobante = new ImpuestoComprobante();
                if (!retenciones.isEmpty()) {
                    impuestoComprobante.setRetenciones(retenciones);
                }
                if (!traslados.isEmpty()) {
                    impuestoComprobante.setTraslados(traslados);
                }
                comprobante.getConceptos().get(i).setImpuesto(impuestoComprobante);
            }
        }
        // se llenan los impuestos locales del comprobante
        if (!retencionesLocales.isEmpty() || !trasladosLocales.isEmpty()) {
            ComplementoComprobante complementoComprobante = new ComplementoComprobante();
            ImpuestoLocal impuestoLocal = new ImpuestoLocal();
            impuestoLocal.setVersion("1.0");
            impuestoLocal.setTotalDeRetenciones(
                redondear(obtenerTotalRetenciones(retencionesLocales), 2)
            );
            impuestoLocal.setTotalDeTraslados(
                redondear(obtenerTotalTraslados(trasladosLocales), 2)
            );
            if (!retencionesLocales.isEmpty()) {
                impuestoLocal.setRetencionesLocales(retencionesLocales);
            }
            if (!trasladosLocales.isEmpty()) {
                impuestoLocal.setTrasladosLocales(trasladosLocales);
            }
            complementoComprobante.setImpuestoLocal(impuestoLocal);
            comprobante.setComplemento(complementoComprobante);
        }
    }
    
    /**
     * metodo para obtener el total de retenciones de los impuestos locales
     * @param retencionesLocales
     * @return 
     */
    private Double obtenerTotalRetenciones(List<RetencionLocal> retencionesLocales) {
        Double totalRetenciones = 0.0;
        for (RetencionLocal retencionLocal : retencionesLocales) {
            if (retencionLocal.getImporte() != null) {
                totalRetenciones += retencionLocal.getImporte();
            }
        }
        return totalRetenciones;
    }
    
    /**
     * metodo para obtener el total de traslados de los impuestos locales
     * @param trasladosLocales
     * @return 
     */
    private Double obtenerTotalTraslados(List<TrasladoLocal> trasladosLocales) {
        Double totalTraslados = 0.0;
        for (TrasladoLocal trasladoLocal : trasladosLocales) {
            if (trasladoLocal.getImporte() != null) {
                totalTraslados += trasladoLocal.getImporte();
            }
        }
        return totalTraslados;
    }
    
    /**
     * metodo para llenar el nodo de impuestos del comprobante
     * @param comprobante 
     */
    private void llenarImpuestosComprobante(Comprobante comprobante) {
        List<ConceptoComprobante> conceptos = comprobante.getConceptos();
        ImpuestoComprobante impuestoComprobante = new ImpuestoComprobante();
        obtenerTotalImpuestosTrasladados(comprobante, impuestoComprobante);
        obtenerTotalImpuestosRetenidos(comprobante, impuestoComprobante);
        llenarImpuestosRetenidos(conceptos, impuestoComprobante);
        llenarImpuestosTrasladados(conceptos, impuestoComprobante);
        
        if (impuestoComprobante.getTotalImpuestosRetenidos() != null
            || impuestoComprobante.getTotalImpuestosTrasladados() != null) {
            comprobante.setImpuesto(impuestoComprobante);
        }
    }
    
    /**
     * metodo para obtener el total de los importes de los impuestos trasladados
     * @param conceptos
     * @return 
     */
    private void obtenerTotalImpuestosTrasladados(Comprobante comprobante
        , ImpuestoComprobante impuestoComprobante) {
        
        Double totalImpuestosTrasladados = null;
        for (ConceptoComprobante concepto : comprobante.getConceptos()) {
            if (concepto.getImpuesto() != null) {
                List<TrasladoComprobante> impuestosTrasladados = 
                    concepto.getImpuesto().getTraslados();
                if (impuestosTrasladados != null) {
                    for (TrasladoComprobante traslado : impuestosTrasladados) {
                        if (traslado.getImporte() != null) {
                            if (totalImpuestosTrasladados == null) {
                                totalImpuestosTrasladados = traslado.getImporte();
                            } else {
                                totalImpuestosTrasladados += traslado.getImporte();
                            }
                        }
                    }
                }
            }
        }
        
        if (totalImpuestosTrasladados != null) {
            impuestoComprobante.setTotalImpuestosTrasladados(
                redondear(totalImpuestosTrasladados, 2)
            );
        }
    }
    
    /**
     * metodo para obtener el total de los importes de los impuestos retenidos
     * @param conceptos
     * @return 
     */
    private void obtenerTotalImpuestosRetenidos(Comprobante comprobante
        , ImpuestoComprobante impuestoComprobante) {
        
        Double totalImpuestosRetenidos = null;
        for (ConceptoComprobante concepto : comprobante.getConceptos()) {
            if (concepto.getImpuesto() != null) {
                List<RetencionComprobante> impuestosRetenidos =
                    concepto.getImpuesto().getRetenciones();
                if (impuestosRetenidos != null) {
                    for (RetencionComprobante retencion : impuestosRetenidos) {
                        if (retencion.getImporte() != null) {
                            if (totalImpuestosRetenidos == null) {
                                totalImpuestosRetenidos = retencion.getImporte();
                            } else {
                                totalImpuestosRetenidos += retencion.getImporte();
                            }
                        }
                    }
                }
            }
        }
        
        if (totalImpuestosRetenidos != null) {
            impuestoComprobante.setTotalImpuestosRetenidos(
                redondear(totalImpuestosRetenidos, 2)
            );
        }
    }
    
    /**
     * metodo para llenar los impuestos retenidos de un comprobante
     * @param conceptos
     * @param impuestoComprobante 
     */
    private void llenarImpuestosRetenidos(List<ConceptoComprobante> conceptos
        , ImpuestoComprobante impuestoComprobante) {
        
        List<RetencionComprobante> retenciones = new ArrayList<>();
        
        for (int i = 0; i < conceptos.size(); i++) {
            ConceptoComprobante concepto = conceptos.get(i);
            if (concepto.getImpuesto() != null 
                && concepto.getImpuesto().getRetenciones() != null 
                && concepto.getImpuesto().getRetenciones().size() > 0) {
                
                List<RetencionComprobante> retenidos = 
                    concepto.getImpuesto().getRetenciones();
                
                for (int j = 0; j < retenidos.size(); j++) {
                    
                    RetencionComprobante retenido = retenidos.get(j);
                    if (retenciones.size() < 1) {
                        RetencionComprobante retencion =
                            new RetencionComprobante();
                        retencion.setImpuesto(retenido.getImpuesto());
                        retencion.setImporte(redondear(retenido.getImporte(), 2));
                        retenciones.add(retencion);
                    } else {
                        int contador = 0;
                        for (int k = 0; k < retenciones.size(); k++) {
                            if (retenido.getImpuesto().equals(
                                retenciones.get(k).getImpuesto())) {
                                contador++;
                            }
                        }
                        if (contador == 0) {
                            RetencionComprobante retencion = 
                                new RetencionComprobante();
                            retencion.setImpuesto(retenido.getImpuesto());
                            retencion.setImporte(
                                redondear(retenido.getImporte(), 2)
                            );
                            retenciones.add(retencion);
                        } else {
                            for (int k = 0; k < retenciones.size(); k++) {
                                if (retenido.getImpuesto().equals(
                                    retenciones.get(k).getImpuesto())) {
                                    
                                    retenciones.get(k).setImporte(
                                        redondear(
                                            retenciones.get(k).getImporte() 
                                                + retenido.getImporte()
                                            , 2
                                        )
                                    );
                                }
                            }
                        }
                    }
                }
            }
        }
        
        if (retenciones.size() > 0) {
            impuestoComprobante.setRetenciones(retenciones);
        }
    }
    
    /**
     * metodo para llenar los impuestos trasladados del nodo impuesto de un comprobante
     * @param conceptos
     * @param impuestoComprobante 
     */
    private void llenarImpuestosTrasladados(List<ConceptoComprobante> conceptos
        , ImpuestoComprobante impuestoComprobante) {
        
        if (!comprobarExentos(conceptos)) {
            List<TrasladoComprobante> traslados = new ArrayList<>();

            for (int i = 0; i < conceptos.size(); i++) {
                ConceptoComprobante concepto = conceptos.get(i);
                if (concepto.getImpuesto() != null 
                    && concepto.getImpuesto().getTraslados() != null
                    && concepto.getImpuesto().getTraslados().size() > 0) {

                    List<TrasladoComprobante> trasladados = 
                        concepto.getImpuesto().getTraslados();

                    for (int j = 0; j < trasladados.size(); j++) {
                        TrasladoComprobante trasladado = trasladados.get(j);
                        if (traslados.size() < 1) {
                            TrasladoComprobante traslado = 
                                new TrasladoComprobante();
                            traslado.setImpuesto(trasladado.getImpuesto());
                            traslado.setTipoFactor(trasladado.getTipoFactor());
                            traslado.setTasaOCuota(trasladado.getTasaOCuota());
                            traslado.setImporte(
                                redondear(trasladado.getImporte(), 2)
                            );
                            traslados.add(traslado);
                        } else {
                            int contador = 0;
                            for (int k = 0; k < traslados.size(); k++) {
                                if (trasladado.getImpuesto().equals(
                                        traslados.get(k).getImpuesto()) 
                                    && trasladado.getTipoFactor().equals(
                                        traslados.get(k).getTipoFactor())
                                    && trasladado.getTasaOCuota().equals(
                                        traslados.get(k).getTasaOCuota())) {
                                    
                                    contador++;
                                }
                            }
                            if (contador == 0) {
                                TrasladoComprobante traslado = 
                                    new TrasladoComprobante();
                                traslado.setImpuesto(trasladado.getImpuesto());
                                traslado.setTipoFactor(
                                    trasladado.getTipoFactor());
                                traslado.setTasaOCuota(
                                    trasladado.getTasaOCuota());
                                traslado.setImporte(
                                    redondear(trasladado.getImporte(), 2));
                                traslados.add(traslado);
                            } else {
                                for (int k = 0; k < traslados.size(); k++) {
                                    if (trasladado.getImpuesto().equals(
                                            traslados.get(k).getImpuesto()) 
                                        && trasladado.getTipoFactor().equals(
                                            traslados.get(k).getTipoFactor())
                                        && trasladado.getTasaOCuota().equals(
                                            traslados.get(k).getTasaOCuota())) {
                                        
                                        traslados.get(k).setImporte(
                                            redondear(traslados.get(k).getImporte() 
                                                + trasladado.getImporte(), 2)
                                        );
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (traslados.size() > 0) {
                impuestoComprobante.setTraslados(traslados);
            }
        }
    }
    
    /**
     * metodo para evaluar que todos los impuestos trasladados no tengan como factor "Exento" 
     * @param conceptos
     * @return 
     */
    private boolean comprobarExentos(List<ConceptoComprobante> conceptos) {
        boolean comprobacion = true;
        
        for (int i = 0; i < conceptos.size(); i++) {
            ConceptoComprobante concepto = conceptos.get(i);
            if (concepto.getImpuesto() != null) {
                List<TrasladoComprobante> traslados =
                    concepto.getImpuesto().getTraslados();
                if (traslados != null) {
                    for (int j = 0; j < traslados.size(); j++) {
                        TrasladoComprobante traslado = traslados.get(j);
                        if (!traslado.getTipoFactor().equals("Exento")) {
                            comprobacion = false;
                        }
                    }
                }
            }
        }
        
        return comprobacion;
    }
    
    /**
     * metodo para sustituir el factor
     * @param factor
     * @return 
     */
    private String sustituirFactor(String factor) {
        switch(factor) {
            case "T":
                factor = "Tasa";
                break;
            case "C":
                factor = "Cuota";
                break;
            case "E":
                factor = "Exento";
                break;
        }
        return factor;
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
}
