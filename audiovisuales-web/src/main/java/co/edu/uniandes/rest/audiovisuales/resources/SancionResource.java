/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;


import co.edu.uniandes.codehub.audiovisuales.api.ISancionLogic;
import co.edu.uniandes.codehub.audiovisuales.api.IUsuarioLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.SancionEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.rest.audiovisuales.dtos.SancionDetailDTO;
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
 * @author lj.pinzon12
 */
@Path("usuarios/{idUsuario}/sanciones")
@Produces("application/json")
public class SancionResource
{
    @Inject
    private ISancionLogic logic;
    
    private List<SancionDetailDTO> listEntityToDTO(List<SancionEntity> entities){
        List<SancionDetailDTO> sanciones = new ArrayList<>();
        for(int i=0;i<entities.size();i++)
        {
            SancionEntity e = entities.get(i);
            sanciones.add(new SancionDetailDTO(e));
        }
        return sanciones;
    }
    @GET
    public List<SancionDetailDTO> getSancionesUsuario(@PathParam("idUsuario") Long idUsuario) throws AudiovisualesLogicException
    {
        List<SancionEntity> sancionesE = logic.getSancionesByUsuario(idUsuario);
        return listEntityToDTO(sancionesE);
    }
    
    @GET
    @Path("{id: \\d+}")
    public SancionDetailDTO getSancion(@PathParam("id") Long id)
    {
        SancionEntity s = logic.getSancion(id);
        if(s==null)
        {
            throw new WebApplicationException("La sancion no existe", 404);
        }
        return new SancionDetailDTO(s);
    }
    
    @GET
    @Path("{fecha}")
    public SancionDetailDTO getSancionByFecha(@PathParam("fecha") String fecha)
    {
        SancionEntity f = logic.getSancionByFecha(fecha);
        if(f!=null)
        {
            return new SancionDetailDTO(f);
        }
        else
        {
                throw new WebApplicationException("La sancion no existe", 404);
        }
    }
    
    @POST
    public SancionDetailDTO agregarSancion(SancionDetailDTO nuevo) throws AudiovisualesLogicException
    {
        SancionEntity e = logic.createSancion(nuevo.toEntity());
        return new SancionDetailDTO(e);
    }
     
    @PUT
    @Path("{id: \\d+}")
    public SancionDetailDTO actualizarSancion(@PathParam("id") Long id, SancionDetailDTO sancion)
    {
        SancionEntity s = logic.updateSancion(sancion.toEntity());
        return new SancionDetailDTO(s);
    }
     
    @DELETE
    @Path("{id: \\d+}")
    public SancionDetailDTO eliminarSancion(@PathParam("id") Long id)
    {
        SancionEntity s = logic.getSancion(id);
        if(s==null)
        {
            throw new WebApplicationException("La sancion no existe", 404);
        }
        logic.deleteSancion(id);
        return new SancionDetailDTO(s);
    }

}
