/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ln.bello10
 */
@RunWith(Arquillian.class)
public class UsuarioPersistenceTest {
        
    /**
     *
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();
    
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
        em.createQuery("delete from ReservaEntity").executeUpdate();
        em.createQuery("delete from SancionEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createUsuarioTest(){
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        
        UsuarioEntity result = usuarioPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Test
    public void getUsuariosTest(){
        
        List<UsuarioEntity> usuarios = usuarioPersistence.findAll();
        Assert.assertEquals(data.size(), usuarios.size());
        for(UsuarioEntity usu:usuarios){
            boolean encontrado = false;
            for(UsuarioEntity otro : data){
                if(usu.getId().equals(otro.getId())){
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void getUsuarioEstudiantesTest(){
        List<UsuarioEntity> usuarios = usuarioPersistence.findEstudiantes();
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
    public void getUsuarioTest(){
        
        UsuarioEntity pojo = data.get(0);
        
        UsuarioEntity prueba = usuarioPersistence.find(pojo.getId());
        Assert.assertNotNull(prueba);
        Assert.assertEquals(pojo.getName(), prueba.getName());  
    }

    @Test
    public void getUsuarioByNameTest(){
        UsuarioEntity pojo = data.get(0);
        UsuarioEntity prueba = usuarioPersistence.findByName(pojo.getName());
        Assert.assertNotNull(prueba);
        Assert.assertEquals(pojo.getName(), prueba.getName());
    }
    
    @Test
    public void deleteUsuarioTest(){
        
        UsuarioEntity pojo = data.get(0);
        usuarioPersistence.delete(pojo.getId());
        UsuarioEntity prueba = em.find(UsuarioEntity.class, pojo.getId());
        Assert.assertNull(prueba);
    }
    
    @Test
    public void updateUsuarioTest(){
        
        UsuarioEntity uno = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity dos = factory.manufacturePojo(UsuarioEntity.class);
        
        dos.setId(uno.getId());
        usuarioPersistence.update(dos);
        UsuarioEntity fin = em.find(UsuarioEntity.class, uno.getId());
        
        Assert.assertEquals(dos.getName(), fin.getName());
    }
            
}
