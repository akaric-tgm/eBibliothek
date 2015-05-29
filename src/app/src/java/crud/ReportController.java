package crud;

import entity.Report;
import crud.util.JsfUtil;
import crud.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "reportController")
@SessionScoped
public class ReportController implements Serializable {

    @EJB
    private crud.ReportFacade ejbFacade;
    private List<Report> items = null;
    private Report selected;

    public ReportController() {
    }

    public Report getSelected() {
        return selected;
    }

    public void setSelected(Report selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getReportPK().setUsername(selected.getUser().getUsername());
        selected.getReportPK().setBookid(selected.getBook().getBookid());
    }

    protected void initializeEmbeddableKey() {
        selected.setReportPK(new entity.ReportPK());
    }

    private ReportFacade getFacade() {
        return ejbFacade;
    }

    public Report prepareCreate() {
        selected = new Report();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ReportCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ReportUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ReportDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Report> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public List<Report> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Report> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Report.class)
    public static class ReportControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReportController controller = (ReportController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reportController");
            return controller.getFacade().find(getKey(value));
        }

        entity.ReportPK getKey(String value) {
            entity.ReportPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.ReportPK();
            key.setUsername(values[0]);
            key.setBookid(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(entity.ReportPK value) {
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
            if (object instanceof Report) {
                Report o = (Report) object;
                return getStringKey(o.getReportPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Report.class.getName()});
                return null;
            }
        }

    }

}
