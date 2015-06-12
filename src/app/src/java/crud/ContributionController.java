package crud;

import entity.Contribution;
import crud.util.JsfUtil;
import crud.util.JsfUtil.PersistAction;
import entity.Book;

import java.io.Serializable;
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

@Named("contributionController")
@SessionScoped
public class ContributionController implements Serializable {

    @EJB
    private crud.ContributionFacade ejbFacade;
    private List<Contribution> items = null;
    private Contribution selected;

    public ContributionController() {
    }

    public Contribution getSelected() {
        return selected;
    }

    public void setSelected(Contribution selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getContributionPK().setUsername(selected.getUser().getUsername());
        selected.getContributionPK().setBookid(selected.getBook().getBookid());
    }

    protected void initializeEmbeddableKey() {
        selected.setContributionPK(new entity.ContributionPK());
    }

    private ContributionFacade getFacade() {
        return ejbFacade;
    }

    public Contribution prepareCreate() {
        selected = new Contribution();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ContributionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ContributionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ContributionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Contribution> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            //setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.DELETE) {
                    getFacade().remove(selected);
                } else if(persistAction == PersistAction.UPDATE) {
                    getFacade().edit(selected);
                } else if(persistAction == PersistAction.CREATE) {
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

    public List<Contribution> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Contribution> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Contribution.class)
    public static class ContributionControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContributionController controller = (ContributionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contributionController");
            return controller.getFacade().find(getKey(value));
        }

        entity.ContributionPK getKey(String value) {
            entity.ContributionPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.ContributionPK();
            key.setUsername(values[0]);
            key.setBookid(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(entity.ContributionPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUsername());
            sb.append(SEPARATOR);
            sb.append(value.getBookid());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Contribution) {
                Contribution o = (Contribution) object;
                return getStringKey(o.getContributionPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contribution.class.getName()});
                return null;
            }
        }

    }

}
