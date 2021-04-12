/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tblfood.tblFoodDAO;
import tblfood.tblFoodDTO;
import tblorderfoods.tblorderfoodsDAO;

/**
 *
 * @author PC
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            boolean check = true;
            String err = "";
            tblFoodDAO dao = new tblFoodDAO();
            HttpSession session = request.getSession(false);
            if (session != null) {

                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                if (cart != null) {

                    Map<tblFoodDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        for (tblFoodDTO dto : items.keySet()) {
                            check = true;
                            if (!dao.checkEnough(dto.getId(), items.get(dto))) {
                                check = false;
                                err = err + dto.getName() + ", ";
                            }
                        }
                    }
                    if (!err.equals("")) {
                        err = err.substring(0, err.length() - 2);
                        session.setAttribute("BUYERROR", err + " is not enough");
                    }
                    if (err.equals("")) {
                        //giảm bớt quantity
                        //add info into tblorderfoods
                        String userid = (String) session.getAttribute("USERID");
                        String username = (String) session.getAttribute("FULLNAME");
                        tblorderfoodsDAO daood = new tblorderfoodsDAO();
                        Date date = new Date();
                        for (tblFoodDTO dto : items.keySet()) {

                            dao.buyFood(dto.getId(), items.get(dto));
                            daood.insertHistory(dto.getId(), dto.getName(), items.get(dto), items.get(dto) * dto.getPrice(), userid, username, date);

                        }

                        session.setAttribute("CUSTCART", null);

                    }
                }//end if cart is existed
            }//end if session is existed

        } catch (SQLException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //goi lai chức năng view cart 1 lần nữa
            String urlRewriting = "viewCart.jsp";
            response.sendRedirect(urlRewriting);
            out.close();

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
