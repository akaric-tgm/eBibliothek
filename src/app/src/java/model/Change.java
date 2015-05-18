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

}
