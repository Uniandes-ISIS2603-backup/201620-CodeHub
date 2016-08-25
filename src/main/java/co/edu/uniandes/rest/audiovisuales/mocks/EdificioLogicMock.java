/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.mocks;

import co.edu.uniandes.rest.audiovisuales.dtos.EdificioDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.EdificioLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class EdificioLogicMock {
    
    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(EdificioLogicMock.class.getName());
	
    // listado de ciudades
    private static ArrayList<EdificioDTO> edificios;
    
    /**
     * Constructor. Crea tres edificios de ejemplo.
     */
    public EdificioLogicMock()
    {
        if(edificios==null)
        {
            edificios = new ArrayList<>();
            edificios.add(new EdificioDTO(1L, "ML", "Mario Laserna"));
            edificios.add(new EdificioDTO(2L, "W", "Carlos Pacheco Devia"));
            edificios.add(new EdificioDTO(3L, "SD", "Julio Mario Santo Domingo"));
        }
    }
    
    /**
     * Obtiene todos los edificios del sistema. (GET)
     * @return Lista de Edificios
     * @throws EdificioLogicException
     */
    public List<EdificioDTO> getEdificios() throws EdificioLogicException
    {
        if(edificios==null)
        {
            throw new EdificioLogicException("No se encuentra inicializada la lista de Edificios.");
        }
        
        return edificios;
    }
    
    /**
     * Obtiene el edificio con el id especificado. (GET)
     * @param id del edificio buscado
     * @return El edificio con el id ingreasado
     * @throws EdificioLogicException
     */
    public EdificioDTO getEdificio(Long id) throws EdificioLogicException
    {
        if(edificios==null)
        {
            throw new EdificioLogicException("No se encuentra inicializada la lista de Edificios.");
        }
        
        for(EdificioDTO edificio : edificios)
        {
            if(edificio.getId().equals(id))
            {
                return edificio;
            }
        }
        
        throw new EdificioLogicException("No se encuentra el edificio con ese id.");
    }
    
    /**
     * Ingresa un nuevo edificio al sistema y le asigna un id. (POST)
     * @param nuevo Informacion del nuevo edificio a ingreasar.
     * @return El edificio ingresado al sistema.
     * @throws EdificioLogicException
     */
    public EdificioDTO crearEdificio(EdificioDTO nuevo) throws EdificioLogicException
    {
        if(nuevo.getId()!=null)
        {
            for (EdificioDTO edificio : edificios) 
            {
	        if (edificio.getId().equals(nuevo.getId()))
                {
                    logger.severe("Ya existe un edificio con ese id");
	            throw new EdificioLogicException("Ya existe un edificio con ese id");
	        }
	    }
        }
        else
        {
            long id = 1;
	    for (EdificioDTO edificio : edificios) 
            {
	        if (id <= edificio.getId()){
	            id =  edificio.getId() + 1;
	        }
	    }
	    nuevo.setId(id);
        }
        
        edificios.add(nuevo);
        return nuevo;
    }
    
    /**
     * Actualiza el edificio con el id ingresado segun la informacion recibida. (PUT)
     * @param id del edificio a actualizar
     * @param edificio datos a actualizar
     * @return El edificio despues de actualizarce
     * @throws EdificioLogicException
     */
    public EdificioDTO actualizarEdificio(Long id, EdificioDTO edificio) throws EdificioLogicException
    {
        if(edificios==null)
        {
            throw new EdificioLogicException("No se encuentra inicializada la lista de Edificios.");
        }
        
        EdificioDTO buscado = null;
        
        for(EdificioDTO e : edificios)
        {
            if(e.getId().equals(id))
            {
                buscado = e;
            }
        }
        
        if(buscado!=null)
        {
            if(edificio.getId()!=null)
                buscado.setId(edificio.getId());
            if(edificio.getNombre()!=null)
                buscado.setNombre(edificio.getNombre());
            if(edificio.getBloque()!=null)
                buscado.setBloque(edificio.getBloque());
            return buscado;
        }
        else
        {
           throw new EdificioLogicException("No se encuentra el edificio con ese id."); 
        }
    }
    
    /**
     * Elimina del sistema el edificio con el id ingresado. (DELETE)
     * @param id del edificio a eliminar.
     * @return EL edificio eliminado.
     * @throws EdificioLogicException 
     */
    public EdificioDTO eliminarEdificio(Long id) throws EdificioLogicException
    {
        if(edificios==null)
        {
            throw new EdificioLogicException("No se encuentra inicializada la lista de Edificios.");
        }
        
        for(EdificioDTO edificio : edificios)
        {
            if(edificio.getId().equals(id))
            {
                edificios.remove(edificio);
                return edificio;
            }
        }
        
        throw new EdificioLogicException("No se encuentra el edificio con ese id.");
    }
}
