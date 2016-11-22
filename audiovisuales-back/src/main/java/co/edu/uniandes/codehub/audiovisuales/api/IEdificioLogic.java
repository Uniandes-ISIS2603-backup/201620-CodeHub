/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.api;

import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import java.util.List;

/**
 *
 * @author s.martinez15
 */
public interface IEdificioLogic 
{
    public List<EdificioEntity> getEdificios();

    public EdificioEntity getEdificio(Long id);

    public EdificioEntity getEdificioByName(String name);
    
    public EdificioEntity getEdificioByBloque(String bloque);

    public EdificioEntity createEdificio(EdificioEntity entity) throws AudiovisualesLogicException;

    public EdificioEntity updateEdificio(EdificioEntity entity);

    public void deleteEdificio(Long id);
}
