/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblorderfoods;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author PC
 */
public class tblorderfoodsDTO implements Serializable {

    public String userid;
    public String foodid;
    public Date date;
    public String username;
    public String foodname;
    public int quantity;
    public float total;

    public tblorderfoodsDTO() {
    }

    public tblorderfoodsDTO(String userid, String foodid, Date date, String username, String foodname, int quantity, float total) {
        this.userid = userid;
        this.foodid = foodid;
        this.date = date;
        this.username = username;
        this.foodname = foodname;
        this.quantity = quantity;
        this.total = total;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFoodid() {
        return foodid;
    }

    public void setFoodid(String foodid) {
        this.foodid = foodid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
