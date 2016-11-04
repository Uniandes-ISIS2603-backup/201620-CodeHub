/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.codehub.audiovisuales.api.IEquipoLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.rest.audiovisuales.dtos.EquipoDetailDTO;
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
 *Clase que implementa el recurso REST correspondiente a "Equipo".
 * @author c.zambrano10
 */

@Path("edificios/{idEdificio}/equipos")
@Produces("application/json")
public class EquipoResource {
    @Inject
    private IEquipoLogic logic;
    
    /**
     * método para converntir listas de EquiposEntity a listas de EquipoDetailDTO.
     * @param entities la lista con las entities.
     * @return una lista con los DTO.
     */
    public List<EquipoDetailDTO> listEntitytoDTO(List<EquipoEntity> entities){
        List<EquipoDetailDTO> res = new ArrayList<EquipoDetailDTO>();
        for(EquipoEntity entity: entities){
            EquipoDetailDTO equipo = new EquipoDetailDTO(entity);
            res.add(equipo);
        }
        return res;
    }

    /**
     * Obtiene el listado de equipos.
     * @param idEdificio
     * @return lista de equipos.
     * @throws AudiovisualesLogicException excepción retornada por la lógica
     */
    @GET
    public List<EquipoDetailDTO> getEquiposEdificio(@PathParam("idEdificio") Long idEdificio) throws AudiovisualesLogicException {
        List<EquipoEntity>equiposE = logic.getByEdificio(idEdificio);
        return listEntitytoDTO(equiposE);
    }
    
    /**
     * busca un equipo según su id
     * @param id
     * @return 
     */
    @GET
    @Path("{id:\\d+}")
    public EquipoDetailDTO getEquipo(@PathParam("id")Long id){
        EquipoEntity entity =logic.getEquipo(id);
        if(entity==null){
            throw new WebApplicationException("El equipo no existe.", 404);
        }
        return new EquipoDetailDTO (entity);
    }
    
   
    /**
     * Agrega un equipo.
     *
     * @param equipo a agregar
     * @return datos del equipo a agregar
     * @throws AudiovisualesLogicException cuando ya existe un equipo con el id suministrado.
     */
    @POST
    public EquipoDetailDTO createEquipo(EquipoDetailDTO equipo) throws AudiovisualesLogicException {
        EquipoEntity e = logic.createEquipo(equipo.toEntity());
        return new EquipoDetailDTO(e);
    }
    
    /**
     * actualiza el equipo existente.
     * @param id el id del equipo a modificar.
     * @param equipo el equipo que sirve de base para actualizar.
     * @return el equipo modificado.
     * @throws AudiovisualesLogicException . 
     */
    @PUT
    @Path("{id:\\d+}")
    public EquipoDetailDTO updateEquipo(@PathParam("id") int id, EquipoDetailDTO equipo) throws AudiovisualesLogicException{
        EquipoEntity e = logic.updateEquipo(equipo.toEntity());
        return new EquipoDetailDTO(e);
    }
    
    /**
     * Elimina un equipo.
     * @param pId el id del equipo a eliminar.
     * @throws AudiovisualesLogicException si el id no existe.
     */
    @DELETE
    @Path("{id:\\d+}")
    public void deleteEquipo(@PathParam("id") Long pId)throws AudiovisualesLogicException{
        logic.deleteEquipo(pId);
    }  
}
