/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author d.cagua10
 */
@XmlRootElement
public class AdminDetailDTO extends AdminDTO
{
    private EdificioDTO edificio;
      
    public AdminDetailDTO(AdministradorEntity entity) 
    {
	super(entity);
        edificio = new EdificioDTO(entity.getEdificio());
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
     * Convierte el objeto a un entity para ser usado en el back
     * @return el entity analogo al objeto
     */
    public AdministradorEntity toEntity()
    {
        AdministradorEntity entity = new AdministradorEntity();
        entity.setId(id);
        entity.setName(nombre);
        entity.setCorreo(correo);
        entity.setEdificio(edificio.toEntity());
        return entity;
    }
    
    
    
}
