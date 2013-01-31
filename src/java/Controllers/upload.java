/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Victor
 */
public class upload extends HttpServlet {

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
        else if(request.getParameter("action").equals("upload")) {
            this.upload(request, response);
        }
    }
    
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {                
        RequestDispatcher rd = request.getRequestDispatcher("upload/index.jsp");
        rd.forward(request, response);
    }
    
    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        request.setAttribute("flash", "<p class='green'>L'image a uploadée</p>");
//        try {
//        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//            for (FileItem item : items) {
//                if (item.isFormField()) {
//                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
//                    String fieldname = item.getFieldName();
//                    String fieldvalue = item.getString();
//                    // ... (do your job here)
//                } else {
//                    // Process form file field (input type="file").
//                    String fieldname = item.getFieldName();
//                    String filename = FilenameUtils.getName(item.getName());
//                    InputStream filecontent = item.getInputStream();
//                    // ... (do your job here)
//                }
//            }
//        } catch (FileUploadException e) {
//            throw new ServletException("Cannot parse multipart request.", e);
//        }
//        Models.images.uploadImage(id, null, null, categorie_id, user_id);
        RequestDispatcher rd = request.getRequestDispatcher("upload/index.jsp");
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
