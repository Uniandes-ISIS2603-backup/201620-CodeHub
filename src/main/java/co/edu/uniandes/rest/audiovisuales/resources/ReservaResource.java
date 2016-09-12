/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.rest.audiovisuales.dtos.ReservaDTO;
import co.edu.uniandes.rest.audiovisuales.mocks.ReservaLogicMock;
import co.edu.uniandes.rest.audiovisuales.exceptions.ReservaLogicException;
import java.util.ArrayList;
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
    public List<ReservaDTO> getReservasUsuario(@PathParam("idUsuario") Long idUsuario) throws ReservaLogicException
    {
        return reservaLogic.getReservasUsuario(idUsuario);
    }
    
    @GET
    @Path("{id:  \\d+}")
    public ReservaDTO getReserva(@PathParam("id")Long id) throws ReservaLogicException
    {
        return reservaLogic.getReserva(id);
    }
    
    @GET
    @Path("pendientes")
    public List<ReservaDTO> getReservasPendientes(@PathParam("idUsuario")Long idUsuario) throws ReservaLogicException
    {
        List<ReservaDTO> res = getReservasUsuario(idUsuario);
        List<ReservaDTO> pendientes = new ArrayList<ReservaDTO>();
        for (int i = 0; i< res.size();i++)
        {
            ReservaDTO actual = res.get(i);
            if(actual.getEstado()==ReservaDTO.SIN_APROBAR)
            {
                pendientes.add(actual);
            }
        }
        return pendientes;
    }
    
    @GET
    @Path("calificacion")
    public double getCalificacionSistema(@PathParam("idUsuario")Long idUsuario) throws ReservaLogicException
    {
        double contador = 0.0;
        int size = 0;
        List<ReservaDTO> res = getReservasUsuario(idUsuario);
        for (int i = 0; i< res.size();i++)
        {
            if(res.get(i).getEstado()==ReservaDTO.INACTIVA)
            {
                contador+=res.get(i).getCalificacion();
                size++;
            }
        }
        return size!=0? contador/size : 0.0;
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
