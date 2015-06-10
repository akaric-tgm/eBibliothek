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

    public UserGroupsController() {
    }
    
    public UserGroups findByUsername(String username){
        List<UserGroups> user = getFacade().findByUsername(username);
        if(user.size() <= 0){
            return null;
        }else{
            return user.get(0);
        }
    }

    public UserGroups getSelected() {
        return selected;
    }

    public void setSelected(UserGroups selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserGroupsFacade getFacade() {
        return ejbFacade;
    }

    public UserGroups prepareCreate() {
        selected = new UserGroups();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserGroupsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserGroupsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserGroupsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UserGroups> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<String> getRoles() {
        return Arrays.asList(roles);
    }
    
    public List<String> getBlocked() {
        return Arrays.asList(blocked);
    }

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

    public UserGroups getUserGroups(java.lang.String id) {
        return getFacade().find(id);
    }

public List<UserGroups> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

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
