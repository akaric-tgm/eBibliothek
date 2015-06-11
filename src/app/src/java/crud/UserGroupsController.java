package crud;

import entity.UserGroups;
import crud.util.JsfUtil;
import crud.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Diese Klasse stellt den Controller der UserGroup dar
 * @author Philipp Adler
 * @version 2015-06-11
 */

@Named("userGroupsController")
@SessionScoped
public class UserGroupsController implements Serializable {

    @EJB
    private crud.UserGroupsFacade ejbFacade;
    private List<UserGroups> items = null;
    private UserGroups selected;
    private final static String[] roles;
    private final static String[] blocked;
    
    static {
        roles = new String[3];
        roles[0] = "User";
        roles[1] = "Moderator";
        roles[2] = "Admin";
    }
    
    static {
        blocked = new String[2];
        blocked[0] = "true";
        blocked[1] = "false";
    }

    /**
     * Default Konstruktor
     */
    public UserGroupsController() {
    }
    
    /**
     * findByUsername sucht in der Datenbank nach einer bestimmten UserGroup
     * @param username die UserGroup nach der gesucht werden soll
     * @return die gefundenen UserGroup, falls es ihn nicht gibt wird ein leeres Objekt zurueck gegeben
     */
    public UserGroups findByUsername(String username){
        List<UserGroups> user = getFacade().findByUsername(username);
        if(user.size() <= 0){
            return null;
        }else{
            return user.get(0);
        }
    }

    /**
     * Gibt aus welche UserGroup selected wurde
     * @return die ausgewaehlte UserGroup
     */
    public UserGroups getSelected() {
        return selected;
    }

    /**
     * Diese Methode selectiert eine UserGroup mit der man dann ein Update,Create,Drop durchfuehren kann
     * @param selected die UserGroup die ausgewaehlt wird
     */
    public void setSelected(UserGroups selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    /**
     * Diese Getter-Methode gibt die UserGroupsFacade zurueck die mit der DB in Verbindung steht
     * @return die UserGroupsFacade
     */
    private UserGroupsFacade getFacade() {
        return ejbFacade;
    }

    /**
     * Diese Methode erzeugt eine neue UserGroup
     * @return die neu erzeugte UserGroup
     */
    public UserGroups prepareCreate() {
        selected = new UserGroups();
        initializeEmbeddableKey();
        return selected;
    }

    /**
     * Hier wird die selectierte UserGroup erzeugt
     */
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserGroupsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Hier wird die selectierte UserGroup geupdate
     */
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserGroupsUpdated"));
    }

    /**
     * Hier wird die selectierte UserGroup geloescht
     */
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserGroupsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Diese Methode gibt alle in der DB vorhandenen UserGroups als Liste zurueck
     * @return die List der UserGroups
     */
    public List<UserGroups> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    /**
     * Diese Methode gibt alle Rolenmoeglichkeiten zurueck
     * @return Liste der Moeglichkeiten
     */
    public List<String> getRoles() {
        return Arrays.asList(roles);
    }
    
    /**
     * Diese Methode gibt alle Sperrmoeglichkeiten zurueck
     * @return Liste der Moeglichkeiten
     */
    public List<String> getBlocked() {
        return Arrays.asList(blocked);
    }

    /**
     * Diese Methode ruft die CRUD Methoden auf welche die Daten in der DB aendern
     * @param persistAction gibt an welchen CRUD Befehl ausgefuehrt werden soll
     * @param successMessage wenn das CRUD erfolgreich war wird ein Pop-up mit der uebergebenen Message ausgegeben
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Diese Methode sucht in DB nach einen bestimmten User
     * @param id ID
     * @return die gefundenen UserGroup
     */
    public UserGroups getUserGroups(java.lang.String id) {
        return getFacade().find(id);
    }

    /**
     * Diese Methode gibt alle in der DB vorhandenen UserGroups als Liste zurueck
     * @return die List der UserGroups
     */
    public List<UserGroups> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    
    /**
     * Diese Methode gibt alle in der DB vorhandenen UserGroups als Liste zurueck
     * @return die List der UserGroups
     */
    public List<UserGroups> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UserGroups.class)
    public static class UserGroupsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserGroupsController controller = (UserGroupsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userGroupsController");
            return controller.getUserGroups(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UserGroups) {
                UserGroups o = (UserGroups) object;
                return getStringKey(o.getUsername());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UserGroups.class.getName()});
                return null;
            }
        }

    }

}
