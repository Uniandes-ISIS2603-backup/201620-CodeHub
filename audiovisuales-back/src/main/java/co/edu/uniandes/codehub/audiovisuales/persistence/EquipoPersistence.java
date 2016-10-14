/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
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
     
     public List<EquipoEntity> findAll(){
         LOGGER.info("Consultando todos los equipos");
         Query q = em.createQuery("select u from EquipoEntity u");
         return q.getResultList();
     }
     
     public List<EquipoEntity>findByedificio(EdificioEntity entity){
         LOGGER.log(Level.INFO, "Consultando Equipos en el edificio = ", entity.getName());
        TypedQuery<EquipoEntity> q = em.createQuery("select e from EquipoEntity e where e.edificio.id = :edificio", EquipoEntity.class);
        q = q.setParameter("edificio", entity.getId());
        return q.getResultList();
         
     }
     
     public EquipoEntity create(EquipoEntity entity){
         LOGGER.info("Creando un Equipo nuevo");
        em.persist(entity);
        LOGGER.info("Equipo creado");
        return entity;
     }
     
     public EquipoEntity update(EquipoEntity entity){
         LOGGER.log(Level.INFO, "Actualizando el equipo con id={0}", entity.getId());
        return em.merge(entity);
     }
     
     public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando Reserva con id={0}", id);
        EquipoEntity entity = em.find(EquipoEntity.class, id);
        em.remove(entity);
    }
}
