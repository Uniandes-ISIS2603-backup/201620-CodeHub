/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.codehub.audiovisuales.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class ReservaEntity extends BaseEntity implements Serializable{

    public final static Integer EN_REVISION = 1;
    public final static Integer ACTIVA = 2;
    public final static Integer INACTIVA = 3;
    public final static Integer CANCELADA = 4;
    public final static Integer RECHAZADA = 5;
    
    private Integer estado; //1-Creada sin aprobar, 2-Activa, 3-Inactiva, 4-Cancelada, 5-Rechazada
    
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    
    private Double calificacion;
    
    private Boolean generoSancion;
    
    @PodamExclude
    @ManyToOne
    private EquipoEntity equipo;
    
    
    @PodamExclude
    @ManyToOne
    @JoinColumn(name= "USUARIO_ID")
    private UsuarioEntity usuario;

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public Boolean getGeneroSancion() {
        return generoSancion;
    }

    public void setGeneroSancion(Boolean generoSancion) {
        this.generoSancion = generoSancion;
    }

    public EquipoEntity getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoEntity equipo) {
        this.equipo = equipo;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    
    
    
}
