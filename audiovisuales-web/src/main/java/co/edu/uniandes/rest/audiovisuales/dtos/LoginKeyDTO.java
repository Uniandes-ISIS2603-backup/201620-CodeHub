/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.audiovisuales.dtos;

/**
 *
 * @author s.martinez15
 */
public class LoginKeyDTO {
    
    private String login;
    private String password;
    
    public LoginKeyDTO(){
        
    }
    
    public LoginKeyDTO(String login, String password){
        this.login = login;
        this.password = password;
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
}
