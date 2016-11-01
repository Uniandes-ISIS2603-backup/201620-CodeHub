/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.codehub.audiovisuales.test.logic;

import co.edu.uniandes.codehub.audiovisuales.api.IReservaLogic;
import co.edu.uniandes.codehub.audiovisuales.ejbs.ReservaLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.codehub.audiovisuales.persistence.ReservaPersistence;
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
 * @author o.sabogal10
 */
@RunWith(Arquillian.class)
public class ReservaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IReservaLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(IReservaLogic.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from ReservaEntity").executeUpdate();
        em.createQuery("delete from EquipoEntity").executeUpdate();
    }
    
    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0 ; i<5;i++)
        {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createReservaTest()
    {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        
        ReservaEntity resultado = logic.createReserva(newEntity);
        Assert.assertNotNull(resultado);
        
        ReservaEntity entity = em.find(ReservaEntity.class, resultado.getId());
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
}
