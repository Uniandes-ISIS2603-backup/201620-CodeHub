/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * 
 * @author s.martinez15
 */
@Entity
public class EdificioEntity extends BaseEntity implements Serializable
{
    /**
     * Abreviatura del edificio
     */
   private String bloque; 
   
   //-----------
    // Metodos
    //-----------
   
   /**
     * Administrador del edificio
     */
   @PodamExclude
   @OneToOne
   private AdministradorEntity admin; 
   
   /**
     * Equipos del edificio
     */
   @PodamExclude
   @OneToMany
   private List<EquipoEntity> equipos = new ArrayList<>(); 
   
   
    //-----------
    // Metodos
    //-----------
   
   public void setBloque(String b)
   {
       this.bloque = b;
   }
   
   public String getBloque()
   {
       return this.bloque;
   }
   
   public void setAdmin(AdministradorEntity a)
   {
       this.admin = a;
   }
   
   public AdministradorEntity getAdmin()
   {
       return admin;
   }
   
   public void setEquipos(ArrayList<EquipoEntity> e)
   {
       this.equipos = e;
   }
   
   public List<EquipoEntity> getEquipos()
   {
       return equipos;
   }
}
