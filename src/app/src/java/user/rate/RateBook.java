/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.rate;

import crud.ContributionController;
import crud.UserController;
import entity.Contribution;
import entity.ContributionPK;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * Kuemmert sich um Bewertung und Kommentar eines User fuer ein Buch
 * @author Melanie Goebel
 */

@Named("rateBook")
@SessionScoped
public class RateBook implements Serializable{
    @Min(1) // Bewertung muss ausgefuellt werden (mind. 1 Stern)
    @Max(5) // Nur 5 Sterne moeglich
    private int rating;
    private String message;
    private int bookId;
    private String username;
    @Inject
    private ContributionController conc;   
    @Inject
    private UserController usec;
    
    public void setRating(int rating){
        this.rating = rating;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public int getRating(){
        return rating;
    }
    public String getMessage(){
        return message;
    }
    /**
     * Speichert Kommentar und Bewertung in der Datenbank
     * @return die zu weiterleitendne URL
     */
    public String save(){
       Contribution c = new Contribution(usec.getLogged_in_user().getUsername(),bookId);
       ContributionPK cpk = new ContributionPK(usec.getLogged_in_user().getUsername(),bookId);//eingeloggten User und BuchID
       c.setRating(new Short(new Integer(rating).shortValue()));
       c.setComment(message);
       c.setContributionPK(cpk);
       conc.setSelected(c);
       conc.create();
       return "book_details?faces-redirect=true&book="+bookId;//weiterleiten auf die Book-Detailseite mit der id bookId
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
