/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian
 */
@XmlRootElement
public class EdificioDTO 
{
    protected Long id;
    protected String bloque;
    protected String nombre;

    /**
     * Constructor por defecto
     */
    public EdificioDTO() 
    {
    }

    /**
     * Constructor con parámetros.
     * @param id identificador del edificio
     * @param nombre nombre del edificio
     * @param bloque nombre del bloque
     */
    public EdificioDTO(Long id, String bloque, String nombre) {
	this.id = id;
	this.nombre = nombre;
        this.bloque = bloque;
    }
    
    /**
     * Constructor con entity.
     * @param entity el entity a convertir a DTO
     */
    public EdificioDTO(EdificioEntity entity) {
	this.id = entity.getId();
	this.nombre = entity.getName();
        this.bloque = entity.getBloque();
    }

	/**
     * @return el id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id el id a poner
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre el nombre a poner
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return el nombe del bloque
     */
    public String getBloque() {
        return bloque;
    }

    /**
     * @param bloque el nombre del bloque a poner.
     */
    public void setBloque(String bloque) {
        this.bloque = bloque;
    }
    
    /**
     * Convierte el objeto a una cadena
     * @return el mensaje con el objeto
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() 
                + ", bloque : \"" + getBloque() 
                + "\", nombre : \""+getNombre()+"}"; 
    }
    
    /**
     * Convierte el objeto a un entity para ser usado en el back
     * @return el entity analogo al objeto
     */
    public EdificioEntity toEntity()
    {
        EdificioEntity entity = new EdificioEntity();
        entity.setId(id);
        entity.setName(nombre);
        entity.setBloque(bloque);
        return entity;
    }
}
