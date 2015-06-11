package crud;

import entity.User;
import crud.util.JsfUtil;
import crud.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 * Diese Klasse stellt den Controller des Users dar
 * @author Philipp Adler
 * @version 2015-06-11
 */

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private crud.UserFacade ejbFacade;
    private List<User> items = null;
    private User selected;
    private boolean logged_in = false;
    private User logged_in_user;
    private static final String SALT = "bibliothek+";

    /**
     * Default Konstruktor
     */
    public UserController() {
    }

    public static String getSALT() {
        return SALT;
    }

    /**
     * Diese Methode gibt aus ob der User eingeloggt ist
     * @return den Status des Login
     */
    public boolean isLogged_in() {
        return logged_in;
    }

    /**
     * Die Methode aendert den Status des Logins
     * @param logged_in true der User ist eingeloggt, false der User ist nicht eingeloggt
     */
    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
    }

    /**
     * Die Getter-Methode gibt aus welcher User eingeloggt ist
     * @return der eingeloggte User
     */
    public User getLogged_in_user() {
        return logged_in_user;
    }

    /**
     * Die Methode aendert den User der eingeloggt ist
     * @param logged_in_user der neue User der eingeloggt ist
     */
    public void setLogged_in_user(User logged_in_user) {
        this.logged_in_user = logged_in_user;
    }
    
    /**
     * Diese Methode verschluesselt den Uebergebenen Text
     * @param text der Text bzw. das Passwort, welches verschluesselt wird
     * @return den verschluesselten Text
     */
    public String getHashedString(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(text.getBytes("UTF-8"));
 
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < hashedBytes.length; i++) {
                stringBuffer.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Diese Methode loggt den User ein und kontrolliert ob der Username & das Passwort korrekt eingegeben wurden
     * @param username der Username 
     * @param password das Passwort
     * @return die URL wo der User weitergeleitet wird
     */
    public String loginUser(String username, String password){
        String hashedPassword = getHashedString(SALT+password);
        if(isValidCredentials(username, hashedPassword)){
            logged_in = true;
            logged_in_user = findByUsername(username);
            return "index?faces-redirect=true";
        }else{
            return "loginError?faces-redirect=true";
        }
    }
    
    /**
     * Hier wird der User ausgeloggt
     * @return die URL zum Login
     */
    public String logoutUser(){
        if(logged_in){
            logged_in = false;
            logged_in_user = null;
        }
        return "benutzer_einloggen?faces-redirect=true";
    }
    
    /**
     * findByUsername sucht in der Datenbank nach einem bestimmten User
     * @param username der User der gesucht werden soll
     * @return den gefundenen User, falls es ihn nicht gibt wird ein leeres Objekt zurueck gegeben
     */
    public User findByUsername(String username){
        List<User> user = getFacade().findByUsername(username);
        if(user.size() <= 0){
            return null;
        }else{
            return user.get(0);
        }
    }
    
    /**
     * findByEmail sucht in der Datenbank nach einem bestimmten User
     * @param email der User der nach der Email gesucht werden soll
     * @return den gefundenen User, falls es ihn nicht gibt wird ein leeres Objekt zurueck gegeben
     */
    public User findByEmail(String email){
        List<User> user = getFacade().findByEmail(email);
        if(user.size() <= 0){
            return null;
        }else{
            return user.get(0);
        }
    }
    
    /**
     * Diese Methode ueberprueft ob die Logindaten korrekt sind
     * @param username der Username
     * @param password das Passwort
     * @return ob die Daten richtig sind und der User die ebibliothek betreten darf
     */
    public boolean isValidCredentials(String username, String password){
        List<User> user= getFacade().findByUsername(username);
        if(user.size() <= 0){
            return false;
        }else{
            return user.get(0).getPassword().equals(password);
        }
    }
    
    /**
     * Diese Methode ueberprueft ob die Logindaten korrekt sind
     * @param username der Username
     * @param email das Email-Adresse
     * @return ob die Daten richtig sind und der User die ebibliothek betreten darf
     */
    public boolean isValidUsernameAndEmail(String username, String email){
        List<User> user= getFacade().findByUsername(username);
        if(user.size() <= 0){
            return false;
        }else{
            return user.get(0).getEmail().equals(email);
        }
    }

    /**
     * Gibt aus welcher User selected wurde
     * @return der ausgewaehlte User
     */
    public User getSelected() {
        return selected;
    }

    /**
     * Diese Methode selectiert einen User mit dem man dan ein Update,Create,Drop durchfuehren kann
     * @param selected der User der ausgewaehlt wird
     */
    public void setSelected(User selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    /**
     * Diese Getter-Methode gibt die UserFacade zurueck die mit der DB in Verbindung steht
     * @return die UserFacade
     */
    private UserFacade getFacade() {
        return ejbFacade;
    }

    /**
     * Diese Methode erzeugt einen neuen User
     * @return den neu erzeugten User
     */
    public User prepareCreate() {
        selected = new User();
        initializeEmbeddableKey();
        return selected;
    }

    /**
     * Hier wird der selectierte User erzeugt
     */
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Hier wird der selectierte User geupdatet
     */
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
    }

    /**
     * Hier wird der selectierte User geloescht
     */
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Diese Methode gibt alle in der DB vorhandenen User als Liste zurueck
     * @return die List der User
     */
    public List<User> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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
                if (persistAction == PersistAction.DELETE) {
                    getFacade().remove(selected);
                } else if(persistAction == PersistAction.UPDATE){
                    getFacade().edit(selected);
                } else if(persistAction == PersistAction.CREATE){
                    getFacade().create(selected);
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
     * Diese Methode gibt alle in der DB vorhandenen User als Liste zurueck
     * @return die List der User
     */
    public List<User> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    /**
     * Diese Methode gibt alle in der DB vorhandenen User als Liste zurueck
     * @return die List der User
     */
    public List<User> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getFacade().find(getKey(value));
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
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getUsername());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), User.class.getName()});
                return null;
            }
        }

    }

}
