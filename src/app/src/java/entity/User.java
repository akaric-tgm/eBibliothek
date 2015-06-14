/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Diese Entity stellt einen User von der ebibliothek dar.
 * @author Philipp Adler
 * @version 2015-06-11
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByFid", query = "SELECT u FROM User u WHERE u.fid = :fid"),
    @NamedQuery(name = "User.findByGid", query = "SELECT u FROM User u WHERE u.gid = :gid"),
    @NamedQuery(name = "User.findByPwtoken", query = "SELECT u FROM User u WHERE u.pwtoken = :pwtoken"),
    @NamedQuery(name = "User.findByEmailtoken", query = "SELECT u FROM User u WHERE u.emailtoken = :emailtoken"),
    @NamedQuery(name = "user.findByBlocked", query = "SELECT u FROM User u WHERE u.blocked = :blocked")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "username")
    private String username;
    @Size(max = 64)
    @Column(name = "password")
    private String password;
    @Size(max = 20)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 30)
    @Column(name = "lastName")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 120)
    @Column(name = "email")
    private String email;
    @Column(name = "fid")
    private Integer fid;
    @Column(name = "gid")
    private Integer gid;
    @Size(max = 64)
    @Column(name = "pwtoken")
    private String pwtoken;
    @Size(max = 64)
    @Column(name = "emailtoken")
    private String emailtoken;
    @Column(name = "blocked")
    private Boolean blocked;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Report> reportCollection;
    @OneToMany(mappedBy = "username")
    private Collection<Book> bookCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<ChangeRequest> changeRequestCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserGroups userGroups;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Contribution> contributionCollection;

    /**
     * Default-Konstruktor
     */
    public User() {
    }

    /**
     * Konstruktor wo man den Primarykey, den Username, als Parameter angibt
     * @param username der eindeutige Username fuer den User
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Konstruktor der alle Attribute vom Benutzer als Parameter verlangt
     * @param username der eindeutige Username fuer den User
     * @param password das Passwort fuer den Login
     * @param firstName der Vorname des Users
     * @param lastName der Nachname des Users
     * @param email die Email-Adresse des User
     * @param pwtoken das Passworttoken
     * @param emailtoken das Emailtoken
     * @param blocked gibt an ob der User von der ebibliothek gesperrt wird
     */
    public User(String username, String password, String firstName, String lastName,
            String email, String pwtoken, String emailtoken, boolean blocked) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pwtoken = pwtoken;
        this.emailtoken = emailtoken;
        this.blocked = blocked;
    }

    /**
     * Das ist die Getter-Methode fuer den Username
     * @return den Username des Users
     */
    public String getUsername() {
        return username;
    }

    /**
     * Die Methode aendert den eindeutigen Usernamen
     * @param username der neue Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Das ist die Getter-Methode fuer das Passwort
     * @return das Passwort des Users
     */
    public String getPassword() {
        return password;
    }

    /**
     * Die Methode aendert das Passwort
     * @param password das neue Passwort des Users
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Das ist die Getter-Methode fuer den Vornamen
     * @return den Vornamen des Users
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Die Methode aendert den Vornamen
     * @param firstName der neue Vorname des Users
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Das ist die Getter-Methode fuer den Nachnamen
     * @return den Nachnamen des Users
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Die Methode aendert den Nachnamen
     * @param lastName der neue Nachname des Users
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Das ist die Getter-Methode fuer die Email-Adresse
     * @return die Email des Users
     */
    public String getEmail() {
        return email;
    }

    /**
     * Die Methode aendert die Email-Adresse
     * @param email die neue Email des Users
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Das ist die Getter-Methode fuer Fid
     * @return die Fid des Users
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * Die Methode aendert die Fid
     * @param fid die neue Fid des Users
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * Das ist die Getter-Methode fuer Gid
     * @return die Gid des Users
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * Die Methode aendert die Gid
     * @param gid die neue Gid des Users
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * Das ist die Getter-Methode fuer das Passworttoken
     * @return das Pwtoken des Users
     */
    public String getPwtoken() {
        return pwtoken;
    }

    /**
     * Die Methode aendert den Passworttoken
     * @param pwtoken der neue Pwtoken des Users
     */
    public void setPwtoken(String pwtoken) {
        this.pwtoken = pwtoken;
    }

    /**
     * Das ist die Getter-Methode fuer den Emailtoken
     * @return das Emailtoken des Users
     */
    public String getEmailtoken() {
        return emailtoken;
    }

    /**
     * Die Methode aendert den Emailtoken
     * @param emailtoken der neue Emailtoken des Users
     */
    public void setEmailtoken(String emailtoken) {
        this.emailtoken = emailtoken;
    }

    /**
     * Diese Getter-Methode gibt alle Buecher zurueck die der Users erstellt hat
     * @return eine Liste der Buecher die in Verbindung mit dem User stehen
     */
    @XmlTransient
    public Collection<Book> getBookCollection() {
        return bookCollection;
    }

    /**
     * Die Methode aendert die Buecher die mit dem User in Verbindung stehen
     * @param bookCollection neue Liste der Buecher die in Verbindung mit dem User stehen
     */
    public void setBookCollection(Collection<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    /**
     * Diese Getter-Methode gibt alle Bewertungen zurueck die der Users erstellt hat
     * @return eine Liste der Bewertungen die in Verbindung mit dem User stehen
     */
    @XmlTransient
    public Collection<Contribution> getContributionCollection() {
        return contributionCollection;
    }

    /**
     * Die Methode aendert die Bewertungen die mit dem User in Verbindung stehen
     * @param contributionCollection neue Liste der Bewertungen die in Verbindung mit dem User stehen
     */
    public void setContributionCollection(Collection<Contribution> contributionCollection) {
        this.contributionCollection = contributionCollection;
    }

    /**
     * Diese Getter-Methode gibt alle Meldungen zurueck die der Users erstellt hat
     * @return eine Liste der Meldungen die in Verbindung mit dem User stehen
     */
    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    /**
     * Die Methode aendert die Meldungen die mit dem User in Verbindung stehen
     * @param reportCollection neue Liste der Meldungen die in Verbindung mit dem User stehen
     */
    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    /**
     * Diese Getter-Methode gibt alle Aenderungen von Buechern zurueck die der Users erstellt hat
     * @return eine Liste der Aenderungen von Buechern die in Verbindung mit dem User stehen
     */
    @XmlTransient
    public Collection<ChangeRequest> getChangeRequestCollection() {
        return changeRequestCollection;
    }

    /**
     * Die Methode aendert die Aenderungen von Buechern die mit dem User in Verbindung stehen
     * @param changeRequestCollection neue Liste der Aenderungen von Buechern die in Verbindung mit dem User stehen
     */
    public void setChangeRequestCollection(Collection<ChangeRequest> changeRequestCollection) {
        this.changeRequestCollection = changeRequestCollection;
    }

    /**
     * Die hashCode-Methode gibt den Hashcode des Users zurueck
     * @return den Hash des Users
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    /**
     * Die equals Methode vergleicht den User mit einem anderen, uebergebenen, User
     * @param object der User der mit dem anderen verglichen wird
     * @return ob der uebergebene User ident mit dem anderen ist
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    /**
     * Die toString-Methode gibt alle wichtigen Daten des Users aus
     * @return Userdaten
     */
    @Override
    public String toString() {
        return "entity.User[ username=" + username + " ]";
    }

    /**
     * Das ist die Getter-Methode fuer die Sperre des Users
     * @return den Status ob der User gesperrt ist
     */
    public Boolean getBlocked() {
        return blocked;
    }

    /**
     * Diese Methode erlaubt es den Status der Usersperre zu aendern
     * @param blocked true wenn der User gesperrt werden soll, false keine Usersperre
     */
    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * Das ist die Getter-Methode fuer ein UserGroup-Objekt
     * @return die Usergroup des Users
     */
    public UserGroups getUserGroups() {
        return userGroups;
    }

    /**
     * Die Methode weist dem User eine neue Usergroup hinzu
     * @param userGroups die neue Usergroup des Users
     */
    public void setUserGroups(UserGroups userGroups) {
        this.userGroups = userGroups;
    }

    /**
     * Diese Methode nimmt einen vorhandenen User entgegen und uebernimmt deren Parameter
     * @param user der User von dem die Daten uebernommen werden
     */
    public void setUser(User user) {
        this.setUsername(user.getUsername());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        /* Group */
        this.setBlocked(user.getBlocked());
    }
}
