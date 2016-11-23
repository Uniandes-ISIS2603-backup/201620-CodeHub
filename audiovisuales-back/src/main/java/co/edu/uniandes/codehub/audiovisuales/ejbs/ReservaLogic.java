/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.codehub.audiovisuales.ejbs;

import co.edu.uniandes.codehub.audiovisuales.api.IReservaLogic;
import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.codehub.audiovisuales.persistence.ReservaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * 
 * @author o.sabogal10
 */
@Stateless
public class ReservaLogic implements IReservaLogic{
    
    @Inject
    private ReservaPersistence persistence;

    @Override
    public List<ReservaEntity> getReservas() {
        return persistence.findAll();  }

    @Override
    public ReservaEntity getReserva(Long id) {
       return persistence.find(id);
    }

    @Override
    public List<ReservaEntity> getReservasByUsuario(Long id) {
       return persistence.findByIdUsuario(id);
    }
    
    @Override
    public List<ReservaEntity> getReservasPendientesEdificio(Long idEdificio) {
       return persistence.findPendientesPorEdificio(idEdificio);
    }

    @Override
    public ReservaEntity createReserva(ReservaEntity entity) throws AudiovisualesLogicException{
        ReservaEntity existe = getReserva(entity.getId());
        if (existe != null)
        {
            throw new AudiovisualesLogicException("Ya existe una reserva con ese id");
        }
        else
        {
            persistence.create(entity);
        }
        return entity;
    }

    @Override
    public ReservaEntity updateReserva(ReservaEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteReserva(Long id) {
        persistence.delete(id);
    }


}
