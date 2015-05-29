/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "Contribution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contribution.findAll", query = "SELECT c FROM Contribution c"),
    @NamedQuery(name = "Contribution.findByUsername", query = "SELECT c FROM Contribution c WHERE c.contributionPK.username = :username"),
    @NamedQuery(name = "Contribution.findByBookid", query = "SELECT c FROM Contribution c WHERE c.contributionPK.bookid = :bookid"),
    @NamedQuery(name = "Contribution.findByComment", query = "SELECT c FROM Contribution c WHERE c.comment = :comment"),
    @NamedQuery(name = "Contribution.findByRating", query = "SELECT c FROM Contribution c WHERE c.rating = :rating")})
public class Contribution implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContributionPK contributionPK;
    @Size(max = 1000)
    @Column(name = "comment")
    private String comment;
    @Column(name = "rating")
    private Short rating;
    @JoinColumn(name = "bookid", referencedColumnName = "bookid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Book book;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Contribution() {
    }

    public Contribution(ContributionPK contributionPK) {
        this.contributionPK = contributionPK;
    }

    public Contribution(String username, int bookid) {
        this.contributionPK = new ContributionPK(username, bookid);
    }

    public ContributionPK getContributionPK() {
        return contributionPK;
    }

    public void setContributionPK(ContributionPK contributionPK) {
        this.contributionPK = contributionPK;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contributionPK != null ? contributionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contribution)) {
            return false;
        }
        Contribution other = (Contribution) object;
        if ((this.contributionPK == null && other.contributionPK != null) || (this.contributionPK != null && !this.contributionPK.equals(other.contributionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Contribution[ contributionPK=" + contributionPK + " ]";
    }
    
}
