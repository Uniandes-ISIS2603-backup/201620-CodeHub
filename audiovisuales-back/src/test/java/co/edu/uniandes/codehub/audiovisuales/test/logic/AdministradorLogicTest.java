/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.logic;

import co.edu.uniandes.codehub.audiovisuales.api.IAdministradorLogic;
import co.edu.uniandes.codehub.audiovisuales.ejbs.AdministradorLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.codehub.audiovisuales.persistence.AdministradorPersistence;
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
 * @author d.cagua10
 */
@RunWith(Arquillian.class)
public class AdministradorLogicTest 
{
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IAdministradorLogic logic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorLogic.class.getPackage())
                .addPackage(IAdministradorLogic.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void setUp() 
    {
        try 
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() 
    {
        em.createQuery("delete from AdministradorEntity").executeUpdate();
        em.createQuery("delete from EdificioEntity").executeUpdate();
    }

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 5; i++) 
        {            
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createAdministradorTestSuccess() throws AudiovisualesLogicException 
    {
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);

        try
        {
         AdministradorEntity result = logic.createAdministrador(newEntity);
         Assert.assertNotNull(result);
         AdministradorEntity entity = em.find(AdministradorEntity.class, result.getId());

         Assert.assertEquals(newEntity.getId(), entity.getId());
        }
        catch (AudiovisualesLogicException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
            throw e;
        }    
    }
    
    @Test(expected = AudiovisualesLogicException.class)
    public void createAdministradorTestFail() throws Exception 
    {
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        newEntity.setLogin(data.get(0).getLogin());
        AdministradorEntity result = logic.createAdministrador(newEntity);
        Assert.assertNull(result);
    }
    
     @Test
    public void getAdministradoresTest() 
    {
        List<AdministradorEntity> list = logic.getAdministradores();
        Assert.assertEquals(data.size(), list.size());
        for (AdministradorEntity entity : list) 
        {
            boolean found = false;
            for (AdministradorEntity dataEntity : data)
            {
                if (entity.getId().equals(dataEntity.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getAdministradorTest() 
    {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity resultEntity = logic.getAdministrador(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void getAdministradorByNameTest() 
    {
        AdministradorEntity entity = data.get(1);
        AdministradorEntity resultEntity = logic.getAdministradorByName(entity.getName());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
     
    @Test
    public void getAdministradorByEdificioTest() 
    {
        AdministradorEntity entity = data.get(1);
        AdministradorEntity resultEntity = logic.getAdministradorByEdificio(entity.getEdificio().getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void updateAdministradorTest() 
    {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity updatedEntity = factory.manufacturePojo(AdministradorEntity.class);

        updatedEntity.setId(entity.getId());

        logic.updateAdministrador(updatedEntity);

        AdministradorEntity resultEntity = em.find(AdministradorEntity.class, entity.getId());

        Assert.assertEquals(updatedEntity.getName(), resultEntity.getName());
        Assert.assertEquals(updatedEntity.getId(), resultEntity.getId());
    }
    
    @Test
    public void deleteAdministradorTest() 
    {
        AdministradorEntity entity = data.get(1);
        logic.deleteAdministrador(entity.getId());
        AdministradorEntity resultEntity = em.find(AdministradorEntity.class, entity.getId());
        Assert.assertNull(resultEntity);
    }

    @Test
    public void loginTest() 
    {
        AdministradorEntity entity = data.get(1);
        AdministradorEntity resultEntity = logic.login(entity.getLogin(),entity.getPassword());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
}
