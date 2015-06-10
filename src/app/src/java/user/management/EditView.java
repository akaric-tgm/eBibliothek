/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.management;

import crud.UserController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import entity.UserGroups;
import crud.UserGroupsController;
import entity.User;
import javax.inject.Inject;

@ManagedBean(name = "dtEditView")
@ViewScoped
public class EditView implements Serializable {

    private List<UserGroups> users1;
    private List<UserGroups> users2;

    @Inject
    private UserGroupsController service;
    @Inject
    private UserController usercontroller;

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
        
        User user = usercontroller.findByUsername(((UserGroups) event.getObject()).getUser().getUsername());
        user.setUser(((UserGroups) event.getObject()).getUser());
        UserGroups groups = service.findByUsername(((UserGroups) event.getObject()).getUser().getUsername());
        groups.setGroupname(((UserGroups) event.getObject()).getGroupname());
        user.setUserGroups(groups);
        usercontroller.setSelected(user);
        usercontroller.update();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Bearbeitung abgebrochen", ((UserGroups) event.getObject()).getUser().getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
} 
