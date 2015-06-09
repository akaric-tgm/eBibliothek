/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import crud.UserController;
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
    public void erfolgreich() {
       // FacesMessage msg = new FacesMessage("User löschen", ((UserGroups) event.getObject()).getUser().getUsername());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        
       // User user = uc.findByUsername(((UserGroups) event.getObject()).getUser().getUsername());
       // user.setUser(((UserGroups) event.getObject()).getUser());
        //System.out.print(user.getFirstName());
        //uc.setSelected(user);
        uc.destroy();
        addMessage("Erfolg", "Buch wurde gelöscht!");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
