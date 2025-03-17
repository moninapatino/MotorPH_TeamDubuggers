package com.mmdc.motor_ph_portal.EmployeeAccess;

import com.motorph_util.Postgresql;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


public class EmpAccessLeave extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
          
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "@dm1n";
    
    public EmpAccessLeave() {
        initComponents();
        setTitle ("Motor PH Employee Leave Management");
        setSize(700, 550);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        
        conn = Postgresql.java_db();
        userList();
        time();
        date();
    }
    
    public final void date(){
    DateTimeFormatter dates = DateTimeFormatter.ofPattern("MMMM d, y");
    LocalDateTime now =LocalDateTime.now();
    date.setText(dates.format(now));
    }
    public final void time(){
    DateTimeFormatter times = DateTimeFormatter.ofPattern("hh:mm:ss a");
    LocalDateTime now =LocalDateTime.now();
    time.setText(times.format(now));
    }

    public ArrayList userList() {
        ArrayList userList = new ArrayList();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            String sql = "SELECT * FROM public.leave_record";
            pst= conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            ResultSetMetaData rsmd=rs.getMetaData();
            int n=rsmd.getColumnCount();
            
            DefaultTableModel leave_table = (DefaultTableModel)leaveTable.getModel();
            leave_table.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i=0;i<n;i++){
                    v.add(rs.getString("leave_num"));
                    v.add(rs.getString("first_name"));
                    v.add(rs.getString("last_name"));
                    v.add(rs.getString("date"));
                    v.add(rs.getString("leave_type"));
                    v.add(rs.getString("status"));
                                    
                }
                leave_table.addRow(v);
            }
            conn.close();
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }  
        return userList;
    }
    
    public ArrayList refreshList() {
        ArrayList refreshList = new ArrayList();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            String sql = "SELECT * FROM public.leave_record";
            pst= conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            ResultSetMetaData rsmd=rs.getMetaData();
            int n=rsmd.getColumnCount();
            
            DefaultTableModel leave_table = (DefaultTableModel)leaveTable.getModel();
            leave_table.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i=0;i<n;i++){
                    v.add(rs.getString("leave_num"));
                    v.add(rs.getString("first_name"));
                    v.add(rs.getString("last_name"));
                    v.add(rs.getString("date"));
                    v.add(rs.getString("leave_type"));
                    v.add(rs.getString("status"));
                                    
                }
                leave_table.addRow(v);
            }
            conn.close();
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }  
        return refreshList;
                     
   }
      
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        darkbluepanel = new javax.swing.JPanel();
        leave_title = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();
        greetings = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        noe_title1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaveTable = new javax.swing.JTable();
        id_field = new javax.swing.JTextField();
        time = new javax.swing.JLabel();
        noe_title2 = new javax.swing.JLabel();
        firstName_field = new javax.swing.JTextField();
        noe_title3 = new javax.swing.JLabel();
        lastName_field = new javax.swing.JTextField();
        leave_type = new javax.swing.JLabel();
        leaveTypeComboBox = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        date_lbl = new javax.swing.JLabel();
        date_chooser = new com.toedter.calendar.JDateChooser();
        leaveNum = new javax.swing.JLabel();
        leaveNum_field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(30, 43, 93));
        mainPanel.setPreferredSize(new java.awt.Dimension(650, 450));

        darkbluepanel.setBackground(new java.awt.Color(250, 250, 255));
        darkbluepanel.setForeground(new java.awt.Color(217, 217, 217));
        darkbluepanel.setPreferredSize(new java.awt.Dimension(260, 60));

        leave_title.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        leave_title.setForeground(new java.awt.Color(92, 101, 138));
        leave_title.setText("EMPLOYEE LEAVE REQUEST");

        javax.swing.GroupLayout darkbluepanelLayout = new javax.swing.GroupLayout(darkbluepanel);
        darkbluepanel.setLayout(darkbluepanelLayout);
        darkbluepanelLayout.setHorizontalGroup(
            darkbluepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(darkbluepanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LOGO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leave_title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        darkbluepanelLayout.setVerticalGroup(
            darkbluepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, darkbluepanelLayout.createSequentialGroup()
                .addGroup(darkbluepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(darkbluepanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(leave_title, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                    .addGroup(darkbluepanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(LOGO)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        greetings.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        greetings.setForeground(new java.awt.Color(250, 250, 255));
        greetings.setText("Greetings!");

        date.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        date.setForeground(new java.awt.Color(250, 250, 255));
        date.setText("Date ");

        backButton.setBackground(new java.awt.Color(253, 56, 29));
        backButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(250, 250, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        noe_title1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title1.setForeground(new java.awt.Color(250, 250, 255));
        noe_title1.setText("Employee ID :");

        leaveTable.setBackground(new java.awt.Color(250, 250, 255));
        leaveTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leaveTable.setForeground(new java.awt.Color(92, 101, 138));
        leaveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leave No.", "First Name", "Last Name", "Date", "Leave Type", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(leaveTable);
        if (leaveTable.getColumnModel().getColumnCount() > 0) {
            leaveTable.getColumnModel().getColumn(1).setResizable(false);
            leaveTable.getColumnModel().getColumn(2).setResizable(false);
            leaveTable.getColumnModel().getColumn(5).setResizable(false);
        }

        id_field.setBackground(new java.awt.Color(250, 250, 255));
        id_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        id_field.setForeground(new java.awt.Color(92, 101, 138));
        id_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_fieldKeyReleased(evt);
            }
        });

        time.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        time.setForeground(new java.awt.Color(250, 250, 255));
        time.setText("Time");

        noe_title2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title2.setForeground(new java.awt.Color(250, 250, 255));
        noe_title2.setText("First Name :");

        firstName_field.setBackground(new java.awt.Color(250, 250, 255));
        firstName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        firstName_field.setForeground(new java.awt.Color(92, 101, 138));

        noe_title3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title3.setForeground(new java.awt.Color(250, 250, 255));
        noe_title3.setText("Last Name :");

        lastName_field.setBackground(new java.awt.Color(250, 250, 255));
        lastName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        lastName_field.setForeground(new java.awt.Color(92, 101, 138));

        leave_type.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leave_type.setForeground(new java.awt.Color(250, 250, 255));
        leave_type.setText("Leave Type :");

        leaveTypeComboBox.setBackground(new java.awt.Color(250, 250, 255));
        leaveTypeComboBox.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveTypeComboBox.setForeground(new java.awt.Color(92, 101, 138));
        leaveTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vacation Leave", "Sick Leave", "Emergency Leave", "Birthday Leave" }));

        addButton.setBackground(new java.awt.Color(253, 56, 29));
        addButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        addButton.setForeground(new java.awt.Color(250, 250, 255));
        addButton.setText("File Leave");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        date_lbl.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        date_lbl.setForeground(new java.awt.Color(250, 250, 255));
        date_lbl.setText("Date :");

        date_chooser.setBackground(new java.awt.Color(250, 250, 255));
        date_chooser.setForeground(new java.awt.Color(92, 101, 138));
        date_chooser.setDateFormatString("dd MMM yyyy");
        date_chooser.setFont(new java.awt.Font("Gadugi", 0, 10)); // NOI18N

        leaveNum.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leaveNum.setForeground(new java.awt.Color(250, 250, 255));
        leaveNum.setText("Leave No. :");

        leaveNum_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveNum_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveNum_field.setForeground(new java.awt.Color(92, 101, 138));
        leaveNum_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                leaveNum_fieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(darkbluepanel, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(greetings)
                .addGap(93, 548, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noe_title1)
                            .addComponent(noe_title3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(noe_title2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(leave_type)
                                    .addComponent(date_lbl))
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(leaveTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(date_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(leaveNum)
                                .addGap(18, 18, 18)
                                .addComponent(leaveNum_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(date)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(darkbluepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(greetings)
                        .addGap(44, 44, 44)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noe_title1)
                            .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noe_title2)
                            .addComponent(firstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noe_title3)
                            .addComponent(lastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(leaveNum)
                            .addComponent(leaveNum_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(date_chooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(leave_type)
                            .addComponent(leaveTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date)
                            .addComponent(time))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(54, 54, 54))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void id_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_fieldKeyReleased
        // SEARCH EMPLOYEE NAME
        try {            
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            String sql = "SELECT * FROM public.employee_data WHERE employee_id= ?";
            
            pst=conn.prepareStatement(sql);
            pst.setString(1, id_field.getText());
            rs=pst.executeQuery();
            if(rs.next()){
               
                String firstName =rs.getString("first_name");
                firstName_field.setText(firstName);
                
                String lastName =rs.getString("last_name");
                lastName_field.setText(lastName);
                                                             
                         
            }
       }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception ex) {
                
            }
        }
    }//GEN-LAST:event_id_fieldKeyReleased

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // add data to sql
        String leaveType = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, y");
        String fDate = dateFormat.format(date_chooser.getDate());
        
        DefaultTableModel model = (DefaultTableModel)leaveTable.getModel();
        model.addRow(new Object[]{leaveNum_field.getText(), firstName_field.getText(), lastName_field.getText(), fDate,leaveTypeComboBox.getSelectedItem().toString()});
             
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            pst=conn.prepareStatement("INSERT INTO public.leave_record(leave_num, employee_id, first_name, "
                    + "last_name, date, leave_type, status)values(?,?,?,?,?,?,?)");
            
            pst.setString(1, leaveNum_field.getText());
            pst.setString(2, id_field.getText());
            pst.setString(3, firstName_field.getText());
            pst.setString(4, lastName_field.getText());
            pst.setString(5, fDate);
            leaveType = leaveTypeComboBox.getSelectedItem().toString();
            pst.setString(6, leaveType);
            
            pst.setString(7, "");
            pst.executeUpdate();
                    
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
}
    
    }//GEN-LAST:event_addButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // back to employee access portal
        String username = "";
        
        EmployeeAccessPortal employeeAccessPortal = new EmployeeAccessPortal ();
        employeeAccessPortal.setVisible(true);               
        setVisible(false); 
    }//GEN-LAST:event_backButtonActionPerformed

    private void leaveNum_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_leaveNum_fieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_leaveNum_fieldKeyReleased

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
            java.util.logging.Logger.getLogger(EmpAccessLeave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmpAccessLeave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmpAccessLeave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmpAccessLeave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmpAccessLeave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LOGO;
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel darkbluepanel;
    private javax.swing.JLabel date;
    private com.toedter.calendar.JDateChooser date_chooser;
    private javax.swing.JLabel date_lbl;
    private javax.swing.JTextField firstName_field;
    private javax.swing.JLabel greetings;
    private javax.swing.JTextField id_field;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastName_field;
    private javax.swing.JLabel leaveNum;
    private javax.swing.JTextField leaveNum_field;
    private javax.swing.JTable leaveTable;
    private javax.swing.JComboBox<String> leaveTypeComboBox;
    private javax.swing.JLabel leave_title;
    private javax.swing.JLabel leave_type;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel noe_title1;
    private javax.swing.JLabel noe_title2;
    private javax.swing.JLabel noe_title3;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
