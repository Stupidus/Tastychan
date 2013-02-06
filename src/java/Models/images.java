/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Datasources.Connexion;
import com.mysql.jdbc.Blob;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Victor
 */
public class images {
    private int id;
    private String label;
    private FileInputStream image;
    public static final int IMAGE_PAR_PAGE = 5;
    
    public static int uploadImage(String label, InputStream image, int categorie_id, int user_id, String ext) {
        int result = 0;
        try {
            Connexion conn = new Connexion();
            PreparedStatement pstmt = conn.getPrepQuery("INSERT INTO images (label, data, user_id, categorie_id, ext) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, label);            
            pstmt.setBinaryStream(2, image);
            pstmt.setInt(3, user_id);
            pstmt.setInt(4, categorie_id);
            pstmt.setString(5, ext);
            result = conn.execPrepQuery(pstmt);
            conn.closeConnexion();
        } catch (SQLException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static int pagination(int id_categorie) {
        Connexion conn = new Connexion();
        ResultSet rset;
        int nbImages = 0;
        int nbPages = 0;
        try {            
            rset = conn.execQuery("SELECT COUNT(*) FROM images WHERE categorie_id = "+id_categorie+"");
            while(rset.next()) {
                nbImages  = rset.getInt(1);
            }
            nbPages = (int) Math.ceil((double) nbImages/(double) images.IMAGE_PAR_PAGE);
        } catch (SQLException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbPages;
    }
    
    public static String[][] getAllByIdCategorie(int id_categorie, int page) {
        String[][] listeImage = null;
        Connexion conn = new Connexion();
        ResultSet rset;
        int nbImage = 10;
        if(page < 1)
            page = 1;
        page--;
        try {            
            rset = conn.execQuery("SELECT COUNT(*) FROM (SELECT 1 FROM images WHERE categorie_id = "+id_categorie+" LIMIT "+page*images.IMAGE_PAR_PAGE+", "+images.IMAGE_PAR_PAGE+") seriously");
            while(rset.next()) {
                nbImage = rset.getInt(1);
            }
            listeImage = new String[nbImage][4];
            rset = conn.execQuery("SELECT * FROM images WHERE categorie_id = "+id_categorie+" LIMIT "+page*images.IMAGE_PAR_PAGE+", "+images.IMAGE_PAR_PAGE);
            try {
                int i = 0;                
                while(rset.next()) {
                    listeImage[i][0] = rset.getString(1); //ID
                    listeImage[i][1] = rset.getString(2); //LABEL
                    int user_id = rset.getInt(4);
                    String[] user = Models.users.getUser(user_id);
                    listeImage[i][2] = user[1]; //USERNAME
                    listeImage[i][3] = rset.getString(5); //CATEGORY_ID
                    i++;
                }
            }
            catch(NullPointerException ex) {
                Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.closeConnexion();        
        return listeImage;
    }
    
    public static BufferedImage getImage(int id) {
        BufferedImage bi = null;
        Connexion conn = new Connexion();
        ResultSet rset;
        try {                        
            rset = conn.execQuery("SELECT * FROM images WHERE id = "+id+"");
            try {
                while(rset.next()) {
                    Blob blob = (Blob) rset.getBlob(3);
                    InputStream is = blob.getBinaryStream();
                    bi = ImageIO.read(is);
                }
            } catch (IOException ex) {
                Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(NullPointerException ex) {
                Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.closeConnexion();
        return bi;
    }
    
    public static String[] getFicheById(int id) {
        String[] listeImage = new String[5];
        Connexion conn = new Connexion();
        ResultSet rset;
        try {            
            rset = conn.execQuery("SELECT * FROM images WHERE id = "+id+"");
            try {
                while(rset.next()) {
                    listeImage[0] = rset.getString(1); //ID
                    listeImage[1] = rset.getString(2); //LABEL
                    int user_id = rset.getInt(4);
                    String[] user = Models.users.getUser(user_id);
                    listeImage[2] = user[1]; //USERNAME
                    int categorie_id = rset.getInt(5);
                    String[] categorie = Models.categories.getCategorie(categorie_id);
                    listeImage[3] = categorie[1]; //CATEGORY_ID
                    listeImage[4] = rset.getString(6); //EXT
                }
            }
            catch(NullPointerException ex) {
                Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.closeConnexion();        
        return listeImage;
    }
    
    public static String[][] getAllByIdList(ArrayList liste) {
        String[][] listeImage = new String[liste.size()][5];
        Connexion conn = new Connexion();
        ResultSet rset;
        String listeId = liste.toString();
        listeId = listeId.substring(1, listeId.length()-1);   
        try {                        
            rset = conn.execQuery("SELECT * FROM images WHERE id IN ("+listeId+")");
            try {
                int i = 0;
                while(rset.next()) {
                    listeImage[i][0] = rset.getString(1); //ID
                    listeImage[i][1] = rset.getString(2); //LABEL
                    int user_id = rset.getInt(4);
                    String[] user = Models.users.getUser(user_id);
                    listeImage[i][2] = user[1]; //USERNAME
                    listeImage[i][3] = rset.getString(5); //CATEGORY_ID
                    listeImage[i][4] = rset.getString(6); //EXT
                    i++;
                }
            }
            catch(NullPointerException ex) {
                Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.closeConnexion();        
        return listeImage;
    }
    
    public static byte[] getRawImage(int id) {
        Connexion conn = new Connexion();
        ResultSet rset;
        byte[] res = null;
        try {                        
            rset = conn.execQuery("SELECT * FROM images WHERE id = "+id+"");
            try {
                while(rset.next()) {      
                    res = rset.getBytes(3);
                }
            } catch(NullPointerException ex) {
                Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.closeConnexion();
        return res;
    }
}
