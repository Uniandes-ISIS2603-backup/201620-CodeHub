/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.*;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * clase que representa un equipo en la base de datos (?)
 * @author c.zambrano10
 */
@Entity
public class EquipoEntity extends BaseEntity implements Serializable{
    /**------------------
     *      Atributos
     -------------------*/
    
    /**
     * Tipo del equipo
     */
    private String tipo;
    /**
     * Estado del equipo
     */
    private Integer estado;
    /**
     * disponibilidad del equipo
     */
    private Boolean disponible;
    /**
     * fecha en la que el equipo queda libre
     */
    @Temporal(TemporalType.DATE)
    private Date quedaLibre;
    /**
     * Edificio al cual pertenece el equipo
     */
    @PodamExclude
    @ManyToOne
    private EdificioEntity edificio;
    /**
     * Reservas del equipo.
     */
    @PodamExclude
    @OneToMany
    private ArrayList<ReservaEntity> reservas;

    
     /**------------------
     *      Metodos
     -------------------*/
    /**
     * cambia el tipo del equipo
     * @param tipo el nuevo tipo del equipo. tipo!=null && tipo!=""
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Cambia el estado del equipo
     * @param estado 
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * Cambia la disponibilidad
     * @param disponible 
     */
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * cambia la fecha en que queda libre el equipo
     * @param quedaLibre 
     */
    public void setQuedaLibre(Date quedaLibre) {
        this.quedaLibre = quedaLibre;
    }

    /**
     * cambia el edificio del equipo
     * @param idEdificio 
     */
    public void setEdificio(EdificioEntity idEdificio) {
        this.edificio = idEdificio;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getEstado() {
        return estado;
    }

    public Boolean isDisponible() {
        return disponible;
    }

    public Date getQuedaLibre() {
        return quedaLibre;
    }

    public EdificioEntity getEdificio() {
        return edificio;
    }

    public ArrayList<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<ReservaEntity> reservas) {
        this.reservas = reservas;
    }  
}
