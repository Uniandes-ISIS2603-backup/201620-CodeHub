/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.mocks;

import co.edu.uniandes.rest.audiovisuales.dtos.EquipoDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.ReservaDTO;
import co.edu.uniandes.rest.audiovisuales.dtos.SancionDTO;
import co.edu.uniandes.rest.audiovisuales.exceptions.ReservaLogicException;

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
            reservas.add(new ReservaDTO(new Date(2016, 8, 10), "ML", 1L, -1l, new ArrayList<EquipoDTO>()));
            reservas.add(new ReservaDTO(new Date(2016, 8, 11), "W", 2L, -1l ,new ArrayList<EquipoDTO>()));
            reservas.add(new ReservaDTO(new Date(2016, 8, 12), "AU", 3L, -1L, new ArrayList<EquipoDTO>()));
        }
        
        logger.setLevel(Level.INFO);
        
        logger.info("Inicializa la lista de Reservas");
        logger.info("ciudades"+  reservas );
    }
    
    public List<ReservaDTO> getReservas(Long idUsuario) throws ReservaLogicException
    {
        if(reservas == null)
        {
            logger.severe("Error interno : lista de reservas no existe.");
            throw new ReservaLogicException("Error Interno: lista de usuarios no existe.");
        }
        logger.info("retornando todos los reservas");
        List<ReservaDTO> reservasUsuario = new ArrayList<>(); 
        for(int i = 0; i<reservas.size();i++)
        {
            ReservaDTO actual = reservas.get(i);
            if(actual.getIdUsuario()==idUsuario)
            {
                reservasUsuario.add(actual);
            }
        }
    	return reservasUsuario;
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
        logger.info("recibiendo solicitud de agregar usuario " + newReserva);
    	
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
    
    public ReservaDTO updateReserva(ReservaDTO pReserva)throws ReservaLogicException
    {
        ReservaDTO r = null;
        for(int i = 0; i<reservas.size();i++){
            ReservaDTO reserva = reservas.get(i);
            if(reserva.getId().equals(pReserva.getId())){
                reserva.update(pReserva.getEstado(),
                        pReserva.getFecha(),
                        pReserva.getCalificacion(),
                        pReserva.getGeneroSancion(),
                        pReserva.getNombreEdificio(),
                        pReserva.getId());
                
                r = reserva;
            }
        }
        if(r == null){
            throw new ReservaLogicException("Error interno: la reserva no existe.");
        }
        return r;
    }
    
    
}
