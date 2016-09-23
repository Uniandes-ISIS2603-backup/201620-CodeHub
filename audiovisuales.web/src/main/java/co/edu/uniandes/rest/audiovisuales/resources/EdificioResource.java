/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.rest.audiovisuales.dtos.EdificioDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.EquipoDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.EdificioLogicException;
import co.edu.uniandes.rest.audiovisuales.mocks.EdificioLogicMock;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Sebastian
 */
@Path("edificios")
@Produces("application/json")
public class EdificioResource {
    
    EdificioLogicMock edificioLogic = new EdificioLogicMock();
    
    @GET
    public List<EdificioDTO> getEdificios() throws EdificioLogicException
    {
        return edificioLogic.getEdificios();
    }
    
    @GET
    @Path("{id: \\d+}")
    public EdificioDTO getEdificio(@PathParam("id") Long id) throws EdificioLogicException
    {
        return edificioLogic.getEdificio(id);
    }
    
    @POST
    public EdificioDTO agregarEdificio(EdificioDTO nuevo) throws EdificioLogicException
    {
        return edificioLogic.crearEdificio(nuevo);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EdificioDTO actualizarEdificio(@PathParam("id") Long id, EdificioDTO edificio) throws EdificioLogicException
    {
        return edificioLogic.actualizarEdificio(id, edificio);
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public EdificioDTO eliminarEdificio(@PathParam("id") Long id) throws EdificioLogicException
    {
        return edificioLogic.eliminarEdificio(id);
    }
}
