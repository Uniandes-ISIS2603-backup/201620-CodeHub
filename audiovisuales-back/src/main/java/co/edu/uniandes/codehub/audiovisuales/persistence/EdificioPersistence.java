/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.martinez15
 */
@Stateless
public class EdificioPersistence 
{

 private static final Logger LOGGER = Logger.getLogger(EdificioPersistence.class.getName());

    @PersistenceContext(unitName = "CodehubPU")
    protected EntityManager em;  
    
     public EdificioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando edificio con id={0}", id);
        return em.find(EdificioEntity.class, id);
     }

    public EdificioEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando company con name = {0}", name);
        TypedQuery<EdificioEntity> q
                = em.createQuery("select e from EdificioEntity e where e.name = :name", EdificioEntity.class);
        q = q.setParameter("name", name); 
        return q.getSingleResult();
    }

    public List<EdificioEntity> findAll() {
        LOGGER.info("Consultando todos los edificios");
        Query q = em.createQuery("select e from EdificioEntity e");
        return q.getResultList();
    }

    public EdificioEntity create(EdificioEntity entity) {
        LOGGER.info("Creando un edificio");
        em.persist(entity);
        LOGGER.info("Edificio creado");
        return entity;
    }

    public EdificioEntity update(EdificioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando edificio con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando edificio con id={0}", id);
        EdificioEntity entity = em.find(EdificioEntity.class, id);
        em.remove(entity);
    }
}
