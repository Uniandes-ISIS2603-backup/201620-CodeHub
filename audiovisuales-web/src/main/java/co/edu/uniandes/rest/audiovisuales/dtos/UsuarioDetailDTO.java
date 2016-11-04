/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.SancionEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ln.bello10
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO{
    
    // relación  cero o muchos con sanciones 
    private List<SancionDTO> sanciones = new ArrayList<>();
    
    // relación  cero o muchos con reservas
    private List<ReservaDTO> reservas = new ArrayList<>();
    
    public UsuarioDetailDTO(){
        super();
    }
    
    public UsuarioDetailDTO(UsuarioEntity entity){
        super(entity);
        
        if (entity != null) {
            for (SancionEntity sancion : entity.getSanciones()) {
                this.sanciones.add(new SancionDTO(sancion));
            }
            for(ReservaEntity reserva : entity.getReservas()){
                this.reservas.add(new ReservaDTO(reserva));
            }
        }
    }
    
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = super.toEntity();
        
         List<SancionDTO> sanciones = this.getSanciones();
         for(SancionDTO san : this.sanciones){
             entity.getSanciones().add(san.toEntity());
         }
         
         List<ReservaDTO> reservas = this.getReservas();
         for(ReservaDTO res : this.reservas){
             entity.getReservas().add(res.toEntity());
         }
        return entity;
    }
    
     public List<SancionDTO> getSanciones() {
        return sanciones;
    }

    public void setSanciones(List<SancionDTO> sanciones) {
        this.sanciones = sanciones;
    }
    
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
}
