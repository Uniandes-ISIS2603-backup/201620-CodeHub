/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.codehub.audiovisuales.persistence.AdministradorPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author d.cagua10
 */
@RunWith(Arquillian.class)
public class AdministradorPersistenceTest {
    /**
     *
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private AdministradorPersistence administradorPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() 
    {
        em.createQuery("delete from AdministradorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        Long id = 1L;
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);
            entity.setId(id);
            em.persist(entity);
            data.add(entity);
            id++;
        }
    }

    /**
     * Prueba para crear un Administrador.
     */
    @Test
    public void createAdministradorTest() throws AudiovisualesLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        AdministradorEntity result = administradorPersistence.create(newEntity);
        Assert.assertNotNull(result);
        AdministradorEntity entity = em.find(AdministradorEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity, entity);        
    }

    /**
     * Prueba para consultar la lista de Administradores.
     *
     *
     */
    @Test
    public void getAdministradoresTest()
    {
        List<AdministradorEntity> list = administradorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AdministradorEntity ent : list) {
            boolean found = false;
            for (AdministradorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Administrador.
     */
    @Test
    public void getAdministradorTest() 
    {
        AdministradorEntity entity = data.get(0);
        Long bId = entity.getId();
        AdministradorEntity newEntity = administradorPersistence.find(bId);
        Assert.assertEquals(entity,newEntity);
    }

    /**
     * Prueba para consultar un Administrador.
     */
    @Test
    public void getAdministradorByNameTest() 
    {
        AdministradorEntity entity = data.get(0);
        String nombre = entity.getName();
        AdministradorEntity newEntity = administradorPersistence.findByName(nombre);
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());   
    }

    /**
     * Prueba para eliminar un Administrador.
     */
    @Test
    public void deleteAdministradorTest() 
    {
        AdministradorEntity entity = data.get(0);
        administradorPersistence.delete(entity.getId());
        AdministradorEntity deleted = em.find(AdministradorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Administrador.
     */
    @Test
    public void updateAdministradorTest() 
    {
        AdministradorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);

        newEntity.setId(entity.getId());
        administradorPersistence.update(newEntity);
        AdministradorEntity resp = em.find(AdministradorEntity.class, entity.getId());
        


    }
    
    @Test
    public void loginSuccesTest(){
        AdministradorEntity entity = data.get(0);
        AdministradorEntity resp = administradorPersistence.login(entity.getLogin(),entity.getPassword());
        Assert.assertNotNull(resp);
        
        //BaseEntity
        Assert.assertEquals(entity.getName(), resp.getName());
        Assert.assertEquals(entity.getId(), resp.getId());
    }
    
    @Test
    public void loginFailTest(){
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        
        AdministradorEntity resp = administradorPersistence.login(newEntity.getLogin(),newEntity.getPassword());
        Assert.assertNull(resp);
    }
    
    @Test
    public void checkUniqueTest(){
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        
        Assert.assertTrue(administradorPersistence.checkUniqueLogin(newEntity.getLogin()));
        
        AdministradorEntity entity = data.get(0);
        
        Assert.assertFalse(administradorPersistence.checkUniqueLogin(entity.getLogin()));
    }
}
