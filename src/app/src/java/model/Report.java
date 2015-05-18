package model;

import java.util.Date;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 * Modell fuer einen Report
 * 
 * @author Niklas Hohenwarter
 */
@RequestScoped
@Named
public class Report {

	private String mongoid;

	private String comment;

	private Date reportdate;

	private String status;

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
     * @return the reportdate
     */
    public Date getReportdate() {
        return reportdate;
    }

    /**
     * @param reportdate the reportdate to set
     */
    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
