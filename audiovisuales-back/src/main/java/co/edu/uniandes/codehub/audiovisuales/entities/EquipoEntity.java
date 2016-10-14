/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private int estado;
    /**
     * disponibilidad del equipo
     */
    private boolean disponible;
    /**
     * fecha en la que el equipo queda libre
     */
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
 

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setQuedaLibre(Date quedaLibre) {
        this.quedaLibre = quedaLibre;
    }

    public void setEdificio(EdificioEntity idEdificio) {
        this.edificio = idEdificio;
    }

    public String getTipo() {
        return tipo;
    }

    public int getEstado() {
        return estado;
    }

    public boolean isDisponible() {
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
