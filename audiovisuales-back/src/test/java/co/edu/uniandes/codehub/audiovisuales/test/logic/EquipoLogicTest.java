/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.test.logic;

import co.edu.uniandes.codehub.audiovisuales.api.IEquipoLogic;
import javax.inject.Inject;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * test para la persistencia de equipos.
 * @author c.zambrano10
 */
@RunWith(Arquillian.class)
public class EquipoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IEquipoLogic Logic;
    
    
}
