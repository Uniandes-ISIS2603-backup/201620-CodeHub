/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.persistence;

import co.edu.uniandes.codehub.audiovisuales.entities.SancionEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import co.edu.uniandes.codehub.audiovisuales.persistence.SancionPersistence;
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
public class SancionPersistenceTest
{
    
    /**-----------------------------------
     *          deployment del test
     ------------------------------------*/
    /**
     * Se crea el .JAR que se va a utilizar para las pruebas de este test
     * @return el .JAR que se utiliza para desplegar la prueba.
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SancionEntity.class.getPackage())
                .addPackage(SancionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**-----------------------------------
     *          atributos del test
     ------------------------------------*/
    @Inject
    private SancionPersistence sancionPersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    /**
     * Usuario "dueño" de las sanciones en la prueba
     */
    UsuarioEntity usuarioEntity;
    /**
     * Estado de las sanciones de prueba
     */
    String estado;
        /**
     * Fecha de lassanciones de prueba
     */
    String fecha;

    /**
     * Lista con las sanciones de prueba
     */
    private List<SancionEntity> data = new ArrayList<SancionEntity>();
    /**-----------------------------------
     *     Metodos de configuración
     ------------------------------------*/
    
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
    private void clearData() {
        em.createQuery("delete from SancionEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        //usuario de pruebas.
        usuarioEntity = factory.manufacturePojo(UsuarioEntity.class);
        usuarioEntity.setId(1L);
        em.persist(usuarioEntity);

        //todos los quipos tienen las mismas reservas
        for (int i = 0; i < 3; i++) {
            SancionEntity entity = factory.manufacturePojo(SancionEntity.class);
            //se agrega el edificio.
            entity.setUsuario(usuarioEntity);
            //se agregan las reservas.
            entity.setEstado("vigente"+i);
            entity.setFecha("1i/0i/2016");
            em.persist(entity);
            data.add(entity);
        }
    }
    /**-----------------------------------
     *          Metodos de Test
     ------------------------------------*/
    
    /**
     * Test para asegurarse de que la búsqueda de todos los equipos arroje la lista corecta.
     */
    @Test
    public void testFindAll(){
        List<SancionEntity> respuesta= sancionPersistence.findAll();
        
        Assert.assertEquals("la respuesta no tiene el mismo tamaño", respuesta.size(), data.size());

        for(SancionEntity s1 : respuesta) {
            boolean encontrado = false;
            for (SancionEntity s2: data) {
                if (s1.getId().equals(s2.getId())) {
                    encontrado = true;
                    break;
                }
            }
            
            Assert.assertTrue("no encuentra el elemento " + s1, encontrado);
        }
        
        // Assert.assertArrayEquals("La respuesta no corresponde con los datos obtenidos.",respuesta.toArray(),data.toArray());
    }
    /**
     * Test para asegurarse de que la búsqueda de un equipo por ID sea correcta. 
     */
    @Test
    public void testFind(){
        long buscar = data.get(0).getId();
        SancionEntity respuesta = sancionPersistence.find(buscar);
        Assert.assertEquals("la busqueda no arrojó el elemento correcto",respuesta,data.get(0));
    }
    
    /**
     * Test para asegurarse de que la búsqueda por edificios de los equipos funciona correctamente.
     * Todos los equipos en el Array de data tienen el mismo edificio, esa es la base del test.
     */
    @Test
    public void testFindByFecha(){
        //caso 1:
        String respuesta= data.get(0).getFecha();
        Assert.assertEquals("La lista de equipos no es la lista de datos",respuesta,data.get(0));
        
    }
}
