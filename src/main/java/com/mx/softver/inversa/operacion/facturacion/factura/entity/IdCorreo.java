/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.entity;

import com.softver.comunes.entity.Id;
import com.softver.comunes.exception.RequiredFieldSoftverException;

/**
 *
 * @author jhernandez
 */
public class IdCorreo extends Id{
    private String correo;
    private String uuid;

    /**
     * constructor
     */
    public IdCorreo() {
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     * @throws java.lang.Exception
     */
    public void setCorreo(String correo) throws Exception{
        if (correo == null) {
            throw new RequiredFieldSoftverException(
                "El correo no puede ser nulo"
            );
        }
        if (correo.isEmpty()) {
            throw new RequiredFieldSoftverException(
                "El correo no puede ser vacio"
            );
        }
        if (!correo.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            throw new RequiredFieldSoftverException(
                "El correo no es válido"
            );
        }
        this.correo = correo;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}