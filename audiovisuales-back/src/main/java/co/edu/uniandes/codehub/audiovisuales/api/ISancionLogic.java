/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.api;

import co.edu.uniandes.codehub.audiovisuales.entities.SancionEntity;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import co.edu.uniandes.codehub.audiovisuales.exceptions.AudiovisualesLogicException;
import java.util.List;

/**
 *
 * @author lj.pinzon12
 */
public interface ISancionLogic
{
    public List<SancionEntity> getSanciones();

    public SancionEntity getSancion(Long id);

    public SancionEntity getSancionByFecha(String fecha);
    
    public List<SancionEntity> getSancionesByUsuario(Long id);

    public SancionEntity createSancion(SancionEntity entity) throws AudiovisualesLogicException;

    public SancionEntity updateSancion(SancionEntity entity);

    public void deleteSancion(Long id);
}
