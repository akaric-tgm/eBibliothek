/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.report;

import java.io.Serializable;
import java.util.HashMap;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



/**
 *
 * @author Tobias Perny
 */

@Named("reportBook")
@SessionScoped
public class ReportBook implements Serializable{
    private String message;
    private String reason;
    private HashMap<String,Integer> reportByBook;
    private int bookID;

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getBookID() {
        return bookID;
    }
   
    public void initialiseReportByBook(){
        reportByBook = new HashMap<>();
    }
    public int getReportByBook(String bookId){
        if(this.reportByBook == null){
            initialiseReportByBook();
        }
        if(this.reportByBook.containsKey(bookId) == false){
            this.reportByBook.put(bookId, 0);
        }
        return reportByBook.get(bookId);
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }
    public String incrementReportcounter(String bookId){
        if(this.reportByBook.containsKey(bookId) == false){
            this.reportByBook.put(bookId, 0);
        }
        int reportcounter = reportByBook.get(bookId)+1;
        this.reportByBook.replace(bookId, reportcounter);
        return "index.xhmtl?faces-redirect=true";
    }
    public void resetReportcounter(String bookId){
         this.reportByBook.replace(bookId, 0);
    }
    public void setReportByBook(String bookId,int counter){
        this.reportByBook.replace(bookId, counter);
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}