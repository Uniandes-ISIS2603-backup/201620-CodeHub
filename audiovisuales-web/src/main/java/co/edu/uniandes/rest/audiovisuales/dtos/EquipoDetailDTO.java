/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author c.zambrano10
 */
public class EquipoDetailDTO extends EquipoDTO{

    /*-----------------------
            Atributos.
    -------------------------*/
    private EdificioDTO edificio;
    
    private List<ReservaDTO> reservas= new ArrayList<ReservaDTO>();
    
    /*-------------------------
            Constructores.
    ---------------------------*/
    /**
     * Constructor para cambio de DTO a Entity.
     * @param entity el entity de base. entity!=null.
     */
    public EquipoDetailDTO(EquipoEntity entity) {
        super(entity);
        edificio = new EdificioDTO(entity.getEdificio());
        for(ReservaEntity reserva: entity.getReservas()){
            //ReservaDTO r = new ReservaDTO(reserva);
            //reservas.add(r);
        }
    }

    /*--------------------
            Metodos.
    --------------------*/ 

    public EdificioDTO getEdificio() {
        return edificio;
    }

    public void setEdificio(EdificioDTO edificio) {
        this.edificio = edificio;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
    private EquipoEntity toEntity(){
        EquipoEntity equipo = new EquipoEntity();
        equipo.setId(id);
        equipo.setTipo(tipo);
        equipo.setEstado(estado);
        equipo.setDisponible(disponible);
        equipo.setQuedaLibre(quedaLibre);
        equipo.setEdificio(edificio.toEntity());
        
        List<ReservaEntity> res;
        res = new ArrayList<ReservaEntity>();
        
        for(ReservaDTO reserva: reservas){
            //ReservaEntity r = reserva.toEntity();
            //res.add(r);
        }
        equipo.setReservas(res);
        return equipo;
    }
    
}
