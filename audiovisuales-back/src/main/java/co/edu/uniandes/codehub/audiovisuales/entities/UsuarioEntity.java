/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ln.bello10
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {
    
    public final static int ESTUDIANTE = 1;
    public final static int PROFESOR = 2;
    
    private Boolean tieneSancion;
    private int tipo;
    
    public Boolean getTieneSancion(){
        return this.tieneSancion;
    }
    public void setTieneSancion(Boolean sancion){
        this.tieneSancion=sancion;
    }
    public int getTipo(){
        return this.tipo;
    }
    public void setTipo(int tipo){
        this.tipo=tipo;
    }
}
