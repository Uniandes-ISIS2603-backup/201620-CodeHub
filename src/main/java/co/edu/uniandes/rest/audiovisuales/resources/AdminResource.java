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
    @Path("{nombre: \\d+}")
    public AdminDTO getAdmin(@PathParam("nombre") String nombre) throws AdminLogicException
    {
        return adminLogic.getAdmin(nombre);
    }
    
    @POST
    public AdminDTO agregarAdministrador(AdminDTO nuevo) throws AdminLogicException
    {
        return adminLogic.crearAdministrador(nuevo);
    }

    /*@PUT
    public AdminDTO actualizarAdministrador(String nombre, String correo) throws AdminLogicException
    {
        return adminLogic.actualizarAdministrador(nombre, correo);
    }*/

    @DELETE
    @Path("{nombre: \\d+}")
    public AdminDTO eliminarAdministrador(@PathParam("nombre") String nombre) throws AdminLogicException
    {
        return adminLogic.eliminarAdministrador(nombre);
    }   
}
