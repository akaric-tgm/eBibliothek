package model;

import java.util.Date;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 * Modell fuer einen Change
 * 
 * @author Niklas Hohenwarter
 */
@RequestScoped
@Named
public class Change {

	private String mongoid;

	private Date changedate;

	private int page;

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
     * @return the changedate
     */
    public Date getChangedate() {
        return changedate;
    }

    /**
     * @param changedate the changedate to set
     */
    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

}
