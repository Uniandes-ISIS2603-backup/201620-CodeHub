/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
    // Relaciones
    //-----------

   /**
     * Administrador del edificio
     */
   @PodamExclude
   @OneToOne(mappedBy= "edificio", optional=true)
   private AdministradorEntity admin; 
   
   /**
     * Equipos del edificio
     */
   @PodamExclude
   @OneToMany(mappedBy = "edificio", cascade= CascadeType.ALL, orphanRemoval = true)
   private List<EquipoEntity> equipos = new ArrayList<>(); 
   
   
    //-----------
    // Metodos
    //-----------
   
   /**
     * Establece el bloque del edificio.
     * @param b nuevo valor. 
     */
   public void setBloque(String b)
   {
       this.bloque = b;
   }
   
    /**
     * Obtiene el bloque del edificio.
     * @return bloque. 
     */
   public String getBloque()
   {
       return this.bloque;
   }
   
   /**
     * Establece el admin del edificio.
     * @param a nuevo valor. 
     */
   public void setAdmin(AdministradorEntity a)
   {
       this.admin = a;
   }
   
   /**
     * Obtiene el admin del edificio.
     * @return admin. 
     */
   public AdministradorEntity getAdmin()
   {
       return this.admin;
   }

   /**
     * Establece los equipos registrados en el edificio.
     * @param e lista de equipos. 
     */
   public void setEquipos(ArrayList<EquipoEntity> e)
   {
       this.equipos = e;
   }
   
   /**
     * Obtiene la lista de equipos del edificio.
     * @return equipos. 
     */
   public List<EquipoEntity> getEquipos()
   {
       return equipos;
   }
}
