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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 * Diese erlaubt des dem Admin csv Files hochzuladen. Die Daten vom csv File
 * werden entnommen und in die Datenbank gespeichert
 *
 * @author Philipp Adler
 * @version 2015-06-11
 */
@Named("userupload")
@RequestScoped
public class UserUpload {

    @Inject
    private UserGroupsController usergroupscontroller;

    /**
     * In dieser Methode kann man Files hochladen
     *
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
     * Diese Methode liest den InputStream aus und speichert die Daten in die
     * Datenbank
     *
     * @param is der Inputstream wo alle Daten des Files drin stehen
     */
    private void insertintoDB(InputStream is) {

        BufferedReader br = null;
        String line;
        String[] keys = null;
        Map<String, String> mapping = new HashMap<>();
        ArrayList<UserGroups> groups = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(is));
            boolean status = true;
            while ((line = br.readLine()) != null) {
                if (status) {//beim erstenmal die Spaltennamen
                    keys = line.split(";");
                    status = false;
                } else {
                    String[] daten = line.split(";");
                    if (mapping.isEmpty()) {
                        System.out.println(daten[0] + " A");
                        for (int i = 0; i < keys.length; i++) {
                            mapping.put(keys[i], daten[i]);
                        }
                    } else {
                        System.out.println(daten[0]);
                        for (int i = 0; i < keys.length; i++) {
                            mapping.replace(keys[i], daten[i]);
                        }
                    }
                    User user = new User();
                    boolean merk = true;
                    for (Map.Entry<String, String> e : mapping.entrySet()) {
                        if (merk) {
                            switch (e.getKey()) {
                                case "username":
                                    try {
                                        user.setUsername(e.getValue());
                                    } catch (NullPointerException f) {
                                        merk = false;
                                    }
                                    break;

                                case "password":
                                    if (!e.getValue().isEmpty()) {
                                        user.setPassword(e.getValue());
                                    } else {
                                        merk = false;
                                    }
                                    break;

                                case "firstName":
                                    if (!e.getValue().isEmpty()) {
                                        user.setFirstName(e.getValue());
                                    } else {
                                        merk = false;
                                    }
                                    break;

                                case "lastName":
                                    if (!e.getValue().isEmpty()) {
                                        user.setLastName(e.getValue());
                                    } else {
                                        merk = false;
                                    }
                                    break;

                                case "email":
                                    if (!isValidEmailAddress(e.getValue())) {
                                        merk = false;
                                    } else {
                                        user.setEmail(e.getValue());
                                    }
                                    break;

                                case "fid":
                                    try {
                                        user.setFid(Integer.parseInt(e.getValue()));
                                    } catch (NumberFormatException f) {
                                        f.getMessage();
                                        merk = false;
                                    }
                                    break;

                                case "gid":
                                    try {
                                        user.setGid(Integer.parseInt(e.getValue()));
                                    } catch (NumberFormatException f) {
                                        merk = false;
                                    }
                                    break;

                                case "pwtoken":

                                    if (!e.getValue().isEmpty()) {
                                        user.setPwtoken(e.getValue());
                                    } else {
                                        merk = false;
                                    }
                                    break;

                                case "emailtoken":
                                    if (!e.getValue().isEmpty()) {
                                        user.setEmailtoken(e.getValue());
                                    } else {
                                        merk = false;
                                    }
                                    break;

                                case "blocked":
                                    if (e.getValue().equals("true") || e.getValue().equals("1")) {
                                        user.setBlocked(true);
                                    } else {
                                        user.setBlocked(false);
                                    }
                                    break;
                            }
                        }
                    }
                    if (merk) {
                        UserGroups group = new UserGroups();
                        group.setUsername(mapping.get("username"));
                        try {
                            mapping.get("groupname").isEmpty();
                            if((mapping.get("groupname").equals("User") || mapping.get("groupname").equals("Moderator") || mapping.get("groupname").equals("Admin"))){
                                group.setGroupname(mapping.get("User"));
                            }else{
                                group.setGroupname(mapping.get("groupname"));
                            }
                            group.setUser(user);
                            groups.add(group);
                            for (UserGroups g : groups) {
                                usergroupscontroller.setSelected(g);
                                usergroupscontroller.create();
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("fail");
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

    /**
     * Diese Methode kontrolliert ob die Email-Adresse valid ist
     * @param email die Email die ueberprueft wird
     * @return ob die Email valid
     */
    private boolean isValidEmailAddress(String email) {
        boolean stricterFilter = true;
        String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
        String emailRegex = stricterFilter ? stricterFilterString : laxString;
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
