/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Selina Brinnich
 */
@Named("bookDownloadBean")
@RequestScoped
public class BookDownloadBean {
    
    private String format = "";

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    
    public void downloadBook(){
        
    }

}