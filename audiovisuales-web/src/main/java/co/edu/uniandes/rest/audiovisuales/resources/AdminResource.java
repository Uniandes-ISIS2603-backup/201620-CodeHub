/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.codehub.audiovisuales.api.IAdministradorLogic;
import co.edu.uniandes.codehub.audiovisuales.api.IEdificioLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.rest.audiovisuales.dtos.AdminDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.AdminDetailDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.EdificioDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.LoginKeyDTO;

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
    public IAdministradorLogic adminLogic;
    
    @Inject
    public IEdificioLogic edificioLogic;
    
    public List<AdminDetailDTO> listEntityToDTO(List<AdministradorEntity> entities)
    {
        List<AdminDetailDTO> administradores = new ArrayList<>();
        for(int i = 0; i<entities.size(); i++)
        {
            AdministradorEntity act = entities.get(i);
            AdminDetailDTO admin = new AdminDetailDTO(act);
            administradores.add(admin);
        }
        return administradores;
    }
    
    @GET
    public List<AdminDetailDTO> getAdministradores() throws AudiovisualesLogicException
    {
        return listEntityToDTO(adminLogic.getAdministradores());
    }
    
    @GET
    @Path("{id:\\d+}")
    public AdminDTO getAdmin(@PathParam("id") Long id) throws AudiovisualesLogicException
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
    @Path("{id:\\d+}/reservas/pendientes")
    public AdminDetailDTO getEdificioByName(@PathParam("id") Long id)
    {
        AdministradorEntity nombre = adminLogic.getAdministrador(id);
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
    public AdminDTO agregarAdministrador(AdminDetailDTO nuevo) throws AudiovisualesLogicException
    {
        EdificioEntity edificio = edificioLogic.getEdificio(nuevo.getEdificio().getId());
        nuevo.setEdificio(new EdificioDTO(edificio));
        AdministradorEntity admin = nuevo.toEntity();
        AdministradorEntity entidad = adminLogic.createAdministrador(admin);
        return new AdminDetailDTO(entidad);
    }

    @PUT
    @Path("{id:\\d+}")
    public AdminDTO actualizarAdministrador(@PathParam("id") int id, AdminDTO admin) throws AudiovisualesLogicException
    {
        AdministradorEntity entidad = adminLogic.updateAdministrador(admin.toEntity());
        return new AdminDetailDTO(entidad);
    }

    @DELETE
    @Path("{id:\\d+}")
    public AdminDetailDTO eliminarAdministrador(@PathParam("id") Long id) throws AudiovisualesLogicException
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
    
    @POST
    @Path("/login")
    public AdminDetailDTO login(LoginKeyDTO login)
    {
        AdministradorEntity entidad = adminLogic.login(login.getLogin(), login.getPassword());
        if (entidad == null)
       {
           throw new WebApplicationException("No existe un administrador con ese login.");
       }
        
       return new AdminDetailDTO(entidad);
    }
}
