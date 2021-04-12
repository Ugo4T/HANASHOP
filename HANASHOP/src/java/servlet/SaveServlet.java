/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "SaveServlet", urlPatterns = {"/SaveServlet"})
public class SaveServlet extends HttpServlet {

    private final String WRONG = "update.jsp";

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
            String[] errlist = new String[6];
            String err = "";
            String pk = request.getParameter("txtid");
            
            String name = request.getParameter("txtname");
            if (name.length() == 0) {
                err = "Input is not blank";
                errlist[1] = err;
            }
            
            String prices = request.getParameter("txtprice");            
            float price = 0;
            if (prices.matches("\\d+\\.\\d+") || prices.matches("\\d+\\d+") || prices.matches("\\d")) {
                price = Float.parseFloat(request.getParameter("txtprice"));

                if (price == 0) {
                    err = "Please input 'Price' >0";
                    errlist[2] = err;
                }
            } else {
                err = "Please input correct 'Price' ";
                errlist[2] = err;
            }

            String image = request.getParameter("txtimage");
            if (image.length() == 0) {
                image = request.getParameter("txtimagehidden");
            } else {

                int lastIndex = image.lastIndexOf("\\");
                image = image.substring(lastIndex + 1);
            }
            
            String category = request.getParameter("cboCategory");
            int quantity = 0;
            if (request.getParameter("txtquantity").equals("")) {
                err = "Input is not blank";
                errlist[4] = err;
            } else {
                quantity = Integer.parseInt(request.getParameter("txtquantity"));
              
            }
            String description = request.getParameter("txtdescription");
            if (description.length() == 0) {
                err = "Input is not blank";
                errlist[5] = err;
            }
            boolean status = Boolean.parseBoolean(request.getParameter("txtstatus"));

            HttpSession session = request.getSession();
            String searchValue = (String) session.getAttribute("lastSearchValuess"); //request.getParameter("lastSearchValue");
            String catagoryValue = (String) session.getAttribute("lastCatagoryValuess"); //request.getParameter("lastCatagoryValue");
            if (catagoryValue.isEmpty()) {
                catagoryValue = "0";
            }
            String priceMin = (String) session.getAttribute("lastPriceMinss");//request.getParameter("lastPriceMin");
            String priceMax = (String) session.getAttribute("lastPriceMaxss");//request.getParameter("lastPriceMax");

            if (priceMax == null) {
                priceMax = "";
            }
            if (priceMin == null) {
                priceMin = "";
            }
            if (priceMax.equals("null")) {
                priceMax = "";
            }
            if (priceMin.equals("null")) {
                priceMin = "";
            }
//            System.out.println(pk);
//            System.out.println(name);
//            System.out.println(price);
//            System.out.println(image);
//            System.out.println(category);
//            System.out.println(quantity);
//            System.out.println(description);
//            System.out.println(status);
            if (err.equals("")) {
                tblFoodDAO dao = new tblFoodDAO();
                boolean result = dao.updateFood(pk, name, price, image, category, quantity, description, status);
                if (result) {
                    url = "DispatcherServlet?txtSearchValue="
                            + searchValue
                            + "&cboCategory="
                            + catagoryValue
                            + "&txtPricemin="
                            + priceMin
                            + "&txtPricemax="
                            + priceMax
                            + "&btAction=Search";
                }
                if (session.getAttribute("lastPriceMaxss").equals("")) {
                    request.setAttribute("TXTMAX", "");
                } else {
                    request.setAttribute("TXTMAX", priceMax);
                }

                if (session.getAttribute("lastPriceMinss").equals("")) {
                    request.setAttribute("TXTMIN", "");
                } else {
                    request.setAttribute("TXTMIN", priceMin);
                }
            } else {
               url = WRONG;
                request.setAttribute("ERROR", errlist);

                tblFoodDTO dto = new tblFoodDTO(pk, name, price, image, category, quantity, description, null, null, status);
                request.setAttribute("UPDATEFOOD", dto);

            }
        } catch (SQLException ex) {
            log("DeleteServlet_SQLException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
