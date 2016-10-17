/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;
import java.util.List;
import javax.ejb.Stateless;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


/**
 *
 * @author d.cagua10
 */
@Stateless
public class AdministradorPersistence 
{
    
 private static final Logger LOGGER = Logger.getLogger(AdministradorPersistence.class.getName());

    @PersistenceContext(unitName = "CodehubPU")
    protected EntityManager em;  
    
     public AdministradorEntity find(Long id) 
     {
        LOGGER.log(Level.INFO, "Consultando administrador con id={0}", id);
        return em.find(AdministradorEntity.class, id);
     }
     
      public AdministradorEntity findByName(String nombre) 
      {
          try
          {
        LOGGER.log(Level.INFO, "Consultando administrador con nombre= ", nombre);
        TypedQuery<AdministradorEntity> q = em.createQuery("select u from AdministradorEntity u where u.nombre = :nombre", AdministradorEntity.class);
        q = q.setParameter("nombre", nombre);
        return q.getSingleResult();
         } catch(NoResultException e) {
        return null;
    }
       }
     
     public List<AdministradorEntity> findAll() 
     {
        LOGGER.info("Consultando todos los administradores");
        Query q = em.createQuery("select u from AdministradorEntity u");
        return q.getResultList();
    }

    public AdministradorEntity create(AdministradorEntity entity) 
    {
        LOGGER.info("Creando un administrador nuevo");
        em.persist(entity);
        LOGGER.info("Administrador creado");
        return entity;
    }

    public AdministradorEntity update(AdministradorEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando administrador con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando administrador con id={0}", id);
        AdministradorEntity entity = em.find(AdministradorEntity.class, id);
        em.remove(entity);
    }
    
}
