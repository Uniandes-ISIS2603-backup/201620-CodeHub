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


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "usuarios".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "usuarios". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/usuarios"
 *
 * @author Asistente
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

}