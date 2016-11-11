/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import co.edu.uniandes.codehub.audiovisuales.entities.SancionEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lj.pinzon12
 */

@XmlRootElement
public class SancionDetailDTO extends SancionDTO
{
    private UsuarioDTO usuario;
    
//   private List<EquipoDTO> equipos = new ArrayList<>();

    public SancionDetailDTO() {
        
        super();
    }

    /**
     * Constructor que transforma el entity a DTO .
     * @param entity el entity a convertir a DTO
     */
    public SancionDetailDTO(SancionEntity entity) {
        super(entity);
        usuario = new UsuarioDTO(entity.getUsuario());
    }
    
    public UsuarioDTO getUsuario()
    {
        return usuario;
    }
    
    public void setUsuario(UsuarioDTO usuario)
    {
        this.usuario=usuario;
    }    
    /**
     * Convierte el objeto a un entity para ser usado en el back
     * @return el entity analogo al objeto
     */
    @Override
    public SancionEntity toEntity()
    {
        SancionEntity entity =super.toEntity();
        entity.setUsuario(usuario.toEntity());
        
        return entity;
    }
}
