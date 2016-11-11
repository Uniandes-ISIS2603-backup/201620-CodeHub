/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ln.bello10
 */
@Stateless
@Default
public class UsuarioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());

    @PersistenceContext(unitName = "CodehubPU")
    protected EntityManager em;  
    
     public UsuarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando usuario con id={0}", id);
        return em.find(UsuarioEntity.class, id);
    }
     
     public List<UsuarioEntity> findEstudiantes(){
         LOGGER.log(Level.INFO, "Consultando estudiantes");
         Query q = em.createQuery("SELECT u FROM UsuarioEntity u where u.tipo = 1",UsuarioEntity.class);
         return q.getResultList();
     }
     
     public List<UsuarioEntity> findProfesores(){
         LOGGER.log(Level.INFO, "Consultando profesores");
         Query q = em.createQuery("SELECT u FROM UsuarioEntity u where u.tipo= 2", UsuarioEntity.class);
         return q.getResultList();
     }
     
     public UsuarioEntity findByName(String name){
         LOGGER.log(Level.INFO, "Consultando usuario con name= ", name);
         TypedQuery<UsuarioEntity> q = em.createQuery("SELECT u FROM UsuarioEntity u where u.name= :name",UsuarioEntity.class);
         q = q.setParameter("name", name);
        List<UsuarioEntity> usu = q.getResultList();
        if(usu.isEmpty()){
            return null;
        }
        else{
            return usu.get(0);
        }
     }
     
     public List<UsuarioEntity> findAll(){
         LOGGER.log(Level.INFO,"Consultando todos los usuario");
         Query q = em.createQuery("select u from UsuarioEntity u");
         return q.getResultList();
     }
     
     
     public UsuarioEntity create(UsuarioEntity entity){
         LOGGER.log(Level.INFO, "Creando un nuevo usuario");
         em.persist(entity);
         LOGGER.info("Usuario creado");
        return entity;
     }
     
     
     public UsuarioEntity update(UsuarioEntity entity){
         LOGGER.log(Level.INFO, "Actualizando usuario con id={0}", entity.getId());
         return em.merge(entity);
     }
     
     
     public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando usuario con id={0}",id);
         UsuarioEntity entity = em.find(UsuarioEntity.class, id);
         em.remove(entity);
     }
     
     public UsuarioEntity login(String login, String password)
     {
         LOGGER.log(Level.INFO, "Consultando si la informacion de login es correcta.");
         TypedQuery<UsuarioEntity> q = em.createQuery("SELECT u FROM UsuarioEntity u where u.login= :login AND u.password= :password",UsuarioEntity.class);
         q = q.setParameter("login", login).setParameter("password", password);
        List<UsuarioEntity> usu = q.getResultList();
        if(usu.isEmpty()){
            return null;
        }
        else{
            return usu.get(0);
        }
     }
     
    public boolean checkUniqueLogin(String login)
    {
        LOGGER.log(Level.INFO, "Consultando si el login ingresado es unico.");
        TypedQuery<UsuarioEntity> q = em.createQuery("SELECT u FROM UsuarioEntity u where u.login= :login",UsuarioEntity.class);
        q = q.setParameter("login", login);
        List<UsuarioEntity> resp = q.getResultList();
        return resp.isEmpty();
    }
}