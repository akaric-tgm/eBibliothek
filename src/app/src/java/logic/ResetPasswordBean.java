/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import crud.UserController;
import entity.User;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Selina Brinnich
 */
@Named("resetPasswordBean")
@RequestScoped
public class ResetPasswordBean implements Serializable {
    
    @EJB
    EmailSender emailSender;
    @Inject
    UserController userController;
    
    private String email = "";
    private String username = "";
    private String newPassword = "";
    private String newPasswordConfirm = "";
    
    private String errorMessage = "";

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getErrorMessage() {
        System.out.println("get");
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Creates a new instance of ResetPasswordBean
     */
    public ResetPasswordBean() {
    }
    
    public String checkCredentialsSendMail(){
        if(userController.isValidUsernameAndEmail(username, email)){
            sendEmailResetPassword();
            return "index?faces-redirect=true";
        }else{
            return "index?faces-redirect=true";
        }
    }
    
    public void checkCredentialsResetPassword(){
        if(userController.isValidUsernameAndEmail(username, email)){
            if(!newPassword.equals(newPasswordConfirm)){
                errorMessage += "Passwörter stimmen nicht überein!";
                //return "password_reset_set";
            }else{
                String token = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("token");
                User user = userController.findByUsername(username);
                if(user.getPwtoken().equals(token)){
                    user.setPassword(newPassword);
                    userController.setSelected(user);
                    userController.update();
                    //return "password_reset_success?faces-redirect=true";
                }else{
                    errorMessage += "Fehler! E-Mail Adresse oder Username falsch!";
                    //return "password_reset_set";
                }
            }
        }else{
            errorMessage += "Fehler! E-Mail Adresse oder Username falsch!";
            //return "password_reset_set";
        }
        System.out.println(errorMessage);
        //return null;
    }
    
    public void sendEmailResetPassword(){
        emailSender.sendEmail(getEmail(), 
                "Readtastic - Passwort Zurücksetzung", 
                "<p>Passwort vergessen?</p>"
                        + "<p>Um ein neues Passwort zu setzen und wieder alle Funktionen von "
                        + "Readtastic nutzen zu k&ouml;nnen, " 
                        + "<a href=\"localhost:8080/app/reset_password/testytesty\">klicke hier!</a></p>"
                        + "<p>Falls du oben keinen Link zum anklicken siehst oder der Link mit deinem "
                        + "E-Mail Programm nicht funktioniert, bitte den folgenden "
                        + "Link in die Adresszeile des Internetbrowsers kopieren: </p>"
                        + "<p>localhost:8080/app/reset_password/testytesty</p>"
                        + "<p>Solltest du die Passwort&auml;nderung f&uuml;r Readtastic nicht "
                        + "angefordert haben, ignoriere diese E-Mail.</p>");
    }
    
}
