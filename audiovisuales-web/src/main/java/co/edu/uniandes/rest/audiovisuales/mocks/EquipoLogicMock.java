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
            equipos.add(new EquipoDTO(1L,EquipoDTO.DISPONIBLE, 1L, "Laptop"));
            equipos.add(new EquipoDTO(2L,EquipoDTO.AVERIADO, 1L, "Microfono"));
            equipos.add(new EquipoDTO(3L,EquipoDTO.RESERVADO, 2L, "Tablet"));          
            equipos.add(new EquipoDTO(4L,EquipoDTO.AVERIADO, 2L, "Auriculares"));
            equipos.add(new EquipoDTO(5L,EquipoDTO.DISPONIBLE, 3L, "Laptop"));
            equipos.add(new EquipoDTO(6L,EquipoDTO.RESERVADO, 3L, "Tablet"));
            equipos.add(new EquipoDTO(7L,EquipoDTO.DISPONIBLE, 4L, "Microfono"));
            equipos.add(new EquipoDTO(8L,EquipoDTO.RESERVADO, 4L, "Tablet"));
            equipos.add(new EquipoDTO(9L,EquipoDTO.DISPONIBLE, 5L, "Microfono"));
            equipos.add(new EquipoDTO(10L,EquipoDTO.AVERIADO, 5L, "Auriculares"));
        }
        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
        
        // muestra información 
    	logger.info("Inicializa la lista de equipos");
    	logger.info("ciudades" + equipos );
    }
    
    public List<EquipoDTO> darEquipos() throws EquipoLogicException{
        if (equipos == null) {
    		logger.severe("Error interno: lista de equipos no existe.");
    		throw new EquipoLogicException("Error interno: lista de equipos no existe.");    		
    	}
    	
    	logger.info("retornando todas los equipos");

    	return equipos;
    }
    
    /**
	 * Obtiene el listado de equipos. 
	 * @return lista de equipos.
	 * @throws EquipoLogicException cuando no existe la lista en memoria.
	 */  
    public List<EquipoDTO> darEquipos(Long idEdificio) throws EquipoLogicException{
        if (equipos == null) {
    		logger.severe("Error interno: lista de equipos no existe.");
    		throw new EquipoLogicException("Error interno: lista de equipos no existe.");    		
    	}
    	
    	logger.info("retornando todas los equipos");
    	List<EquipoDTO> equiposEdificio = new ArrayList<>(); 
        for(int i = 0; i<equipos.size();i++)
        {
            EquipoDTO actual = equipos.get(i);
            if(actual.getIdEdificio()==idEdificio)
            {
                equiposEdificio.add(actual);
            }
        }
    	return equiposEdificio;
    }
    
    public List<EquipoDTO> darEquiposEstado(Long idEdificio, int estado) throws EquipoLogicException {
    	
        List<EquipoDTO> equi = darEquipos(idEdificio);
        List<EquipoDTO> lista =new ArrayList<>();
        for(int i = 0; i<equi.size();i++)
        {
            EquipoDTO actual = equi.get(i);
            if(actual.getEstado()==estado)
            {
                lista.add(actual);
            }
        }
    	return lista;
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
    	if ( nuevoEquipo.getId()!= 0 ) {
	    	// busca el equipo con el id suministrado
	        for (EquipoDTO equipo : equipos) {
	        	// si existe una ciudad con ese id
	            if (equipo.getId()== nuevoEquipo.getId()){
	            	logger.severe("Ya existe un equipo con ese id");
	                throw new EquipoLogicException("Ya existe un equipo con ese id");
	            }
	        }
	        
	    // el nuevo equipo no tiene id ? 
    	} else {

    		// genera un id para el equipo
    		logger.info("Generando id para el nuevo Equipo");
    		Long newId = 1L;
	        for (EquipoDTO equipo : equipos) {
	            if (newId < equipo.getId()){
	                break;  
	            }
                    newId =  equipo.getId()+ 1;
	        }
	        nuevoEquipo.setId(newId);
    	}
    	
        // agrega el equipo
    	logger.info("agregando equipo " + nuevoEquipo);
        equipos.add(nuevoEquipo);
        return nuevoEquipo;
    }
    
    public EquipoDTO updateEquipo (EquipoDTO pEquipo, int pId) throws EquipoLogicException{
       if(pEquipo.getId()!=0){
           for (int i=0; i<equipos.size(); i++) {
	        	// si existe un equipo con ese id la actualiza
                        EquipoDTO Equipo= equipos.get(i);
	            if (Equipo.getId()== pId){
                        Equipo.setEstado(pEquipo.getEstado());
                        return Equipo;
                    }
	        }
       }//si no encuentra un equipo enotnces envia un error.
       logger.severe("No existe un equipo con ese id");
       throw new EquipoLogicException("No existe un equipo con ese id");
   }
   
   public void deleteEquipo(int pId) throws EquipoLogicException{
       logger.log(Level.INFO, "eliminando el equipo con id: {0} .", pId);
       for(EquipoDTO equipo : equipos){
           if(pId ==equipo.getId()){
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