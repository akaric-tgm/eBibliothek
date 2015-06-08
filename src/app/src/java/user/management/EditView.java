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
import entity.UserGroups;
import crud.UserGroupsController;
import org.primefaces.event.CellEditEvent;

@ManagedBean(name = "dtEditView")
@ViewScoped
public class EditView implements Serializable {

    private List<UserGroups> users1;
    private List<UserGroups> users2;

    @ManagedProperty("#{userGroupsController}")
    private UserGroupsController service;

    @PostConstruct
    public void init() {
        users1 = service.getItems();
        users2 = service.getItems();
    }

    public List<UserGroups> getUsers1() {
        return users1;
    }

    public List<UserGroups> getUsers2() {
        return users2;
    }
    
    public List<String> getRoles() {
        return service.getRoles();
    }
    
    public List<String> getBlocked() {
        return service.getBlocked();
    }
    
    public void setService(UserGroupsController service) {
        this.service = service;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Userdaten geändert", ((UserGroups) event.getObject()).getUser().getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Bearbeitung abgebrochen", ((UserGroups) event.getObject()).getUser().getUsername());
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
