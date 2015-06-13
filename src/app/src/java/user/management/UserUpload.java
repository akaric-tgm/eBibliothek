/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.management;

import crud.UserGroupsController;
import entity.User;
import entity.UserGroups;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 * Diese erlaubt des dem Admin csv Files hochzuladen.
 * Die Daten vom csv File werden entnommen und in die Datenbank gespeichert
 * @author Philipp Adler
 * @version 2015-06-11
 */

@Named("userupload")
@RequestScoped
public class UserUpload {

    @Inject private UserGroupsController usergroupscontroller;

    /**
     * In dieser Methode kann man Files hochladen
     * @param event Filedaten wie Inhalt
     */
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        InputStream file = null;
        try {
            file = event.getFile().getInputstream();
        } catch (IOException ex) {
            Logger.getLogger(UserUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        insertintoDB(file);

    }

    /**
     * Diese Methode liest den InputStream aus und speichert die Daten in die Datenbank
     * @param is der Inputstream wo alle Daten des Files drin stehen
     */
    private void insertintoDB(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        ArrayList<User> users = new ArrayList<>();
        ArrayList<UserGroups> groups = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(is));
            boolean status = true;
            while ((line = br.readLine()) != null) {
                if (status) {//beim erstenmal die Spaltennamen
                    status = false;
                } else {
                    String[] daten = line.split(";");
                    User user = new User();
                    user.setUser(daten);
                    users.add(user);
                    UserGroups group = new UserGroups();
                    group.setUsername(daten[0]);
                    group.setGroupname(daten[10]);
                    group.setUser(user);
                    groups.add(group);
                    for (UserGroups g : groups) {
                        usergroupscontroller.setSelected(g);
                        usergroupscontroller.create();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}