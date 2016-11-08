/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.logic;

import co.edu.uniandes.codehub.audiovisuales.api.ISancionLogic;
import co.edu.uniandes.codehub.audiovisuales.ejbs.SancionLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.SancionEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import co.edu.uniandes.codehub.audiovisuales.persistence.SancionPersistence;
import co.edu.uniandes.codehub.audiovisuales.persistence.UsuarioPersistence;
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
 * @author lj.pinzon12
 */
@RunWith(Arquillian.class)
public class SancionLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ISancionLogic logic;
    
    @Inject
    private UsuarioPersistence user;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<SancionEntity> data = new ArrayList<SancionEntity>();
    
        @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SancionEntity.class.getPackage())
                .addPackage(SancionLogic.class.getPackage())
                .addPackage(ISancionLogic.class.getPackage())
                .addPackage(SancionPersistence.class.getPackage())
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
        em.createQuery("delete from SancionEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 5; i++) {            
            SancionEntity entity = factory.manufacturePojo(SancionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
        /**
     * Prueba para obtener un edificio.
     */
    @Test
    public void getSancionTest() {
        SancionEntity entity = data.get(0);
        SancionEntity resultEntity = logic.getSancion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getFecha(), resultEntity.getFecha());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
        
    /**
     * Prueba para obtener una sancion por fecha.
     */
    @Test
    public void getSancionByFechaTest() {
        SancionEntity entity = data.get(1);
        SancionEntity resultEntity = logic.getSancionByFecha(entity.getFecha());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getFecha(), resultEntity.getFecha());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    /**
     * Prueba para obtener una sancion por usuario.
     */
    @Test
    public void getSancionByUsuarioTest() {
        SancionEntity entity = data.get(1);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity u = factory.manufacturePojo(UsuarioEntity.class);
        user.create(u);
        entity.setUsuario(u);
        logic.updateSancion(entity);
        List<SancionEntity> resultEntity = logic.getSancionesByUsuario(entity.getUsuario().getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getFecha(), resultEntity.get(0).getFecha());
        Assert.assertEquals(entity.getId(), resultEntity.get(0).getId());
    }
        /**
     * Prueba para actualizar una sancion.
     */
    @Test
    public void updateSancionTest() {
        SancionEntity entity = data.get(0);
        SancionEntity updatedEntity = factory.manufacturePojo(SancionEntity.class);

        updatedEntity.setId(entity.getId());

        logic.updateSancion(updatedEntity);

        SancionEntity resultEntity = em.find(SancionEntity.class, entity.getId());

        Assert.assertEquals(updatedEntity.getFecha(), resultEntity.getFecha());
        Assert.assertEquals(updatedEntity.getId(), resultEntity.getId());
    }
        /**
     * Prueba para eliminar un edificio.
     */
    @Test
    public void deleteSancionTest() {
        SancionEntity entity = data.get(1);
        logic.deleteSancion(entity.getId());
        EdificioEntity resultEntity = em.find(EdificioEntity.class, entity.getId());
        Assert.assertNull(resultEntity);
    }
}
