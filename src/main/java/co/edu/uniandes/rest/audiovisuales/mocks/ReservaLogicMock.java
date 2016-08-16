/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.mocks;

import co.edu.uniandes.rest.audiovisuales.dtos.ReservaDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.ReservaLogicException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orlando
 */
public class ReservaLogicMock {
    
    private final static Logger logger = Logger.getLogger(ReservaLogicMock.class.getName());
    private static ArrayList<ReservaDTO> reservas;
    
    @SuppressWarnings("LoggerStringConcat")
    public ReservaLogicMock()
    {
        if(reservas == null)
        {
            reservas = new ArrayList<>();
            reservas.add(new ReservaDTO(Date.from(Instant.MIN), 0.0, "ML", 1L));
            reservas.add(new ReservaDTO(Date.from(Instant.MIN), 0.0, "W", 2L));
            reservas.add(new ReservaDTO(Date.from(Instant.MIN), 0.0, "AU", 3L));
        }
        
        logger.setLevel(Level.INFO);
        
        logger.info("Inicializa la lista de Reservas");
        Logger.info("ciudades"+  reservas );
    }
    
    public List<ReservaDTO> getReservas() throws ReservaLogicException
    {
        if(reservas == null)
        {
            logger.severe("Error interno : lista de reservas no existe.");
            throw new ReservaLogicException("Error Interno: lista de usuarios no existe.");
        }
        logger.info("retornando todos los reservas");
        return reservas;
    }
    
    public ReservaDTO getReserva(Long id)throws ReservaLogicException
    {
        ReservaDTO r = null;
        for(int i = 0; i<reservas.size();i++){
            ReservaDTO reserva = reservas.get(i);
            if(reserva.getId().equals(id)){
                r = reserva;
            }
        }
        if(r == null){
            throw new ReservaLogicException("Error interno: la reserva no existe.");
        }
        return r;
    }
    
    public ReservaDTO createReserva(ReservaDTO newReserva)throws ReservaLogicException
    {
        logger.info("recibiendo solicitud de agregar usuario " + newUser);
    	
    	// el nuevo usuario tiene id ?
    	if ( newReserva.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (ReservaDTO reserva : reservas) {
	        	// si existe una ciudad con ese id
	            if (reserva.getId().equals(newReserva.getId())){
	            	logger.severe("Ya existe una reserva con ese id");
	                throw new ReservaLogicException("Ya existe una reserva con ese id");
	            }
	        }
	        
	    // el nuevo usuario no tiene id ? 
    	} else {

    		// genera un id para el usuario
    		logger.info("Generando id para la nueva reserva");
    		long newId = 1;
	        for (ReservaDTO res : reservas) {
	            if (newId <= res.getId()){
	                newId =  res.getId() + 1;
	            }
	        }
	        newReserva.setId(newId);
    	}
    	
        // agrega el usuario
    	logger.info("agregando usuario " + newReserva);
        reservas.add(newReserva);
        return newReserva;
    }
    
}
