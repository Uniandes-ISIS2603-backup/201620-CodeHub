/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.ejbs;

import co.edu.uniandes.codehub.audiovisuales.api.IAdministradorLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.codehub.audiovisuales.persistence.AdministradorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author d.cagua10
 */
@Stateless
public class AdministradorLogic implements IAdministradorLogic
{
    @Inject
    private AdministradorPersistence persistence;
    
    @Override
    public List<AdministradorEntity> getAdministradores()
    {
        return persistence.findAll();
    }
    
    @Override
    public AdministradorEntity getAdministrador(Long idAdm)
    {
        return persistence.find(idAdm);
    }
    
    @Override
    public AdministradorEntity getAdministradorByName(String name)
    {
        return persistence.findByName(name);
    }
    
    @Override
    public AdministradorEntity getAdministradorByEdificio(Long idEdificio)
    {
        return persistence.findByEdificio(idEdificio);
    }
    
    @Override
    public AdministradorEntity createAdministrador(AdministradorEntity entity) throws AudiovisualesLogicException 
    {
        AdministradorEntity entidad = getAdministradorByName(entity.getName());
        if (entidad != null) 
        {
            throw new AudiovisualesLogicException("Ya existe un administrador con ese nombre");
        }
        persistence.create(entity);
        return entity;
    }
  
    public AdministradorEntity updateAdministrador(AdministradorEntity entity) 
    {
        return persistence.update(entity);
    }

    @Override
    public void deleteAdministrador(Long id)
    {
        persistence.delete(id);
    }
}
