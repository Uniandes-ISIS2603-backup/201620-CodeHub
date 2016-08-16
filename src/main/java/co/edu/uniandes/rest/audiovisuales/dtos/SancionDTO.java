/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

/**
 *
 * @author lj.pinzon12
 */
public class SancionDTO
{
    private Long id;
    private String fecha;
    private String estado;

    /**
     * Constructor por defecto
     */
    public SancionDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador de la sancion
     * @param fecha fecha de la sancion
     * @param estado estado de la sancion
     */
    public SancionDTO(Long id, String fecha, String estado) {
		super();
		this.id = id;
		this.fecha = fecha;
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
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.fecha = estado;
    }
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", estado:"+ getEstado()+", fecha : \"" + getFecha() + "\" }" ;  
    }
}
