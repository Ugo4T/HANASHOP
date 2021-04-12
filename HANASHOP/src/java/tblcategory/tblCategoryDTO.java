/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblcategory;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class tblCategoryDTO implements Serializable{
    String CategoryID;
    String name;

    public tblCategoryDTO() {
    }

    public tblCategoryDTO(String CategoryID, String name) {
        this.CategoryID = CategoryID;
        this.name = name;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
