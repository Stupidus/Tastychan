/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Datasources.Connexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Components.StringComponent;
import java.io.File;
import java.sql.PreparedStatement;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Victor
 */
public class images {
    private int id;
    private String label;
    private FileInputStream image;
    
    public static void uploadImage(String label, InputStream image, int categorie_id, int user_id) {
        try {
//            System.out.println(label);
//            System.out.println(image);
//            System.out.println(categorie_id);
//            System.out.println(user_id);
            Connexion conn = new Connexion();
            PreparedStatement pstmt = conn.getPrepQuery("INSERT INTO images (label, data, user_id, categorie_id) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, label);            
            pstmt.setBinaryStream(2, image);
            pstmt.setInt(3, user_id);
            pstmt.setInt(4, categorie_id);
            conn.execPrepQuery(pstmt);
            conn.closeConnexion();
        } catch (SQLException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
