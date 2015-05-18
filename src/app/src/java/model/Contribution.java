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

}
