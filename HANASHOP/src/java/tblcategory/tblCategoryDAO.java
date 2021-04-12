/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblcategory;

import Util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class tblCategoryDAO implements Serializable{
    List<tblCategoryDTO> list ;

    public List<tblCategoryDTO> getList() {
        return list;
    }
    public void getCategoryList () throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select CategoryID, name "
                        + "From tblCategory ";
                          
                //tạo statement
                stm = con.prepareStatement(sql);
                
                
                //execute
                rs = stm.executeQuery();
                while (rs.next()) {
                    tblCategoryDTO dto = new tblCategoryDTO();
                    dto.setCategoryID(rs.getString("CategoryID"));
                    dto.setName(rs.getString("name"));
                    if (this.list==null){
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
    
}
