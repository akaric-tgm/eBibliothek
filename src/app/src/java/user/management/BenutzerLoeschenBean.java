/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.management;

import crud.UserController;
import crud.UserGroupsController;
import entity.User;
import entity.UserGroups;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Adin Karic
 */
@Named("benutzerLoeschenBean")
@RequestScoped
public class BenutzerLoeschenBean {
    @Inject UserController uc;
    @Inject UserGroupsController gc;
    public void erfolgreich(UserGroups usergroup) {
        gc.setSelected(usergroup);
        gc.destroy();
        uc.setSelected(usergroup.getUser());
        System.out.print(uc.getSelected().getUsername());
        uc.destroy();
        addMessage("Erfolg", uc.getSelected().getUsername()+" wurde gelöscht!");
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
