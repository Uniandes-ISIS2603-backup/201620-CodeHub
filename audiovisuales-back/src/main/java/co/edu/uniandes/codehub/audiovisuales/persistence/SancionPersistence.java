/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.persistence;
import co.edu.uniandes.codehub.audiovisuales.entities.SancionEntity;
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
 * @author lj.pinzon12
 */
@Stateless
public class SancionPersistence
{
     private static final Logger LOGGER = Logger.getLogger(SancionPersistence.class.getName());

    @PersistenceContext(unitName = "CodehubPU")
    protected EntityManager em;  
    
     public SancionEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando company con id={0}", id);
        return em.find(SancionEntity.class, id);
     }
     public SancionEntity findByFecha(String fecha) 
      {
        LOGGER.log(Level.INFO, "Consultando sancion con fecha= ", fecha);
        TypedQuery<SancionEntity> q = em.createQuery("select u from SancionEntity u where u.fecha = :fecha", SancionEntity.class);
        q = q.setParameter("fecha", fecha);
        return q.getSingleResult();
      }
     public List<SancionEntity> findAll() 
     {
        LOGGER.info("Consultando todas las sanciones");
        Query q = em.createQuery("select u from SancionEntity u");
        return q.getResultList();
    }

    public SancionEntity create(SancionEntity entity) 
    {
        LOGGER.info("Creando una sancion nueva");
        em.persist(entity);
        LOGGER.info("Sancion creada");
        return entity;
    }

    public SancionEntity update(SancionEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando sancion con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando sancion con id={0}", id);
        SancionEntity entity = em.find(SancionEntity.class, id);
        em.remove(entity);
    }
        public List<SancionEntity> findByIdUsuario(Long id){
                LOGGER.log(Level.INFO, "Consultando sanciones con id de usuario = ", id);
        TypedQuery<SancionEntity> q = em.createQuery("select u from SancionEntity u where u.usuario.id = :usuarioId", SancionEntity.class);
        q = q.setParameter("usuarioId", id);
        return q.getResultList();
        
    } 
     
}