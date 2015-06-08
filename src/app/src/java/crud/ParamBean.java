package crud;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
@ManagedBean
@SessionScoped
/**
 * @see  http://www.itcuties.com/j2ee/jsf-get-context-param/#sthash.cY8rdbcI.dpuf
 * 
 * Keine Auslese
 */
public class ParamBean implements Serializable {
 
    private static final long serialVersionUID = 8541599579318786034L;
    
    /**
     * This method returns the book parameter value.
     * @return
     */
    public String getContextParameter() {
         return FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getInitParameter("book");
    }  
}