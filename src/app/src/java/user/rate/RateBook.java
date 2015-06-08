/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.rate;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
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
    
    public void setRating(int rating){
        this.rating = rating;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public int getRating(){
        return rating;
    }
    public String getMessage(String message){
        return message;
    }
}
