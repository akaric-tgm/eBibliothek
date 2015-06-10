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
 *
 * @author user
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

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getPwtoken() {
        return pwtoken;
    }

    public void setPwtoken(String pwtoken) {
        this.pwtoken = pwtoken;
    }

    public String getEmailtoken() {
        return emailtoken;
    }

    public void setEmailtoken(String emailtoken) {
        this.emailtoken = emailtoken;
    }
    

    public void setRole(boolean blocked) {
        this.blocked = blocked;
    }

    @XmlTransient
    public Collection<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(Collection<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    @XmlTransient
    public Collection<Contribution> getContributionCollection() {
        return contributionCollection;
    }

    public void setContributionCollection(Collection<Contribution> contributionCollection) {
        this.contributionCollection = contributionCollection;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public Collection<ChangeRequest> getChangeRequestCollection() {
        return changeRequestCollection;
    }

    public void setChangeRequestCollection(Collection<ChangeRequest> changeRequestCollection) {
        this.changeRequestCollection = changeRequestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "entity.User[ username=" + username + " ]";
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public UserGroups getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(UserGroups userGroups) {
        this.userGroups = userGroups;
    }
    
    public void setUser(String[] daten){
        this.setUsername(daten[0]);
        this.setPassword(daten[1]);
        this.setFirstName(daten[2]);
        this.setLastName(daten[3]);
        this.setEmail(daten[4]);
        this.setFid(Integer.parseInt(daten[5]));
        this.setGid(Integer.parseInt(daten[6]));
        this.setPwtoken(daten[7]);
        this.setEmailtoken(daten[8]);
        /* Group */
        this.setBlocked(Boolean.parseBoolean(daten[9]));
    }
    
    public void setUser(User user){
        this.setUsername(user.getUsername());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        /* Group */
        this.setBlocked(user.getBlocked());
    }
}
