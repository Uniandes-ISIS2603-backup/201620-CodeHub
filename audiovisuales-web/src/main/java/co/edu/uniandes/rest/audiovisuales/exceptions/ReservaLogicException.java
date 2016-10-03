/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.exceptions;

/**
 *
 * @author orlando
 */
public class ReservaLogicException extends Exception{
    
    public ReservaLogicException()
    {
        
    }
    
    public ReservaLogicException(String message){
        super(message);
    }
    
    public ReservaLogicException(Throwable cause)
    {
        super(cause);
    }
    
    public ReservaLogicException(String message, Throwable cause)
    {
        super(message,cause);
    }
    
}
