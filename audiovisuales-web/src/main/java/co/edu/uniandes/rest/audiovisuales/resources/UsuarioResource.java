/*
 * UsuarioResource.java
 * Clase que representa el recurso "/usuarios"
 * Implementa varios métodos para manipular los usuarios
 */
package co.edu.uniandes.rest.audiovisuales.resources;

import co.edu.uniandes.codehub.audiovisuales.api.IUsuarioLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.rest.audiovisuales.dtos.UsuarioDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.UsuarioDetailDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.UsuarioLogicException;
import co.edu.uniandes.rest.audiovisuales.mappers.WebApplicationExceptionMapper;
import co.edu.uniandes.rest.audiovisuales.mocks.UsuarioLogicMock;
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

//    UsuarioLogicMock usuarioLogic = new UsuarioLogicMock();
    
    @Inject
    private IUsuarioLogic usuarioLogic;

    private List<UsuarioDetailDTO> listEntityToDTO(List<UsuarioEntity> entities){
        List<UsuarioDetailDTO> usuarios = new ArrayList<>();
        for(UsuarioEntity usuario: entities){
            usuarios.add(new UsuarioDetailDTO(usuario));
        }
        return usuarios;
    }
    
    /**
     * Obtiene el listado de usuarios.
     *
     * @return lista de usuarios
     * @throws CityLogicException excepción retornada por la lógica
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios(){
        
        return listEntityToDTO(usuarioLogic.getUsuarios());
    }

    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) throws UsuarioLogicException
    {
        return new UsuarioDetailDTO(usuarioLogic.getUsuario(id));
    }
    
    @GET
    @Path("name")
    public UsuarioDetailDTO getUsuarioByName(@PathParam("name") String name){
        UsuarioEntity usuario = usuarioLogic.getUsuarioByName(name);
        if(usuario==null){
            throw new WebApplicationException("El usuario no existe", 404);
        }
        else{
            return new UsuarioDetailDTO(usuario);
        }
    }
    
    @GET
    @Path("estudiantes")
    public List<UsuarioDetailDTO> getEstudiantes() throws UsuarioLogicException
    {
        return listEntityToDTO(usuarioLogic.getEstudiantes());
    }
    
    @GET
    @Path("profesores")
    public List<UsuarioDetailDTO> getProfesores() throws UsuarioLogicException
    {
        return listEntityToDTO(usuarioLogic.getProfesores());
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
    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO usuario) throws AudiovisualesLogicException {
        return new UsuarioDetailDTO(usuarioLogic.createUsuario(usuario.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailDTO actualizarUsuario(@PathParam("id") Long id, UsuarioDetailDTO usuario) throws UsuarioLogicException
    {
        UsuarioEntity usu = usuario.toEntity();
        usu.setId(id);
        usu.setName(usuario.getName());
        usu.setTipo(usuario.getTipo());
        return new UsuarioDetailDTO(usuarioLogic.updateUsuario(usu));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarUsuario(@PathParam("id") Long id) throws UsuarioLogicException
    {
        usuarioLogic.deleteUsuario(id);
    }

}