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
    
    /**
     * Constructor por defecto
     */
    public EdificioDetailDTO() 
    {
       super();       
    }
    
    /**
     * Constructor con entity.
     * @param entity el entity a convertir a DTO
     */
    public EdificioDetailDTO(EdificioEntity entity) {
	super(entity);
        //admin = entity.getAdmin();
        List<EquipoEntity> e = entity.getEquipos();
        for(int i=0;i<e.size();i++)
        {
            
        }
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
}
