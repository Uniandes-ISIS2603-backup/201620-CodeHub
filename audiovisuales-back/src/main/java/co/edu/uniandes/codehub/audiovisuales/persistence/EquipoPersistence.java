/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 * Persistencia de los equipos.
 * @author c.zambrano10
 */
@Stateless
public class EquipoPersistence {
    
    private static final Logger LOGGER= Logger.getLogger(EquipoPersistence.class.getName());
    
    @PersistenceContext(unitName = "CodehubPU")
    protected EntityManager em;  
    
     public EquipoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando eqiupo con id={0}", id);
        return em.find(EquipoEntity.class, id);
    }
}
