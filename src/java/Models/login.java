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
public class login {
    private int id;
    private String username;
    private String password;
    
    public static boolean authenticate(String username, String plainPassword) {
        Connexion conn = new Connexion();
        boolean auth = false;
        try {
            String password = StringComponent.getEncodedPassword(plainPassword);
//            ResultSet rset = conn.execQuery("SELECT * FROM users WHERE username = '"+username+"' && password = '"+password+"'");
            ResultSet rset = conn.execQuery("SELECT * FROM users");
            try {
            
            }
            catch(NullPointerException ex) {
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auth;
    }
}
