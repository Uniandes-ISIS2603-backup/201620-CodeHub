/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

/**
 *
 * @author dcagua10
 */
public class AdminDTO 
{
    private Long id;
    private String nombre;
    private String correo;
    private Long edificioId;
  
    /**
     * Constructor por defecto
     */
    public AdminDTO() 
    {
    }

    /**
     * Constructor con parámetros.
     * @param id identificador del edificio
     * @param nombre nombre del edificio
     */
    public AdminDTO(Long pId, String nombre, String correo, Long edificioId) 
    {
	super();
        this.id = pId;
	this.nombre = nombre;
        this.correo = correo;
        this.edificioId = edificioId;
    }

        public Long getId()
    {
        return id;
    }
    
    public void setId(Long pId)
    {
        id = pId;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return nombre;
    }

    /**
     * @param nombre the name to set
     */
    public void setName(String nombre) {
        this.nombre = nombre;
    }
    
        /**
     * @return the name
     */
    public String getEmail() {
        return correo;
    }

    /**
     * @param nombre the name to set
     */
    public void setEmail(String correo) {
        this.correo = correo;
    }
    
    public Long getEdificioId()
    {
        return edificioId;
    }
    
    public void setEdificioId(Long edificioId)
    {
        this.edificioId = edificioId;
    }
      
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() 
    {
    	return "{ id : " + getId() + ", nombre : \"" + getName() + ", correo : \"" + getEmail() +  "\" }" ;  
    }


}
