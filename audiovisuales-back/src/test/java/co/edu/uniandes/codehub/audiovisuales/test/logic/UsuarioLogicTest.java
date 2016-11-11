/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.logic;

import co.edu.uniandes.codehub.audiovisuales.api.IUsuarioLogic;
import co.edu.uniandes.codehub.audiovisuales.ejbs.UsuarioLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
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
 * @author ln.bello10
 */
@RunWith(Arquillian.class)
public class UsuarioLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IUsuarioLogic logic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(IUsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
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
        em.createQuery("delete from SancionEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 5; i++) {            
           UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createUsuarioTestSuccess() throws AudiovisualesLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        
        UsuarioEntity result = logic.createUsuario(newEntity);
        Assert.assertNotNull(result);

        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test(expected = AudiovisualesLogicException.class)
    public void createUsuarioTestFail() throws Exception {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setLogin(data.get(0).getLogin());
        
        UsuarioEntity result = logic.createUsuario(newEntity);
        Assert.assertNull(result);
    }
    
    @Test
    public void getUsuariosTest() {
        List<UsuarioEntity> list = logic.getUsuarios();
        Assert.assertEquals(data.size(), list.size());
        for (UsuarioEntity entity : list) 
        {
            boolean found = false;
            for (UsuarioEntity dataEntity : data) {
                if (entity.getId().equals(dataEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getUsuarioEstudiantesTest(){
        List<UsuarioEntity> usuarios = logic.getEstudiantes();
        Assert.assertNotNull(usuarios);
        boolean encontrado = true;
        for(UsuarioEntity usu: usuarios){
            
            if(usu.getTipo()==2){
                encontrado = false;
            }
        }
        Assert.assertTrue(encontrado);
    }
    
    @Test
    public void getUsuarioProfesoresTest(){
        List<UsuarioEntity> usuarios = logic.getProfesores();
        Assert.assertNotNull(usuarios);
        boolean encontrado = true;
        for(UsuarioEntity usu: usuarios){
            
            if(usu.getTipo()==1){
                encontrado = false;
            }
        }
        Assert.assertTrue(encontrado);
    }
    
    @Test
    public void getUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity resultEntity = logic.getUsuario(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void getUsuarioByNameTest() {
        UsuarioEntity entity = data.get(1);
        UsuarioEntity resultEntity = logic.getUsuarioByName(entity.getName());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void updateUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity updatedEntity = factory.manufacturePojo(UsuarioEntity.class);

        updatedEntity.setId(entity.getId());

        logic.updateUsuario(updatedEntity);

        UsuarioEntity resultEntity = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(updatedEntity.getName(), resultEntity.getName());
        Assert.assertEquals(updatedEntity.getId(), resultEntity.getId());
    }
    
    @Test
    public void deleteUsuarioTest() {
        UsuarioEntity entity = data.get(1);
        logic.deleteUsuario(entity.getId());
        UsuarioEntity resultEntity = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(resultEntity);
    }
    
    @Test
    public void loginTest() 
    {
        UsuarioEntity entity = data.get(1);
        UsuarioEntity resultEntity = logic.login(entity.getLogin(),entity.getPassword());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

}
