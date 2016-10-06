/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import java.util.Date;
import java.util.List;

/**
 *Objeto de transferencia de datos de Reservas.
 * @author orlando
 */
public class ReservaDTO 
{
    public final static int EN_REVISION = 1;
    public final static int ACTIVA = 2;
    public final static int INACTIVA = 3;
    public final static int CANCELADA = 4;
    public final static int RECHAZADA = 5;
    
    
    
    private int estado; //1-Creada sin aprobar, 2-Activa, 3-Inactiva, 4-Cancelada, 5-Rechazada
    private Date fechaInicial;
    private Date fechaFinal;
    private Double calificacion;
    private Boolean generoSancion;
    private Long edificioId;
    private Long id;
    
    private Long idEquipo;
    
    private Long idUsuario;
    
    public ReservaDTO()
    {
        
    }

    public ReservaDTO( Date fechaInicial, Date fechaFinal,  Long edificioId, Long id, Long idUsuario, Long idEquipo) {
        this.estado = 1;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.generoSancion = false;
        this.edificioId = edificioId;
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEquipo = idEquipo;
        this.calificacion = 0.0;
    }

    public int getEstado() {
        return estado;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }
    
    public Date getFechaFinal() {
        return fechaFinal;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public Boolean getGeneroSancion() {
        return generoSancion;
    }

    public Long getEdificioId() {
        return edificioId;
    }

    public Long getId() {
        return id;
    }
    
    public void update(int pEstado,Date pFechaInicial, Date pFechaFinal ,Double pCalificacion, Boolean pSancion,Long pEdificio, Long pId)
    {
        estado = pEstado;
        fechaInicial = pFechaInicial;
        fechaFinal = pFechaFinal;
        calificacion = pCalificacion;
        generoSancion = pSancion;
        edificioId = pEdificio;
        id = pId;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setFechaInicial(Date fecha) {
        this.fechaInicial = fecha;
    }
    
    public void setFechaFinal(Date fecha) {
        this.fechaFinal = fecha;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public void setGeneroSancion(Boolean generoSancion) {
        this.generoSancion = generoSancion;
    }

    public void setNombreEdificio(long edificioId) {
        this.edificioId = edificioId;
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
    
    public Long getEquipos()
    {
        return idEquipo;
    }
    
    public void setEquipos(Long idEquipo)
    {
        this.idEquipo = idEquipo;
    }
    
    public String toString()
    {
        return "{ id : " + getId() + ", name : \"" + getEdificioId()+ "\" }" ;
    }
    

}