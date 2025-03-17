
package com.mmdc.motor_ph_portal;

import com.motorph_util.Postgresql;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.sql.Connection;
import javax.swing.JFrame;


public class Motor_PH_Portal extends JFrame {

    public static void main(String[] args) {
        Connection conn=null;
        conn = Postgresql.java_db();
        
        Login loginFrame = new Login();
        loginFrame.setVisible(true);
    }
    
    public void init() {
        setTitle ("Motor PH Employee Portal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
}
