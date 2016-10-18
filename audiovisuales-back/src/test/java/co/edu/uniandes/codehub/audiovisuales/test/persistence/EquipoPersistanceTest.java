/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.persistence;
import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.codehub.audiovisuales.persistence.EquipoPersistence;
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

import org.junit.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Clase test para la persistencia de los equipos
 * @author c.zambrano10
 */
@RunWith(Arquillian.class)
public class EquipoPersistanceTest {

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
                .addPackage(EquipoEntity.class.getPackage())
                .addPackage(EquipoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**-----------------------------------
     *          atributos del test
     ------------------------------------*/
    @Inject
    private EquipoPersistence equipoPersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    /**
     * edifcio "dueño" de los equipos en la prueba
     */
    EdificioEntity edificioEntity;
    /**
     * reservas "hijas" de los equipos de prueba
     */
    ArrayList<ReservaEntity> reservas;

    /**
     * lista co los equipos de prueba
     */
    private List<EquipoEntity> data = new ArrayList<EquipoEntity>();
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
        em.createQuery("delete from EquipoEntity").executeUpdate();
        em.createQuery("delete from EdificioEntity").executeUpdate();
        em.createQuery("delete from ReservaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        //edificio de pruebas.
        edificioEntity = factory.manufacturePojo(EdificioEntity.class);
        edificioEntity.setId(1L);
        em.persist(edificioEntity);
        //reservas de pruevas
        for(int  i=0; i<3;i++){
            ReservaEntity res = factory.manufacturePojo(ReservaEntity.class);
            res.setId((Long)(long)i); //esto es valido? :v
            em.persist(res);
            reservas.add(res);
        }
        
        //todos los quipos tienen las mismas reservas
        for (int i = 0; i < 3; i++) {
            EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
            //se agrega el edificio.
            entity.setEdificio(edificioEntity);
            //se agregan las reservas.
            entity.setReservas(reservas);
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
        List<EquipoEntity> respuesta= equipoPersistence.findAll();
        Assert.assertArrayEquals("La respuesta no corresponde con los datos obtenidos.",respuesta.toArray(),data.toArray());
    }
    /**
     * Test para asegurarse de que la búsqueda de un equipo por ID sea correcta. 
     */
    @Test
    public void testFind(){
        long buscar = data.get(0).getId();
        EquipoEntity respuesta = equipoPersistence.find(buscar);
        Assert.assertEquals("la busqueda no arrojó el elemento correcto",respuesta,data.get(0));
    }
    
    /**
     * Test para asegurarse de que la búsqueda por edificios de los equipos funciona correctamente.
     * Todos los equipos en el Array de data tienen el mismo edificio, esa es la base del test.
     */
    @Test
    public void testFindByEdificio(){
        //caso 1:
        List<EquipoEntity> respuesta= equipoPersistence.findByedificio(edificioEntity);
        Assert.assertArrayEquals("La lista de equipos no es la lista de datos",respuesta.toArray(),data.toArray());
        //caso 2:
        edificioEntity.setId(2L);
        respuesta= equipoPersistence.findByedificio(edificioEntity); 
        EquipoEntity[] e = new EquipoEntity[respuesta.size()];
        edificioEntity.setId(1L);
        Assert.assertArrayEquals("Las listas no pueden ser iguales!!!",respuesta.toArray(), e);
    }
}
