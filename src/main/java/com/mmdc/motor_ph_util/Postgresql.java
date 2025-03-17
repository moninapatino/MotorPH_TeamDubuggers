
package com.mmdc.motor_ph_util;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Postgresql {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public static Connection java_db() {
     
        try{
            
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Postgresql.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Trying to connect");
            
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","@dm1n");
            System.out.println("Connection Established Successful");
                        
            
        }catch (SQLException ex){
            Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            JOptionPane.showMessageDialog(null, ex);
            
            System.out.println("Unable to make connection with DB");
        }
        return null;
        }
    }
