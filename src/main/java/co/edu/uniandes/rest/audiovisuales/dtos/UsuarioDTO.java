/*
 * CityDTO
 * Objeto de transferencia de datos de Usuarios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de transferencia de datos de Usuarios.
 * @author Asistente
 */
public class UsuarioDTO {
    
    public final static int ESTUDIANTE = 1;
    public final static int PROFESOR = 2;
    
    private Long id;
    private String name;
    private Boolean tieneSancion;
    private int tipo;
    
    private List<SancionDTO> sanciones;
    private List<ReservaDTO> reservas;
    
    private List<Double> calificaciones;

    /**
     * Constructor por defecto
     */
    public UsuarioDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador del usuario
     * @param name nombre del usuario
     */
    public UsuarioDTO(Long id, String name, int tipo) {
		super();
		this.id = id;
		this.name = name;
                this.tieneSancion = false;
                this.tipo = tipo;
                this.sanciones = new ArrayList<>();
                this.reservas = new ArrayList<>();
                this.calificaciones = new ArrayList<>();
	}

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getTieneSancion(){
        return tieneSancion;
    }
    
    public void setTieneSancion(Boolean b){
        this.tieneSancion = b;
    }
    
    public List<SancionDTO> getSanciones()
    {
        return sanciones;
    }
    
    public void setSanciones(List<SancionDTO> sanciones)
    {
        this.sanciones = sanciones;
    }
    
    public List<ReservaDTO> getReservas()
    {
        return reservas;
    }
    
    public int getTipo()
    {
        return tipo;
    }
    
    public void setReservas(List<ReservaDTO> reservas)
    {
        this.reservas = reservas;
    }
    
    public void addCalificacion(double calificacion)
    {
        calificaciones.add(calificacion);
    }
    
    public double getPromedioCalificaciones()
    {
        if(calificaciones.size()!=0)
        {
          double contador = 0.0;
            for(int i = 0; i<calificaciones.size();i++)
            {
                contador+= calificaciones.get(i);
            }
            return contador/calificaciones.size();  
        }
        return 0.0;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + "\" }" ;  
    }
    
}
