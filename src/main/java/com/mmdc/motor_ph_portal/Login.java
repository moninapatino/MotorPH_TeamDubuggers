package com.mmdc.motor_ph_portal;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_portal.EmployeeAccess.Employee_Class;
import com.mmdc.motor_ph_util.DatabaseConnect;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Login extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public Login() {
        initComponents();
        setTitle("Motor PH Employee Portal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        motorPhTitle1 = new javax.swing.JLabel();
        titleUserID1 = new javax.swing.JLabel();
        userIDText1 = new javax.swing.JTextField();
        titlePassword1 = new javax.swing.JLabel();
        passwordText1 = new javax.swing.JPasswordField();
        logInButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(30, 43, 93));

        motorPhTitle1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        motorPhTitle1.setForeground(new java.awt.Color(250, 250, 255));
        motorPhTitle1.setText("EMPLOYEE PORTAL");

        titleUserID1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        titleUserID1.setForeground(new java.awt.Color(250, 250, 255));
        titleUserID1.setText("USERNAME");

        userIDText1.setBackground(new java.awt.Color(30, 43, 93));
        userIDText1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        userIDText1.setForeground(new java.awt.Color(250, 250, 255));

        titlePassword1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        titlePassword1.setForeground(new java.awt.Color(250, 250, 255));
        titlePassword1.setText("PASSWORD");

        passwordText1.setBackground(new java.awt.Color(30, 43, 93));
        passwordText1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        passwordText1.setForeground(new java.awt.Color(250, 250, 255));

        logInButton1.setBackground(new java.awt.Color(253, 56, 29));
        logInButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        logInButton1.setForeground(new java.awt.Color(250, 250, 255));
        logInButton1.setText("LOG IN");
        logInButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(titlePassword1)
                                .addComponent(titleUserID1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(userIDText1)
                                .addComponent(passwordText1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logInButton1)
                            .addGap(77, 77, 77)))
                    .addComponent(motorPhTitle1))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(164, Short.MAX_VALUE)
                .addComponent(motorPhTitle1)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleUserID1)
                    .addComponent(userIDText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titlePassword1)
                    .addComponent(passwordText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logInButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 379, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 294, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logInButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInButton1ActionPerformed
        // login action
        String username = userIDText1.getText();
        String password = passwordText1.getText();

        DatabaseConnect dbConnect = new DatabaseConnect() {};
        conn = dbConnect.connect();

        try {
            String sql = "SELECT employee_id, first_name, last_name, birthday, address, phone_number, "
                    + "sss_num, philhealth_num, tin_num, pagibig_num, status, position, "
                    + "supervisor, basic_salary, sss_c, rice_s, phone_a, clothing_a, "
                    + "grosssemi_monthly_rate, hourly_rate, role "
                    + "FROM public.employee_data WHERE username = ? AND password = ?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                String employeeID = rs.getString("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String birthday = rs.getString("birthday");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                String sssNum = rs.getString("sss_num");
                String philHealthNum = rs.getString("philhealth_num");
                String tinNum = rs.getString("tin_num");
                String pagibigNum = rs.getString("pagibig_num");
                String status = rs.getString("status");
                String position = rs.getString("position");
                String supervisor = rs.getString("supervisor");
                double basicSalary = rs.getDouble("basic_salary");
                double sssC = rs.getDouble("sss_c");
                double riceA = rs.getDouble("rice_s");
                double phoneA = rs.getDouble("phone_a");
                double clothingA = rs.getDouble("clothing_a");
                double grossSemiMonthlyRate = rs.getDouble("grosssemi_monthly_rate");
                double hourlyRate = rs.getDouble("hourly_rate");
                String role = rs.getString("role");

                if ("Admin".equalsIgnoreCase(role)) {
                    Admin_Class admin = new Admin_Class(employeeID, firstName, lastName, birthday, address, phoneNumber,
                            sssNum, philHealthNum, tinNum, pagibigNum, status, position,
                            supervisor, basicSalary, sssC, riceA, phoneA, clothingA,
                            grossSemiMonthlyRate, hourlyRate, username, password,role);
                    admin.login(username, password);
                    dispose();
                } else {
                    Employee_Class emp = new Employee_Class(employeeID, firstName, lastName, birthday, address,
                            phoneNumber, sssNum, philHealthNum, tinNum,
                            pagibigNum, status, position, supervisor,
                            username, password);
                    emp.login(username, password);
                    dispose();
                }
                JOptionPane.showMessageDialog(null, "Log in Successful");

            } else if (userIDText1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Fill Out User ID");

            } else if (passwordText1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Fill Out Password");

            } else {
                JOptionPane.showMessageDialog(null, "Wrong User ID or Password");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_logInButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton logInButton1;
    private javax.swing.JLabel motorPhTitle1;
    public javax.swing.JPasswordField passwordText1;
    private javax.swing.JLabel titlePassword1;
    private javax.swing.JLabel titleUserID1;
    private javax.swing.JTextField userIDText1;
    // End of variables declaration//GEN-END:variables
}
