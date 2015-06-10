/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Niklas Hohenwarter
 */
@Named("searchBookBean")
@ViewScoped
public class SearchBookBean implements Serializable{
    
    private String searchText = "";

    /**
     * Creates a new instance of SearchBookBean
     */
    public SearchBookBean() {
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
    
}
