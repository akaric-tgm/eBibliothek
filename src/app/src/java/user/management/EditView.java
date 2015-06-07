/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.management;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import entity.User;
import crud.UserController;
import org.primefaces.event.CellEditEvent;

@ManagedBean(name = "dtEditView")
@ViewScoped
public class EditView implements Serializable {

    private List<User> users1;
    private List<User> users2;

    @ManagedProperty("#{userController}")
    private UserController service;

    @PostConstruct
    public void init() {
        users1 = service.getItems();
        users2 = service.getItems();
    }

    public List<User> getUsers1() {
        return users1;
    }

    public List<User> getUsers2() {
        return users2;
    }

    public List<String> getRoles() {
        for(String r : service.getRoles()){
        }
        return service.getRoles();
    }
    
    public void setService(UserController service) {
        this.service = service;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Userdaten geändert", ((User) event.getObject()).getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Bearbeitung abgebrochen", ((User) event.getObject()).getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
} 
