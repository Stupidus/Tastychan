/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Victor
 */
public class fiche extends HttpServlet {

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
    }
    
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {     
        int id = Integer.parseInt(request.getParameter("id"));    
        String[] ficheImage = Models.images.getFicheById(id);
        request.setAttribute("ficheImage",  ficheImage);
        if(request.getParameter("ajout") != null && request.getParameter("ajout").equals("true")) {
            ArrayList panier = (ArrayList) request.getSession().getAttribute("Panier");
            if(panier == null)
                panier = new ArrayList();
            if(!panier.contains(id))
                panier.add(id);
            request.getSession().setAttribute("Panier", panier);
        }
        else if (request.getParameter("ajout") != null && request.getParameter("ajout").equals("remove")) { 
            ArrayList panier = (ArrayList) request.getSession().getAttribute("Panier");
            if(panier == null)
                panier = new ArrayList();
            if(panier.contains(id))
                panier.remove((Object) id);
            request.getSession().setAttribute("Panier", panier);
        }
        RequestDispatcher rd = request.getRequestDispatcher("fiche/index.jsp");
        rd.forward(request, response);
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
