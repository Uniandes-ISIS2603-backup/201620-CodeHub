/*
 * UsuarioResource.java
 * Clase que representa el recurso "/usuarios"
 * Implementa varios métodos para manipular los usuarios
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.rest.audiovisuales.dtos.UsuarioDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.UsuarioLogicException;
import co.edu.uniandes.rest.audiovisuales.mocks.UsuarioLogicMock;

import java.util.List;
import javax.ws.rs.DELETE;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "usuarios".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "usuarios". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/usuarios"
 *
 * @author ln.bello10
 */
@Path("usuarios")
@Produces("application/json")
public class UsuarioResource {

    UsuarioLogicMock usuarioLogic = new UsuarioLogicMock();

    /**
     * Obtiene el listado de usuarios.
     *
     * @return lista de usuarios
     * @throws CityLogicException excepción retornada por la lógica
     */
    @GET
    public List<UsuarioDTO> getUsuarios() throws UsuarioLogicException {
        return usuarioLogic.getUsuarios();
    }

    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO getUsuario(@PathParam("id") Long id) throws UsuarioLogicException
    {
        return usuarioLogic.getUsuario(id);
    }
    
    @GET
    @Path("estudiantes")
    public List<UsuarioDTO> getEstudiantes() throws UsuarioLogicException
    {
        return usuarioLogic.getUsuarioTipo(UsuarioDTO.ESTUDIANTE);
    }
    
    @GET
    @Path("profesores")
    public List<UsuarioDTO> getProfesores() throws UsuarioLogicException
    {
        return usuarioLogic.getUsuarioTipo(UsuarioDTO.PROFESOR);
    }
    
    /**
     * Agrega un usuario
     *
     * @param usuario usuario a agregar
     * @return datos del usuario a agregar
     * @throws CityLogicException cuando ya existe un usuario con el id
     * suministrado
     */
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws UsuarioLogicException {
        return usuarioLogic.createUsuario(usuario);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO actualizarUsuario(@PathParam("id") Long id, UsuarioDTO usuario) throws UsuarioLogicException
    {
        return usuarioLogic.actualizarUsuario(id, usuario);
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public UsuarioDTO eliminarUsuario(@PathParam("id") Long id) throws UsuarioLogicException
    {
        return usuarioLogic.eliminarUsuario(id);
    }

}