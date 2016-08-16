/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.exceptions;

/**
 *
 * @author dcagua10
 */
public class AdminLogicException extends Exception
{
   
    private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
    public AdminLogicException() 
    {
        
    }

	/**
	 * Constructor con un mensaje
	 * @param message mensaje de la excepción
	 */
    public AdminLogicException(String message) 
    {
	super(message);
    }

	/**
	 * Constructor con una causa
	 * @param cause causa de la excepción. Usada para generar la traza.
	 */
    public AdminLogicException(Throwable cause) 
    {
	super(cause);
    }

	/**
	 * Constructor con mensaje y causa.
	 * @param message mensaje de la excepción
	 * @param cause causa de la excepción. Usada para generar la traza.
	 */
    public AdminLogicException(String message, Throwable cause) 
    {
	super(message, cause);
    }

    
}
