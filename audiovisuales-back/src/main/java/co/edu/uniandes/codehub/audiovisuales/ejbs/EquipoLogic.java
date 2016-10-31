/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.ejbs;

import co.edu.uniandes.codehub.audiovisuales.api.IEquipoLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.codehub.audiovisuales.persistence.EquipoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Logica de los equipos.
 * @author c.zambrano10
 */
@Stateless
public class EquipoLogic implements IEquipoLogic{
    
    /**
     * persistencia de los equipos.
     */
    @Inject
    private EquipoPersistence persistencia;

    /**
     * retorna todos los equipos en la persistencia.
     * @return lista con todos los equipos en la persistencia.
     */
    @Override
    public List<EquipoEntity> getEquipos() {
        return persistencia.findAll();
    }

    /**
     * retorna el equipo con el id que llega por parámetro.
     * @param id el identificador del equipo. id!=null.
     * @return el equipo con ese id. null en caso de que no exista un equipo con ese id.
     */
    @Override
    public EquipoEntity getEquipo(Long id) {
        return persistencia.find(id);
    }

    /**
     * busca los equipos que pertenecen al edificio
     * @param idEdificio el id del edificio al que pertenecen los edificios. idEdificio != null.
     * @return la lista con los equipos que pertenecen al edificio dado.
     */
    @Override
    public List<EquipoEntity> getByEdificio(Long idEdificio)throws AudiovisualesLogicException {
        if(idEdificio == null){
            throw new AudiovisualesLogicException("El edificio no puede ser nulo. ");
        }
        return persistencia.findByedificio(idEdificio);
    }

    /**
     * Agrega un nuevo equipo a la persistencia.
     * @param equipo el equipo a agregar.
     * @return el equipo ya agregado.
     * @throws AudiovisualesLogicException si equipo== null o equipo.id==null.
     */
    @Override
    public EquipoEntity createEquipo(EquipoEntity equipo) throws AudiovisualesLogicException{
        if(equipo ==null){
            throw new AudiovisualesLogicException("El equipo enviado no es correcto, debe cumplir equipo!=null");
        }else if(equipo.getId() == null){
            throw new AudiovisualesLogicException("El equipo enviado no es correcto, debe cumplir equipo.id!=null");
        }else if(persistencia.find(equipo.getId())!=null){
            throw new AudiovisualesLogicException("Ya existe un equipo con ese ID. ");
        }
        
        return persistencia.create(equipo);
    }

    /**
     * Actualiza un equipo en la persistencia
     * @param equipo el equipo con los datos a modificar. equipo!=null.
     * @return el equipo ya actualizado.
     * @throws AudiovisualesLogicException si el id del equipo a actualizar no existe en la persistencia.
     */
    @Override
    public EquipoEntity updateEquipo(EquipoEntity equipo)throws AudiovisualesLogicException{
        EquipoEntity eq = persistencia.find(equipo.getId());
        if(eq ==null){
            throw new AudiovisualesLogicException("El equipo a actualizar no existe en la persistencia.");
        }
        persistencia.update(equipo);
        return equipo;
    }

    /**
     * elimina un equipo con el id que llega por parámetro.
     * @param id el id del equipo a eliminar.
     * @throws AudiovisualesLogicException si no existe un equipo con ese id en la persistencia o si el id==null.
     */
    @Override
    public Long deleteEquipo(Long id)throws AudiovisualesLogicException{
        if(id==null){
            throw new AudiovisualesLogicException("El id no puede ser nulo. ");
        }
        EquipoEntity eq = persistencia.find(id);
        if(eq ==null){
            throw new AudiovisualesLogicException("El equipo a eliminar no existe en la persistencia.");
        }
        persistencia.delete(id);
        return id;
    }
    
}