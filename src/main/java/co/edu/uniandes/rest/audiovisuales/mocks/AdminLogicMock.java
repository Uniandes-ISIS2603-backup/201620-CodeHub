/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.mocks;

import co.edu.uniandes.rest.audiovisuales.dtos.AdminDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.EdificioDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.AdminLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author dcagua10
 */
public class AdminLogicMock 
{
  // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(AdminLogicMock.class.getName());
	
    // Lista de los administradores
    private static ArrayList<AdminDTO> administradores;
    
    public AdminLogicMock()
    {
        if(administradores==null)
        {
            administradores = new ArrayList<>();

            administradores.add(new AdminDTO("Admin1", "Admin1@uniandes.edu.co"));
            
        }
    }
    
    public List<AdminDTO> getAdministradores() throws AdminLogicException
    {
        if(administradores==null)
        {
            throw new AdminLogicException("No se encuentra inicializada la lista de Administradores.");
        }
        
        return administradores;
    }
    
    public AdminDTO getAdmin(String nombre) throws AdminLogicException
    {
        if(administradores==null)
        {
            throw new AdminLogicException("No se encuentra inicializada la lista de Administradores.");
        }
        
        for(AdminDTO admin : administradores)
        {
            if(admin.getName().equals(nombre))
            {
                return admin;
            }
        }
        
        throw new AdminLogicException("No se encuentra el administrador con el nombre indicado.");
    }
    
    
    public AdminDTO crearAdministrador(AdminDTO nuevo) throws AdminLogicException
    {
  
        
        if(nuevo.getName()!=null)
        {
            for (AdminDTO administrador : administradores) 
            {
	        if (administrador.getName().equals(nuevo.getName()))
                {
                    logger.severe("Ya existe un administrador con ese nombre");
	            throw new AdminLogicException("Ya existe un administrador con ese nombre");
	        }
	    }
        }
        
        administradores.add(nuevo);
        return nuevo;
    }
    
    public AdminDTO actualizarAdministrador(String nombre, String correo) throws AdminLogicException
    {
        if(administradores==null)
        {
            throw new AdminLogicException("No se encuentra inicializada la lista de Administradores.");
        }
        
        AdminDTO buscado = null;
        
        for(AdminDTO admin : administradores)
        {
            if(admin.getName().equals(nombre))
            {
                buscado = admin;
            }
        }
        
        if(buscado!=null)
        {
         if(nombre!=null)
            buscado.setName(nombre);
         if(correo!=null)
            buscado.setEmail(correo);
         return buscado;
  
        }
        else
        {
           throw new AdminLogicException("No se encuentra el Administrador con ese nombre."); 
        }
    }
    
    public AdminDTO eliminarAdministrador(String nombre) throws AdminLogicException
    {
        if(administradores==null)
        {
            throw new AdminLogicException("No se encuentra inicializada la lista de Administradores.");
        }
        
        for(AdminDTO admin : administradores)
        {
            if(admin.getName().equals(nombre))
            {
                administradores.remove(admin);
                return admin;
            }
        }
        
        throw new AdminLogicException("No se encuentra el administrador con ese nombre");
    }
}  

