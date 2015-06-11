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

/**
 * Diese Klasse stellt einen Paginator dar, er holt sich die Daten aus der DB und leitet sie an die View weiter
 * @author Philipp Adler
 * @version 2015-06-11
 */

@ManagedBean(name = "dtPaginatorView")
@ViewScoped
public class PaginatorView implements Serializable {

    private List<UserGroups> users;

    @ManagedProperty("#{userGroupsController}")
    private UserGroupsController service;

    /**
     * Diese Methode holt sich die UserGroups aus der DB
     */
    @PostConstruct
    public void init() {
        users = service.getItems();
    }

    /**
     * Diese Getter-Methode gibt alle UserGroups als Liste aus
     * @return Liste der Usergroups
     */
    public List<UserGroups> getUsers() {
        return users;
    }

    /**
     * Hier wird Controller fuer die UserGroup veraendert
     * @param service der neue Controller
     */
    public void setService(UserGroupsController service) {
        this.service = service;
    }
} 
