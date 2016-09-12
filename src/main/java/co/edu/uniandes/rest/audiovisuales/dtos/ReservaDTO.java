/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *Objeto de transferencia de datos de Reservas.
 * @author orlando
 */
public class ReservaDTO 
{
    public final static int SIN_APROBAR = 1;
    public final static int ACTIVA = 2;
    public final static int INACTIVA = 3;
    public final static int CANCELADA = 4;
    public final static int RECHAZADA = 5;
    
    
    
    private int estado; //1-Creada sin aprobar, 2-Activa, 3-Inactiva, 4-Cancelada, 5-Rechazada
    private Date fecha;
    private Double calificacion;
    private Boolean generoSancion;
    private String nombreEdificio;
    private Long id;
    
    private List<EquipoDTO> equipos;
    
    private Long idUsuario;
    
    public ReservaDTO()
    {
        
    }

    public ReservaDTO( Date fecha,  String nombreEdificio, Long id, Long idUsuario, List<EquipoDTO> equipos) {
        this.estado = 1;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.generoSancion = false;
        this.nombreEdificio = nombreEdificio;
        this.id = id;
        this.idUsuario = idUsuario;
        this.equipos = equipos;
    }

    public int getEstado() {
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
    
    public void update(int pEstado,Date pFecha,Double pCalificacion, Boolean pSancion,String pEdificio, Long pId)
    {
        estado = pEstado;
        fecha = pFecha;
        calificacion = pCalificacion;
        generoSancion = pSancion;
        nombreEdificio = pEdificio;
        id = pId;
    }

    public void setEstado(int estado) {
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
    
    /**
     * @return the id
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param id the id to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public List<EquipoDTO> getEquipos()
    {
        return equipos;
    }
    
    public void setEquipos(List<EquipoDTO> equipos)
    {
        this.equipos = equipos;
    }
    
    public String toString()
    {
        return "{ id : " + getId() + ", name : \"" + getNombreEdificio()+ "\" }" ;
    }
    

}
