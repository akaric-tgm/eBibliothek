/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import crud.UserController;
import crud.UserGroupsController;
import entity.User;
import entity.UserGroups;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jakubkopec
 */
@Named("benutzerRegistrierenBean")
@SessionScoped
public class BenutzerRegistrierenBean implements Serializable {

    private String errorMessages = "";

    private String username = "";
    private String password = "";
    private String passwordConfirm = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String emailConfirm = "";

    @Inject
    private UserController userController;
    @Inject
    private UserGroupsController userGroupsController;

    public String signup() {
        if (userController.findByUsername(username) == null) {
            if (userController.findByEmail(email) == null) {
                User user = new User(username, userController.getHashedString(UserController.getSALT() + password),
                        firstName, lastName, email,
                        userController.getHashedString(UserController.getSALT() + username + "resetpassword"),
                        userController.getHashedString(UserController.getSALT() + username + "confirmemail"), false);
                userController.setSelected(user);
                userController.create();
                UserGroups userGroups = new UserGroups(username, "User");
                userGroups.setUser(user);
                userGroupsController.setSelected(userGroups);
                userGroupsController.create();
                return "benutzer_einloggen?faces-redirect=true";
            } else {
                errorMessages += "E-Mail Adresse bereits vergeben!";
                return " ";
            }
        } else {
            errorMessages += "Username bereits vergeben! Bitte wähle einen anderen!";
            return " ";
        }
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmailConfirm() {
        return emailConfirm;
    }

    public void setEmailConfirm(String emailConfirm) {
        this.emailConfirm = emailConfirm;
    }

}
