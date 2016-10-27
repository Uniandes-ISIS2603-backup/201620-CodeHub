/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.ejbs;

import co.edu.uniandes.codehub.audiovisuales.api.ISancionLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.SancionEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.codehub.audiovisuales.persistence.SancionPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lj.pinzon12
 */
@Stateless
public class SancionLogic implements ISancionLogic
{
    @Inject
    private SancionPersistence persistence;
    
    @Override
    public List<SancionEntity> getSanciones()
    {
        return persistence.findAll();
    }

    @Override
    public SancionEntity getSancion(Long id)
    {
        return persistence.find(id);
    }

    @Override
    public SancionEntity getSancionByFecha(String fecha)
    {
         return persistence.findByFecha(fecha);
    }

    @Override
    public SancionEntity getSancionByUsuario(UsuarioEntity usuario)
    {
         return persistence.findByUsuario(usuario);
    }

    @Override
    public SancionEntity createSancion(SancionEntity entity) throws AudiovisualesLogicException
    {
        persistence.create(entity);
        return entity;
    }

    @Override
    public SancionEntity updateSancion(SancionEntity entity)
    {
         return persistence.update(entity);
    }

    @Override
    public void deleteSancion(Long id)
    {
        persistence.delete(id);
    }
    
}
