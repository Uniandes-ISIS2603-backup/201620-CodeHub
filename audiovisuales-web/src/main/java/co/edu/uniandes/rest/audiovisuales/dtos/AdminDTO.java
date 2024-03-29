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
    protected String login;
    protected String password;
  
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
    public AdminDTO(Long pId, String nombre, String correo, Long edificioId, String login, String password) 
    {
        this.id = pId;
	this.nombre = nombre;
        this.correo = correo;
        this.login = login;
        this.password = password;        
    }
    
    public AdminDTO(AdministradorEntity entity)
    {
        this.id = entity.getId();
        this.nombre = entity.getName();
        this.correo = entity.getCorreo();
        this.login = entity.getLogin();
        this.password = entity.getPassword();
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
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the name to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
        /**
     * @return the name
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param nombre the name to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
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
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() 
    {
    	return "{ id : " + getId() + ", nombre : \"" + getNombre() + ", correo : \"" + getCorreo() +  "\" }" ;  
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
        entity.setLogin(login);
        entity.setPassword(password);
        return entity;
    }


}

