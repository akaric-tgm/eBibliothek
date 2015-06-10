/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.rate;

import crud.ContributionController;
import entity.Contribution;
import entity.ContributionPK;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 *
 * @author Melanie Goebel
 */

@Named("rateBook")
@SessionScoped
public class RateBook implements Serializable{
    @Min(1) 
    @Max(5)
    private int rating;
    private String message;
    private int bookId;
    private String username;
    @Inject
    private ContributionController conc;
    
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
    public void save(){
       Contribution c = new Contribution(username,bookId);
       ContributionPK cpk = new ContributionPK(username,bookId);
       c.setRating(new Short(new Integer(rating).shortValue()));
       c.setComment(message);
       c.setContributionPK(cpk);
       
       conc.setSelected(c);
       // Embeddedkeys fehlen!
       //conc.create();
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
