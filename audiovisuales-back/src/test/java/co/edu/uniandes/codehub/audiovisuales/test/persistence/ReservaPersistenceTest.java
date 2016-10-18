/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import co.edu.uniandes.codehub.audiovisuales.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@RunWith(Arquillian.class)
public class ReservaPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
                
    }
    
    @Inject
    private ReservaPersistence reservaPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    UsuarioEntity usuarioEntity;
    
    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();
    
    @Before
    public void setUp() {
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            try
            {
                utx.rollback();
            }
            catch (Exception e1){
                e1.printStackTrace();
                
            }
        }
    }
    
    @Test
    public void createReservaTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);

        ReservaEntity result = reservaPersistence.create(newEntity);

        Assert.assertNotNull(result);
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertNotNull(entity);
       
        //BaseEntity
        Assert.assertEquals(newEntity.getName(), entity.getName());
        //Assert.assertEquals(newEntity.getId(), entity.getId());
        
        //ReservaEntity
        
       
    }
    
     @Test
    public void testFindAll(){
        List<ReservaEntity> respuesta= reservaPersistence.findAll();
        for(ReservaEntity equipo:data){
            Assert.assertTrue("no contiene un reserva.",respuesta.contains(equipo));
        }
    }
    
     @Test
    public void getReservaTest(){
        long buscar = data.get(0).getId();
        System.out.println("================================================================");
        System.out.println("id:"+buscar);
        System.out.println("================================================================");
        ReservaEntity respuesta = reservaPersistence.find(buscar);
        Assert.assertEquals("la busqueda no arrojó el elemento correcto",data.get(0),respuesta);
    }
   
     @Test
      public void deleteReservaTest() 
    {
        ReservaEntity entity = data.get(0);
        reservaPersistence.delete(entity.getId());
        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
      
      
      @Test
    public void testmierda(){
        Assert.assertNotNull(data);
        ReservaEntity[] e = new ReservaEntity[data.size()];
        System.out.println("==================================================================================");
        System.out.println("El tamaño del puto arreglo es"+data.size());
        System.out.println("==================================================================================");
        System.out.println("El puto arreglo es"+data.toArray());
        System.out.println("==================================================================================");
        Assert.assertArrayEquals(e,data.toArray());
    }
      
      @Test
    public void updateReservaTest() 
    {
        ReservaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);

        newEntity.setId(entity.getId());
        reservaPersistence.update(newEntity);
        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());
        
        //BaseEntity
        Assert.assertEquals(newEntity.getName(), resp.getName());
        

    }

   

    private void clearData() {
        em.createQuery("delete from DepartmentEntity").executeUpdate();
        em.createQuery("delete from CompanyEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        usuarioEntity = factory.manufacturePojo(UsuarioEntity.class);
        usuarioEntity.setId(1L);
        em.persist(usuarioEntity);
        Long id = 1L;
        for (int i=0;i<3;i++)
        {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            entity.setId(id);
            entity.set
            em.persist(entity);
            data.add(entity);
            id++;
        }
    }
    
}
