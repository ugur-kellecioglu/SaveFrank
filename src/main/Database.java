/**
*
* 
* 
* @author  Uğur Kellecioğlu
* @version 1.0
* @since   2021-01-17
*/
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ugurk
 */
public class Database {
    
    Connection conn = null;  
    String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\db.db";  
    Statement stmt = null;
    ResultSet rs = null;
    
    public void connect(){
        try {  
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn = DriverManager.getConnection(url);  
            System.out.println("Connection to SQLite has been established.");  
              
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } 
          
    }
    
    public void save(String query){
        if(conn == null) connect();
        if(stmt == null){
            try {
                stmt = conn.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getBestScore(){
        if(conn == null) connect();
        if(stmt == null){
            try {
                stmt = conn.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        String username = "";
        int score = 0;
        try {  
            rs = stmt.executeQuery("select * from scoretable ORDER BY score DESC");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
             if(rs.next()) {
                username = rs.getString("username");
                score = rs.getInt("score");
             } 
             
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return username + "  " + score;
    }
}
