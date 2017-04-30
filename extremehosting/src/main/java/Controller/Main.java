/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import Model.Rate;
import Model.User;
import Util.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Igor-Surface
 */
@WebServlet(name = "Main", urlPatterns = {"/main"})
public class Main extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    UserDAO userDAO = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Long k;
        try{
            k = Long.parseLong(request.getParameter("id"));
        }
        catch(java.lang.NumberFormatException e){
            k = 2L;
        }
        
        if(userDAO == null)
            userDAO = Database.FillDB();
        
        User user = userDAO.getActiveUser(k);
        
        List<Rate> rpersonal = new ArrayList();
        List<Rate> rguest = new ArrayList();
        List<Rate> rhost = new ArrayList();
        float tpersonal = 0,tguest = 0,thost = 0;
        
        for(Rate r : userDAO.getRates(user.getId())){
            switch(r.getType()){
                case 0:
                    tpersonal += r.getValue();
                    rpersonal.add(r);
                    break;
                case 2:
                    tguest += r.getValue();
                    rguest.add(r);
                    break;
                case 1:
                    thost += r.getValue();
                    rhost.add(r);
                    break;
            }
        }

        
        request.setAttribute("user", user);
        request.setAttribute("rate-personal", rpersonal);
        request.setAttribute("rate-guest", rguest);
        request.setAttribute("rate-host", rhost);
        request.setAttribute("media-personal", (tpersonal/rpersonal.size()));
        request.setAttribute("media-guest", (tguest/rguest.size()));
        request.setAttribute("media-host", (thost/rhost.size()));
        
        request.getRequestDispatcher("/main.jsp").forward(request, response);
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
