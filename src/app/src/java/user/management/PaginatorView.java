/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.management;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import crud.UserGroupsController;
import entity.UserGroups;

@ManagedBean(name = "dtPaginatorView")
@ViewScoped
public class PaginatorView implements Serializable {

    private List<UserGroups> users;

    @ManagedProperty("#{userGroupsController}")
    private UserGroupsController service;

    @PostConstruct
    public void init() {
        System.out.println("A");
        users = service.getItems();
    }

    public List<UserGroups> getUsers() {
        return users;
    }

    public void setService(UserGroupsController service) {
        this.service = service;
    }
} 
