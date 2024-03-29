/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.entities;

//import co.edu.uniandes.rest.audiovisuales.dtos.UsuarioDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author lj.pinzon12
 */
@Entity
public class SancionEntity extends BaseEntity implements Serializable
{
    private Integer noDias;
    private String fecha;
    private Integer estado;
    
    @PodamExclude
    @ManyToOne
    @JoinColumn(name= "USUARIO_ID")
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
    public Integer getEstado()
    {
        return estado;
    }
    
     /**
     * Establece el valor del atributo estado.
     *
     * @param estado nuevo valor del atributo
     *
     */
    public void setEstado(Integer estado) 
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
    public void setUsuario(UsuarioEntity usuario) 
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
/**
    public void setUsuario(UsuarioDTO usuario)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUsuario(UsuarioDTO usuario)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     **/ 
    
    public void setNoDias(Integer noDias)
    {
        this.noDias = noDias;
    }
    
    public Integer getNoDias()
    {
        return noDias;
    }
}
