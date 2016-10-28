/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.logic;

import co.edu.uniandes.codehub.audiovisuales.api.IEdificioLogic;
import co.edu.uniandes.codehub.audiovisuales.ejbs.EdificioLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
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
public class EdificioLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IEdificioLogic logic;
    
    @Inject
    private AdministradorPersistence adminPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<EdificioEntity> data = new ArrayList<EdificioEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EdificioEntity.class.getPackage())
                .addPackage(EdificioLogic.class.getPackage())
                .addPackage(IEdificioLogic.class.getPackage())
                .addPackage(EdificioPersistence.class.getPackage())
                .addPackage(AdministradorEntity.class.getPackage())
                //.addPackage(AdministradorLogic.class.getPackage())
                //.addPackage(IAdministradorLogic.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
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
        for (int i = 0; i < 5; i++) {            
            EdificioEntity entity = factory.manufacturePojo(EdificioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un edificio con un nombre y bloque que no existe
     */
    @Test
    public void createEdificioSuccesTest() throws AudiovisualesLogicException {
        EdificioEntity newEntity = factory.manufacturePojo(EdificioEntity.class);

        EdificioEntity result =logic.createEdificio(newEntity);
        Assert.assertNotNull(result);

        EdificioEntity entity = em.find(EdificioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getBloque(), entity.getBloque());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para crear un edificio con un nombre que ya existe
     */
    @Test(expected = AudiovisualesLogicException.class)
    public void createEdificioFailTest1() throws Exception {
        EdificioEntity newEntity = factory.manufacturePojo(EdificioEntity.class);
        newEntity.setName(data.get(0).getName());
        EdificioEntity resultEntity = logic.createEdificio(newEntity);
        Assert.assertNull(resultEntity);
    }
    
        /**
     * Prueba para crear un edificio con un bloque que ya existe
     */
    @Test(expected = AudiovisualesLogicException.class)
    public void createEdificioFailTest2() throws Exception {
        EdificioEntity newEntity = factory.manufacturePojo(EdificioEntity.class);
        newEntity.setBloque(data.get(0).getBloque());
        EdificioEntity resultEntity = logic.createEdificio(newEntity);
        Assert.assertNull(resultEntity);
    }
    
    /**
     * Prueba para obtener la lista de edificios
     */
    @Test
    public void getEdificiosTest() {
        List<EdificioEntity> list = logic.getEdificios();
        Assert.assertEquals(data.size(), list.size());
        for (EdificioEntity entity : list) 
        {
            boolean found = false;
            for (EdificioEntity dataEntity : data) {
                if (entity.getId().equals(dataEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para obtener un edificio.
     */
    @Test
    public void getEdificioTest() {
        EdificioEntity entity = data.get(0);
        EdificioEntity resultEntity = logic.getEdificio(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getBloque(), resultEntity.getBloque());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para obtener un edificio por nombre.
     */
    @Test
    public void getEdificioByNameTest() {
        EdificioEntity entity = data.get(1);
        EdificioEntity resultEntity = logic.getEdificioByName(entity.getName());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getBloque(), resultEntity.getBloque());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para obtener un edificio por bloque.
     */
    @Test
    public void getEdificioByBloqueTest() {
        EdificioEntity entity = data.get(1);
        EdificioEntity resultEntity = logic.getEdificioByBloque(entity.getBloque());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getBloque(), resultEntity.getBloque());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para obtener un edificio por su administrador.
     */
    @Test
    public void getEdificioByAdminTest() throws AudiovisualesLogicException{
        EdificioEntity entity = data.get(1);
        AdministradorEntity admin = factory.manufacturePojo(AdministradorEntity.class);
        entity.setAdmin(admin);
        admin.setEdificio(entity);
        adminPersistence.create(admin);
        EdificioEntity updatedEntity = logic.updateEdificio(entity);
        EdificioEntity resultEntity = logic.getEdificioByAdmin(admin.getId());
        
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(updatedEntity.getName(), resultEntity.getName());
        Assert.assertEquals(updatedEntity.getBloque(), resultEntity.getBloque());
        Assert.assertEquals(updatedEntity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para actualizar un edificio.
     */
    @Test
    public void updateEdificioTest() {
        EdificioEntity entity = data.get(0);
        EdificioEntity updatedEntity = factory.manufacturePojo(EdificioEntity.class);

        updatedEntity.setId(entity.getId());

        logic.updateEdificio(updatedEntity);

        EdificioEntity resultEntity = em.find(EdificioEntity.class, entity.getId());

        Assert.assertEquals(updatedEntity.getName(), resultEntity.getName());
        Assert.assertEquals(updatedEntity.getBloque(), resultEntity.getBloque());
        Assert.assertEquals(updatedEntity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para eliminar un edificio.
     */
    @Test
    public void deleteEdificioTest() {
        EdificioEntity entity = data.get(1);
        logic.deleteEdificio(entity.getId());
        EdificioEntity resultEntity = em.find(EdificioEntity.class, entity.getId());
        Assert.assertNull(resultEntity);
    }
}
