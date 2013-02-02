/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.*;

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
       
        List items = null;
       boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            System.out.println("File Not Uploaded");
        } 
        else {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException ex) {
                Logger.getLogger(upload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                    
                
        request.setAttribute("listeCategories", Models.categories.getAll());
        if ("index".equals(request.getParameter("action")) || request.getParameter("action") == null) {
            this.index(request, response);
        } else if (request.getParameter("action").equals("upload")) {
            this.upload(request, response, items);
        }
    }

    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("upload/index.jsp");
        rd.forward(request, response);
    }

    public void upload(HttpServletRequest request, HttpServletResponse response, List items) throws ServletException, IOException {
        request.setAttribute("flash", "<p class='green'>L'image a uploadée</p>");
        String label = null;
        int categorie_id = 0;
        int user_id = 0;
        InputStream io = null;
        Iterator i = items.iterator();
        while (i.hasNext()) {
            FileItem item = (FileItem) i.next();
            System.out.println(item);
            if (item.isFormField()) {
                if(item.getFieldName().equals("label")) {
                    label = item.getString();
                }
                else if(item.getFieldName().equals("categorie")) {
                    categorie_id = Integer.parseInt(item.getString());
                }
                else if(item.getFieldName().equals("user_id")) {
                    user_id = Integer.parseInt(item.getString());
                }
            } else {
                if(item.getFieldName().equals("image")) {
                    io = item.getInputStream();     
                }
            }
        }
        if(Models.images.uploadImage(label, io, categorie_id, user_id) > 0)
            request.setAttribute("flash", "<p class='green'>L'image a uploadée</p>");
        else
            request.setAttribute("flash", "<p class='red'>Erreur lors de l'upload, peut-être dû à une taille d'image trop grande</p>");
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
