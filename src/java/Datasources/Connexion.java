package Datasources;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion {

    Connection conn;

    public Connexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/2012_vantoine", "2012_vantoine", "jpkc5k6t");        
            //this.conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:1664/2012_vantoine", "2012_vantoine", "jpkc5k6t");        
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet execQuery(String query) throws SQLException
    {
            Statement stm = this.conn.createStatement();
            return stm.executeQuery(query);
    }
    
    public void closeConnexion()
    {
        try {
            this.conn.close();
            //System.out.println("Connexion fermée");
        } catch (SQLException ex) {
                System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public Connection getConnection() {
        return this.conn;
    }
}
