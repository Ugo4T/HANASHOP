/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tblfood.tblFoodDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "DeleteFoodFromCardServlet", urlPatterns = {"/DeleteFoodFromCardServlet"})
public class DeleteFoodFromCardServlet extends HttpServlet {

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
        //    System.out.println("dang o day");
        try {
            //1.vào chỗ lấy giỏ của nó
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. lấy cái giỏ
                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                if (cart != null) {
                    //3.lấy đồ ra 
                    Map<tblFoodDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. cusr pic all remove
                        String[] removedItems = request.getParameterValues("chkItem");
                        //          System.out.println("52 deletfood tfrom card size chk "+removedItems.length);
                        if (removedItems != null) {
                            for (String item : removedItems) {
                                // System.out.println("item dong 56 delete form card "+ item);
                                cart.removeItemFromCart(item);
                            } //end for item
                            session.setAttribute("CUSTCART", cart);

                        }//having selected
                    }
                }//end if cart is existed
            }//end if session is existed
            if (session.getAttribute("BUYERROR") != null) {
                session.removeAttribute("BUYERROR");
            }

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
