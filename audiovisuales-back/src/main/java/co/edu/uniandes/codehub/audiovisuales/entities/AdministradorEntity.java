/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.entities;

import java.io.Serializable;
import javax.persistence.Entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 *
 * @author d.cagua10
 */
@Entity
public class AdministradorEntity extends BaseEntity implements Serializable
{
    
    private String nombre;
    private String correo;
    
    @OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.REMOVE},optional=false)
    private EdificioEntity edificio;
 
    
     /**
     * Obtiene el atributo nombre.
     * @return atributo nombre.
     */
    public String getNombre()
    {
        return nombre;
    }
    
     /**
     * Establece el valor del atributo nombre.
     * @param nombre nuevo valor del atributo
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
    
      /**
     * Obtiene el atributo correo.
     * @return atributo correo.
     */
    public String getCorreo()
    {
        return correo;
    }
    
     /**
     * Establece el valor del atributo correo.
     * @param correo nuevo valor del atributo
     */
    public void setCorreo(String correo) 
    {
        this.correo = correo;
    }
    
     /**
     * Obtiene la colecci�n de edificios.
     * @return colecci�n edificios. 
     */
    public EdificioEntity getEdificio() 
    {
        return edificio;
    }

    /**
     * Establece el valor de edificio.
     * @param edificio nuevo valor. 
     */
    public void setEdificio(EdificioEntity edificio)
    {
        this.edificio = edificio;
    }
    
    
}
 