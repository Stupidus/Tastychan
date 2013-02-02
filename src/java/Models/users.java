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

/**
 *
 * @author Victor
 */
public class users {
    private int id;
    private String username;
    private String password;
    
    public static String[] authenticate(String username, String plainPassword) {
        Connexion conn = new Connexion();
        String[] result = new String[2];
        try {
            String password = StringComponent.getEncodedPassword(plainPassword);
            ResultSet rset = conn.execQuery("SELECT * FROM users WHERE username = '"+username+"' && password = '"+password+"'");
            try {
                while(rset.next()) {
                    result[0] = "true";
                    result[1] = rset.getString(1);
                }
            }
            catch(NullPointerException ex) {
                Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.closeConnexion();
        return result;
    }
    
    public static String[] getUser(int id) {
        String[] user = new String[3];
        Connexion conn = new Connexion();
        try {
            ResultSet rset = conn.execQuery("SELECT * FROM users WHERE id = "+id+"");
            try {
                while(rset.next()) {
                    user[0] = rset.getString(1);
                    user[1] = rset.getString(2);
                    user[2] = rset.getString(3);
                }
            }
            catch(NullPointerException ex) {
                Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.closeConnexion();
        return user;
    }
}
