/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.rest.audiovisuales.dtos.AdminDTO;

import co.edu.uniandes.rest.audiovisuales.exceptions.AdminLogicException;
import co.edu.uniandes.rest.audiovisuales.mocks.AdminLogicMock;
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
 * @author dcagua10
 */
@Path("admin")
@Produces("application/json")
public class AdminResource 
{
 AdminLogicMock adminLogic = new AdminLogicMock();
    
    @GET
    public List<AdminDTO> getAdministradores() throws AdminLogicException
    {
        return adminLogic.getAdministradores();
    }
    
    @GET
    @Path("{id:\\d+}")
    public AdminDTO getAdmin(@PathParam("id") int id) throws AdminLogicException
    {
        return adminLogic.getAdmin(id);
    }
    
    @POST
    public AdminDTO agregarAdministrador(AdminDTO nuevo) throws AdminLogicException
    {
        return adminLogic.crearAdministrador(nuevo);
    }

    @PUT
    @Path("{id:\\d+}")
    public AdminDTO actualizarAdministrador(@PathParam("id") int id, AdminDTO admin) throws AdminLogicException
    {
        return adminLogic.actualizarAdministrador(admin, id);
    }

    @DELETE
    @Path("{id:\\d+}")
    public void eliminarAdministrador(@PathParam("id") int id) throws AdminLogicException
    {
        adminLogic.eliminarAdministrador(id);
    }   
}
