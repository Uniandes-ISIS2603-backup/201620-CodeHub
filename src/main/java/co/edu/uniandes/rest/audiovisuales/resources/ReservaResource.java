/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.rest.audiovisuales.dtos.ReservaDTO;
import co.edu.uniandes.rest.audiovisuales.mocks.ReservaLogicMock;
import co.edu.uniandes.rest.audiovisuales.exceptions.ReservaLogicException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author orlando
 */
@Path("usuarios/(idUsuario: \\d+)/reservas")
@Produces("application/json")
public class ReservaResource {
    
    ReservaLogicMock reservaLogic = new ReservaLogicMock();
    
    @GET
    public List<ReservaDTO> getReservas(@PathParam("idUsuario") Long idUsuario) throws ReservaLogicException
    {
        return reservaLogic.getReservas(idUsuario);
    }
    
    @GET
    @Path("{id:  \\d+}")
    public ReservaDTO getreserva(@PathParam("id")Long id) throws ReservaLogicException
    {
        return reservaLogic.getReserva(id);
    }
    
    @POST
    public ReservaDTO createReserva(ReservaDTO reserva) throws ReservaLogicException
    {
        return reservaLogic.createReserva(reserva);
    }
    
    @PUT
    public ReservaDTO updateReserva(ReservaDTO reserva) throws ReservaLogicException
    {
        return reservaLogic.updateReserva(reserva);
    }
    
    
}
