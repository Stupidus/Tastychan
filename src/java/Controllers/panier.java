/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Victor
 */
public class panier extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listeCategories", Models.categories.getAll());
        if ("index".equals(request.getParameter("action")) || request.getParameter("action") == null) {
            this.index(request, response);
        } else if (request.getParameter("action").equals("down")) {            
            this.downloadAll(request, response);
        }
    }

    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList panier = (ArrayList) request.getSession().getAttribute("Panier");
        if (panier == null) {
            panier = new ArrayList();
        }
        Object[][] listeImages = Models.images.getAllByIdList(panier);
        request.setAttribute("listeImages", listeImages);
        RequestDispatcher rd = request.getRequestDispatcher("panier/index.jsp");
        rd.forward(request, response);
    }

    private void downloadAll(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream ouputStream;
        response.setContentType("application/zip");
        response.addHeader("Content-Disposition", "attachment; filename=TastyExport.zip");
        try {
            ouputStream = response.getOutputStream();
            ZipOutputStream out = new ZipOutputStream(ouputStream);                    
            
            ArrayList panier = (ArrayList) request.getSession().getAttribute("Panier");
            if(panier == null)
                panier = new ArrayList();
            Iterator it = panier.iterator();
            while(it.hasNext()) {
                int id = Integer.parseInt(it.next().toString());
                String[] ficheImage = Models.images.getFicheById(id);
                out.putNextEntry(new ZipEntry(ficheImage[1]+"."+ficheImage[4]));
                byte[] data = Models.images.getRawImage(id);
                out.write(data, 0, data.length);
            }               
            out.finish();
            out.close();
        } catch (IOException e) {
            Logger.getLogger(panier.class.getName()).log(Level.SEVERE, null, e);
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
