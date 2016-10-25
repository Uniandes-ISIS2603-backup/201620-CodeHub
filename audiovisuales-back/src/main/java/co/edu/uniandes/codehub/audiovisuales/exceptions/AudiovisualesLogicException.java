/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.codehub.audiovisuales.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author ln.bello10
 */
@ApplicationException(rollback = true)
public class AudiovisualesLogicException extends Exception{
    
    public AudiovisualesLogicException() {
        super();
    }

    public AudiovisualesLogicException(String message) {
        super(message);
    }

    public AudiovisualesLogicException(Throwable cause) {
        super(cause);
    }

    public AudiovisualesLogicException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
