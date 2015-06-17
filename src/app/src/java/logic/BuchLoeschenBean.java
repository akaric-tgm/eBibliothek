package logic;
 
import crud.BookController;
import entity.Book;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
 
import org.primefaces.event.CloseEvent;
/**
 * 
 * @author Adin Karic
 */
@Named("buchLoeschenBean")
@RequestScoped
public class BuchLoeschenBean {
 @Inject BookController bc;
    public void erfolgreich() {
        Book buch = bc.getBookByBookId(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookid")));
        bc.setSelected(buch);
        bc.destroy();
        addMessage("Erfolg", "Buch wurde gelöscht!");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}