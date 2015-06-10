/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import crud.UserController;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    private String username = "";
    private String password = "";
    
    @Inject
    private UserController userController;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    public String validateLogin(){
        return userController.loginUser(username, password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
