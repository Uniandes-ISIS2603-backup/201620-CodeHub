/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;

/**
 * clase que representa un equipo en la base de datos (?)
 * @author c.zambrano10
 */
@Entity
public class EquipoEntity extends BaseEntity implements Serializable{
    
    /**
    * ID del equipo
    */
    private Long id;
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

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setIdEdificio(Long idEdificio) {
        this.idEdificio = idEdificio;
    }

    public Long getId() {
        return id;
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

    public Long getIdEdificio() {
        return idEdificio;
    }
    private Long idEdificio;
}
