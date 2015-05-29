/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "ChangeRequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChangeRequest.findAll", query = "SELECT c FROM ChangeRequest c"),
    @NamedQuery(name = "ChangeRequest.findByUsername", query = "SELECT c FROM ChangeRequest c WHERE c.changeRequestPK.username = :username"),
    @NamedQuery(name = "ChangeRequest.findByBookid", query = "SELECT c FROM ChangeRequest c WHERE c.changeRequestPK.bookid = :bookid"),
    @NamedQuery(name = "ChangeRequest.findByChangedate", query = "SELECT c FROM ChangeRequest c WHERE c.changedate = :changedate"),
    @NamedQuery(name = "ChangeRequest.findByPage", query = "SELECT c FROM ChangeRequest c WHERE c.page = :page")})
public class ChangeRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChangeRequestPK changeRequestPK;
    @Column(name = "changedate")
    @Temporal(TemporalType.DATE)
    private Date changedate;
    @Column(name = "page")
    private Integer page;
    @JoinColumn(name = "bookid", referencedColumnName = "bookid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Book book;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public ChangeRequest() {
    }

    public ChangeRequest(ChangeRequestPK changeRequestPK) {
        this.changeRequestPK = changeRequestPK;
    }

    public ChangeRequest(String username, int bookid) {
        this.changeRequestPK = new ChangeRequestPK(username, bookid);
    }

    public ChangeRequestPK getChangeRequestPK() {
        return changeRequestPK;
    }

    public void setChangeRequestPK(ChangeRequestPK changeRequestPK) {
        this.changeRequestPK = changeRequestPK;
    }

    public Date getChangedate() {
        return changedate;
    }

    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
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
        hash += (changeRequestPK != null ? changeRequestPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChangeRequest)) {
            return false;
        }
        ChangeRequest other = (ChangeRequest) object;
        if ((this.changeRequestPK == null && other.changeRequestPK != null) || (this.changeRequestPK != null && !this.changeRequestPK.equals(other.changeRequestPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ChangeRequest[ changeRequestPK=" + changeRequestPK + " ]";
    }
    
}
