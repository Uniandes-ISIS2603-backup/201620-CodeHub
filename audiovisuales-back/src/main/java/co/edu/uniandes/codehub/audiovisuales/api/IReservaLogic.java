/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.api;

import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;
import java.util.List;

/**
 *
 * @author o.sabogal10
 */
public interface IReservaLogic {
    
    public List<ReservaEntity> getReservas();
    
    public ReservaEntity getReserva(Long id);
    
    public List<ReservaEntity> getReservasByUsuario(Long id);
    
    public ReservaEntity createReserva(ReservaEntity entity);
    
    public ReservaEntity updateReserva(ReservaEntity entity);
    
    public void deleteReserva(Long id);
    
}
