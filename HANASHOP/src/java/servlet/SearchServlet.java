/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tblcategory.tblCategoryDAO;
import tblcategory.tblCategoryDTO;
import tblfood.tblFoodDAO;
import tblfood.tblFoodDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String SHOW_PAGE = "search.jsp";

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
      //  System.out.println("da toi day dong 51 search servlet");
        int pagesize = 0;
        int page = 0;
        String curPage = request.getParameter("txtPage");
        if (curPage == null) {
            page = 0;
        } else {
            page = Integer.parseInt(curPage);
        }
        // System.out.println(page);
        String searchValue = request.getParameter("txtSearchValue");
      //     System.out.println("da toi day dong 62 search servlet "+ searchValue );
        try {
        //       System.out.println("da toi day dong 63 search servlet");
            if (searchValue != null) {
           //     System.out.println("da toi day dong 66 search servlet");
                tblFoodDAO dao = new tblFoodDAO();
                dao.searchName();
                //lấy list lớn
                List<tblFoodDTO> result0 = dao.getList();
                //chia nhỏ để show
                // phan loai
                List<tblFoodDTO> result1 = new ArrayList<tblFoodDTO>();
                List<tblFoodDTO> result2 = new ArrayList<tblFoodDTO>();
                List<tblFoodDTO> result3 = new ArrayList<tblFoodDTO>();
                List<tblFoodDTO> result = new ArrayList<tblFoodDTO>();
               //    System.out.println("da toi day dong 75 search servlet");
                String category = request.getParameter("cboCategory");
               //    System.out.println("da toi day dong 77 search servlet");
                if (category.equals("0")) {
                    result1 = dao.getList();
                } else {
                    for (tblFoodDTO dto : result0) {
                        if (dto.getCategoryID().equals(category)) {
                            result1.add(dto);
                        }
                    }
                }
                //      System.out.println("1 " + result1.size());
                //search chu cai
                if (searchValue.equals("")) {
                    result2 = result1;
                } else {
                    for (tblFoodDTO dto : result1) {
                        if (dto.getName().toLowerCase().contains(searchValue.toLowerCase())) {
                            result2.add(dto);
                        }
                    }
                }
                  
                //    System.out.println("2 " + result2.size());
                // search gia
                String max = request.getParameter("txtPricemax");
                String min = request.getParameter("txtPricemin");
              //  System.out.println(max + " va "+ min);
                if (max.equals("") || max == null) {
                    max = "999999";
                }
                if (min.equals("") || min == null) {
                    min = "0";
                }
                float txtPricemax = Float.parseFloat(max);
                float txtPricemin = Float.parseFloat(min);
                if (txtPricemin > txtPricemax) {
                    float t = txtPricemax;
                    txtPricemax = txtPricemin;
                    txtPricemin = t;
                }
                //    System.out.println("min: " + txtPricemin);
                //    System.out.println("max: " + txtPricemax);
                for (tblFoodDTO dto : result2) {
                    if ((dto.getPrice() >= txtPricemin) && (dto.getPrice() <= txtPricemax)) {
                        result3.add(dto);
                    }
                }
                //     System.out.println("3 " + result3.size());
                //for delete
                HttpSession session = request.getSession();
                String isadmin = "";
                if (session.getAttribute("ISADMIN") != null) {
                    isadmin = (boolean) session.getAttribute("ISADMIN") + "";
                }
              
                if (isadmin.equals("true")) {
                    for (tblFoodDTO dto : result3) {

                        result.add(dto);

                    }
                } else {
                    for (tblFoodDTO dto : result3) {
                        if (dto.isStatus()&&dto.getQuantity()>0) {
                            result.add(dto);
                        }
                    }
                }
                //   System.out.println("4 " + result.size());
                // phân trang
                if (result.size() % 5 != 0) {
                    pagesize = result.size() / 5 + 1;
                } else {
                    pagesize = result.size() / 5;
                }
                List<tblFoodDTO> finalresult = new ArrayList<tblFoodDTO>();

                String button = request.getParameter("btAction");
                if (button.equals("NEXT")) {

                    for (int i = page * 5; i < (page + 1) * 5; i++) {
                        //   System.out.println(result.size() + " size");
                        //    System.out.println(i + " day la i duoi size");
                        if (i == result.size()) {

                            break;
                        }
                        //       System.out.println(i + " cua next");
                        finalresult.add(result.get(i));

                    }
                    request.setAttribute("PAGE", page + 1);
                } else if (button.equals("BACK")) {

                    for (int i = (page - 2) * 5; i < (page - 1) * 5; i++) {
                        if (i == result.size()) {
                            break;
                        }
                        //       System.out.println(i + " cac trang cua back");
                        finalresult.add(result.get(i));

                    }
                    request.setAttribute("PAGE", page - 1);
                } else {
                    for (int i = page * 5; i < (page + 1) * 5; i++) {
                        if (i == result.size()) {
                            break;
                        }
                        //       System.out.println(i + " cua ko phai 2 tren");
                        
                        finalresult.add(result.get(i));

                    }
                    request.setAttribute("PAGE", page + 1);
                }

                request.setAttribute("PAGESIZE", pagesize);
                if (request.getParameter("txtPricemax").equals("")||request.getParameter("txtPricemax").equals("9999999")) {
                    request.setAttribute("TXTMAX", "");
                } else {
                    request.setAttribute("TXTMAX", txtPricemax);
                }

                if (request.getParameter("txtPricemin").equals("")||request.getParameter("txtPricemin").equals("-1")) {
                    request.setAttribute("TXTMIN", "");
                } else {
                    request.setAttribute("TXTMIN", txtPricemin);
                }

                request.setAttribute("SEARCHRESULT", finalresult);

//                tblCategoryDAO cdao= new tblCategoryDAO();
//                cdao.getCategoryList();
//                List<tblCategoryDTO> clist=  cdao.getList();
//                request.setAttribute("CATEGORYLIST", clist);
                url = SHOW_PAGE;
            }//end if search value has

        } catch (SQLException ex) {
            log("SQLException in SearchServlet " + ex.getMessage());
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
