/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import java.util.Date;

/**
 *Objeto de transferencia de datos de Reservas.
 * @author orlando
 */
public class ReservaDTO 
{
    private Boolean estado;
    private Date fecha;
    private Double calificacion;
    private Boolean generoSancion;
    private String nombreEdificio;
    private Long id;
    
    public ReservaDTO()
    {
        
    }

    public ReservaDTO( Date fecha, Double calificacion,  String nombreEdificio, Long id) {
        this.estado = true;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.generoSancion = false;
        this.nombreEdificio = nombreEdificio;
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public Boolean getGeneroSancion() {
        return generoSancion;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public Long getId() {
        return id;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public void setGeneroSancion(Boolean generoSancion) {
        this.generoSancion = generoSancion;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String toString()
    {
        return "{ id : " + getId() + ", name : \"" + getNombreEdificio()+ "\" }" ;
    }
    

}
