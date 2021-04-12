/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblusers;

import Util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class tblUsersDAO implements Serializable{
    public boolean checkLogin(String username, String password) throws SQLException,ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select username "
                        + "From tblUsers "
                        + "Where username =? and password =? ";
                //tạo statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //execute
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    public String getFullname (String username,String password) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select fullname "
                        + "From tblUsers "
                        + "Where username =? and password =? ";
                //tạo statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //execute
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getString("fullname");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return "";
    }
    public boolean getRole (String username,String password) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select isAdmin "
                        + "From tblUsers "
                        + "Where username =? and password =? ";
                //tạo statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //execute
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getBoolean("isAdmin");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
