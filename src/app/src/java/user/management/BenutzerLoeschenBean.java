/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.management;

import crud.UserController;
import crud.UserGroupsController;
import entity.UserGroups;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Diese Klasse loescht einen vorhandenen User und UserGroup
 * @author Philipp Adler
 * @author Adin Karic
 * @version 2015-06-11
 */
@Named("benutzerLoeschenBean")
@RequestScoped
public class BenutzerLoeschenBean {
    @Inject UserController uc;
    @Inject UserGroupsController gc;
    
    /**
     * Diese Methode loescht eine Usergroup und somit auch einen User
     * @param usergroup die UserGroup die geloescht werden soll
     */
    public void erfolgreich(UserGroups usergroup) {
        gc.setSelected(usergroup);
        gc.destroy();
        uc.setSelected(usergroup.getUser());
        System.out.print(uc.getSelected().getUsername());
        uc.destroy();
        addMessage("Erfolg", " wurde gelöscht!");
    }
    
    /**
     * Diese Methode erzeugt ein Pop-up welches eine Message ausgibt, ob es geklappt oder nicht geklappt hat
     * @param summary Zusammenfassung
     * @param detail Details
     */
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
