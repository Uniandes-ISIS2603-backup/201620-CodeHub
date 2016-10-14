/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.persistence;
import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.codehub.audiovisuales.persistence.EquipoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.*;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Clase test para la persistencia de los equipos
 * @author c.zambrano10
 */
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
    private void clearData() 
    {
        em.createQuery("delete from EquipoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**-----------------------------------
     *          Metodos de Test
     ------------------------------------*/
    
}
