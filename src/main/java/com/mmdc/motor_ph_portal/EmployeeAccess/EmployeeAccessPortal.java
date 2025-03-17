
package com.mmdc.motor_ph_portal.EmployeeAccess;

import com.mmdc.motor_ph_portal.Login;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.WindowConstants;
import javax.swing.*;


public class EmployeeAccessPortal extends javax.swing.JFrame {
   
    
    public EmployeeAccessPortal() {
        initComponents();
        setTitle ("Employee Portal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        
        
        //Displaying date and Time
        time();
        date();
              
    }

    public final void time(){
    DateTimeFormatter times = DateTimeFormatter.ofPattern("hh:mm:ss a");
    LocalDateTime now =LocalDateTime.now();
    time.setText(times.format(now));
    }
    public final void date(){
    DateTimeFormatter dates = DateTimeFormatter.ofPattern("MMMM d, y");
    LocalDateTime now =LocalDateTime.now();
    date.setText(dates.format(now));
    }
    
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        logOutButton = new javax.swing.JButton();
        empportal_label = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        empProfile_btn = new javax.swing.JButton();
        leave_btn = new javax.swing.JButton();
        attendance_btn = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(217, 217, 217));
        jLabel1.setText("Employee Portal");

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 250, 255));
        jPanel1.setForeground(new java.awt.Color(250, 250, 255));

        date.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        date.setForeground(new java.awt.Color(92, 101, 138));
        date.setText("Date");

        time.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        time.setForeground(new java.awt.Color(92, 101, 138));
        time.setText("Time");

        logOutButton.setBackground(new java.awt.Color(253, 56, 29));
        logOutButton.setForeground(new java.awt.Color(217, 217, 217));
        logOutButton.setText("Log out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        empportal_label.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        empportal_label.setForeground(new java.awt.Color(92, 101, 138));
        empportal_label.setText("EMPLOYEE PORTAL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(LOGO)
                .addGap(73, 73, 73))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(date)
                .addGap(18, 18, 18)
                .addComponent(time)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(empportal_label)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(logOutButton)
                        .addGap(134, 134, 134))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(empportal_label, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LOGO)
                .addGap(18, 18, 18)
                .addComponent(logOutButton)
                .addGap(107, 107, 107)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date)
                    .addComponent(time))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(30, 43, 93));

        empProfile_btn.setBackground(new java.awt.Color(30, 43, 93));
        empProfile_btn.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        empProfile_btn.setForeground(new java.awt.Color(217, 217, 217));
        empProfile_btn.setText("Employee Profile");
        empProfile_btn.setBorder(null);
        empProfile_btn.setMargin(new java.awt.Insets(2, 0, 3, 0));
        empProfile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empProfile_btnActionPerformed(evt);
            }
        });

        leave_btn.setBackground(new java.awt.Color(30, 43, 93));
        leave_btn.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        leave_btn.setForeground(new java.awt.Color(217, 217, 217));
        leave_btn.setText("Leave Request");
        leave_btn.setBorder(null);
        leave_btn.setMargin(new java.awt.Insets(2, 0, 3, 0));
        leave_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leave_btnActionPerformed(evt);
            }
        });

        attendance_btn.setBackground(new java.awt.Color(30, 43, 93));
        attendance_btn.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        attendance_btn.setForeground(new java.awt.Color(217, 217, 217));
        attendance_btn.setText("Time In/ Time Out");
        attendance_btn.setBorder(null);
        attendance_btn.setMargin(new java.awt.Insets(2, 0, 3, 0));
        attendance_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendance_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(attendance_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(empProfile_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leave_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(empProfile_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(attendance_btn)
                .addGap(27, 27, 27)
                .addComponent(leave_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void empProfile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empProfile_btnActionPerformed
        // go to Employee Access Profile
       EmployeeAccess_Profile employeeAccessProfile = new EmployeeAccess_Profile();
       employeeAccessProfile.show();
        
        dispose();
    }//GEN-LAST:event_empProfile_btnActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        // log out
        String username="";
        
        int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to Log Out?", "Employee Portal", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION){
        
        Login loginPage = new Login();
        loginPage.show();
                
        dispose();
        } else if (result == JOptionPane.NO_OPTION){
        EmployeeAccessPortal employeeAccessPortal = new EmployeeAccessPortal ();
        employeeAccessPortal.setVisible(true);
                
        setVisible(false);   
        }
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void leave_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leave_btnActionPerformed
        // go to Employee Access Leave
        EmpAccessLeave empLeave = new EmpAccessLeave();
        empLeave.show();

        dispose();
    }//GEN-LAST:event_leave_btnActionPerformed

    private void attendance_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendance_btnActionPerformed
        // go to Employee Time Log
        EmployeeTimeLog timeLog = new EmployeeTimeLog();
        timeLog.show();
        
        dispose();
    }//GEN-LAST:event_attendance_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccessPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccessPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccessPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccessPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String username = null;
                new EmployeeAccessPortal().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LOGO;
    private javax.swing.JButton attendance_btn;
    private javax.swing.JLabel date;
    private javax.swing.JButton empProfile_btn;
    private javax.swing.JLabel empportal_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton leave_btn;
    private javax.swing.JButton logOutButton;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
