/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.api;

import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import java.util.List;

/**
 *  Interfaz de la lógica de equipos.
 * @author c.zambrano10
 */
public interface IEquipoLogic {
    
    /**
     * La lista de todos los equipos en la base de datos.
     * @return lista de todos los equipos.
     */
    public List<EquipoEntity> getEquipos();
    
    /**
     * busca un equipo según su ID.
     * @param id id!=null.
     * @return el equipo con identificador=id. null si el equipo no existe.
     */
    public EquipoEntity getEquipo(Long id);
    
    /**
     * busca todos los equipos que pertenecen a un edifcio.
     * @param edifcio el edificio al que pertenecen los equipos. edificio!=null.
     * @return la lista con todos los equipos que pertenecen al edificio.
     * @throws co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException
     */
    public List<EquipoEntity> getByEdificio(EdificioEntity edifcio) throws AudiovisualesLogicException;
    
    /**
     * agrega un equipo a la persistencia.
     * @param equipo el nuevo equipo a agregar. equipo!=null.
     * @return el equipo agregado en caso de no ocurrir ninguna violacion a la lógica.
     * @throws co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException
     */
    public EquipoEntity createEquipo(EquipoEntity equipo)throws AudiovisualesLogicException;
    
    /**
     * actualiza un equipo en la persistencia.
     * @param equipo el equipo con los datos nuevos a actualizar. equipo!=null.
     * @return el equipo actualizado en caso de no ocurrir ninguna violacion a la lógica.
     * @throws co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException
     */
    public EquipoEntity updateEquipo (EquipoEntity equipo)throws AudiovisualesLogicException;
    
    /**
     * Elimina un equipo de la persistencia.
     * @param id el id del equipo a eliminar. id!=null.
     * @return el id del equipo ya eliminado.
     * @throws co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException
     */
    public Long deleteEquipo (Long id) throws AudiovisualesLogicException;
}
