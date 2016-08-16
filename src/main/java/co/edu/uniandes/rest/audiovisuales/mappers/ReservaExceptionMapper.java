/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import co.edu.uniandes.rest.audiovisuales.exceptions.ReservaLogicException;


/**
 *
 * @author orlando
 */
public class ReservaExceptionMapper implements ExceptionMapper<ReservaLogicException>{
    public Response toResponse(ReservaLogicException ex)
    {
        return Response
                        .status(Response.Status.NOT_FOUND)	// estado HTTP 404
                	.entity(ex.getMessage())			// mensaje adicional
			.type("text/plain")
			.build();
    }
}
