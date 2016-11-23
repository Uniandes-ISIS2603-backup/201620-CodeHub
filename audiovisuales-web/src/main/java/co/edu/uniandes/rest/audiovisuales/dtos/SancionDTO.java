/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import co.edu.uniandes.codehub.audiovisuales.entities.SancionEntity;

/**
 *
 * @author lj.pinzon12
 */
public class SancionDTO
{
    public final static Integer ACTIVO = 1;
    public final static Integer CANCELADO = 2;
    
    
    protected Long id;
    protected String fecha;
    protected Integer noDias;
    protected Integer estado;

    /**
     * Constructor por defecto
     */
    public SancionDTO() {
        
	}
    
    public SancionDTO(SancionEntity entity) {

        this.id = entity.getId();
	this.fecha = entity.getFecha();
        this.noDias = entity.getNoDias();
        this.estado = entity.getEstado();
    }
    

    /**
     * Constructor con parámetros.
     * @param id identificador de la sancion
     * @param fecha fecha de la sancion
     * @param noDias
     * @param estado estado de la sancion
     * @param idUsuario
     */
    public SancionDTO(Long id, String fecha, Integer noDias, Integer estado, Long idUsuario) {
		super();
		this.id = id;
		this.fecha = fecha;
                this.noDias = noDias;
                this.estado = estado;
              //  this.idUsuario = idUsuario;
	}

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
        /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        if(estado==ACTIVO || estado==CANCELADO)
            this.estado = estado;
    }
    
    public void setNoDias(Integer noDias)
    {
        this.noDias = noDias;
    }
    
    public Integer getNoDias()
    {
        return noDias;
    }
    
    /**
     * @return the id
     */
    //public Long getIdUsuario() {
      //  return idUsuario;
    //}

    /**
     * @param id the id to set
     */
    //public void setIdUsuario(Long idUsuario) {
      //  this.idUsuario = idUsuario;
    //}
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", estado:"+ getEstado()+", fecha : \"" + getFecha() + "\" }" ;  
    }
    
    /**
     * Convierte el objeto a un entity para ser usado en el back
     * @return el entity analogo al objeto
     */
    public SancionEntity toEntity()
    {
        SancionEntity entity = new SancionEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setNoDias(noDias);
        return entity;
    }
}
