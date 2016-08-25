/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.mocks;

import co.edu.uniandes.rest.audiovisuales.dtos.EquipoDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.EquipoLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mock del recurso Equipo (Mock servicio REST).
 * @author c.zambrano10
 */
public class EquipoLogicMock {
     /*--------------------
          Atributos.
    --------------------*/ 
    /**
     * objeto para presentar logs de las operaciones
     */
    private final static Logger logger = Logger.getLogger(EdificioLogicMock.class.getName());
	
    /**
     * listado de Equipos
     */
    private static ArrayList<EquipoDTO> equipos;
    
    /*--------------------
          Metodos.
    --------------------*/
    /**
     * Constructor para ejemplo.
     */
    public EquipoLogicMock(){
        if(equipos ==null){
            equipos = new ArrayList<>();
            equipos.add(new EquipoDTO(1,"bien"));
            equipos.add(new EquipoDTO(2,"roto"));
            equipos.add(new EquipoDTO(3,"reservado"));
        }
        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
        
        // muestra información 
    	logger.info("Inicializa la lista de equipos");
    	logger.info("ciudades" + equipos );
    }
    
    /**
	 * Obtiene el listado de equipos. 
	 * @return lista de equipos.
	 * @throws EquipoLogicException cuando no existe la lista en memoria.
	 */  
    public List<EquipoDTO> darEquipos() throws EquipoLogicException{
        if (equipos == null) {
    		logger.severe("Error interno: lista de ciudades no existe.");
    		throw new EquipoLogicException("Error interno: lista de ciudades no existe.");    		
    	}
    	
    	logger.info("retornando todas las ciudades");
    	return equipos;
    }
    
     /**
     * Agrega un Equipo a la lista.
     * @param nuevoEquipo equipo a adicionar
     * @throws EquipoLogicException cuando ya existe un equipo con el id suministrado
     * @return equipo agregado
     */
    public EquipoDTO crearEquipo(EquipoDTO nuevoEquipo) throws EquipoLogicException {
    	logger.info("recibiendo solicitud de agregar equipo " + nuevoEquipo);
    	
    	// el nuevo equipo tiene id ?
    	if ( nuevoEquipo.darCodigo()!= 0 ) {
	    	// busca el equipo con el id suministrado
	        for (EquipoDTO equipo : equipos) {
	        	// si existe una ciudad con ese id
	            if (equipo.darCodigo()== nuevoEquipo.darCodigo()){
	            	logger.severe("Ya existe un equipo con ese id");
	                throw new EquipoLogicException("Ya existe un equipo con ese id");
	            }
	        }
	        
	    // el nuevo equipo no tiene id ? 
    	} else {

    		// genera un id para el equipo
    		logger.info("Generando id para el nuevo Equipo");
    		int newId = 1;
	        for (EquipoDTO equipo : equipos) {
	            if (newId <= equipo.darCodigo()){
	                newId =  equipo.darCodigo()+ 1;
	            }
	        }
	        nuevoEquipo.cambiarCodigo(newId);
    	}
    	
        // agrega el equipo
    	logger.info("agregando equipo " + nuevoEquipo);
        equipos.add(nuevoEquipo);
        return nuevoEquipo;
    }
    
    public EquipoDTO updateEquipo (EquipoDTO pEquipo, int pId) throws EquipoLogicException{
       if(pEquipo.darCodigo()!=0){
           for (int i=0; i<equipos.size(); i++) {
	        	// si existe un equipo con ese id la actualiza
                        EquipoDTO Equipo= equipos.get(i);
	            if (Equipo.darCodigo()== pId){
                        Equipo.cambiarEstado(pEquipo.darEstado());
                        return Equipo;
                    }
	        }
       }//si no encuentra un equipo enotnces envia un error.
       logger.severe("No existe una ciudad con ese id");
       throw new EquipoLogicException("No existe una ciudad con ese id");
   }
   
   public void deleteEquipo(int pId) throws EquipoLogicException{
       logger.log(Level.INFO, "eliminando el equipo con id: {0} .", pId);
       for(EquipoDTO equipo : equipos){
           if(pId ==equipo.darCodigo()){
               equipos.remove(equipo);//busca y elimina el equipo
               return;
           }
       }//en caso de no existir envia un mensaje de error
       logger.severe("No existe un equipo con ese id");
       throw new EquipoLogicException("No existe un equipo con ese id");
   }

    public EquipoDTO getEquipo(int id) throws EquipoLogicException{
        EquipoDTO equipo = equipos.get(id-1);
        if(equipo == null){
            logger.log(Level.SEVERE, "el equipo con el Id: {0} no existe", id);
        }
        return equipo;
    }
}
