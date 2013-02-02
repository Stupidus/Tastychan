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
import java.security.NoSuchAlgorithmException;

public class categories {
    private int id;
    private String label;
    
    public static String[][] getAll() {        
        String[][] listeCategorie = new String[10][2];
        Connexion conn = new Connexion();
        ResultSet rset;
        int nbCateg = 10;
        try {            
            rset = conn.execQuery("SELECT COUNT(*) FROM categories");
            while(rset.next()) {
                nbCateg = rset.getInt(1);
            }
            listeCategorie = new String[nbCateg][2];
            rset = conn.execQuery("SELECT * FROM categories");
            try {
                int i = 0;
                while(rset.next()) {
                    listeCategorie[i][0] = rset.getString(1);
                    listeCategorie[i][1] = rset.getString(2);
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
        return listeCategorie;
    }
}
