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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the fid
     */
    public int getFid() {
        return fid;
    }

    /**
     * @param fid the fid to set
     */
    public void setFid(int fid) {
        this.fid = fid;
    }

    /**
     * @return the gid
     */
    public int getGid() {
        return gid;
    }

    /**
     * @param gid the gid to set
     */
    public void setGid(int gid) {
        this.gid = gid;
    }

    /**
     * @return the pwtoken
     */
    public String getPwtoken() {
        return pwtoken;
    }

    /**
     * @param pwtoken the pwtoken to set
     */
    public void setPwtoken(String pwtoken) {
        this.pwtoken = pwtoken;
    }

    /**
     * @return the emailtoken
     */
    public String getEmailtoken() {
        return emailtoken;
    }

    /**
     * @param emailtoken the emailtoken to set
     */
    public void setEmailtoken(String emailtoken) {
        this.emailtoken = emailtoken;
    }

}
