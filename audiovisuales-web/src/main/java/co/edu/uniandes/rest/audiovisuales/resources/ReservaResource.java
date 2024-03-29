/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.codehub.audiovisuales.api.IEquipoLogic;
import co.edu.uniandes.codehub.audiovisuales.api.IReservaLogic;
import co.edu.uniandes.codehub.audiovisuales.api.IUsuarioLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.rest.audiovisuales.dtos.EquipoDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.ReservaDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.ReservaDetailDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author o.sabogal10
 */
@Path("usuarios/{idUsuario}/reservas")
@Produces("application/json")
public class ReservaResource {
    
    @Inject
    private IReservaLogic reservaLogic;
    
    @Inject
    private IUsuarioLogic usuarioLogic;
    
    @Inject
    private IEquipoLogic equipoLogic;
    
    public List<ReservaDetailDTO> listEntityToDTO(List<ReservaEntity> entities)
    {
        List<ReservaDetailDTO> reservas = new ArrayList<>();
        for(ReservaEntity reserva: entities)
        {
            reservas.add(new ReservaDetailDTO(reserva));
        }
        return reservas;
    }
    
    @GET
    public List<ReservaDetailDTO> getReservasUsuario(@PathParam("idUsuario") Long idUsuario) throws AudiovisualesLogicException
    {
        List<ReservaEntity> reservasE = reservaLogic.getReservasByUsuario(idUsuario);
        return listEntityToDTO(reservasE);
    }
    
    @GET
    @Path("{id: \\d+}")
    public ReservaDTO getReserva(@PathParam("id")Long id) throws AudiovisualesLogicException
    {
       ReservaEntity entity = reservaLogic.getReserva(id);
       if(entity==null)
       {
           throw new WebApplicationException("La reserva no existe.", 404);
       }
       return new ReservaDetailDTO(entity);
    }
    
    @GET
    @Path("/pendientes/{idEdificio: \\d+}")
    public List<ReservaDetailDTO> getReservasPendientes(@PathParam("idEdificio") Long idEdificio)
    {
        List<ReservaEntity> reservas = reservaLogic.getReservasPendientesEdificio(idEdificio);
        return listEntityToDTO(reservas);
    }
    
    @GET
    @Path("calificacion")
    public String getCalificacionSistema(@PathParam("idUsuario")Long idUsuario) throws AudiovisualesLogicException
    {
        double contador = 0.0;
        int size = 0;
        double calificacion= 0;
        List<ReservaDetailDTO> res = getReservasUsuario(idUsuario);
        for (int i = 0; i< res.size();i++)
        {
            if(res.get(i).getEstado()==ReservaDTO.INACTIVA)
            {
                contador+=res.get(i).getCalificacion();
                size++;
            }
        }
        if(size==0){
            calificacion = 0.0;
        }
        else{
            calificacion = contador/size;
        }
        
        return "{ calificacion: " + calificacion + "\" }";
    }
    
    @POST
    public ReservaDetailDTO createReserva(ReservaDetailDTO reserva, @PathParam("idUsuario")Long idUsuario) throws AudiovisualesLogicException
    {
        reserva.setEquipo(new EquipoDTO(equipoLogic.getEquipo(reserva.getEquipo().getId())));
        reserva.setUsuario(new UsuarioDTO(usuarioLogic.getUsuario(idUsuario)));
        ReservaEntity res = reserva.toEntity();
        ReservaEntity entity = reservaLogic.createReserva(res);
        return new ReservaDetailDTO(entity);
    }
    
    @PUT
    @Path("{id:\\d+}")
    public ReservaDTO updateReserva(@PathParam("id") int id, ReservaDTO reserva) throws AudiovisualesLogicException
    {
        ReservaEntity entity = reservaLogic.updateReserva(reserva.toEntity());
        return new ReservaDetailDTO(entity);
    }  
}
