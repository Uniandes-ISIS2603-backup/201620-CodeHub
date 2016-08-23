/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

/**
 * Objeto de transferencia de Equipos
 * @author c.zambrano10
 */
public class EquipoDTO {
    
    /*--------------------
          Atributos.
    --------------------*/
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
    
    /*--------------------
          Metodos.
    --------------------*/
    /**
     * Constructor para el DTO de equipo
     * @param pCodigo el codigo del equipo a crear. pCodigo !=null && pCodigo!=0.
     * @param pEstado el estado del equipo a crear. pEstado !=null&& pEstado!=""
     */
    public EquipoDTO(int pCodigo, String pEstado){
        codigo = pCodigo;
        estado = pEstado;        
    }
    /**
     * Metodo para conocer el codigo del equipo
     * @return el codigo del equipo
     */
    public int darCodigo(){
        return codigo;
    }
    /**
     * Metodo para modificar el codigo del equipo.
     * @param pCodigo el codigo nuevo del equipo. pCodigo !=null
     */
    public void cambiarCodigo(int pCodigo){
        codigo= pCodigo;
    }
    /**
     * Metodo para conocer el estado del equipo.
     * @return el estado del equipo.
     */
    public String darEstado(){
        return estado;
    }
    /**
     * Metodo para modificar el estado del equipo.
     * @param pEstado el nuevo estado del equipo. pEstado !=null&& pEstado!=""
     */
    public void cambiarEstado(String pEstado){
        estado = pEstado;
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
