/*
 * CityDTO
 * Objeto de transferencia de datos de Usuarios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.codehub.audiovisuales.entities.UsuarioEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto de transferencia de datos de Usuarios.
 * @author Asistente
 */
@XmlRootElement
public class UsuarioDTO {
    
    public final static int ESTUDIANTE = 1;
    public final static int PROFESOR = 2;
    
    private Long id;
    private String name;
    private Boolean tieneSancion;
    private int tipo;
    protected String login;
    protected String password;
  
    
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
    public UsuarioDTO(Long id, String name, int tipo, String login, String password) {
		super();
		this.id = id;
		this.name = name;
                this.tieneSancion = false;
                this.tipo = tipo;
                this.calificaciones = new ArrayList<>();
                this.login = login;
                this.password = password;
	}
    
    public UsuarioDTO(UsuarioEntity entity){
        if(entity !=null){
            this.name=entity.getName();
            this.id=entity.getId();
            if(entity.getSanciones().isEmpty()){
                this.tieneSancion=false;
            }
            else{
                this.tieneSancion=true;
            }
            this.tipo=entity.getTipo();
            this.login = entity.getLogin();
            this.password = entity.getPassword();
        }
    }
    
    public UsuarioEntity toEntity(){
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(this.getId());
        usuario.setName(this.getName());
        usuario.setTipo(this.getTipo());
        usuario.setTieneSancion(this.getTieneSancion());
        usuario.setLogin(login);
        usuario.setPassword(password);
        
        return usuario;
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
    
    public int getTipo()
    {
        return tipo;
    }
    
    public void setTipo(int pTipo)
    {
        tipo = pTipo;
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
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public String getLogin(){
        return login;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + "\" }" ;  
    }
    
}
