/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;

/**
 *
 * @author dcagua10
 */
public class AdminDTO 
{
    protected Long id;
    protected String nombre;
    protected String correo;
  
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
        this.id = pId;
	this.nombre = nombre;
        this.correo = correo;
    }
    
    public AdminDTO(AdministradorEntity entity)
    {
        this.id = entity.getId();
        this.nombre = entity.getName();
        this.correo = entity.getCorreo();
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
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() 
    {
    	return "{ id : " + getId() + ", nombre : \"" + getName() + ", correo : \"" + getEmail() +  "\" }" ;  
    }
    
       /**
     * Convierte el objeto a un entity para ser usado en el back
     * @return el entity analogo al objeto
     */
    public AdministradorEntity toEntity()
    {
       AdministradorEntity entity = new AdministradorEntity();
        entity.setId(id);
        entity.setName(nombre);
        entity.setCorreo(correo);
        return entity;
    }


}

