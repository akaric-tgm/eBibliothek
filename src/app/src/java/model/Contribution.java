package model;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;


/**
 * Modell fuer eine Contribution
 * 
 * @author Niklas Hohenwarter
 */
@RequestScoped
@Named
public class Contribution {

	private String mongoid;

	private String comment;

	private String rating;

    /**
     * @return the mongoid
     */
    public String getMongoid() {
        return mongoid;
    }

    /**
     * @param mongoid the mongoid to set
     */
    public void setMongoid(String mongoid) {
        this.mongoid = mongoid;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

}
