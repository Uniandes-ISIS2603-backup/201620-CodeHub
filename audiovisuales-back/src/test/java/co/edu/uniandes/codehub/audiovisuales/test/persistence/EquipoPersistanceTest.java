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
     * lista con los equipos de prueba
     */
    private final List<EquipoEntity> data = new ArrayList<EquipoEntity>();
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
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
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

    /**
     * Test para asegurarse de que la búsqueda de todos los equipos arroje la lista corecta.
     */
     @Test
    public void testFindAll(){
        List<EquipoEntity> respuesta= equipoPersistence.findAll();
        Assert.assertArrayEquals("La respuesta no corresponde con los datos obtenidos.",data.toArray(),respuesta.toArray());
    }
    /**
     * Test para asegurarse de que la búsqueda de un equipo por ID sea correcta. 
     */
     @Test
    public void testFind(){
        long buscar = data.get(0).getId();
        System.out.println("================================================================");
        System.out.println("id:"+buscar);
        System.out.println("================================================================");
        EquipoEntity respuesta = equipoPersistence.find(buscar);
        Assert.assertEquals("la busqueda no arrojó el elemento correcto",data.get(0),respuesta);
    }
    
    /**
     * Test para asegurarse de que la búsqueda por edificios de los equipos funciona correctamente.
     * Todos los equipos en el Array de data tienen el mismo edificio, esa es la base del test.
     */
    @Test
    public void testFindByEdificio(){
        //caso 1: los equipos tiene un edificio
        List<EquipoEntity> respuesta= equipoPersistence.findByedificio(edificioEntity);
        Assert.assertArrayEquals("La lista de equipos no es la lista de datos",data.toArray(),respuesta.toArray());
        //caso 2: los equipos no tienen un edificio
        edificioEntity.setId(2L);
        respuesta= equipoPersistence.findByedificio(edificioEntity); 
        EquipoEntity[] e = new EquipoEntity[respuesta.size()];
        edificioEntity.setId(1L);
        Assert.assertArrayEquals("Las listas no pueden ser iguales!!!",e, respuesta.toArray());
    }
    
    /**
     * Test para asegurar la creación de un nuevo equipo.
     */
    @Test
    public void testCreate(){
        //se crea un nuevo equipo para probar si se agregar o no un objeto.
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
        entity.setId(55L);
        equipoPersistence.create(entity);
        Assert.assertEquals("no se agrega o se encuentra el equipo.",entity, equipoPersistence.find(55L));
    }
    
    /**
     * Test para asegurar la actualización de un equipo.
     */
     @Test
    public void testUpdate(){
        EquipoEntity prueba= data.get(0);
        prueba.setTipo("aguacate");
        //prueba actual
        Assert.assertEquals("no se está actualizando el equipo.", prueba, equipoPersistence.update(prueba));
        //por si acaso
        Assert.assertEquals("no se está actualizando el equipo.", prueba.getTipo(), equipoPersistence.update(prueba).getTipo());
    }
    
    public void testDelete(){
        EquipoEntity eq = data.get(2);
        data.remove(2);
        equipoPersistence.delete(eq.getId());
        Assert.assertArrayEquals("no se esta eliminando el equipo.",data.toArray(), equipoPersistence.findAll().toArray());
        //otro por si acaso
        Assert.assertNull("no debería poder encontrar nada", equipoPersistence.find(eq.getId()));
    }
}
