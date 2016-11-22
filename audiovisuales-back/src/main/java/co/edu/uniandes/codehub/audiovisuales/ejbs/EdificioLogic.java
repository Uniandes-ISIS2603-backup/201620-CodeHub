/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.ejbs;

import co.edu.uniandes.codehub.audiovisuales.api.IEdificioLogic;
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
public class EdificioLogic implements IEdificioLogic {
    
    @Inject
    private EdificioPersistence persistence;
    
    @Override
    public List<EdificioEntity> getEdificios() {
        return persistence.findAll();
    }
    
    @Override
    public EdificioEntity getEdificio(Long id)
    {
        return persistence.find(id);
    }
    
    @Override
    public EdificioEntity getEdificioByName(String name) {
        return persistence.findByName(name);
    }
    
    @Override
    public EdificioEntity getEdificioByBloque(String bloque) {
        return persistence.findByBloque(bloque);
    }
    
    @Override
    public EdificioEntity createEdificio(EdificioEntity entity) throws AudiovisualesLogicException {
        EdificioEntity existeName = getEdificioByName(entity.getName());
        if (existeName != null) 
        {
            throw new AudiovisualesLogicException("Ya existe un edificio con ese nombre");
        } 
        EdificioEntity existeBloque = getEdificioByBloque(entity.getBloque());
        if (existeBloque != null) 
        {
            throw new AudiovisualesLogicException("Ya existe un edificio con ese bloque");
        } 
        persistence.create(entity);
        return entity;
    }
    
    @Override
    public EdificioEntity updateEdificio(EdificioEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteEdificio(Long id) {
        persistence.delete(id);
    }
}
