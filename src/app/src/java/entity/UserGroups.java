/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Diese Entity stellt eine UserGroup von der ebibliothek dar.
 * @author Philipp Adler
 * @version 2015-06-11
 */
@Entity
@Table(name = "User_Groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroups.findAll", query = "SELECT u FROM UserGroups u"),
    @NamedQuery(name = "UserGroups.findByGroupname", query = "SELECT u FROM UserGroups u WHERE u.groupname = :groupname"),
    @NamedQuery(name = "UserGroups.findByUsername", query = "SELECT u FROM UserGroups u WHERE u.username = :username")})
public class UserGroups implements Serializable {
    
     private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "groupname")
    private String groupname;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "username")
    private String username;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    /**
     * Default-Konstruktor
     */
    public UserGroups() {
    }

    /**
     * Konstruktor wo man den Primarykey, den Username, als Parameter angibt
     * @param username der eindeutige Username fuer den User
     */
    public UserGroups(String username) {
        this.username = username;
    }

    /**
     * Konstruktor der alle Attribute vom Benutzer als Parameter verlangt
     * @param username der eindeutige Username fuer den User
     * @param groupname hier gibt man ob der User ein Admin,Moderator oder User ist
     */
    public UserGroups(String username, String groupname) {
        this.username = username;
        this.groupname = groupname;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    /**
     * Das ist die Getter-Methode fuer den Username
     * @return den Username der UserGroup
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
     * Das ist die Getter-Methode die von der UserGroup den User zurueck gibt
     * @return den User als Objekt
     */
    public User getUser() {
        return user;
    }

    /**
     * Die Methode aendert den User
     * @param user das neue User-Objekt
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Die hashCode-Methode gibt den Hashcode der UserGroup zurueck
     * @return den Hash der UserGroup
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    /**
     * Die equals Methode vergleicht die UserGroup mit einer anderen, uebergebenen, UserGroup
     * @param object die UserGroup die mit dem anderen verglichen wird
     * @return ob die uebergebene UserGroup ident mit der anderen ist
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroups)) {
            return false;
        }
        UserGroups other = (UserGroups) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    /**
     * Die toString-Methode gibt alle wichtigen Daten der UserGroup aus
     * @return UserGroupdaten
     */
    @Override
    public String toString() {
        return "entity.UserGroups[ username=" + username + " ]";
    }
    
}