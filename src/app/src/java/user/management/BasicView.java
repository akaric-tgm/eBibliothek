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
import entity.User;
import crud.UserController;


@ManagedBean(name = "BasicView")
@ViewScoped
public class BasicView implements Serializable {

    private List<User> users;

    @ManagedProperty("#{userController}")
    private UserController service;

    @PostConstruct
    public void init() {
        users = service.getItems();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setService(UserController service) {
        this.service = service;
    }
}