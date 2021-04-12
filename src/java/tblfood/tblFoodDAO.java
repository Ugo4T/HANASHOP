/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblfood;

import Util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public class tblFoodDAO implements Serializable {

    List<tblFoodDTO> list;

    public List<tblFoodDTO> getList() {
        return list;
    }

    public void searchName() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select id, name,price,image,categoryID,quantity,description,createDate,updateDate,status from tblFoods order by  createDate desc";
                stm = con.prepareStatement(sql);
                // stm.setString(1, "%"+name+"%");

                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String nameS = rs.getString("name");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    String categoryID = rs.getString("categoryID");
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                    Date createDate = rs.getDate("createDate");
                    Date updateDate = rs.getDate("updateDate");

                    boolean status = rs.getBoolean("status");
                    tblFoodDTO dto = new tblFoodDTO(id, nameS, price, image, categoryID, quantity, description, createDate, updateDate, status);
                    if (this.list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
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
    }

    public boolean deleteFood(String id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update tblFoods set  status=0 where id = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
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

    public tblFoodDTO getFood(String pk) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select id, name,price,image,categoryID,quantity,description,createDate,updateDate,status from tblFoods  where id=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, pk);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("id");
                    String nameS = rs.getString("name");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    String categoryID = rs.getString("categoryID");
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                    Date createDate = rs.getDate("createDate");
                    Date updateDate = rs.getDate("updateDate");

                    boolean status = rs.getBoolean("status");
                    tblFoodDTO dto = new tblFoodDTO(id, nameS, price, image, categoryID, quantity, description, createDate, updateDate, status);

                    return dto;
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
        return null;
    }

    public boolean updateFood(String id, String name, float price, String image, String categoryID, int quantity, String description, boolean status) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update tblFoods set  name=?, price=? , image=?,categoryID=?,quantity=? ,description=?,updateDate=?, status=?  where id = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setFloat(2, price);
                ps.setString(3, image);
                ps.setString(4, categoryID);
                ps.setInt(5, quantity);
                ps.setString(6, description);
                ps.setBoolean(8, status);
                ps.setString(9, id);
                Date day = new Date(new java.util.Date().getTime());
                ps.setDate(7,  day);
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
    public boolean addFood(String id, String name, float price, String image, String categoryID, int quantity, String description, boolean status) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into tblFoods values  (?,?,?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, name);
                ps.setFloat(3, price);
                ps.setString(4, image);
                ps.setString(5, categoryID);
                ps.setInt(6, quantity);
                ps.setString(7, description);
                ps.setBoolean(10, status);
                
                Date day = new Date(new java.util.Date().getTime());
                ps.setDate(8,  day);
                
                ps.setDate(9,  day);
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
     public boolean checkEnough(String pk, int quantity1) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select quantity from tblFoods  where id=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, pk);

                rs = stm.executeQuery();
                if (rs.next()) {
                  
                    int quantity = rs.getInt("quantity");
                    if (quantity>=quantity1)
                    

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
     
      public boolean buyFood(String pk, int quantity1) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update tblFoods set quantity =quantity-?   where id=?";
                stm = con.prepareStatement(sql);
                stm.setString(2, pk);
                stm.setInt(1, quantity1);

                int result = stm.executeUpdate();
                if (result>0) return true;

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
