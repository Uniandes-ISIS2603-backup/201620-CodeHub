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

/**
 *
 * @author orlando
 */
@Path("reservas")
@Produces("application/json")
public class ReservaResource {
    
    ReservaLogicMock reservaLogic = new ReservaLogicMock();
    
    @GET
    public List<ReservaDTO> getReservas() throws ReservaLogicException
    {
        return reservaLogic.getReservas();
    }
    
    @POST
    public ReservaDTO createReserva(ReservaDTO reserva) throws ReservaLogicException
    {
        return reservaLogic.createReserva(reserva);
    }
}
