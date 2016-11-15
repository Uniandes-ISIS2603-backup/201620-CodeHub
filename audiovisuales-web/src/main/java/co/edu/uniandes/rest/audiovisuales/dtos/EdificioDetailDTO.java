/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import co.edu.uniandes.codehub.audiovisuales.entities.EdificioEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.EquipoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.martinez15
 */
@XmlRootElement
public class EdificioDetailDTO extends EdificioDTO
{
    
    private AdminDTO admin;
    
    private List<EquipoDTO> equipos = new ArrayList<>();
    
    public EdificioDetailDTO()
    {
        
    }
    
    /**
     * Constructor que transforma el entity a DTO .
     * @param entity el entity a convertir a DTO
     */
    public EdificioDetailDTO(EdificioEntity entity) {
	super(entity);
        
        List<EquipoEntity> e = entity.getEquipos();
        for(int i=0;i<e.size();i++)
        {
            EquipoEntity equipo = e.get(i);
            equipos.add(new EquipoDTO(equipo));
        }
        if(entity.getAdmin()!=null)
            admin = new AdminDTO(entity.getAdmin());
        else
            admin = new AdminDTO();
    }
    
    public AdminDTO getAdmin()
    {
        return admin;
    }
    
    public void setAdmin(AdminDTO admin)
    {
        this.admin=admin;
    }
    
    public List<EquipoDTO> getEquipos()
    {
        return equipos;
    }
    
    public void addEquipo(EquipoDTO equipo)
    {
       if(equipo!=null)
        equipos.add(equipo);
    }
    
    /**
     * Convierte el objeto a un entity para ser usado en el back
     * @return el entity analogo al objeto
     */
    @Override
    public EdificioEntity toEntity()
    {
        EdificioEntity entity = new EdificioEntity();
        entity.setId(id);
        entity.setName(nombre);
        entity.setBloque(bloque);
        entity.setAdmin(admin.toEntity());
        for(int i=0;i<equipos.size();i++)
        {
            EquipoDTO e = equipos.get(i);
            entity.addEquipo(e.toEntity());
        }
        return entity;
    }
}
