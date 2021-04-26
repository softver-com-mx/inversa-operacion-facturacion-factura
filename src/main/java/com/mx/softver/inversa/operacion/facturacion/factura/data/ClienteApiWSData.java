/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softver.inversa.operacion.facturacion.factura.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.softver.comunes.entity.RespuestaBase;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Arquitecto
 */
public class ClienteApiWSData {
    private String ruta;
    private String recurso;
    private Client cliente;
    private Response respuesta;
    private String respuestaEntidad;
    private RespuestaBase respuestaBase;
    private ObjectMapper json;
    private String erpToken;
    private String erpRfc;
    
    /**
     * constructor
     */
    public ClienteApiWSData() {
        inicializar(null, null);
    }
    
    /**
     * constructor
     * @param token
     * @param rfc 
     */
    public ClienteApiWSData(String token, String rfc) {
        inicializar(token, rfc);
    }
    
    /**
     * metodo para inicializar el objeto
     * @param token
     * @param rfc 
     */
    private void inicializar(String token, String rfc) {
        ruta = null;
        recurso = null;
        erpToken = token;
        erpRfc = rfc;
    
        cliente = ClientBuilder.newClient();
        json = new ObjectMapper();
        
        json.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //json.setDateFormat(new ISO8601DateFormat());
        json.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        json.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        
        respuestaBase = new RespuestaBase();
        respuestaBase.setError(true);
        respuestaBase.setCodigoHttp(500);
    }
    
    /**
     * metodo para asignar el token del ERP
     * @param token 
     */
    public void setErpToken(String token) {
        erpToken = token;
    }
    
    /**
     * metodo para asignar el RFC de la sesion
     * @param rfc 
     */
    public void setErpRfc(String rfc) {
        erpRfc = rfc;
    }
    
    /**
     * metodo para obtener el objeto que parsea JSON a objetos o viceversa
     * @return 
     */
    public ObjectMapper obtenerConversorJson() {
        return json;
    }
    
    /**
     * metodo que consume un endpoint por GET
     * @param ruta
     * @param recurso
     * @param tipoReferencia
     * @return 
     */
    public RespuestaBase get(
        String ruta, String recurso, TypeReference tipoReferencia
    ) {
        this.ruta = ruta;
        this.recurso = recurso;
        
        try {
            respuesta = configurar().get(Response.class);
            procesar(tipoReferencia);
            respuestaBase.setCodigoHttp(respuesta.getStatus());
            
        } catch(InvalidFormatException ex) {
            respuestaBase.setMensaje(
                "error en la conversion de la respuesta del API"
            );
            respuestaBase.setMensajeDetalle(respuestaEntidad);
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);

        } catch (ProcessingException ex) {
            respuestaBase.setMensaje(String.format(
                "%s - %s%s",
                "error en el consumo del API" ,
                this.ruta,
                this.recurso
            ));
            respuestaBase.setMensajeDetalle(ex.getMessage());
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);
            
        } catch (Exception ex) {
            respuestaBase.setMensaje("error interno");
            respuestaBase.setMensajeDetalle(ex.getMessage());
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);
        }
        
        return respuestaBase;
    }
    
    /**
     * metodo que consume un endpoint por el metodo POST
     * @param <T>
     * @param ruta
     * @param recurso
     * @param tipoReferencia
     * @param peticion
     * @return 
     */
    public <T> RespuestaBase post(
        String ruta, String recurso, TypeReference tipoReferencia, T peticion
    ) {
        this.ruta = ruta;
        this.recurso = recurso;
        
        try {
            respuesta = configurar().post(
                Entity.entity(peticion, MediaType.APPLICATION_JSON),
                Response.class
            );
            procesar(tipoReferencia);
            respuestaBase.setCodigoHttp(respuesta.getStatus());
            
        } catch(InvalidFormatException ex) {
            respuestaBase.setMensaje(
                "error en la conversion de la respuesta del API"
            );
            respuestaBase.setMensajeDetalle(respuestaEntidad);
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);

        } catch (ProcessingException ex) {
            respuestaBase.setMensaje(String.format(
                "%s - %s%s",
                "error en el consumo del API" ,
                this.ruta,
                this.recurso
            ));
            respuestaBase.setMensajeDetalle(ex.getMessage());
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);
            
        } catch (Exception ex) {
            respuestaBase.setMensaje("error interno");
            respuestaBase.setMensajeDetalle(ex.getMessage());
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);
        }
        
        return respuestaBase;
    }
    
    /**
     * metodo que consume un endpoint por el metodo POST
     * @param <T>
     * @param ruta
     * @param recurso
     * @param tipoReferencia
     * @param peticion
     * @return 
     */
    public <T> RespuestaBase put(
        String ruta, String recurso, TypeReference tipoReferencia, T peticion
    ) {
        this.ruta = ruta;
        this.recurso = recurso;
        
        try {
            respuesta = configurar().put(
                Entity.entity(peticion, MediaType.APPLICATION_JSON),
                Response.class
            );
            procesar(tipoReferencia);
            respuestaBase.setCodigoHttp(respuesta.getStatus());
            
        } catch(InvalidFormatException ex) {
            respuestaBase.setMensaje(
                "error en la conversion de la respuesta del API"
            );
            respuestaBase.setMensajeDetalle(respuestaEntidad);
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);

        } catch (ProcessingException ex) {
            respuestaBase.setMensaje(String.format(
                "%s - %s%s",
                "error en el consumo del API" ,
                this.ruta,
                this.recurso
            ));
            respuestaBase.setMensajeDetalle(ex.getMessage());
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);
            
        } catch (Exception ex) {
            respuestaBase.setMensaje("error interno");
            respuestaBase.setMensajeDetalle(ex.getMessage());
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);
        }
        
        return respuestaBase;
    }
    
    /**
     * metodo que consume un endpoint por el metodo DELETE
     * @param ruta
     * @param recurso
     * @param tipoReferencia
     * @return 
     */
    public RespuestaBase delete(
        String ruta, String recurso, TypeReference tipoReferencia
    ) {
        this.ruta = ruta;
        this.recurso = recurso;
        
        try {
            respuesta = configurar().delete(Response.class);
            procesar(tipoReferencia);
            respuestaBase.setCodigoHttp(respuesta.getStatus());
            
        } catch(InvalidFormatException ex) {
            respuestaBase.setMensaje(
                "error en la conversion de la respuesta del API"
            );
            respuestaBase.setMensajeDetalle(respuestaEntidad);
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);

        } catch (ProcessingException ex) {
            respuestaBase.setMensaje(String.format(
                "%s - %s%s",
                "error en el consumo del API" ,
                this.ruta,
                this.recurso
            ));
            respuestaBase.setMensajeDetalle(ex.getMessage());
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);
            
        } catch (Exception ex) {
            respuestaBase.setMensaje("error interno");
            respuestaBase.setMensajeDetalle(ex.getMessage());
            respuestaBase.setError(true);
            respuestaBase.setCodigoHttp(500);
        }
        
        return respuestaBase;
    }
    
    /**
     * metodo para establecer valores para la peticion
     * @return 
     */
    protected Builder configurar() {
        return cliente
            .target(ruta)
                .path(recurso)
                    .request(MediaType.APPLICATION_JSON)
                        .header("FRACTAL-ERP-TOKEN", erpToken)
                            .header("FRACTAL-ERP-RFC", erpRfc);            
    }
    
    /**
     * metodo que procesa la respuesta del endpoint
     * @param tipoReferencia
     * @return 
     */
    protected void procesar(TypeReference tipoReferencia) throws Exception {
        respuestaEntidad = respuesta.readEntity(String.class); 
        respuestaBase = json.readValue(
            respuestaEntidad, tipoReferencia
        );
    }
}
