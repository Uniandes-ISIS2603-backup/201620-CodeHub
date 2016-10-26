/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.ejbs;

import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.codehub.audiovisuales.persistence.EdificioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.martinez15
 */
@Stateless
public class EdificioLogic {
    
    @Inject
    private EdificioPersistence persistence;
    
    public List<EdificioEntity> getEdificios() {
        return persistence.findAll();
    }
    
    public EdificioEntity getEdificio(Long id)
    {
        return persistence.find(id);
    }
    
    public EdificioEntity getEdificioByName(String name) {
        return persistence.findByName(name);
    }
    
    public EdificioEntity getEdificioByBloque(String bloque) {
        return persistence.findByBloque(bloque);
    }
       
    public EdificioEntity getEdificioByAdmin(Long adminId)
    {
        return persistence.findByAdmin(adminId);
    }
    
    public EdificioEntity createEdificio(EdificioEntity entity) throws AudiovisualesLogicException {
        EdificioEntity existe = getEdificioByName(entity.getName());
        if (existe != null) 
        {
            throw new AudiovisualesLogicException("Ya existe un edificio con ese nombre");
        } 
        else
        {
            persistence.create(entity);
        }
        return entity;
    }
    
    public EdificioEntity updateEdificio(EdificioEntity entity) {
        return persistence.update(entity);
    }

    public void deleteEdificio(Long id) {
        persistence.delete(id);
    }
}
