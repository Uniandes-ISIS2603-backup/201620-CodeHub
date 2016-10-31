/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.logic;

import co.edu.uniandes.codehub.audiovisuales.api.IEquipoLogic;
import co.edu.uniandes.codehub.audiovisuales.ejbs.EquipoLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.codehub.audiovisuales.persistence.EquipoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import java.util.ArrayList;
import java.util.List;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * test para la persistencia de equipos.
 * @author c.zambrano10
 */
@RunWith(Arquillian.class)
public class EquipoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IEquipoLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<EquipoEntity> data = new ArrayList<EquipoEntity>();
    
    EdificioEntity edificioEntity;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoEntity.class.getPackage())
                .addPackage(EquipoLogic.class.getPackage())
                .addPackage(IEquipoLogic.class.getPackage())
                .addPackage(EquipoPersistence.class.getPackage())
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
        em.createQuery("delete from EquipoEntity").executeUpdate();
        em.createQuery("delete from EdificioEntity").executeUpdate();
    }
    
    private void insertData() {
        //agrega un edificio de prueba
        edificioEntity = factory.manufacturePojo(EdificioEntity.class);
        edificioEntity.setId(1L);
        em.persist(edificioEntity);
        for (int i = 0; i < 3; i++) {
            EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
            entity.setEdificio(edificioEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**-----------------------------------
     *          Metodos de Test
     ------------------------------------*/
    
    
    @Test
    public void createEquipoTestSucces() throws AudiovisualesLogicException{
        EquipoEntity nuevo = factory.manufacturePojo(EquipoEntity.class);
        
        EquipoEntity result = logic.createEquipo(nuevo);
        Assert.assertNotNull("No puede retornar nulo.",result);
        
        EquipoEntity entity = logic.getEquipo(nuevo.getId());
        
        Assert.assertEquals("El equipo no se agrega.",nuevo, entity);
    }
    
    @Test(expected = AudiovisualesLogicException.class)
    public void createEquipoTestFail1() throws Exception{
        EquipoEntity nuevo = null;
        
        EquipoEntity result = logic.createEquipo(nuevo);
        Assert.assertNull("Debe retornar nulo.",result);
    }
    
    @Test(expected = AudiovisualesLogicException.class)
    public void createEquipoTestFail2() throws Exception{
        EquipoEntity result = logic.createEquipo(data.get(0));
        Assert.assertNull("Debe retornar nulo.",result);
    }
    
    @Test
    public void getbyEdifcioTestSucces() throws AudiovisualesLogicException{
        List<EquipoEntity> respuesta = logic.getByEdificio(edificioEntity.getId());
        for (EquipoEntity eq : respuesta){
            Assert.assertTrue("no contiene un equipo.",data.contains(eq));
        }
    }
    
     @Test(expected = AudiovisualesLogicException.class)
    public void getbyEdificioTestfail() throws Exception{
        Assert.assertNull("debería ser nulo", logic.getByEdificio(null));
    }
    
    @Test
    public void updateEquipoTestSucces() throws AudiovisualesLogicException{
        EquipoEntity eq = data.get(0);
        eq.setTipo("Arracacha");
        Assert.assertEquals("deberían ser el mismo.", eq, logic.updateEquipo(eq));
        Assert.assertEquals("deberían tener el mismo tipo.", eq.getTipo(), logic.getEquipo(eq.getId()).getTipo());
    }
    
    @Test(expected = AudiovisualesLogicException.class)
    public void updateEquipoTestFail() throws Exception{
        EquipoEntity equipo = factory.manufacturePojo(EquipoEntity.class);
        Assert.assertNull("no debe retornar nada",logic.updateEquipo(equipo));
    }
    
    @Test
    public void deleteEquipoTestSuccess() throws AudiovisualesLogicException{
        EquipoEntity eq = data.get(1);
        logic.deleteEquipo(eq.getId());
        Assert.assertNull("no debería encontrar nada", logic.getEquipo(eq.getId()));
    }
    
    @Test(expected = AudiovisualesLogicException.class)
    public void deleteEquipoTestFail1() throws Exception{
        Assert.assertNotNull("no debería devolver nada",logic.deleteEquipo(null));
    }
    
    @Test(expected = AudiovisualesLogicException.class)
    public void deleteEquipoTestFail2() throws Exception{
        EquipoEntity equipo = factory.manufacturePojo(EquipoEntity.class);
        Assert.assertNotNull("no debería devolver nada",logic.deleteEquipo(equipo.getId()));
    }
}
