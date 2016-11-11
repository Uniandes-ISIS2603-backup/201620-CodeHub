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
    
    private String correo;
    private String login;
    private String password;
    
    @OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private EdificioEntity edificio;
    
    
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
     * Obtiene la coleccion de edificios.
     * @return coleccion edificios. 
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
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public String getLogin(){
        return login;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
}
 