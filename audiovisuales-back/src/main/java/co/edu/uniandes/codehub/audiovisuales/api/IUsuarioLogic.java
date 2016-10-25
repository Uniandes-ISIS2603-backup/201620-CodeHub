/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.api;

import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import java.util.List;

/**
 *
 * @author ln.bello10
 */
public interface IUsuarioLogic {
    
    public List<UsuarioEntity> getUsuarios();

    public UsuarioEntity getUsuario(Long id);

    public UsuarioEntity getUsuarioByName(String name);

    public UsuarioEntity createUsuario(UsuarioEntity entity);

    public UsuarioEntity updateUsuario(UsuarioEntity entity);

    public void deleteUsuario(Long id);

    public Integer getNumberOfSancionesUsuario(Long id);
    
    public Integer getNumberOfReservasUsuario(Long id);
}
