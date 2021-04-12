/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblfood;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author PC
 */
public class tblFoodDTO implements Serializable{
    String id;
    String name;
    float price;
    String image;
    String categoryID;
    int quantity;
    String description;
    Date createDate;
    Date updateDate;
    boolean status;

    public tblFoodDTO() {
    }

    public tblFoodDTO(String id, String name, float price, String image, String categoryID, int quantity, String description, Date createDate, Date updateDate, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
}
