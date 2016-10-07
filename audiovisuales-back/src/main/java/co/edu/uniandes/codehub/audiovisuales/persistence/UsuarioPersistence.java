/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ln.bello10
 */
public class UsuarioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(EdificioPersistence.class.getName());

    @PersistenceContext(unitName = "CodehubPU")
    protected EntityManager em;  
    
     public UsuarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando company con id={0}", id);
        return em.find(UsuarioEntity.class, id);
    }
}
