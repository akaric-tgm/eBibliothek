package crud;

import entity.User;
import crud.util.JsfUtil;
import crud.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Arrays;
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

    public UserController() {
    }

    public static String getSALT() {
        return SALT;
    }

    public boolean isLogged_in() {
        return logged_in;
    }

    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
    }

    public User getLogged_in_user() {
        return logged_in_user;
    }

    public void setLogged_in_user(User logged_in_user) {
        this.logged_in_user = logged_in_user;
    }
    
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
    
    public String logoutUser(){
        if(logged_in){
            logged_in = false;
            logged_in_user = null;
        }
        return "benutzer_einloggen?faces-redirect=true";
    }
    
    public User findByUsername(String username){
        List<User> user = getFacade().findByUsername(username);
        if(user.size() <= 0){
            return null;
        }else{
            return user.get(0);
        }
    }
    
    public User findByEmail(String email){
        List<User> user = getFacade().findByEmail(email);
        if(user.size() <= 0){
            return null;
        }else{
            return user.get(0);
        }
    }
    
    public boolean isValidCredentials(String username, String password){
        List<User> user= getFacade().findByUsername(username);
        if(user.size() <= 0){
            return false;
        }else{
            return user.get(0).getPassword().equals(password);
        }
    }
    
    public boolean isValidUsernameAndEmail(String username, String email){
        List<User> user= getFacade().findByUsername(username);
        if(user.size() <= 0){
            return false;
        }else{
            return user.get(0).getEmail().equals(email);
        }
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public User prepareCreate() {
        selected = new User();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<User> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

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

    public List<User> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

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
