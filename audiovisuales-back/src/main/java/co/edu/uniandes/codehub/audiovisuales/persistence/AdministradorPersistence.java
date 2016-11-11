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
     
      public AdministradorEntity findByName(String name) 
      {
         LOGGER.log(Level.INFO, "Consultando usuario con nombre= ", name);
         TypedQuery<AdministradorEntity> q = em.createQuery("SELECT u FROM AdministradorEntity u where u.name= :name",AdministradorEntity.class);
         q = q.setParameter("name", name);
         List<AdministradorEntity> lista = q.getResultList();
         if (lista.isEmpty())
         {
             return null;
         }
         else
         {
             return lista.get(0);
         }
         
      }
     
       public AdministradorEntity findByEdificio(Long edificioId) {
        LOGGER.log(Level.INFO, "Consultando el administrador con edificio Id = {0}", ""+edificioId);
        Query q = em.createQuery("SELECT ed FROM AdministradorEntity ed WHERE ed.edificio.id = :edificio");
        q = q.setParameter("edificio", edificioId);
        List<AdministradorEntity> ar = q.getResultList();
        if(ar.isEmpty())
        {
            return null;
        }
        else
        {
            return ar.get(0);
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
    
    public AdministradorEntity login(String login, String password)
     {
         LOGGER.log(Level.INFO, "Consultando si la informacion de login es correcta.");
         TypedQuery<AdministradorEntity> q = em.createQuery("SELECT u FROM AdministradorEntity u where u.login= :login AND u.password= :password",AdministradorEntity.class);
         q = q.setParameter("login", login).setParameter("password", password);
        List<AdministradorEntity> resp = q.getResultList();
        if(resp.isEmpty()){
            return null;
        }
        else{
            return resp.get(0);
        }
     }
}
