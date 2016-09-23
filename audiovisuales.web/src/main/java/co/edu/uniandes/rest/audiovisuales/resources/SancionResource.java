/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.rest.audiovisuales.dtos.SancionDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.SancionLogicException;
import co.edu.uniandes.rest.audiovisuales.mocks.SancionLogicMock;
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
 * @author lj.pinzon12
 */
@Path("usuarios/{idUsuario: \\d+}/sanciones")
@Produces("application/json")
public class SancionResource
{
        SancionLogicMock sancionLogic = new SancionLogicMock();
        
    /**
     * Obtiene el listado de sanciones.
     *
     * @return lista de sanciones
     * @throws SancionLogicException excepción retornada por la lógica
     */
    @GET
    public List<SancionDTO> getSancionesUsuario(@PathParam("idUsuario") Long idUsuario) throws SancionLogicException {
        return sancionLogic.getSancionesUsuario(idUsuario);
    }
    
    @GET
    @Path("{id:  \\d+}")
    public SancionDTO getSancion(@PathParam("id") Long id) throws SancionLogicException {
        return sancionLogic.getSancion(id);
    }
     
     @PUT
     @Path("{id:  \\d+}")
     public SancionDTO putSancion(@PathParam("id")long id, SancionDTO sancion ) throws SancionLogicException{
          System.out.println("esto: "+sancion.getEstado()+" esto bien:"+sancion.getFecha());
     return sancionLogic.putSancion(sancion, id);
     }
     
     @DELETE
     @Path("{id:  \\d+}")
     public SancionDTO deleteSancion(@PathParam("id")Long id) throws SancionLogicException{
         return sancionLogic.deleteSancion(id);
     }

   
    /**
     * Agrega una sancion
     *
     * @param sancion sancion a agregar
     * @return datos de la sancion a agregar
     * @throws SancionLogicException cuando ya existe una sancion con el id
     * suministrado
     */
    @POST
    public SancionDTO createSancion(SancionDTO sancion) throws SancionLogicException {
        return sancionLogic.createSancion(sancion);
    }
}
