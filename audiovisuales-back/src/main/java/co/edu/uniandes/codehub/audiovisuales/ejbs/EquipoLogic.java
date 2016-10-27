/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.ejbs;

import co.edu.uniandes.codehub.audiovisuales.api.IEquipoLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
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
     * @param edifcio el edificio al que pertenecen los edificios. edificio != null.
     * @return la lista con los equipos que pertenecen al edificio dado.
     */
    @Override
    public List<EquipoEntity> getByEdificio(EdificioEntity edifcio) {
        return persistencia.findByedificio(edifcio);
    }

    @Override
    public EquipoEntity createEquipo(EquipoEntity equipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EquipoEntity updateEquipo(EquipoEntity equipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteEquipo(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}