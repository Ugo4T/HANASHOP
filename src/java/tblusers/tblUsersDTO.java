/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblusers;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class tblUsersDTO implements Serializable{
    String username;
    String password;
    String fullname;
    boolean isAdmin;

    public tblUsersDTO() {
    }

    public tblUsersDTO(String username, String password, String fullname, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}
