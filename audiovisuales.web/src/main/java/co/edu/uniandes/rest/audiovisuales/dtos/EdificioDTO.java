/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public class EdificioDTO 
{
    private Long id;
    private String bloque;
    private String nombre;
    
    private AdminDTO admin;
    
    private List<EquipoDTO> equipos;

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
	super();
	this.id = id;
	this.nombre = nombre;
        this.bloque = bloque;
        this.equipos = new ArrayList<>();
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
    
    public AdminDTO getAdmin()
    {
        return admin;
    }
    
    public void setAdmin(AdminDTO admin)
    {
        this.admin = admin;
    }
    
    public List<EquipoDTO> getEquipos()
    {
        return equipos;
    }
    
    public void setEquipos(List<EquipoDTO> equipos)
    {
        this.equipos = equipos;
    }
    
    /**
     * Convierte el objeto a una cadena
     * @return el mensaje con el objeto
     */
    @Override
    public String toString() {
        Long adminID = -1L;
        if(admin!=null)
        {
            adminID=getAdmin().getId();
        }
    	return "{ id : " + getId() 
                + ", bloque : \"" + getBloque() 
                + "\", nombre : \""+getNombre()
                +"\", admin : \""+adminID+"}" ;  
    }
}
