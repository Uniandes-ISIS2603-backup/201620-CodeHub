/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.mappers;

import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author s.martinez15
 */
@Provider
public class AudiovisualesLogicExceptionMapper implements ExceptionMapper<AudiovisualesLogicException> {
    
     /**
     * Generador de una respuesta a partir de una excepción
     *
     * @param ex excecpión a convertir a una respuesta REST
     * @return Respuesta modificada
     */
    @Override
    public Response toResponse(AudiovisualesLogicException ex) {
        return Response
		.status(Response.Status.NOT_FOUND)	// estado HTTP 404
		.entity(ex.getMessage())		// mensaje adicional
		.type("text/plain")
		.build();
    }
}
