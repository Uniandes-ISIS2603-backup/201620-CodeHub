/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import co.edu.uniandes.codehub.audiovisuales.entities.ReservaEntity;

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
    private String fechaInicial;
    private String fechaFinal;
    private Double calificacion;
    private Boolean generoSancion;   
    private Long id;

    
    public ReservaDTO()
    {
        
    }

    public ReservaDTO( String fechaInicial, String fechaFinal,  Long edificioId, Long id, Long idUsuario, Long idEquipo) {
        this.estado = 1;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.generoSancion = false;
        
        this.id = id;
        this.calificacion = 0.0;
    }
    
    public ReservaDTO (ReservaEntity entity)
    {
        if (entity!=null)
        {
        this.estado = entity.getEstado();
        this.fechaInicial = entity.getFechaInicial();
        this.fechaFinal = entity.getFechaFinal();
        this.generoSancion = entity.getGeneroSancion();
        this.id = entity.getId();
        this.calificacion = entity.getCalificacion();
        }
    }
    
    public ReservaEntity toEntity()
    {
        ReservaEntity reserva = new ReservaEntity();
        reserva.setId(this.getId());
        reserva.setEstado(this.getEstado());
        reserva.setCalificacion(this.getCalificacion());
        reserva.setGeneroSancion(this.getGeneroSancion());
        reserva.setFechaInicial(this.getFechaInicial());
        reserva.setFechaFinal(this.getFechaFinal());
        
        return reserva;
    }

    public int getEstado() {
        return estado;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }
    
    public String getFechaFinal() {
        return fechaFinal;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public Boolean getGeneroSancion() {
        return generoSancion;
    }



    public Long getId() {
        return id;
    }
    
    public void update(int pEstado,String pFechaInicial, String pFechaFinal ,Double pCalificacion, Boolean pSancion,Long pEdificio, Long pId)
    {
        estado = pEstado;
        fechaInicial = pFechaInicial;
        fechaFinal = pFechaFinal;
        calificacion = pCalificacion;
        generoSancion = pSancion;
        id = pId;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setFechaInicial(String fecha) {
        this.fechaInicial = fecha;
    }
    
    public void setFechaFinal(String fecha) {
        this.fechaFinal = fecha;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public void setGeneroSancion(Boolean generoSancion) {
        this.generoSancion = generoSancion;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String toString()
    {
        return "{ id : " + getId() + ", estado : \"" + getEstado()+ "\" }" ;
    }
    

}
