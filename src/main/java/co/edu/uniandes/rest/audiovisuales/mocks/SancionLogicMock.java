/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.mocks;

import co.edu.uniandes.rest.audiovisuales.dtos.SancionDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.SancionLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lj.pinzon12
 */
public class SancionLogicMock
{
    // objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(SancionLogicMock.class.getName());
	
	// listado de ciudades
    private static ArrayList<SancionDTO> sanciones;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public SancionLogicMock() {

    	if (sanciones == null) {
            sanciones = new ArrayList<>();
    	logger.info("Inicializa la lista de sanciones");
            sanciones.add(new SancionDTO(1L, "15/07/2016", "vigente"));
            sanciones.add(new SancionDTO(2L, "17/06/2016", "saldada"));
            sanciones.add(new SancionDTO(3L, "04/08/2016", "vigente"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("sanciones" + sanciones );
    }    
    
	/**
	 * Obtiene el listado de sanciones. 
	 * @return lista de sanciones
	 * @throws SancionLogicException cuando no existe la lista en memoria  
	 */    
    public List<SancionDTO> getSanciones() throws SancionLogicException {
    	if (sanciones == null) {
    		logger.severe("Error interno: lista de sanciones no existe.");
    		throw new SancionLogicException("Error interno: lista de sanciones no existe.");    		
    	}
    	
    	logger.info("retornando todas las sanciones");
    	return sanciones;
    }

 

    /**
     * Agrega una sancion a la lista.
     * @param newSancion sancion a adicionar
     * @throws SancionLogicException cuando ya existe una sancion con el id suministrado
     * @return Sancion agregada
     */
    public SancionDTO createSancion(SancionDTO newSancion) throws SancionLogicException {
    	logger.info("recibiendo solicitud de agregar sancion " + newSancion);
    	
    	// la nueva sancion tiene id ?
    	if ( newSancion.getId() != null ) {
	    	// busca la sancion con el id suministrado
	        for (SancionDTO sancion : sanciones) {
	        	// si existe una sancion con ese id
	            if (Objects.equals(sancion.getId(), newSancion.getId())){
	            	logger.severe("Ya existe una sancion con ese id");
	                throw new SancionLogicException("Ya existe una sancion con ese id");
	            }
	        }
	        
	    // la nueva sancion no tiene id ? 
    	} else {

    		// genera un id para la sancion
    		logger.info("Generando id paa la nueva sancion");
    		long newId = 1;
	        for (SancionDTO sancion : sanciones) {
	            if (newId <= sancion.getId()){
	                newId =  sancion.getId() + 1;
	            }
	        }
	        newSancion.setId(newId);
    	}
    	
        // agrega la sancion
    	logger.info("agregando sancion " + newSancion);
        sanciones.add(newSancion);
        return newSancion;
    }

   public SancionDTO getSancion(Long idSancion) throws SancionLogicException {
          for (SancionDTO sancion : sanciones) {
	            if (idSancion == sancion.getId()){
	                return sancion;
	            }
	        }
          logger.severe("No se encontro la sancion solicitada");
	  throw new SancionLogicException("No se encontro la sancion solicitada");
   }
   
   public SancionDTO putSancion(SancionDTO newSancion, Long id) throws SancionLogicException {
       logger.severe("Actualizando la sancion con id "+id);
                 for (SancionDTO sancion : sanciones)
                 {
	            if (newSancion.getId() == sancion.getId())
                    {
	            sancion.setEstado(newSancion.getEstado());
                    return newSancion;
                        //sancion.setName(newNameCity); 
	            }
	        }
                 
          logger.severe("No se encontro la sancion solicitada");
	  throw new SancionLogicException("No se encontro la sancion solicitada");
                 
                 
   }
   public void deleteSancion(Long id) throws SancionLogicException {
       logger.info("Eliminando la sancion con id "+id);
       for (SancionDTO sancion : sanciones) {
	            if (id == sancion.getId()){
                        sanciones.remove(sancion);
                        return;
	            }
	        }
          logger.severe("No se encontro la ciudad solicitada");
	  throw new SancionLogicException("No se encontro la ciudad solicitada");
   }
}
