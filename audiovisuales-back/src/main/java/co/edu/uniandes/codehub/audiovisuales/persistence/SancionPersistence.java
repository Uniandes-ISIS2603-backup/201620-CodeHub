/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.SancionEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lj.pinzon12
 */
public class SancionPersistence
{
     private static final Logger LOGGER = Logger.getLogger(SancionPersistence.class.getName());

    @PersistenceContext(unitName = "CodehubPU")
    protected EntityManager em;  
    
     public SancionEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando company con id={0}", id);
        return em.find(SancionEntity.class, id);
     }
     
}