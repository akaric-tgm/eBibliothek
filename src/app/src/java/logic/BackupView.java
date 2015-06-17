/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import crud.BookController;
import crud.ChangeRequestController;
import crud.ContributionController;
import crud.ReportController;
import crud.UserController;
import crud.UserGroupsController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.DualListModel;
import entity.*;
import java.io.Serializable;

/**
 *
 * @author Manuel Reiländer
 */
@ManagedBean
public class BackupView {
    @PersistenceContext EntityManager a;
    
    @Inject BookController books;
    @Inject ChangeRequestController changeRequest;
    @Inject ContributionController contribution;
    @Inject ReportController report;
    @Inject UserGroupsController groups;
    @Inject UserController user;
    
    private DualListModel<String> tables;
    
    private String[] col_book = {"bookid", "title", "author", "summary", "language", "filepath", "downloads", "views", "pages", "username"},
            col_changeRequest = {"username", "bookid", /* "changedate",*/ "page"},
            col_contribution = {"username", "bookid", "comment", "rating"},
            col_report = {"username", "bookid", "comment", /* "reportdate" ,*/ "status"},
            col_user = {"username", "password", "firstName", "lastName", "eMail", "fid", "gid", "pwtoken", "emailtoken", "blocked"},
            col_groups = {"groupname", "username"}
            ;
    @PostConstruct
    public void init() {
        List<String> tbs = new ArrayList<>();
        List<String> tbs_tar = new ArrayList<>();
        
        tbs.add("Book");
        tbs.add("ChangeRequest");
        tbs.add("Contribution");
        tbs.add("Report");
        tbs.add("User");
        tbs.add("User_Groups");
        
        tables = new DualListModel(tbs, tbs_tar);
    }

    public DualListModel<String> getTables() {
        return tables;
    }

    public void setTables(DualListModel<String> tables) {
        this.tables = tables;
    }
    
    private void sendMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    /**
     * This method will called when the button "Backup tables" has been pressed. <br />
     * It will backup all contents from the, in the GUI selected, tables. <br />
     * <u>ATTENTION</u> <br/>If there have been any changes, in the structure of the database such as a new column 
     * was added or the type of some column was changed, you may need to edit this method.
     * @param e ActionEvent
    */
    public void action(ActionEvent e) {
        //TODO
        //BUGs
            //Date doesn't work, looks like this 'Thu Jan 01 00:00:00 CET 2015' -> String processing
            //Caused by: java.sql.SQLException: Value '0000-00-00' can not be represented as java.sql.Date --> Dates out commented in Book and Report
            //EJB Exception out of nowhere, dont care, rage ...
        sendMessage("Backing up tables ...");
        
        List<String> tmp = tables.getTarget();
        Iterator<String> tars = tmp.iterator();
        
        String backup = "";
        while(tars.hasNext()) {
            switch(tars.next()) {
                case "Book":
                    {
                    List<Book> tmp1 = books.getItems();
                    Iterator<Book> i1 = tmp1.iterator();
                    while(i1.hasNext()) {
                        Book b = i1.next();
                        backup += "INSERT INTO Book(";
                        for(int i = 0;i < col_book.length;++i) {
                            if(i==0)
                                backup += col_book[i] + ", ";
                            if((col_book.length-1)==i)
                                backup += col_book[i] + ")";
                            if((i!=0) && (col_book.length-1 != i))
                                backup += col_book[i] + ", ";
                        }
                        backup += " VALUES(";
                        backup += "" + b.getBookid() + ", '" + b.getTitle() + "', '" + b.getAuthor() + "', '" + b.getSummary() + "', '"
                                + b.getLanguage() + "', '" + b.getFilepath()
                                + "', " + b.getDownloads() + ", " + b.getViews() + ", " + b.getPages()
                                + ", '" + b.getUsername().getUsername()
                                + "');\n";
                    }
                    }
                    break;
                case "ChangeRequest":
                    {
                        List<ChangeRequest> tmp1 = changeRequest.getItems();
                        Iterator<ChangeRequest> i1 = tmp1.iterator();
                        while(i1.hasNext()) {
                            ChangeRequest b = i1.next();
                            backup += "INSERT INTO ChangeRequest(";
                            for(int i = 0;i < col_changeRequest.length;++i) {
                                if(i==0)
                                    backup += col_changeRequest[i] + ", ";
                                if((col_changeRequest.length-1)==i)
                                    backup += col_changeRequest[i] + ")";
                                if((i!=0) && (col_changeRequest.length-1 != i))
                                    backup += col_changeRequest[i] + ", ";
                            }
                            backup += " VALUES(";
                            backup += "'" + b.getUser().getUsername() + "', " + b.getBook().getBookid() /* + ", '"
                                    +  b.getChangedate() */ + ", " + b.getPage() + ");\n";
                        }
                    }
                    break;
                case "Contribution":
                    {
                        List<Contribution> tmp1 = contribution.getItems();
                        Iterator<Contribution> i1 = tmp1.iterator();
                        while(i1.hasNext()) {
                            Contribution b = i1.next();
                            backup += "INSERT INTO Contribution(";
                            for(int i = 0;i < col_contribution.length;++i) {
                                if(i==0) {
                                    backup += col_contribution[i] + ", ";
                                }
                                if((col_contribution.length-1)==i) {
                                    backup += col_contribution[i] + ")";
                                }
                                if((i!=0) && (col_contribution.length-1 != i)) {
                                    backup += col_contribution[i] + ", ";
                                }
                            }
                            backup += " VALUES(";
                            backup += "'" + b.getUser().getUsername() + "', " + b.getBook().getBookid()
                                    + ", '" + b.getComment() + "', " + b.getRating() + ");\n";
                        }
                    }
                    break;
                case "Report":
                    {
                        List<Report> tmp1 = report.getItems();
                        Iterator<Report> i1 = tmp1.iterator();
                        while(i1.hasNext()) {
                            Report b = i1.next();
                            backup += "INSERT INTO Report(";
                            for(int i = 0;i < col_report.length;++i) {
                                if(i==0)
                                    backup += col_report[i] + ", ";
                                if((col_report.length-1)==i)
                                    backup += col_report[i] + ")";
                                if((i!=0) && (col_report.length-1 != i))
                                    backup += col_report[i] + ", ";
                            }
                            backup += " VALUES(";
                            backup += "'" + b.getUser().getUsername() + "', " + b.getBook().getBookid()
                                    + ", '" + b.getComment() + /* "', '" + b.getReportdate()+ */ "', " + b.getStatus() + ");\n";
                        }
                    }
                    break;
                case "User":
                    {
                        List<User> tmp1 = user.getItems();
                        Iterator<User> i1 = tmp1.iterator();
                        while(i1.hasNext()) {
                            User b = i1.next();
                            backup += "INSERT INTO User(";
                            for(int i = 0;i < col_user.length;++i) {
                                if(i==0)
                                    backup += col_user[i] + ", ";
                                if((col_user.length-1)==i)
                                    backup += col_user[i] + ")";
                                if((i!=0) && (col_user.length-1 != i))
                                    backup += col_user[i] + ", ";
                            }
                            backup += " VALUES(";
                            backup += "'" + b.getUsername() + "', '" + b.getPassword() + "', '" + b.getFirstName() + "', '" + b.getLastName()
                                    + "', '" + b.getEmail() + "', " + b.getFid() + ", " + b.getGid() + ", '" + b.getPwtoken()
                                    + "', '" + b.getEmailtoken() + "', " + b.getBlocked() + ");\n"
                                    ;
                        }
                    }
                    break;
                case "User_Groups":
                    {
                        List<UserGroups> tmp1 = groups.getItems();
                        Iterator<UserGroups> i1 = tmp1.iterator();
                        while(i1.hasNext()) {
                            UserGroups b = i1.next();
                            backup += "INSERT INTO User_Groups(";
                            for(int i = 0;i < col_groups.length;++i) {
                                if(i==0)
                                    backup += col_groups[i] + ", ";
                                if((col_groups.length-1)==i)
                                    backup += col_groups[i] + ")";
                                if((i!=0) && (col_groups.length-1 != i))
                                    backup += col_groups[i] + ", ";
                            }
                            backup += " VALUES(";
                            backup += "'" + b.getGroupname() + "', '" + b.getUsername() + "');\n";
                        }
                    }
                    break;
            }
        }
        System.out.println(backup);
    }
    /**
     * Creates the Inserts for a given Entity, which may extends from java.io.Serializable
     * @param b
     * @return 
     */
    public String createInserts(Serializable b) {
        
        return "";
    }
}