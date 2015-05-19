package model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
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
    
    public BasicDBObject toDBObject() {
        BasicDBObject doc = new BasicDBObject();

        doc.put("changedate", getChangedate());
        doc.put("page", getPage());

        return doc;
    }

    public static Change fromDBObject(DBObject doc) {
        Change c = new Change();

        c.setMongoid((String) doc.get("_id"));
        c.setChangedate((Date) doc.get("changedate"));
        c.setPage((int) doc.get("page"));
;

        return c;
    }

}
