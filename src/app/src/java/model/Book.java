package model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 * Modell fuer ein Buch
 * 
 * @author Niklas Hohenwarter
 */
@RequestScoped
@Named
public class Book {

	private String mongoid;

	private String title;

	private String author;

	private String summary;

	private String language;

	private String filepath;

	private int downloads;

	private int views;

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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return the downloads
     */
    public int getDownloads() {
        return downloads;
    }

    /**
     * @param downloads the downloads to set
     */
    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    /**
     * @return the views
     */
    public int getViews() {
        return views;
    }

    /**
     * @param views the views to set
     */
    public void setViews(int views) {
        this.views = views;
    }
    
    public BasicDBObject toDBObject() {
        BasicDBObject doc = new BasicDBObject();

        doc.put("author", getAuthor());
        doc.put("downloads", getDownloads());
        doc.put("filepath", getFilepath());
        doc.put("language", getLanguage());
        doc.put("summary", getSummary());
        doc.put("title", getTitle());
        doc.put("views", getViews());

        return doc;
    }

    public static Book fromDBObject(DBObject doc) {
        Book b = new Book();

        b.setMongoid((String) doc.get("_id"));
        b.setAuthor((String) doc.get("author"));
        b.setDownloads((int) doc.get("downloads"));
        b.setFilepath((String) doc.get("filepath"));
        b.setLanguage((String) doc.get("language"));
        b.setSummary((String) doc.get("summary"));
        b.setTitle((String) doc.get("title"));
        b.setViews((int) doc.get("views"));

        return b;
    }

}
