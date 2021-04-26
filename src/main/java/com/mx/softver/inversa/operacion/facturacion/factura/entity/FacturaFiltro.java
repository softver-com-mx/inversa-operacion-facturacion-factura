/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import com.softver.comunes.entity.RangoFecha;
import com.softver.comunes.exception.MaxLimitExceededSoftverException;
import com.softver.comunes.exception.RequiredFieldSoftverException;
import com.softver.comunes.exception.ValueNotAllowedSoftverException;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author jhernandez
 */
public class FacturaFiltro extends RangoFecha{
    private int idCliente;
    private String estatus;
    
    private final String[] estatuses = {"0", "1", "2", "3", "4", "5", "6", "7"};

    /**
     * constructor
     */
    public FacturaFiltro() {}
    
    /**
     * @return the id cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the id cliente to set
     * @throws java.lang.Exception
     */
    public void setIdCliente(int idCliente) throws Exception{
        if (idCliente < 0) {
            throw new RequiredFieldSoftverException(
                "El id del cliente no puede ser menor a 0"
            );
        }
        this.idCliente = idCliente;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     * @throws java.lang.Exception
     */
    public void setEstatus(String estatus) 
        throws Exception{
        if (estatus == null) {
            throw new RequiredFieldSoftverException(
                "El estatus de cancelacion es requerido"
            );
        }
        if (estatus.isEmpty()) {
            throw new RequiredFieldSoftverException(
                "El estatus de cancelacion no debe estar vacío"
            );
        }
        estatus = estatus.trim();
        if (estatus.length() > 1 ) {
            throw new MaxLimitExceededSoftverException(
                "El estatus de cancelacion no debe ser mayor a 1 digito"
            );
        }
        if (!ArrayUtils.contains(estatuses, estatus)) {
            throw new ValueNotAllowedSoftverException(
                "El estatus de cancelacion es invalido"
            );
        }
        this.estatus = estatus;
    }
}
