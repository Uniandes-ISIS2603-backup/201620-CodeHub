package co.edu.uniandes.rest.audiovisuales.mocks;

/**
 * Mock del recurso Ciudades (Mock del servicio REST)
 * @author Asistente
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;




import co.edu.uniandes.rest.audiovisuales.dtos.UsuarioDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.UsuarioLogicException;


/*
 * CityLogicMock
 * Mock del recurso Usuarios (Mock del servicio REST)
 */

public class UsuarioLogicMock {
	
	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(UsuarioLogicMock.class.getName());
	
	// listado de usuarios
    private static ArrayList<UsuarioDTO> usuarios;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public UsuarioLogicMock() {

    	if (usuarios == null) {
            usuarios = new ArrayList<>();
            usuarios.add(new UsuarioDTO(1L, "Rubby Casallas"));
            usuarios.add(new UsuarioDTO(2L, "Fernando de la Rosa"));
            usuarios.add(new UsuarioDTO(3L, "Rodrigo Cardoso"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de usuarios");
    	logger.info("ciudades" + usuarios );
    }    
    
	/**
	 * Obtiene el listado de personas. 
	 * @return lista de usuarios
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public List<UsuarioDTO> getUsuarios() throws UsuarioLogicException {
    	if (usuarios == null) {
    		logger.severe("Error interno: lista de usuarios no existe.");
    		throw new UsuarioLogicException("Error interno: lista de usuarios no existe.");    		
    	}
    	
    	logger.info("retornando todos los usuarios");
    	return usuarios;
    }

    public UsuarioDTO getCity(Long id) throws UsuarioLogicException
    {
        if(usuarios==null)
        {
            throw new UsuarioLogicException("No se encuentra inicializada la lista de Usuarios.");
        }
        
        for(UsuarioDTO usuario : usuarios)
        {
            if(usuario.getId().equals(id))
            {
                return usuario;
            }
        }
        
        throw new UsuarioLogicException("No se encuentra el usuario con ese id.");
    }

    /**
     * Agrega un usuario a la lista.
     * @param newUser usuario a adicionar
     * @throws CityLogicException cuando ya existe una ciudad con el id suministrado
     * @return usuario agregado
     */
    public UsuarioDTO createUsuario(UsuarioDTO newUser) throws UsuarioLogicException {
    	logger.info("recibiendo solicitud de agregar usuario " + newUser);
    	
    	// el nuevo usuario tiene id ?
    	if ( newUser.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (UsuarioDTO user : usuarios) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(user.getId(), newUser.getId())){
	            	logger.severe("Ya existe un usuario con ese id");
	                throw new UsuarioLogicException("Ya existe un usuario con ese id");
	            }
	        }
	        
	    // el nuevo usuario no tiene id ? 
    	} else {

    		// genera un id para el usuario
    		logger.info("Generando id para el nuevo usuario");
    		long newId = 1;
	        for (UsuarioDTO usu : usuarios) {
	            if (newId <= usu.getId()){
	                newId =  usu.getId() + 1;
	            }
	        }
	        newUser.setId(newId);
    	}
    	
        // agrega el usuario
    	logger.info("agregando usuario " + newUser);
        usuarios.add(newUser);
        return newUser;
    }

    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuario) throws UsuarioLogicException
    {
        if(usuarios==null)
        {
            throw new UsuarioLogicException("No se encuentra inicializada la lista de Edificios.");
        }
        
        UsuarioDTO buscado = null;
        
        for(UsuarioDTO e : usuarios)
        {
            if(e.getId().equals(id))
            {
                buscado = e;
            }
        }
        
        if(buscado!=null)
        {
            if(usuario.getId()!=null)
                buscado.setId(usuario.getId());
            if(usuario.getName()!=null)
                buscado.setName(usuario.getName());
            if(usuario.getTieneSancion()!=null)
                buscado.setTieneSancion(usuario.getTieneSancion());
            return buscado;
        }
        else
        {
           throw new UsuarioLogicException("No se encuentra el edificio con ese id."); 
        }
    }
   
    
        public UsuarioDTO eliminarUsuario(Long id) throws UsuarioLogicException
    {
        if(usuarios==null)
        {
            throw new UsuarioLogicException("No se encuentra inicializada la lista de Usuarios.");
        }
        
        for(UsuarioDTO usuario : usuarios)
        {
            if(usuario.getId().equals(id))
            {
                usuarios.remove(usuario);
                return usuario;
            }
        }
        
        throw new UsuarioLogicException("No se encuentra el usuario con ese id.");
    }
}
