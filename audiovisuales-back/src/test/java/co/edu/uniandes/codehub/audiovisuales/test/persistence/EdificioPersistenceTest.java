/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.persistence.AdministradorPersistence;
import co.edu.uniandes.codehub.audiovisuales.persistence.EdificioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.martinez15
 */
@RunWith(Arquillian.class)
public class EdificioPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EdificioEntity.class.getPackage())
                .addPackage(EdificioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private EdificioPersistence edificioPersistence;
    
    @Inject
    private AdministradorPersistence adminPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<EdificioEntity> data = new ArrayList<EdificioEntity>();
    
    
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
    
    private void clearData() {
        em.createQuery("delete from AdministradorEntity").executeUpdate();
        em.createQuery("delete from EdificioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EdificioEntity entity = factory.manufacturePojo(EdificioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Edificio.
     */
    @Test
    public void createEdificioTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        EdificioEntity newEntity = factory.manufacturePojo(EdificioEntity.class);

        EdificioEntity result = edificioPersistence.create(newEntity);

        Assert.assertNotNull(result);
        EdificioEntity entity = em.find(EdificioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
     /**
     * Prueba para consultar los edificios.
     */
    @Test
    public void getEdificiosTest() 
    {
        List<EdificioEntity> list = edificioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EdificioEntity ent : list) {
            boolean found = false;
            for (EdificioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un edificio particular.
     */
    @Test
    public void getEdificioTest() 
    {
        EdificioEntity entity = data.get(0);
        EdificioEntity newEntity = edificioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    /**
     * Prueba para consultar un edificio pos su nombre.
     */
    @Test
    public void getEdificioByNameTest() 
    {
        EdificioEntity entity = data.get(0);
        EdificioEntity newEntity = edificioPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    /**
     * Prueba para consultar un edificio pos su bloque.
     */
    @Test
    public void getEdificioByBloqueTest() 
    {
        EdificioEntity entity = data.get(0);
        EdificioEntity newEntity = edificioPersistence.findByBloque(entity.getBloque());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    /**
     * Prueba para consultar un edificio pos su administrador.
     */
    @Test
    public void getEdificioByAdminTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity admin = factory.manufacturePojo(AdministradorEntity.class);
        EdificioEntity entity = data.get(0);
        entity.setAdmin(admin);
        admin.setEdificio(entity);    
        adminPersistence.create(admin);
        EdificioEntity updatedEntity = edificioPersistence.update(entity);  
        EdificioEntity resultEntity = edificioPersistence.findByAdmin(admin.getId());
        
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(updatedEntity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para eliminar un edificio.
     */
    @Test
    public void deleteEdificioTest() 
    {
        EdificioEntity entity = data.get(0);
        edificioPersistence.delete(entity.getId());
        EdificioEntity deleted = em.find(EdificioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un edificio.
     */
    @Test
    public void updateEdificioTest() 
    {
        EdificioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EdificioEntity newEntity = factory.manufacturePojo(EdificioEntity.class);

        newEntity.setId(entity.getId());
        edificioPersistence.update(newEntity);
        EdificioEntity resp = em.find(EdificioEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
