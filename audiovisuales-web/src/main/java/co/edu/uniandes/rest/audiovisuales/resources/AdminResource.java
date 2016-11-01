/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.codehub.audiovisuales.api.IAdministradorLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.rest.audiovisuales.dtos.AdminDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.AdminDetailDTO;

import co.edu.uniandes.rest.audiovisuales.exceptions.AdminLogicException;
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
 * @author dcagua10
 */
@Path("admin")
@Produces("application/json")
public class AdminResource 
{
    @Inject
    private IAdministradorLogic adminLogic;
    
    public List<AdminDetailDTO> listEntityToDTO(List<AdministradorEntity> entities)
    {
        List<AdminDetailDTO> administradores = new ArrayList<>();
        for(int i = 0; i<administradores.size(); i++)
        {
            AdministradorEntity act = entities.get(i);
            AdminDetailDTO admin = new AdminDetailDTO(act);
            administradores.add(admin);
        }
        return administradores;
    }
    
    @GET
    public List<AdminDetailDTO> getAdministradores() throws AdminLogicException
    {
        return listEntityToDTO(adminLogic.getAdministradores());
    }
    
    @GET
    @Path("{id:\\d+}")
    public AdminDTO getAdmin(@PathParam("id") Long id) throws AdminLogicException
    {
        AdministradorEntity entidad = adminLogic.getAdministrador(id);
        if(entidad==null)
        {
            throw new WebApplicationException("El administrador no existe");
        }
        AdminDetailDTO adm = new AdminDetailDTO(entidad);
        return adm;
    }
    
    @GET
    @Path("{name}")
    public AdminDetailDTO getEdificioByName(@PathParam("name") String name)
    {
        AdministradorEntity nombre = adminLogic.getAdministradorByName(name);
        if(nombre!=null)
        {
            return new AdminDetailDTO(nombre);
        }
        else
        {
            throw new WebApplicationException("El administrador no existe");
        }
    }
    
    @POST
    public AdminDTO agregarAdministrador(AdminDTO nuevo) throws AdminLogicException
    {
        AdministradorEntity entidad = adminLogic.updateAdministrador(nuevo.toEntity());
        return new AdminDetailDTO(entidad);
    }

    @PUT
    @Path("{id:\\d+}")
    public AdminDTO actualizarAdministrador(@PathParam("id") int id, AdminDTO admin) throws AdminLogicException
    {
        AdministradorEntity entidad = adminLogic.updateAdministrador(admin.toEntity());
        return new AdminDetailDTO(entidad);
    }

    @DELETE
    @Path("{id:\\d+}")
    public AdminDetailDTO eliminarAdministrador(@PathParam("id") Long id) throws AdminLogicException
    {
       AdministradorEntity entidad = adminLogic.getAdministrador(id);
       if (entidad == null)
       {
           throw new WebApplicationException("El administrador a buscar no existe");
       }
           adminLogic.deleteAdministrador(id);
           AdminDetailDTO adm = new AdminDetailDTO(entidad);
           return adm;      
    }   
}
