/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author lj.pinzon12
 */
@Entity
public class SancionEntity extends BaseEntity implements Serializable
{
    private String fecha;
    private String estado;
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    
    
        
     /**
     * Obtiene el atributo fecha.
     *
     * @return atributo fecha.
     *
     */
    public String getFecha()
    {
        return fecha;
    }
    
     /**
     * Obtiene el atributo estado.
     *
     * @return atributo estado.
     *
     */
    public String getEstado()
    {
        return estado;
    }
    
     /**
     * Establece el valor del atributo estado.
     *
     * @param estado nuevo valor del atributo
     *
     */
    public void setEstado(String estado) 
    {
        this.estado = estado;
    }
    
     /**
     * Establece el valor del atributo fecha.
     *
     * @param fecha nuevo valor del atributo
     *
     */
    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }
    
     /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setUsuario(UsuarioEntity id) 
    {
        this.usuario = usuario;
    }
    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     *
     */
    public UsuarioEntity getUsuario()
    {
        return usuario;
    }
      
}
