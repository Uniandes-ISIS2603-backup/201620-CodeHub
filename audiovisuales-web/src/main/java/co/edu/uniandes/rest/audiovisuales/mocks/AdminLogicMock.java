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
import java.util.logging.Level;
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
            administradores.add(new AdminDTO(1L,"Admin1", "Admin1@uniandes.edu.co",1L));
            administradores.add(new AdminDTO(2L,"Admin2","Admin2@uniandes.edu.co",2L));
            administradores.add(new AdminDTO(3L,"Admin3","Admin3@uniandes.edu.co",5L));
            administradores.add(new AdminDTO(4L,"Admin4","Admin4@uniandes.edu.co",3L));
            administradores.add(new AdminDTO(5L,"Admin5","Admin5@uniandes.edu.co",4L));
        }
        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
        
        // muestra información 
    	logger.info("Inicializa la lista de administradores");
    	logger.info("Admins" + administradores );
    }
        
    public List<AdminDTO> getAdministradores() throws AdminLogicException
    {
        if(administradores==null)
        {
            logger.severe("Error interno: lista de administradores no existe.");
            throw new AdminLogicException("No se encuentra inicializada la lista de Administradores.");
        }
        logger.info("Retornando la lista de administradores");
        return administradores;
    }
    
    public AdminDTO getAdmin(Long id) throws AdminLogicException
    {
        if (administradores == null)
        {
            throw new AdminLogicException("No se encuentra inicializada la lista de Administradores.");
        }
        
        for(AdminDTO admin : administradores)
        {
            if(admin.getId().equals(id))
            {
                return admin;
            }
        }
        throw new AdminLogicException("No se encuentra el administrador con ese id.");
    }
    
    public AdminDTO crearAdministrador(AdminDTO nuevoAdmin) throws AdminLogicException
    {   
        if(nuevoAdmin.getId()!=0)
        {
            for (AdminDTO administrador : administradores) 
            {
	        if (administrador.getId()==(nuevoAdmin.getId()))
                {
                    logger.severe("Ya existe un administrador con ese id");
	            throw new AdminLogicException("Ya existe un administrador con ese id");
	        }
	    }
        }
        else
        {
            logger.info("Se esta generando un nuevo id para el Administrador");
            Long nuevoID = 1L;
             for (AdminDTO administrador : administradores) 
            {
                if (nuevoID <= administrador.getId())
                {
                    nuevoID = administrador.getId()+1;
                }
            }
             nuevoAdmin.setId(nuevoID);
        }
        logger.info("El administrador " + nuevoAdmin.toString() + " se esta agregando");
        administradores.add(nuevoAdmin);
        return nuevoAdmin;
    }
    
    public AdminDTO actualizarAdministrador(AdminDTO pAdmin, int pID) throws AdminLogicException
    {
        if(pAdmin.getId() != 0)
        {
            for (AdminDTO admin : administradores)
            {
                if (admin.getId()==pID)
                {
                    admin.setName(pAdmin.getName());
                    admin.setEmail(pAdmin.getEmail());
                    return admin;
                }
            }
        }
        
        logger.severe("No existe un administrador con ese id");
        throw new AdminLogicException("No se encuentra el Administrador con ese nombre."); 
    }
    
    
    public void eliminarAdministrador(int pId) throws AdminLogicException
    {
        if(administradores==null)
        {
            throw new AdminLogicException("No se encuentra inicializada la lista de Administradores.");
        }
        
        logger.log(Level.INFO, "eliminando el equipo con id: {0} .", pId);
        
        for(AdminDTO admin : administradores)
        {
            if(pId==admin.getId())
            {
                administradores.remove(admin);
            }
        }
        logger.severe("No existe un administrador con el id suministrado");
        throw new AdminLogicException("No se encuentra el administrador con ese nombre");
    }
}  

