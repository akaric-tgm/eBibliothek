/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Selina Brinnich
 */
@Named("resetPasswordBean")
@SessionScoped
public class ResetPasswordBean implements Serializable {
    
    @EJB
    EmailSender emailSender;
    
    private String email = "";
    private String username = "";

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

    /**
     * Creates a new instance of ResetPasswordBean
     */
    public ResetPasswordBean() {
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
