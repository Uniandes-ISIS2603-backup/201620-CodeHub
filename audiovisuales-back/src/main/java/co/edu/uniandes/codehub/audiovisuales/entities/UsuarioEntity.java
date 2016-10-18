/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ln.bello10
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {
    
    public final static int ESTUDIANTE = 1;
    public final static int PROFESOR = 2;
    
    
    @PodamExclude 
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaEntity> reservas = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SancionEntity> sanciones = new ArrayList<>();

    public List<SancionEntity> getSanciones(){
        return sanciones;
    }
    
    public List<ReservaEntity> getReservas(){
        return reservas;
    }
    
    public void setSanciones(List<SancionEntity> sanciones){
        this.sanciones = sanciones;
    }
    
    public void setReservas(List<ReservaEntity> reservas){
        this.reservas = reservas;
    }
    
    private Boolean tieneSancion;
    private int tipo;
    
    public Boolean getTieneSancion(){
        return this.tieneSancion;
    }
    public void setTieneSancion(Boolean sancion){
        this.tieneSancion=sancion;
    }
    public Integer getTipo(){
        return this.tipo;
    }
    public void setTipo(Integer tipo){
        this.tipo=tipo;
    }
}
