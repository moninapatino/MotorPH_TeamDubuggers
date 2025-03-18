
package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_util.DatabaseConnect;
import com.motorph_util.Postgresql;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.WindowConstants;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.table.TableRowSorter;


public class EmployeeAttendance extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    DatabaseConnect dbConnect = new DatabaseConnect() {};
          
       
    public EmployeeAttendance() {
        initComponents();
        setTitle ("Motor PH Employee Attendance");
        //setSize(700, 550);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        
        conn = Postgresql.java_db();
        show_table();
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
    
    public ArrayList show_table() {
        ArrayList userList = new ArrayList();
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = dbConnect.connect();
            String sql = "SELECT * FROM public.attendance_record";
            pst= conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            ResultSetMetaData rsmd=rs.getMetaData();
            int n=rsmd.getColumnCount();
            
            DefaultTableModel attendanceTable = (DefaultTableModel)attendance_table.getModel();
            attendanceTable.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i=0;i<n;i++){
                    v.add(rs.getString("date"));
                    v.add(rs.getString("employee_id"));
                    v.add(rs.getString("first_name"));
                    v.add(rs.getString("last_name"));
                    v.add(rs.getString("clock_in"));
                    v.add(rs.getString("clock_out"));
                    v.add(rs.getString("late"));
                    v.add(rs.getString("overtime"));
                    v.add(rs.getString("hours_worked"));
                                       
                    
                }
                attendanceTable.addRow(v);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        return userList;
    }
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        darkbluePanel = new javax.swing.JPanel();
        attendance_title = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        greetings = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        attendance_table = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        noe_title1 = new javax.swing.JLabel();
        id_field = new javax.swing.JTextField();
        time = new javax.swing.JLabel();
        payPeriodComboBox = new javax.swing.JComboBox<>();
        payperiod_lbl = new javax.swing.JLabel();
        hoursWorked_lbl = new javax.swing.JLabel();
        hoursworked_field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(30, 43, 93));

        darkbluePanel.setBackground(new java.awt.Color(250, 250, 255));

        attendance_title.setBackground(new java.awt.Color(92, 101, 138));
        attendance_title.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        attendance_title.setForeground(new java.awt.Color(92, 101, 138));
        attendance_title.setText("EMPLOYEE ATTENDANCE");

        javax.swing.GroupLayout darkbluePanelLayout = new javax.swing.GroupLayout(darkbluePanel);
        darkbluePanel.setLayout(darkbluePanelLayout);
        darkbluePanelLayout.setHorizontalGroup(
            darkbluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, darkbluePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attendance_title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        darkbluePanelLayout.setVerticalGroup(
            darkbluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, darkbluePanelLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(darkbluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(attendance_title, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo))
                .addGap(12, 12, 12))
        );

        greetings.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        greetings.setForeground(new java.awt.Color(250, 250, 255));
        greetings.setText("Greetings!");

        date.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        date.setForeground(new java.awt.Color(250, 250, 255));
        date.setText("Date");

        attendance_table.setBackground(new java.awt.Color(250, 250, 255));
        attendance_table.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        attendance_table.setForeground(new java.awt.Color(92, 101, 138));
        attendance_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Employee ID", "First Name", "Last Name", "Clock In", "Clock Out", "Late", "Overtime", "Hours Worked"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(attendance_table);

        backButton.setBackground(new java.awt.Color(253, 56, 29));
        backButton.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(250, 250, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        noe_title1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        noe_title1.setForeground(new java.awt.Color(250, 250, 255));
        noe_title1.setText("Employee ID :");

        id_field.setBackground(new java.awt.Color(250, 250, 255));
        id_field.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        id_field.setForeground(new java.awt.Color(92, 101, 138));
        id_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_fieldKeyReleased(evt);
            }
        });

        time.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        time.setForeground(new java.awt.Color(250, 250, 255));
        time.setText("Time");

        payPeriodComboBox.setBackground(new java.awt.Color(250, 250, 255));
        payPeriodComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        payPeriodComboBox.setForeground(new java.awt.Color(92, 101, 138));
        payPeriodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January 1-15, 2024", "January 16-31, 2024" }));
        payPeriodComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payPeriodComboBoxActionPerformed(evt);
            }
        });

        payperiod_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        payperiod_lbl.setForeground(new java.awt.Color(250, 250, 255));
        payperiod_lbl.setText("Pay Period :");

        hoursWorked_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        hoursWorked_lbl.setForeground(new java.awt.Color(250, 250, 255));
        hoursWorked_lbl.setText("Total numbers of Hours Worked :");

        hoursworked_field.setEditable(false);
        hoursworked_field.setBackground(new java.awt.Color(250, 250, 255));
        hoursworked_field.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        hoursworked_field.setForeground(new java.awt.Color(92, 101, 138));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(darkbluePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(744, 744, 744)
                        .addComponent(backButton))
                    .addComponent(greetings))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(hoursWorked_lbl)
                            .addComponent(payperiod_lbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(noe_title1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(224, 224, 224)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hoursworked_field, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payPeriodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(date)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(darkbluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(greetings)
                .addGap(22, 22, 22)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payPeriodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payperiod_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noe_title1)
                    .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoursWorked_lbl)
                    .addComponent(hoursworked_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date)
                    .addComponent(time))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // back to Employee Portal
        EmployeePortal employeePortal = new EmployeePortal();
        employeePortal.show();
        
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void id_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_fieldKeyReleased
        // search data from table
        
        DefaultTableModel attendanceTable = (DefaultTableModel)attendance_table.getModel();
        TableRowSorter<DefaultTableModel> table=new TableRowSorter<>(attendanceTable);
        attendance_table.setRowSorter(table);
        table.setRowFilter(RowFilter.regexFilter(id_field.getText()));
    }//GEN-LAST:event_id_fieldKeyReleased

    private void payPeriodComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payPeriodComboBoxActionPerformed
        // Pay Period Function
         try {
            Class.forName("org.postgresql.Driver");
            Connection conn = dbConnect.connect();
            String sql = "SELECT * FROM public.attendance_record WHERE employee_id =?";
            pst= conn.prepareStatement(sql);
            pst.setString(1, id_field.getText());
            rs = pst.executeQuery();
            
            if(rs.next()){
                if (payPeriodComboBox.getSelectedItem() == "January 1-15, 2024"){
                    String hoursWorked1 =rs.getString("first_cutOff");
                    hoursworked_field.setText(hoursWorked1);
                }else if (payPeriodComboBox.getSelectedItem() == "January 16-31, 2024"){
                    String hoursWorked2 =rs.getString("second_cutOff");
                    hoursworked_field.setText(hoursWorked2);
                }
            }
            
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_payPeriodComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeeAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeAttendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable attendance_table;
    private javax.swing.JLabel attendance_title;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel darkbluePanel;
    private javax.swing.JLabel date;
    private javax.swing.JLabel greetings;
    private javax.swing.JLabel hoursWorked_lbl;
    private javax.swing.JTextField hoursworked_field;
    private javax.swing.JTextField id_field;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel noe_title1;
    private javax.swing.JComboBox<String> payPeriodComboBox;
    private javax.swing.JLabel payperiod_lbl;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
