/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de transferencia de Equipos
 * @author c.zambrano10
 */
public class EquipoDTO {
    
    /*-----------------------
            Atributos.
    -------------------------*/
    /**
     * Codigo de identificación
     */
    private int codigo;
   /**
    * Tipo de equipo
    */
    //private String tipo;
    /**
     * Estado en que se encuentra el equipo
     */
    private String estado;
    private boolean disponible;
    
    private Long idEdificio;
    
    private List<ReservaDTO> reservas;
    
    /*-------------------------
            Constructores.
    ---------------------------*/
    /**
     * Constructor para el DTO de equipo
     * @param pCodigo el codigo del equipo a crear. pCodigo !=null && pCodigo!=0.
     * @param pEstado el estado del equipo a crear. pEstado !=null&& pEstado!=""
     */
    public EquipoDTO(int pCodigo, String pEstado, Long edificio){
        codigo = pCodigo;
        estado = pEstado;
        idEdificio = edificio;
        disponible = true;
        reservas = new ArrayList<>();
    }
    /**
     * Constructor vacio necesario porque aja(?)
     */
    public EquipoDTO() 
    {//because of reasons.
    }
    
    /*--------------------
            Metodos.
    --------------------*/    
    /**
     * Metodo para conocer el codigo del equipo
     * @return el codigo del equipo
     */
    public int getCodigo(){
        return codigo;
    }
    /**
     * Metodo para modificar el codigo del equipo.
     * @param pCodigo el codigo nuevo del equipo. pCodigo !=null
     */
    public void setCodigo(int pCodigo){
        codigo= pCodigo;
    }
    /**
     * Metodo para conocer el estado del equipo.
     * @return el estado del equipo.
     */
    public String getEstado(){
        return estado;
    }
    /**
     * Metodo para modificar el estado del equipo.
     * @param pEstado el nuevo estado del equipo. pEstado !=null&& pEstado!=""
     */
    public void setEstado(String pEstado){
        estado = pEstado;
    }
    /**
     * @return the id
     */
    public Long getIdEdificio() {
        return idEdificio;
    }
    
    public boolean getDisponible()
    {
        return disponible;
    }
    
    public void setDisponible(boolean disponible)
    {
        this.disponible = disponible;
    }

    /**
     * @param id the id to set
     */
    public void setIdEdificio(Long idEdificio) {
        this.idEdificio = idEdificio;
    }
    
    public List<ReservaDTO> getReservas()
    {
        return reservas;
    }
    
    public void addReserva(ReservaDTO reserva)
    {
        this.reservas.add(reserva);
    }

    
    /**
     * Metodo to string para enviar la información como un JSON
     * @return la informacion de la clase como un JSON en un string.
     */
    @Override
    public String toString(){
        return "{ codigo: "+codigo+", estado: \""+estado+"\"}";
    }
}
