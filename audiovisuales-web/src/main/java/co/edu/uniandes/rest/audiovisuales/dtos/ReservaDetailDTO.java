/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.rest.audiovisuales.dtos;

import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author o.sabogal10
 */
@XmlRootElement
public class ReservaDetailDTO extends ReservaDTO {
   
    private UsuarioDTO usuario;
    private EquipoDTO equipo;

    public ReservaDetailDTO()
    {
        super();
    }
    
    public ReservaDetailDTO(ReservaEntity entity)
    {
        super(entity);
        
        equipo = new EquipoDTO(entity.getEquipo());
        usuario = new UsuarioDTO(entity.getUsuario());
    }
    
    public EquipoDTO getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoDTO equipo) {
        this.equipo = equipo;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public ReservaEntity toEntity()
    {
        ReservaEntity reserva = super.toEntity();
        reserva.setEquipo(equipo.toEntity());
        reserva.setUsuario(usuario.toEntity());
        
        return reserva;
    }

}
