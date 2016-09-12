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
            sanciones.add(new SancionDTO(1L, "15/07/2016", "vigente",-1l));
            sanciones.add(new SancionDTO(2L, "17/06/2016", "saldada",-1l));
            sanciones.add(new SancionDTO(3L, "04/08/2016", "vigente",-1l));
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
    public List<SancionDTO> getSancionesUsuario(Long idUsuario) throws SancionLogicException {
    	if (sanciones == null) {
    		logger.severe("Error interno: lista de sanciones no existe.");
    		throw new SancionLogicException("Error interno: lista de sanciones no existe.");    		
    	}
    	
    	logger.info("retornando todas las sanciones");
        List<SancionDTO> sancionesUsuario = new ArrayList<>(); 
        for(int i = 0; i<sanciones.size();i++)
        {
            SancionDTO actual = sanciones.get(i);
            if(actual.getIdUsuario()==idUsuario)
            {
                sancionesUsuario.add(actual);
            }
        }
    	return sancionesUsuario;
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
        logger.log(Level.INFO, "la sanci\u00f3n que llega es: {0} {1}", new Object[]{newSancion.getEstado(), newSancion.getFecha()});
        if(sanciones==null)
        {
            throw new SancionLogicException("No se encuentra inicializada la lista de sanciones.");
        }
        
        SancionDTO buscado = null;
        //logger.info("Id ingresado "+id);
        for(SancionDTO e : sanciones)
        {
             //logger.info("Id ingresado "+id+" otro "+e.getId());
            if(e.getId().equals(id)||e.getId()==(id))
            {
                buscado = e;
                break;
            }
        }
        
        logger.info("llego aqui1");
        
        if(buscado!=null)
        {
             logger.info("entro if");
            if(newSancion.getId()!=null)
                buscado.setId(newSancion.getId());
            if(newSancion.getFecha()!=null)
                buscado.setFecha(newSancion.getFecha());
            logger.info(newSancion.getEstado());
            if(newSancion.getEstado()!=null)
                buscado.setEstado(newSancion.getEstado());
            
            logger.info("id "+buscado.getId()+" fecha "+buscado.getFecha() +" estado "+buscado.getEstado()+" ");
            
            return buscado;
        }
        else
        {
           throw new SancionLogicException("No se encuentra el estado con ese id."); 
        }
    }
   public SancionDTO deleteSancion(Long id) throws SancionLogicException {
       logger.info("Eliminando la sancion con id "+id);
       for (SancionDTO sancion : sanciones) {
	            if (sancion.getId().equals(id)){
                        sanciones.remove(sancion);
                        return sancion;
	            }
	        }
          logger.severe("No se encontro la ciudad solicitada");
	  throw new SancionLogicException("No se encontro la ciudad solicitada");
   }
}
