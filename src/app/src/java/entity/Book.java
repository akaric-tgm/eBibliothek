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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "Book")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findByBookid", query = "SELECT b FROM Book b WHERE b.bookid = :bookid"),
    @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
    @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author"),
    @NamedQuery(name = "Book.findBySummary", query = "SELECT b FROM Book b WHERE b.summary = :summary"),
    @NamedQuery(name = "Book.findByLanguage", query = "SELECT b FROM Book b WHERE b.language = :language"),
    @NamedQuery(name = "Book.findByFilepath", query = "SELECT b FROM Book b WHERE b.filepath = :filepath"),
    @NamedQuery(name = "Book.findByDownloads", query = "SELECT b FROM Book b WHERE b.downloads = :downloads"),
    @NamedQuery(name = "Book.findByViews", query = "SELECT b FROM Book b WHERE b.views = :views"),
    @NamedQuery(name = "Book.findByPages", query = "SELECT b FROM Book b WHERE b.pages = :pages"),
    @NamedQuery(name = "Book.findLikeTitleOrAuthor", query = "SELECT b FROM Book b WHERE b.title LIKE :title OR b.author LIKE :author")})
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "bookid")
    private Integer bookid;
    @Size(max = 100)
    @Column(name = "title")
    private String title;
    @Size(max = 100)
    @Column(name = "author")
    private String author;
    @Size(max = 1000)
    @Column(name = "summary")
    private String summary;
    @Size(max = 15)
    @Column(name = "language")
    private String language;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "filepath")
    private String filepath;
    @Column(name = "downloads")
    private Integer downloads;
    @Column(name = "views")
    private Integer views;
    @Column(name = "pages")
    private Integer pages;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private User username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Collection<Contribution> contributionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Collection<Report> reportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Collection<ChangeRequest> changeRequestCollection;

    public Book() {
    }

    public Book(Integer bookid) {
        this.bookid = bookid;
    }

    public Book(Integer bookid, String filepath) {
        this.bookid = bookid;
        this.filepath = filepath;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
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
        hash += (bookid != null ? bookid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.bookid == null && other.bookid != null) || (this.bookid != null && !this.bookid.equals(other.bookid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Book[ bookid=" + bookid + " ]";
    }
    
}
