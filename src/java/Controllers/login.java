/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author Victor
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listeCategories",  Models.categories.getAll());
        if("index".equals(request.getParameter("action")) || request.getParameter("action") == null) {
            this.index(request, response);
        }
        else if("login".equals(request.getParameter("action"))) {
            this.login(request, response);
        }
    }
    
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {                
        RequestDispatcher rd = request.getRequestDispatcher("login/index.jsp");
        rd.forward(request, response);
    }
    
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {                
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        if(username != null && password != null) {
            String[] authenticationResult = Models.login.authenticate(username, password);
            if(authenticationResult[0].equals("true")) {
                request.setAttribute("flash", "<p class='green'>Vous êtes à présent connecté</p>");
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("id", authenticationResult[1]);
            }
            else
                request.setAttribute("flash", "<p class='red'>Erreur lors de la connexion</p>");
            RequestDispatcher rd = request.getRequestDispatcher("home/index.jsp");
            rd.forward(request, response);
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("template/error.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
