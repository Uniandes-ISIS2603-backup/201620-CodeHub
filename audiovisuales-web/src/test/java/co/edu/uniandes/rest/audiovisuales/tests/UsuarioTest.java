/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.tests;

import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import co.edu.uniandes.rest.audiovisuales.dtos.UsuarioDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.UsuarioDetailDTO;
import co.edu.uniandes.rest.audiovisuales.resources.UsuarioResource;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.AfterClass;
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
public class UsuarioTest {
    
//    private final int Ok = Response.Status.OK.getStatusCode();
//    private final int Created = 200; // Status.CREATED.getStatusCode();
//    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();
//    private final String usuarioPath = "usuarios";
//    private final static List<UsuarioEntity> usuarioList = new ArrayList<>();
//    private WebTarget target;
//    private final String apiPath = "api";
//    
//    @ArquillianResource
//    private URL deploymentURL;
//    
//    @Deployment
//    public static WebArchive createDeployment() {
//        return ShrinkWrap.create(WebArchive.class)
//                // Se agrega las dependencias
//                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
//                        .importRuntimeDependencies().resolve()
//                        .withTransitivity().asFile())
//                // Se agregan los compilados de los paquetes de servicios
//                .addPackage(UsuarioResource.class.getPackage())
//                // El archivo que contiene la configuracion a la base de datos.
//                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
//                // El archivo beans.xml es necesario para injeccion de dependencias.
//                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
//               
//                // El archivo web.xml es necesario para el despliegue de los servlets
//                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
//    }
//    
//    private WebTarget createWebTarget() {
//        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
//    }
//    
//    @PersistenceContext
//    private EntityManager em;
//    
//    @Inject
//    private UserTransaction utx;
//    
//    private void clearData() {
//        
//        em.createQuery("delete from UsuarioEntity").executeUpdate();    
//        usuarioList.clear();
//    }
//    
//    public void insertData() {
//        PodamFactory factory = new PodamFactoryImpl();
//        for (int i = 0; i < 5; i++) {            
//            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
//            usuario.setId(i + 1L);
//            em.persist(usuario);
//            usuarioList.add(usuario);
//        }
//    }
//    
//    
//    @Before
//    public void setUpTest() {
//        
//        try {
//            utx.begin();
//            clearData();
//            insertData();
//            utx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                utx.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//        target = createWebTarget().path(usuarioPath);
//    }
//    
//    @Test
//    public void getUsuarioById(){
//        UsuarioDetailDTO usuario = target.path(usuarioList.get(0).getId().toString())
//                .request().get(UsuarioDetailDTO.class);
//        
//        Assert.assertEquals(usuario.getId(), usuarioList.get(0).getId());
//        Assert.assertEquals(usuario.getName(), usuarioList.get(0).getName());
//    }
// 
//    @Test
//    public void deleteUsuarioTest(){
//        UsuarioDTO eliminar = new UsuarioDTO();
//        Response response = target.path(eliminar.getId().toString()).request().delete();
//        
//        Assert.assertEquals(OkWithoutContent,response.getStatus());
//    }
}
