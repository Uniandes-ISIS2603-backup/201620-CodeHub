/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.api;

import java.util.List;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import co.edu.uniandes.codehub.audiovisuales.entities.AdministradorEntity;

/**
 *
 * @author d.cagua10
 */
public interface IAdministradorLogic 
{
    public List<AdministradorEntity> getAdministradores();
    public AdministradorEntity getAdministrador(Long idAdmin);
    public AdministradorEntity getAdministradorByName(String name);
    public AdministradorEntity getAdministradorByEdificio(Long idEdficio);
    public AdministradorEntity createAdministrador(AdministradorEntity entity) throws AudiovisualesLogicException;
    public AdministradorEntity updateAdministrador(AdministradorEntity entity);
    public void deleteAdministrador(Long idAdmin);
    public AdministradorEntity login(String login, String password);
}
