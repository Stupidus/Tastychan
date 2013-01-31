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
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Victor
 */
public class images {
    private int id;
    private String label;
    private InputStream image;
    
    public static void uploadImage(int id, String label, InputStream image, int categorie_id, int user_id) {
        
    }
}
