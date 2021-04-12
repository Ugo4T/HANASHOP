/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblorderfoods;

import Util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public class tblorderfoodsDAO implements Serializable{
    
    public boolean insertHistory(String id, String name, int quantity, float total, String userID, String username, Date date)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblorderfoods "
                        + "Values(?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, id);
                ps.setString(4, username);
                ps.setString(5, name);
                ps.setInt(6, quantity);
                ps.setFloat(7, total);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                ps.setString(3, sdf.format(date));
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    List<tblorderfoodsDTO> listHistory;

    public List<tblorderfoodsDTO> getListHistory() {
        return listHistory;
    }

    public void showHistory(String userID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select userid, foodid,date,username,foodname,quantity,total "
                        + "From tblorderfoods "
                        + "Where userid=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userid =rs.getString("userid");
                    String foodid =rs.getString("foodid");
                    String date = rs.getString("date");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String username =rs.getString("username");
                    String foodname =rs.getString("foodname");
                    int quantity =rs.getInt("quantity");
                    float total =rs.getFloat("total");
                    tblorderfoodsDTO dto = new tblorderfoodsDTO(userid, foodid, sdf.parse(date), username, foodname, quantity, total);
                    if (this.listHistory == null) {
                        this.listHistory = new ArrayList<>();
                    }
                    this.listHistory.add(dto);
                }
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
