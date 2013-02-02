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
import java.sql.PreparedStatement;

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
        result[0] = "false";
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
    
    public static boolean addUser(String username, String password) {
        Connexion conn = new Connexion();
        boolean ret = false;
        try {
            PreparedStatement pstmt = conn.getPrepQuery("INSERT INTO users (username, password) VALUES (?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, StringComponent.getEncodedPassword(password));
            int res = conn.execPrepQuery(pstmt);
            if(res > 0)
                ret = true;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.closeConnexion();
        return ret;
    }
}
