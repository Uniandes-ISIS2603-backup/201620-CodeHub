/*
 * CityDTO
 * Objeto de transferencia de datos de Usuarios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

/**
 * Objeto de transferencia de datos de Usuarios.
 * @author Asistente
 */
public class UsuarioDTO {
    private Long id;
    private String name;
    private Boolean tieneSancion;
    

    /**
     * Constructor por defecto
     */
    public UsuarioDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador del usuario
     * @param name nombre del usuario
     */
    public UsuarioDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
                this.tieneSancion = false;
	}

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getTieneSancion(){
        return tieneSancion;
    }
    
    public void setTieneSancion(Boolean b){
        this.tieneSancion = b;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + "\" }" ;  
    }
}
