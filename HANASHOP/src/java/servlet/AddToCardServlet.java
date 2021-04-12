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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.openmbean.SimpleType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tblfood.tblFoodDAO;
import tblfood.tblFoodDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "AddToCardServlet", urlPatterns = {"/AddToCardServlet"})
public class AddToCardServlet extends HttpServlet {

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
        String url = "";
        try {

            String searchValue = request.getParameter("txtSearchValue");
            String catagoryValue = request.getParameter("cboCategory");
            int priceMin = -1;
            if (request.getParameter("txtPricemin").equals("")) {
                priceMin = -1;
            } else {
                priceMin = Integer.parseInt(request.getParameter("txtPricemin"));
            }
            int priceMax = 9999999;
            if (request.getParameter("txtPricemax").equals("")) {
                priceMax = 9999999;
            } else {
                priceMax = Integer.parseInt(request.getParameter("txtPricemax"));
            }
            String curpage = request.getParameter("txtPage");
            int page = Integer.parseInt(curpage) - 1;
            String pagesize = request.getParameter("txtPageSize");
           
            
            HttpSession session = request.getSession();
            //2
            CartObject cart = (CartObject) session.getAttribute("CUSTCART");
            if (cart==null){
                cart= new CartObject();
            }
            tblFoodDAO dao= new tblFoodDAO();
            String pk = request.getParameter("pk");
            tblFoodDTO dto = dao.getFood(pk);
            cart.addItemToCard(dto);
            session.setAttribute("CUSTCART", cart);
            
            
            
            url = "DispatcherServlet?txtSearchValue="
                    + searchValue
                    + "&cboCategory="
                    + catagoryValue
                    + "&txtPricemin="
                    + priceMin
                    + "&txtPricemax="
                    + priceMax
                    + "&txtPage="
                    + page
                    + "&btAction=Search";
            if (request.getParameter("txtPricemax").equals("") || request.getParameter("txtPricemax").equals("9999999")) {
                request.setAttribute("TXTMAX", "");
            } else {
                request.setAttribute("TXTMAX", priceMax);
            }

            if (request.getParameter("txtPricemin").equals("") || request.getParameter("txtPricemin").equals("-1")) {
                request.setAttribute("TXTMIN", "");
            } else {
                request.setAttribute("TXTMIN", priceMin);
            }

            request.setAttribute("PAGE", curpage);
            request.setAttribute("PAGESIZE", pagesize);
        } catch (SQLException ex) {
            Logger.getLogger(AddToCardServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            //  response.sendRedirect(url);
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
