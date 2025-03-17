package com.mmdc.motor_ph_portal.AdminAccess;

import com.motorph_util.Postgresql;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;



public class LeaveManagement extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
          
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "@dm1n";
    
    public LeaveManagement() {
        initComponents();
        
        setTitle ("Motor PH Employee Leave Management");
        setSize(780, 600);
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
    
      public void clear(){
        leaveNum_field.setText("");
        id_field.setText("");
        firstName_field.setText("");
        lastName_field.setText("");
        date_field.setText("");
        leaveType_field.setText("");
        approve_rb.setSelected(false);
        reject_rb.setSelected(false);
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
                    v.add(rs.getString("employee_id"));
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
                    v.add(rs.getString("employee_id"));
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
        logo = new javax.swing.JLabel();
        greetings = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        noe_title1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaveTable = new javax.swing.JTable();
        id_field = new javax.swing.JTextField();
        time = new javax.swing.JLabel();
        lbl_firstName = new javax.swing.JLabel();
        firstName_field = new javax.swing.JTextField();
        lbl_lastName = new javax.swing.JLabel();
        lastName_field = new javax.swing.JTextField();
        lbl_leaveType = new javax.swing.JLabel();
        leaveType_field = new javax.swing.JTextField();
        lbl_status = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();
        date_field = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        approve_rb = new javax.swing.JRadioButton();
        reject_rb = new javax.swing.JRadioButton();
        clearBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
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
        leave_title.setText("EMPLOYEE LEAVE MANAGEMENT");

        javax.swing.GroupLayout darkbluepanelLayout = new javax.swing.GroupLayout(darkbluepanel);
        darkbluepanel.setLayout(darkbluepanelLayout);
        darkbluepanelLayout.setHorizontalGroup(
            darkbluepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(darkbluepanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leave_title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        darkbluepanelLayout.setVerticalGroup(
            darkbluepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, darkbluepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leave_title, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, darkbluepanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logo)
                .addGap(15, 15, 15))
        );

        greetings.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        greetings.setForeground(new java.awt.Color(250, 250, 255));
        greetings.setText("Greetings!");

        date.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        date.setForeground(new java.awt.Color(250, 250, 255));
        date.setText("Date ");

        backButton.setBackground(new java.awt.Color(253, 56, 29));
        backButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(250, 250, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        noe_title1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        noe_title1.setForeground(new java.awt.Color(250, 250, 255));
        noe_title1.setText("Employee ID :");

        leaveTable.setBackground(new java.awt.Color(250, 250, 255));
        leaveTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leaveTable.setForeground(new java.awt.Color(92, 101, 138));
        leaveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leave No.", "Employee ID", "First Name", "Last Name", "Date", "Leave Type", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        leaveTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leaveTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(leaveTable);

        id_field.setBackground(new java.awt.Color(250, 250, 255));
        id_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        id_field.setForeground(new java.awt.Color(92, 101, 138));

        time.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        time.setForeground(new java.awt.Color(250, 250, 255));
        time.setText("Time");

        lbl_firstName.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_firstName.setForeground(new java.awt.Color(250, 250, 255));
        lbl_firstName.setText("First Name :");

        firstName_field.setBackground(new java.awt.Color(250, 250, 255));
        firstName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        firstName_field.setForeground(new java.awt.Color(92, 101, 138));

        lbl_lastName.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_lastName.setForeground(new java.awt.Color(250, 250, 255));
        lbl_lastName.setText("Last Name :");

        lastName_field.setBackground(new java.awt.Color(250, 250, 255));
        lastName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        lastName_field.setForeground(new java.awt.Color(92, 101, 138));

        lbl_leaveType.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_leaveType.setForeground(new java.awt.Color(250, 250, 255));
        lbl_leaveType.setText("Leave Type :");

        leaveType_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveType_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveType_field.setForeground(new java.awt.Color(92, 101, 138));

        lbl_status.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_status.setForeground(new java.awt.Color(250, 250, 255));
        lbl_status.setText("Status :");

        lbl_date.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_date.setForeground(new java.awt.Color(250, 250, 255));
        lbl_date.setText("Date :");

        date_field.setBackground(new java.awt.Color(250, 250, 255));
        date_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        date_field.setForeground(new java.awt.Color(92, 101, 138));

        updateBtn.setBackground(new java.awt.Color(253, 56, 29));
        updateBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(250, 250, 255));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        approve_rb.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        approve_rb.setForeground(new java.awt.Color(250, 250, 255));
        approve_rb.setText("Approved");

        reject_rb.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        reject_rb.setForeground(new java.awt.Color(250, 250, 255));
        reject_rb.setText("Rejected");

        clearBtn.setBackground(new java.awt.Color(253, 56, 29));
        clearBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(250, 250, 255));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(253, 56, 29));
        deleteBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(250, 250, 255));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        leaveNum.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        leaveNum.setForeground(new java.awt.Color(250, 250, 255));
        leaveNum.setText("Leave No. :");

        leaveNum_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveNum_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveNum_field.setForeground(new java.awt.Color(92, 101, 138));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(darkbluepanel, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(date)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(updateBtn)
                                .addGap(18, 18, 18)
                                .addComponent(deleteBtn)
                                .addGap(17, 17, 17)
                                .addComponent(clearBtn)))))
                .addGap(23, 23, 23))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(greetings))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(lbl_lastName)
                                .addGap(39, 39, 39)
                                .addComponent(lastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(lbl_firstName)
                                .addGap(39, 39, 39)
                                .addComponent(firstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(noe_title1)
                                .addGap(39, 39, 39)
                                .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_leaveType)
                            .addComponent(lbl_status)
                            .addComponent(lbl_date))
                        .addGap(39, 39, 39)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(date_field, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(leaveType_field, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(approve_rb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reject_rb))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(leaveNum)
                        .addGap(39, 39, 39)
                        .addComponent(leaveNum_field, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(darkbluepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(greetings)
                .addGap(8, 8, 8)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leaveNum)
                    .addComponent(leaveNum_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noe_title1)
                    .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_date)
                    .addComponent(date_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_firstName)
                            .addComponent(firstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_leaveType)
                            .addComponent(leaveType_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_lastName)
                            .addComponent(lastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_status)
                            .addComponent(approve_rb)
                            .addComponent(reject_rb))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateBtn)
                            .addComponent(clearBtn)
                            .addComponent(deleteBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(backButton)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date)
                            .addComponent(time))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // back to Employee Portal
        EmployeePortal employeePortal = new EmployeePortal();
        employeePortal.show();
        
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // Update data to SQL
        try {
            
            String status = "";
            if (approve_rb.isSelected()){
               status = "Approved";
            }
            if (reject_rb.isSelected()){
                status = "Rejected";
            }
            
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            pst=conn.prepareStatement("UPDATE public.leave_record SET status ='"+status+"' WHERE leave_num =?");
            
            pst.setString(1, leaveNum_field.getText());
            pst.execute();
            
            
            DefaultTableModel leaveRecord1 = (DefaultTableModel)leaveTable.getModel();
            int i = leaveTable.getSelectedRow();
            date_field.setText(leaveRecord1.getValueAt(i, 3) .toString());
            String statusRB = leaveRecord1.getValueAt(i, 5) .toString();
                if (statusRB.equals("Approved")){
                    approve_rb.setSelected(true);
                    reject_rb.setSelected(false);
                }
                else{
                    reject_rb.setSelected(false);
                    approve_rb.setSelected(false);
                    
                }if (statusRB.equals("Rejected")){
                    reject_rb.setSelected(true);
                    approve_rb.setSelected(false);
                }        
                
            JOptionPane.showMessageDialog(this, "Leave Record Updated!");
            conn.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        refreshList();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void leaveTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaveTableMouseClicked
        // Selected Row Function
                
        DefaultTableModel leaveRecord1 = (DefaultTableModel)leaveTable.getModel();
        int i = leaveTable.getSelectedRow();
        
        try {
            leaveNum_field.setText(leaveRecord1.getValueAt(i, 0) .toString());
            id_field.setText(leaveRecord1.getValueAt(i, 1) .toString());
            firstName_field.setText(leaveRecord1.getValueAt(i, 2) .toString());
            lastName_field.setText(leaveRecord1.getValueAt(i, 3) .toString());
            date_field.setText(leaveRecord1.getValueAt(i, 4) .toString());
            leaveType_field.setText(leaveRecord1.getValueAt(i, 5) .toString());
            String statusRB = leaveRecord1.getValueAt(i, 6) .toString();
            if (statusRB.equals("Approved")){
                approve_rb.setSelected(true);
                reject_rb.setSelected(false);
            }
            else{
                reject_rb.setSelected(true);
                approve_rb.setSelected(false);
            }
            if (statusRB.equals("")){
                reject_rb.setSelected(false);
                approve_rb.setSelected(false);
            }
                    
        } catch (Exception e){

            }
    }//GEN-LAST:event_leaveTableMouseClicked

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // Clear Textfield
        clear();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // DELETE RECORD
                
        try {
            int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this?", "Employee Profile Deleting...", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
                
            
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url,user,password);
            pst=conn.prepareStatement("DELETE FROM public.leave_record WHERE leave_num =?");
                    
            pst.setString(1, leaveNum_field.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Selected Record Deleted");
            clear();
            
            } else if (result == JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(this, "Employee Profile Deletion Not Successful!");
            }      
            
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        refreshList();

    }//GEN-LAST:event_deleteBtnActionPerformed

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
            java.util.logging.Logger.getLogger(LeaveManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeaveManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeaveManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeaveManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeaveManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton approve_rb;
    private javax.swing.JButton backButton;
    private javax.swing.JButton clearBtn;
    private javax.swing.JPanel darkbluepanel;
    private javax.swing.JLabel date;
    private javax.swing.JTextField date_field;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField firstName_field;
    private javax.swing.JLabel greetings;
    private javax.swing.JTextField id_field;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastName_field;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_firstName;
    private javax.swing.JLabel lbl_lastName;
    private javax.swing.JLabel lbl_leaveType;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel leaveNum;
    private javax.swing.JTextField leaveNum_field;
    private javax.swing.JTable leaveTable;
    private javax.swing.JTextField leaveType_field;
    private javax.swing.JLabel leave_title;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel noe_title1;
    private javax.swing.JRadioButton reject_rb;
    private javax.swing.JLabel time;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
