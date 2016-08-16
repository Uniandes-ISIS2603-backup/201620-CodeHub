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
    private final static Logger logger = Logger.getLogger(co.edu.uniandes.rest.cities.mocks.CityLogicMock.class.getName());
	
    // listado de ciudades
    private static ArrayList<EdificioDTO> edificios;
    
    public EdificioLogicMock()
    {
        if(edificios==null)
        {
            edificios = new ArrayList<>();
            edificios.add(new EdificioDTO(1L, "Bloque ML - Mario Laserna"));
            edificios.add(new EdificioDTO(2L, "Bloque W - Carlos Pacheco Devia"));
            edificios.add(new EdificioDTO(3L, "Bloque SD - Julio Mario Santo Domingo"));
        }
    }
    
    public List<EdificioDTO> getEdificios() throws EdificioLogicException
    {
        if(edificios==null)
        {
            throw new EdificioLogicException("No se encuentra inicializada la lista de Edificios.");
        }
        
        return edificios;
    }
    
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
            if(edificio.getName()!=null)
                buscado.setName(edificio.getName());
            return buscado;
        }
        else
        {
           throw new EdificioLogicException("No se encuentra el edificio con ese id."); 
        }
    }
    
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
