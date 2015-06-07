/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author jakubkopec
 */
public class BenutzerRegistrierenBean {
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String email;
    
    public void persist(){
        
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return this.username;
    }
}
