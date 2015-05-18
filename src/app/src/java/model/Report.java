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

}
