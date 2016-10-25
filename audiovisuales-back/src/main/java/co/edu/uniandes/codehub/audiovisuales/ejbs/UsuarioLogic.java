/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.ejbs;

import co.edu.uniandes.codehub.audiovisuales.api.IUsuarioLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.codehub.audiovisuales.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ln.bello10
 */

@Stateless
public class UsuarioLogic implements IUsuarioLogic {

   @Inject
    private UsuarioPersistence persistence;
    
    @Override
    public List<UsuarioEntity> getUsuarios() {
        return persistence.findAll();
    }

    @Override
    public UsuarioEntity getUsuario(Long id) {
        return persistence.find(id);
    }

    @Override
    public UsuarioEntity getUsuarioByName(String name) {
        return persistence.findByName(name);
    }

    @Override
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws AudiovisualesLogicException {
        UsuarioEntity existe = getUsuarioByName(entity.getName());
        if (existe != null) {
            throw new AudiovisualesLogicException("Ya existe un usuario con ese nombre");
        } else
        {
            persistence.create(entity);
        }
        return entity;
    }

    @Override
    public UsuarioEntity updateUsuario(UsuarioEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteUsuario(Long id) {
        persistence.delete(id);
    }

    @Override
    public Integer getNumberOfSancionesUsuario(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getNumberOfReservasUsuario(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
