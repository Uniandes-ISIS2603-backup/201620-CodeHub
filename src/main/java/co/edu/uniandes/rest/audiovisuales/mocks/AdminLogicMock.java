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
            administradores.add(new AdminDTO(1,"Admin1", "Admin1@uniandes.edu.co"));
            administradores.add(new AdminDTO(2,"Admin2","Admin2@uniandes.edu.co"));
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
    
    public AdminDTO getAdmin(int id) throws AdminLogicException
    {
        AdminDTO admin = administradores.get(id-1);
        if (administradores == null)
        {
            throw new AdminLogicException("No se encuentra inicializada la lista de Administradores.");
        }
        if (admin == null)
        {
            logger.log(Level.SEVERE, "El administrador con el Id: {0} no existe", id);
            throw new AdminLogicException("El administrador no se encuentra en la lista");
        }
        return admin;
        
    }
    
    public AdminDTO crearAdministrador(AdminDTO nuevoAdmin) throws AdminLogicException
    {   
        if(nuevoAdmin.getID()!=0)
        {
            for (AdminDTO administrador : administradores) 
            {
	        if (administrador.getID()==(nuevoAdmin.getID()))
                {
                    logger.severe("Ya existe un administrador con ese id");
	            throw new AdminLogicException("Ya existe un administrador con ese id");
	        }
	    }
        }
        else
        {
            logger.info("Se esta generando un nuevo id para el Administrador");
            int nuevoID = 1;
             for (AdminDTO administrador : administradores) 
            {
                if (nuevoID <= administrador.getID())
                {
                    nuevoID = administrador.getID()+1;
                }
            }
             nuevoAdmin.setID(nuevoID);
        }
        logger.info("El administrador " + nuevoAdmin.toString() + " se esta agregando");
        administradores.add(nuevoAdmin);
        return nuevoAdmin;
    }
    
    public AdminDTO actualizarAdministrador(AdminDTO pAdmin, int pID) throws AdminLogicException
    {
        if(pAdmin.getID() != 0)
        {
            for (AdminDTO admin : administradores)
            {
                if (admin.getID()==pID)
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
            if(pId==admin.getID())
            {
                administradores.remove(admin);
            }
        }
        logger.severe("No existe un administrador con el id suministrado");
        throw new AdminLogicException("No se encuentra el administrador con ese nombre");
    }
}  

