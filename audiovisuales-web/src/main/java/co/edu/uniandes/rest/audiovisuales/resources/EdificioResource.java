/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.codehub.audiovisuales.api.IEdificioLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.rest.audiovisuales.dtos.EdificioDetailDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.EdificioLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Sebastian
 */
@Path("edificios")
@Produces("application/json")
public class EdificioResource {
    
    @Inject
    private IEdificioLogic logic;
    
    private List<EdificioDetailDTO> listEntityToDTO(List<EdificioEntity> entities){
        List<EdificioDetailDTO> edificios = new ArrayList<>();
        for(int i=0;i<entities.size();i++)
        {
            EdificioEntity e = entities.get(i);
            edificios.add(new EdificioDetailDTO(e));
        }
        return edificios;
    }
    
    @GET
    public List<EdificioDetailDTO> getEdificios()
    {
        return listEntityToDTO(logic.getEdificios());
    }
    
    @GET
    @Path("{id: \\d+}")
    public EdificioDetailDTO getEdificio(@PathParam("id") Long id)
    {
        EdificioEntity ed = logic.getEdificio(id);
        if(id!=null)
        {
            return new EdificioDetailDTO(ed);
        }
        else
        {
            throw new WebApplicationException("El edifico no existe", 404);
        }
    }
    
    @POST
    public EdificioDetailDTO agregarEdificio(EdificioDetailDTO nuevo) throws AudiovisualesLogicException
    {
        EdificioEntity e = logic.createEdificio(nuevo.toEntity());
        return new EdificioDetailDTO(e);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EdificioDetailDTO actualizarEdificio(@PathParam("id") Long id, EdificioDetailDTO edificio) throws EdificioLogicException
    {
        EdificioEntity ed = logic.updateEdificio(edificio.toEntity());
        return new EdificioDetailDTO(ed);
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public EdificioDetailDTO eliminarEdificio(@PathParam("id") Long id) throws EdificioLogicException
    {
         EdificioEntity ed = logic.getEdificio(id);
        if(id!=null)
        {
            logic.deleteEdificio(id);
            return new EdificioDetailDTO(ed);
        }
        else
        {
            throw new WebApplicationException("El edifico no existe", 404);
        }
    }
}
