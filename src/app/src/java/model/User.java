package model;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;


/**
 * Modell fuer einen User
 * 
 * @author Niklas Hohenwarter
 */
@RequestScoped
@Named
public class User {

	private String mongoid;

	private String username;

	private String password;

	private String firstName;

	private String lastName;

	private String email;

	private String role;

	private int fid;

	private int gid;

	private String pwtoken;

	private String emailtoken;

}
