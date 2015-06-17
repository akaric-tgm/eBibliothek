package logic;
 
import crud.UserController;
import entity.User;
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
@Named("userLoeschenBean")
@RequestScoped
public class UserLoeschenBean {
 @Inject UserController uc;
    public void delete() {
        User user = uc.getLogged_in_user();
        uc.setSelected(user);
        uc.logoutUser();
        uc.destroy();
        System.out.println("Benutzer wurde gelöscht");
    }
     
}
