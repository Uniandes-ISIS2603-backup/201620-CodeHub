/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.rest.audiovisuales.dtos.EquipoDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.EquipoLogicException;
import co.edu.uniandes.rest.audiovisuales.mocks.EquipoLogicMock;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *Clase que implementa el recurso REST correspondiente a "Equipo".
 * @author c.zambrano10
 */

@Path("equipos")
@Produces("application/json")
public class EquipoResource {
    EquipoLogicMock EquipoLogic = new EquipoLogicMock();

    /**
     * Obtiene el listado de equipos.
     * @return lista de equipos.
     * @throws EquipoLogicException excepción retornada por la lógica
     */
    @GET
    public List<EquipoDTO> getEquipos() throws EquipoLogicException {
        return EquipoLogic.darEquipos();
    }
    
    @GET
    @Path("/edificios/(idEdificio: \\d+)/equipos")
    public List<EquipoDTO> getEquipos(@PathParam("idEdificio") Long idEdificio) throws EquipoLogicException {
        return EquipoLogic.darEquipos(idEdificio);
    }
    
    @GET
    @Path("edificios/(idEdificio: \\d+)/equipos")
    public List<EquipoDTO> getEquiposDisponibles(@PathParam("idEdificio") Long idEdificio, @QueryParam("estado") int estado) throws EquipoLogicException {
        return EquipoLogic.darEquiposEstado(idEdificio, estado);
    }

   
    /**
     * Agrega un equipo.
     *
     * @param equipo a agregar
     * @return datos del equipo a agregar
     * @throws EquipoLogicException cuando ya existe un equipo con el id suministrado
     */
    @POST
    public EquipoDTO createEquipo(EquipoDTO equipo) throws EquipoLogicException {
        return EquipoLogic.crearEquipo(equipo);
    }
    
    /**
     * actualiza el equipo existente.
     * @param id el id del equipo a modificar.
     * @param equipo el equipo que sirve de base para actualizar.
     * @return el equipo modificado.
     * @throws EquipoLogicException . 
     */
    @PUT
    @Path("{id:\\d+}")
    public EquipoDTO updateEquipo(@PathParam("id") int id, EquipoDTO equipo) throws EquipoLogicException{
        return EquipoLogic.updateEquipo(equipo, id);
    }
    
    /**
     * elimina un equipo.
     * @param pId el id de la ciudad a eliminar.
     * @throws EquipoLogicException 
     */
    @DELETE
    @Path("{id:\\d+}")
    public void deleteEquipo(@PathParam("id") int pId)throws EquipoLogicException{
        EquipoLogic.deleteEquipo(pId);//envia el id a eliminar
        
    }
    
    @GET
    @Path("{id:\\d+}")
    public EquipoDTO getEquipo(@PathParam("id")int id)throws EquipoLogicException{
        return EquipoLogic.getEquipo(id);
    }
    
}
