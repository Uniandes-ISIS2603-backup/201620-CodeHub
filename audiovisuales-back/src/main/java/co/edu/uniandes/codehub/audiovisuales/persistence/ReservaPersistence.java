/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.codehub.audiovisuales.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;
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
 * @author o.sabogal10
 */
@Stateless
public class ReservaPersistence 
{

 private static final Logger LOGGER = Logger.getLogger(ReservaPersistence.class.getName());

    @PersistenceContext(unitName = "CodehubPU")
    protected EntityManager em;  
    
     public ReservaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando reserva con id={0}", id);
        return em.find(ReservaEntity.class, id);
    }
     
     public List<ReservaEntity> findByIdUsuario(Long id) 
      {//No estoy seguro de esto.
        LOGGER.log(Level.INFO, "Consultando Reservas con id de usuario = ", id);
        TypedQuery<ReservaEntity> q = em.createQuery("select u from ReservaEntity u where u.usuario.id = :usuarioId", ReservaEntity.class);
        q = q.setParameter("usuarioId", id);
        return q.getResultList();
       }
     
     public List<ReservaEntity> findAll() 
     {
        LOGGER.info("Consultando todos los Reservaes");
        Query q = em.createQuery("select u from ReservaEntity u");
        return q.getResultList();
    }

    public ReservaEntity create(ReservaEntity entity) 
    {
        LOGGER.info("Creando un Reserva nuevo");
        em.persist(entity);
        LOGGER.info("Reserva creado");
        return entity;
    }

    public ReservaEntity update(ReservaEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando Reserva con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando Reserva con id={0}", id);
        ReservaEntity entity = em.find(ReservaEntity.class, id);
        em.remove(entity);
    }
}
