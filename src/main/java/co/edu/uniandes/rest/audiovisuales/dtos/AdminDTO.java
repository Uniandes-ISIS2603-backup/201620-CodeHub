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
    private String nombre;
    private String correo;
    private EdificioDTO edificio;

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
    public AdminDTO(String nombre, String correo, EdificioDTO edificio) {
	super();
	this.nombre = nombre;
        this.correo = correo;
        this.edificio = edificio;
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
    
    public EdificioDTO getEdificio() 
    {
        return edificio;
    }
        
    public void setEdificio(EdificioDTO edificio) 
    {
        this.edificio = edificio;
    }
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() 
    {
    	return "{ nombre : " + getName() + ", correo : \"" + getEmail() + ", edificio : \"" + getEdificio().getName() + "\" }" ;  
    }


}

