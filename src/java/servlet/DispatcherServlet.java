package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tblcategory.tblCategoryDAO;
import tblcategory.tblCategoryDTO;

/**
 *
 * @author PC
 */
public class DispatcherServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
   
    private final String SEARCH_SERVLET = "SearchServlet";
    private final String DELETE_SERVLET = "DeleteServlet";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGIN_PAGE = "login.html";
    private final String UPDATE_SERVLET = "UpdateServlet";
    private final String SAVE_SERVLET = "SaveServlet";
    private final String ADD_SERVLET = "AddServlet";
    private final String ADD_TO_CARD_SERVLET = "AddToCardServlet";
    private final String DELETE_FOOD_FROM_CARD = "DeleteFoodFromCardServlet";
    private final String HISTORY = "HistoryServlet";
    private final String CHECK_OUT_SERVLET = "CheckOutServlet";
    private final String ADD_PAGE = "add.jsp";
    private final String VIEW_CARD= "viewCard.jsp";

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
        String url = SEARCH_PAGE;
        int logout = 0;
        tblCategoryDAO cdao = new tblCategoryDAO();
        try {
            cdao.getCategoryList();
            List<tblCategoryDTO> clist = cdao.getList();
            HttpSession session = request.getSession();
            session.setAttribute("CATEGORYLIST", clist);

        } catch (SQLException ex) {
            Logger.getLogger(DispatcherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String button = request.getParameter("btAction");
        try {
            if (button == null) {
                url = SEARCH_SERVLET;
            } else if (button.equals("Search")) {
                url = SEARCH_SERVLET;
            } else if (button.equals("Login Now")) {
                url = LOGIN_PAGE;
            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (button.equals("BACK")) {
                url = SEARCH_SERVLET;
            } else if (button.equals("NEXT")) {
                url = SEARCH_SERVLET;
            } else if (button.equals("Logout")) {
                logout = 1;
            } else if (button.equals("Delete")){
                url = DELETE_SERVLET;
            } else if (button.equals("Update")){
                url =UPDATE_SERVLET;
            } else if (button.equals("Save")){
                url = SAVE_SERVLET;
            } else if (button.equals("AddNew")){
                url=ADD_PAGE;
            } else if (button.equals("Create")){
                url = ADD_SERVLET;
            } else if (button.equals("Add to cart")){
                url = ADD_TO_CARD_SERVLET;
            } else if (button.equals("View Your Cart")){
                url = VIEW_CARD;
            } else if (button.equals("Remove Selected Item")){
                url = DELETE_FOOD_FROM_CARD;
            } else if (button.equals("Check out")){
                url= CHECK_OUT_SERVLET;
            } else if (button.equals("history")){
                url = HISTORY;
            } else if (button.equals("View your card")){
                url = "viewCart.jsp";
            }
        } finally {
            HttpSession session = request.getSession();
            if (logout == 1) {
                session.invalidate();
                url = SEARCH_SERVLET;
                response.sendRedirect(url);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
