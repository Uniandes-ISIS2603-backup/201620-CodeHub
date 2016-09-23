/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.mappers;
import co.edu.uniandes.rest.audiovisuales.exceptions.SancionLogicException;
import javax.ws.rs.core.Response;

/**
 *
 * @author lj.pinzon12
 */
public class SancionLogicExceptionMapper
{
    
    /**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
         * @return respuesta
	 */

	public Response toResponse(SancionLogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
	}
}
