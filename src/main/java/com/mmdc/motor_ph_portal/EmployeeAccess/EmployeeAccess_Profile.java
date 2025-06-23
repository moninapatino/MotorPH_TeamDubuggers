
package com.mmdc.motor_ph_portal.EmployeeAccess;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_portal.AdminAccess.EmployeePortal;
import com.mmdc.motor_ph_portal.LeaveRecord;
import com.mmdc.motor_ph_portal.Login;
import com.mmdc.motor_ph_util.DatabaseConnect;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import javax.swing.WindowConstants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class EmployeeAccess_Profile extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    DatabaseConnect dbConnect = new DatabaseConnect() {};
    private String username;
    private String employeeID;

     
    public EmployeeAccess_Profile() {
        initComponents();
        
    }
    
   /*public EmployeeAccess_Profile(String employeeID, String firstName, String lastName) {
        initComponents();// Full initialization setup 


        setTitle ("Motor PH Employee Profile");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);// Window positioning
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        

        loadEmployeeData(employeeID);
        loadLeaveRecords();
        time();
        date();
        setupTabChangeListener();
    }*/
   
//NEW: Constructor with username parameter - properly structured 
    public EmployeeAccess_Profile(String username) {
        initComponents();
       
        setTitle ("Motor PH Employee Profile");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
       //NEW: Store username as instance variable
        this.username = username;
       
        loadAndFillEmployeeDataByUsername(username);
        loadEmployeeData(employeeID);
        loadLeaveRecords();
        time();
        date();
        setupTabChangeListener();
    }
  
    public final void time(){
    DateTimeFormatter times = DateTimeFormatter.ofPattern("hh:mm:ss a");
    LocalDateTime now =LocalDateTime.now();
    time.setText(times.format(now));          
    }
     public final void date(){
    DateTimeFormatter dates = DateTimeFormatter.ofPattern("MMM d, y");
    LocalDateTime now =LocalDateTime.now();
    date.setText(dates.format(now));
    }
    
     private void loadEmployeeData(String employeeID) {
        try {
            conn = dbConnect.connect();
            Admin_Class employee = dbConnect.getEmployeeDetails(employeeID);
            if (employee != null) {
  
                id_field.setText(employee.getEmployeeID());
                firstName_field.setText(employee.getFirstName());
                lastname_field.setText(employee.getLastName());
                timeLogFirstNameField.setText(employee.getFirstName());
                timeLogLastNameField.setText(employee.getLastName());
                leaveFirstName_field.setText(employee.getFirstName());
                leaveLastName_field.setText(employee.getLastName());
  
                String fullName = employee.getFirstName() + " " + employee.getLastName();
                empName.setText(fullName);

                 bday_field.setText(employee.getBirthday() != null ? employee.getBirthday() : "");
               
                ArrayList<String> addressParts = new ArrayList<>();
                if (employee.getStreet() != null && !employee.getStreet().isEmpty()) addressParts.add(employee.getStreet());
                if (employee.getBarangay() != null && !employee.getBarangay().isEmpty()) addressParts.add(employee.getBarangay());
                if (employee.getCity() != null && !employee.getCity().isEmpty()) addressParts.add(employee.getCity());
                if (employee.getProvince() != null && !employee.getProvince().isEmpty()) addressParts.add(employee.getProvince());
                String fullAddress = String.join(", ", addressParts);
                if (employee.getPostalcode() != null && !employee.getPostalcode().isEmpty()) {
                    fullAddress += " " + employee.getPostalcode();
                }
                street_field.setText(fullAddress);
               
                contact_field.setText(employee.getPhoneNumber() != null ? employee.getPhoneNumber() : "");
                email_field.setText(employee.getEmail() != null ? employee.getEmail() : "");
                sss_field.setText(employee.getSssNum() != null ? employee.getSssNum() : "");
                philhealth_field.setText(employee.getPhilHealthNum() != null ? employee.getPhilHealthNum() : "");
                pagibig_field.setText(employee.getPagibigNum() != null ? employee.getPagibigNum() : "");
                tin_field.setText(employee.getTinNum() != null ? employee.getTinNum() : "");
            } else {
                JOptionPane.showMessageDialog(null, "No employee found with ID: " + employeeID, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading employee data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   public void loadAndFillEmployeeDataByUsername(String username) {
    if (username == null || username.trim().isEmpty()) {
        return; // skip empty input
    }

    try {
        Admin_Class employee = dbConnect.getEmployeeByUsername(username);


        if (employee != null) {
            this.employeeID = employee.getEmployeeID();

            id_field.setText(employee.getEmployeeID()); 
            firstName_field.setText(employee.getFirstName());
            lastname_field.setText(employee.getLastName());
            timeLogFirstNameField.setText(employee.getFirstName());
            timeLogLastNameField.setText(employee.getLastName());
            leaveFirstName_field.setText(employee.getFirstName());
            leaveLastName_field.setText(employee.getLastName());


            String fullName = employee.getFirstName() + " " + employee.getLastName();
            empName.setText(fullName);
            bday_field.setText(employee.getBirthday() != null ? employee.getBirthday() : "");
           
            ArrayList<String> addressParts = new ArrayList<>();
            if (employee.getStreet() != null && !employee.getStreet().isEmpty()) addressParts.add(employee.getStreet());
            if (employee.getBarangay() != null && !employee.getBarangay().isEmpty()) addressParts.add(employee.getBarangay());
            if (employee.getCity() != null && !employee.getCity().isEmpty()) addressParts.add(employee.getCity());
            if (employee.getProvince() != null && !employee.getProvince().isEmpty()) addressParts.add(employee.getProvince());
            String fullAddress = String.join(", ", addressParts);
            if (employee.getPostalcode() != null && !employee.getPostalcode().isEmpty()) {
                fullAddress += " " + employee.getPostalcode();
            }
            street_field.setText(fullAddress);
                     
 	
            contact_field.setText(employee.getPhoneNumber() != null ? employee.getPhoneNumber() : "");
            email_field.setText(employee.getEmail() != null ? employee.getEmail() : "");
            sss_field.setText(employee.getSssNum() != null ? employee.getSssNum() : "");
            philhealth_field.setText(employee.getPhilHealthNum() != null ? employee.getPhilHealthNum() : "");
            pagibig_field.setText(employee.getPagibigNum() != null ? employee.getPagibigNum() : "");
            tin_field.setText(employee.getTinNum() != null ? employee.getTinNum() : "");
        } else {
            JOptionPane.showMessageDialog(null, "No employee found with username: " + username);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error loading employee: " + e.getMessage());
    }
}
 
    public void loadLeaveRecords() {
        ArrayList<LeaveRecord> leaveRecords = dbConnect.userList();
        
        DefaultTableModel leaveTableModel = (DefaultTableModel) leaveTable.getModel();
        leaveTableModel.setRowCount(0); // Clear existing rows

        for (LeaveRecord record : leaveRecords) {
            Vector<String> row = new Vector<>();
            row.add(record.getLeaveId());
            row.add(record.getEmployeeId());
            row.add(record.getFirstName());
            row.add(record.getLastName());
            row.add(record.getStartDate());
            row.add(record.getEndDate());
            row.add(record.getLeaveType());
            row.add(record.getStatus());
            leaveTableModel.addRow(row);
        }              
   }
   
    public ArrayList refreshList() {
         ArrayList<LeaveRecord> leaveRecords = dbConnect.refreshList();
        
        DefaultTableModel leaveTableModel = (DefaultTableModel) leaveTable.getModel();
        leaveTableModel.setRowCount(0); // Clear existing rows

        for (LeaveRecord record : leaveRecords) {
            Vector<String> row = new Vector<>();
            row.add(record.getLeaveId());
            row.add(record.getEmployeeId());
            row.add(record.getFirstName());
            row.add(record.getLastName());
            row.add(record.getStartDate());
            row.add(record.getEndDate());
            row.add(record.getLeaveType());
            row.add(record.getStatus());
            leaveTableModel.addRow(row);
        }
        return leaveRecords;
    }
    
    public ArrayList loadTimeLog() {
        ArrayList timeLog = new ArrayList();
        try {
            String sql = "SELECT e.first_name, e.last_name, ar.date, ar.time_in, ar.time_out " +
                         "FROM attendance_records ar " +
                         "JOIN employee e ON ar.employee_id = e.employee_id " +
                         "WHERE ar.employee_id = ?";
            pst= conn.prepareStatement(sql);
            pst.setString(1, id_field.getText());
            rs = pst.executeQuery();
            
            ResultSetMetaData rsmd=rs.getMetaData();
            int n=rsmd.getColumnCount();
            
            DefaultTableModel leave_table = (DefaultTableModel)attendance_table.getModel();
            leave_table.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i=0;i<n;i++){
                    
                    v.add(rs.getString("first_name"));
                    v.add(rs.getString("last_name"));
                    v.add(rs.getString("date"));
                    v.add(rs.getString("time_in"));
                    v.add(rs.getString("time_out"));
                                    
                }
                leave_table.addRow(v);
            }    
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }  
        return timeLog;
     }
    public void refreshTimeLog() {
         loadTimeLog();
     }
        
    private void setupTabChangeListener() {
        tab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                if (tab.getSelectedIndex() == 1) { // Time Log tab
                    refreshTimeLog();
                } else if (tab.getSelectedIndex() == 2) { // Leave tab
                    loadLeaveRecords();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        empProfileTab = new javax.swing.JPanel();
        noe_title = new javax.swing.JLabel();
        b_title = new javax.swing.JLabel();
        cn_title = new javax.swing.JLabel();
        email_title = new javax.swing.JLabel();
        firstName_field = new javax.swing.JTextField();
        bday_field = new javax.swing.JTextField();
        contact_field = new javax.swing.JTextField();
        email_field = new javax.swing.JTextField();
        govIdNum_title = new javax.swing.JLabel();
        sss_title = new javax.swing.JLabel();
        pagibig_title = new javax.swing.JLabel();
        phhealth_title = new javax.swing.JLabel();
        tin_title = new javax.swing.JLabel();
        sss_field = new javax.swing.JTextField();
        pagibig_field = new javax.swing.JTextField();
        philhealth_field = new javax.swing.JTextField();
        tin_field = new javax.swing.JTextField();
        noe_title8 = new javax.swing.JLabel();
        lastname_field = new javax.swing.JTextField();
        address_title = new javax.swing.JLabel();
        street_field = new javax.swing.JTextField();
        empProfileTitle = new javax.swing.JLabel();
        brgy_field = new javax.swing.JTextField();
        city_field = new javax.swing.JTextField();
        province_field = new javax.swing.JTextField();
        postalcode_field = new javax.swing.JTextField();
        address_title1 = new javax.swing.JLabel();
        address_title2 = new javax.swing.JLabel();
        address_title3 = new javax.swing.JLabel();
        address_title4 = new javax.swing.JLabel();
        timeLogTab = new javax.swing.JPanel();
        timeLogFirstNameField = new javax.swing.JTextField();
        first_name = new javax.swing.JLabel();
        timeLogLastNameField = new javax.swing.JTextField();
        timeLogLastNameLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        attendance_table = new javax.swing.JTable();
        timeInBtn = new javax.swing.JButton();
        timeOutBtn = new javax.swing.JButton();
        attendance_title = new javax.swing.JLabel();
        leaveTab = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        leaveTable = new javax.swing.JTable();
        noe_title6 = new javax.swing.JLabel();
        leaveFirstName_field = new javax.swing.JTextField();
        noe_title7 = new javax.swing.JLabel();
        leaveLastName_field = new javax.swing.JTextField();
        leave_type = new javax.swing.JLabel();
        leaveTypeComboBox = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        enddate_lbl1 = new javax.swing.JLabel();
        enddate_chooser = new com.toedter.calendar.JDateChooser();
        leaveNum1 = new javax.swing.JLabel();
        leaveNum_field = new javax.swing.JTextField();
        startdate_lbl2 = new javax.swing.JLabel();
        startdate_chooser = new com.toedter.calendar.JDateChooser();
        leave_title = new javax.swing.JLabel();
        leaveTab1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        leaveTable1 = new javax.swing.JTable();
        noe_title9 = new javax.swing.JLabel();
        leaveFirstName_field1 = new javax.swing.JTextField();
        noe_title10 = new javax.swing.JLabel();
        leaveLastName_field1 = new javax.swing.JTextField();
        payslipBtn = new javax.swing.JButton();
        leave_title1 = new javax.swing.JLabel();
        noe_title11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        empName = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        en_title = new javax.swing.JLabel();
        id_field = new javax.swing.JTextField();
        img = new javax.swing.JLabel();
        logOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 250, 255));
        jPanel1.setForeground(new java.awt.Color(250, 250, 255));

        tab.setBackground(new java.awt.Color(30, 43, 93));
        tab.setForeground(new java.awt.Color(250, 250, 255));
        tab.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tab.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        tab.setOpaque(true);
        tab.setPreferredSize(new java.awt.Dimension(80, 150));

        empProfileTab.setBackground(new java.awt.Color(30, 43, 93));
        empProfileTab.setForeground(new java.awt.Color(30, 43, 93));

        noe_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        noe_title.setForeground(new java.awt.Color(250, 250, 255));
        noe_title.setText("First Name :");

        b_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        b_title.setForeground(new java.awt.Color(250, 250, 255));
        b_title.setText("Birthday :");

        cn_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cn_title.setForeground(new java.awt.Color(250, 250, 255));
        cn_title.setText("Contact Number:");

        email_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        email_title.setForeground(new java.awt.Color(250, 250, 255));
        email_title.setText("Email:");

        firstName_field.setBackground(new java.awt.Color(250, 250, 255));
        firstName_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        firstName_field.setForeground(new java.awt.Color(92, 101, 138));

        bday_field.setBackground(new java.awt.Color(250, 250, 255));
        bday_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        bday_field.setForeground(new java.awt.Color(92, 101, 138));

        contact_field.setBackground(new java.awt.Color(250, 250, 255));
        contact_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        contact_field.setForeground(new java.awt.Color(92, 101, 138));

        email_field.setBackground(new java.awt.Color(250, 250, 255));
        email_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        email_field.setForeground(new java.awt.Color(92, 101, 138));

        govIdNum_title.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        govIdNum_title.setForeground(new java.awt.Color(250, 250, 255));
        govIdNum_title.setText("Government ID Numbers :");

        sss_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        sss_title.setForeground(new java.awt.Color(250, 250, 255));
        sss_title.setText("SSS Number :");

        pagibig_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        pagibig_title.setForeground(new java.awt.Color(250, 250, 255));
        pagibig_title.setText("PAG-IBIG Number :");

        phhealth_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        phhealth_title.setForeground(new java.awt.Color(250, 250, 255));
        phhealth_title.setText("PhilHealth Number :");

        tin_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tin_title.setForeground(new java.awt.Color(250, 250, 255));
        tin_title.setText("TIN :");

        sss_field.setBackground(new java.awt.Color(250, 250, 255));
        sss_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        sss_field.setForeground(new java.awt.Color(92, 101, 138));

        pagibig_field.setBackground(new java.awt.Color(250, 250, 255));
        pagibig_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        pagibig_field.setForeground(new java.awt.Color(92, 101, 138));

        philhealth_field.setBackground(new java.awt.Color(250, 250, 255));
        philhealth_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        philhealth_field.setForeground(new java.awt.Color(92, 101, 138));

        tin_field.setBackground(new java.awt.Color(250, 250, 255));
        tin_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tin_field.setForeground(new java.awt.Color(92, 101, 138));

        noe_title8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        noe_title8.setForeground(new java.awt.Color(250, 250, 255));
        noe_title8.setText("Last Name :");

        lastname_field.setBackground(new java.awt.Color(250, 250, 255));
        lastname_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lastname_field.setForeground(new java.awt.Color(92, 101, 138));

        address_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title.setForeground(new java.awt.Color(250, 250, 255));
        address_title.setText("Street Address :");

        street_field.setBackground(new java.awt.Color(250, 250, 255));
        street_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        street_field.setForeground(new java.awt.Color(92, 101, 138));

        empProfileTitle.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        empProfileTitle.setForeground(new java.awt.Color(253, 56, 29));
        empProfileTitle.setText("EMPLOYEE PROFILE");

        brgy_field.setBackground(new java.awt.Color(250, 250, 255));
        brgy_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        brgy_field.setForeground(new java.awt.Color(92, 101, 138));

        city_field.setBackground(new java.awt.Color(250, 250, 255));
        city_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        city_field.setForeground(new java.awt.Color(92, 101, 138));

        province_field.setBackground(new java.awt.Color(250, 250, 255));
        province_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        province_field.setForeground(new java.awt.Color(92, 101, 138));

        postalcode_field.setBackground(new java.awt.Color(250, 250, 255));
        postalcode_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        postalcode_field.setForeground(new java.awt.Color(92, 101, 138));

        address_title1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title1.setForeground(new java.awt.Color(250, 250, 255));
        address_title1.setText("Barangay:");

        address_title2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title2.setForeground(new java.awt.Color(250, 250, 255));
        address_title2.setText("City:");

        address_title3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title3.setForeground(new java.awt.Color(250, 250, 255));
        address_title3.setText("Province:");

        address_title4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title4.setForeground(new java.awt.Color(250, 250, 255));
        address_title4.setText("Postal Code:");

        javax.swing.GroupLayout empProfileTabLayout = new javax.swing.GroupLayout(empProfileTab);
        empProfileTab.setLayout(empProfileTabLayout);
        empProfileTabLayout.setHorizontalGroup(
            empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empProfileTabLayout.createSequentialGroup()
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tin_title)
                            .addComponent(phhealth_title)
                            .addComponent(pagibig_title)
                            .addComponent(sss_title))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(philhealth_field, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pagibig_field, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sss_field, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tin_field, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(empProfileTabLayout.createSequentialGroup()
                                .addComponent(b_title)
                                .addGap(18, 18, 18)
                                .addComponent(bday_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(empProfileTabLayout.createSequentialGroup()
                                .addComponent(noe_title8)
                                .addGap(18, 18, 18)
                                .addComponent(lastname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(empProfileTabLayout.createSequentialGroup()
                                .addComponent(noe_title)
                                .addGap(18, 18, 18)
                                .addComponent(firstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(empProfileTabLayout.createSequentialGroup()
                                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(address_title2)
                                    .addComponent(address_title))
                                .addGap(18, 18, 18)
                                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(street_field, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(city_field, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)))
                        .addGap(54, 54, 54)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email_title, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cn_title, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(contact_field)
                            .addComponent(email_field, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(govIdNum_title))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(empProfileTitle))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(address_title1)
                        .addGap(18, 18, 18)
                        .addComponent(brgy_field, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(address_title3)
                        .addGap(18, 18, 18)
                        .addComponent(province_field, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(address_title4)
                        .addGap(18, 18, 18)
                        .addComponent(postalcode_field, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        empProfileTabLayout.setVerticalGroup(
            empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empProfileTabLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(empProfileTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noe_title)
                            .addComponent(firstName_field))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noe_title8)
                            .addComponent(lastname_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contact_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cn_title))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email_title))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bday_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_title))
                .addGap(12, 12, 12)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address_title)
                    .addComponent(street_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brgy_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(address_title1))
                .addGap(9, 9, 9)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(province_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postalcode_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(city_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(address_title2)
                    .addComponent(address_title3)
                    .addComponent(address_title4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(govIdNum_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sss_title)
                    .addComponent(sss_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagibig_title)
                    .addComponent(pagibig_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phhealth_title)
                    .addComponent(philhealth_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tin_title)
                    .addComponent(tin_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
        );

        tab.addTab("Profile", empProfileTab);

        timeLogTab.setBackground(new java.awt.Color(30, 43, 93));
        timeLogTab.setForeground(new java.awt.Color(69, 123, 157));

        timeLogFirstNameField.setBackground(new java.awt.Color(250, 250, 255));
        timeLogFirstNameField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        timeLogFirstNameField.setForeground(new java.awt.Color(92, 101, 138));

        first_name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        first_name.setForeground(new java.awt.Color(250, 250, 255));
        first_name.setText("First Name :");

        timeLogLastNameField.setBackground(new java.awt.Color(250, 250, 255));
        timeLogLastNameField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        timeLogLastNameField.setForeground(new java.awt.Color(92, 101, 138));

        timeLogLastNameLbl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        timeLogLastNameLbl.setForeground(new java.awt.Color(250, 250, 255));
        timeLogLastNameLbl.setText("Last Name :");

        attendance_table.setBackground(new java.awt.Color(250, 250, 255));
        attendance_table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        attendance_table.setForeground(new java.awt.Color(92, 101, 138));
        attendance_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Date", "Time In", "Time Out"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(attendance_table);

        timeInBtn.setBackground(new java.awt.Color(253, 56, 29));
        timeInBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        timeInBtn.setForeground(new java.awt.Color(250, 250, 255));
        timeInBtn.setText("Time In");
        timeInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeInBtnActionPerformed(evt);
            }
        });

        timeOutBtn.setBackground(new java.awt.Color(253, 56, 29));
        timeOutBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        timeOutBtn.setForeground(new java.awt.Color(250, 250, 255));
        timeOutBtn.setText("Time Out");
        timeOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeOutBtnActionPerformed(evt);
            }
        });

        attendance_title.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        attendance_title.setForeground(new java.awt.Color(253, 56, 29));
        attendance_title.setText("EMPLOYEE TIME LOG");

        javax.swing.GroupLayout timeLogTabLayout = new javax.swing.GroupLayout(timeLogTab);
        timeLogTab.setLayout(timeLogTabLayout);
        timeLogTabLayout.setHorizontalGroup(
            timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timeLogTabLayout.createSequentialGroup()
                .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timeLogTabLayout.createSequentialGroup()
                        .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(timeLogTabLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(timeLogLastNameLbl)
                                    .addComponent(first_name))
                                .addGap(31, 31, 31)
                                .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(timeLogFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(timeLogLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(timeLogTabLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(timeInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(timeOutBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(timeLogTabLayout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(attendance_title)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        timeLogTabLayout.setVerticalGroup(
            timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timeLogTabLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(attendance_title, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timeLogTabLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(first_name)
                            .addComponent(timeLogFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeLogLastNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeLogLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(timeLogTabLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))))
        );

        tab.addTab("Time Log", timeLogTab);

        leaveTab.setBackground(new java.awt.Color(30, 43, 93));
        leaveTab.setPreferredSize(new java.awt.Dimension(650, 450));

        leaveTable.setBackground(new java.awt.Color(250, 250, 255));
        leaveTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leaveTable.setForeground(new java.awt.Color(92, 101, 138));
        leaveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leave No.", "Employee ID", "First Name", "Last Name", "Start Date", "End Date", "Leave Type", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(leaveTable);

        noe_title6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title6.setForeground(new java.awt.Color(250, 250, 255));
        noe_title6.setText("First Name :");

        leaveFirstName_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveFirstName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveFirstName_field.setForeground(new java.awt.Color(92, 101, 138));

        noe_title7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title7.setForeground(new java.awt.Color(250, 250, 255));
        noe_title7.setText("Last Name :");

        leaveLastName_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveLastName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveLastName_field.setForeground(new java.awt.Color(92, 101, 138));

        leave_type.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leave_type.setForeground(new java.awt.Color(250, 250, 255));
        leave_type.setText("Leave Type :");

        leaveTypeComboBox.setBackground(new java.awt.Color(250, 250, 255));
        leaveTypeComboBox.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveTypeComboBox.setForeground(new java.awt.Color(92, 101, 138));
        leaveTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vacation Leave", "Sick Leave", "Emergency Leave", "Birthday Leave" }));

        addButton.setBackground(new java.awt.Color(253, 56, 29));
        addButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addButton.setForeground(new java.awt.Color(250, 250, 255));
        addButton.setText("File Leave");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        enddate_lbl1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        enddate_lbl1.setForeground(new java.awt.Color(250, 250, 255));
        enddate_lbl1.setText("End Date :");

        enddate_chooser.setBackground(new java.awt.Color(250, 250, 255));
        enddate_chooser.setForeground(new java.awt.Color(92, 101, 138));
        enddate_chooser.setDateFormatString("dd MMM yyyy");
        enddate_chooser.setFont(new java.awt.Font("Gadugi", 0, 10)); // NOI18N

        leaveNum1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leaveNum1.setForeground(new java.awt.Color(250, 250, 255));
        leaveNum1.setText("Leave No. :");

        leaveNum_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveNum_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveNum_field.setForeground(new java.awt.Color(92, 101, 138));
        leaveNum_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                leaveNum_fieldKeyReleased(evt);
            }
        });

        startdate_lbl2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        startdate_lbl2.setForeground(new java.awt.Color(250, 250, 255));
        startdate_lbl2.setText("Start Date :");

        startdate_chooser.setBackground(new java.awt.Color(250, 250, 255));
        startdate_chooser.setForeground(new java.awt.Color(92, 101, 138));
        startdate_chooser.setDateFormatString("dd MMM yyyy");
        startdate_chooser.setFont(new java.awt.Font("Gadugi", 0, 10)); // NOI18N

        leave_title.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        leave_title.setForeground(new java.awt.Color(253, 56, 29));
        leave_title.setText("EMPLOYEE LEAVE REQUEST");

        javax.swing.GroupLayout leaveTabLayout = new javax.swing.GroupLayout(leaveTab);
        leaveTab.setLayout(leaveTabLayout);
        leaveTabLayout.setHorizontalGroup(
            leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leaveTabLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(321, 321, 321))
            .addGroup(leaveTabLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(leaveTabLayout.createSequentialGroup()
                                .addComponent(leaveNum1)
                                .addGap(18, 18, 18)
                                .addComponent(leaveNum_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leaveTabLayout.createSequentialGroup()
                                .addComponent(noe_title7)
                                .addGap(18, 18, 18)
                                .addComponent(leaveLastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leaveTabLayout.createSequentialGroup()
                                .addComponent(noe_title6)
                                .addGap(18, 18, 18)
                                .addComponent(leaveFirstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(139, 139, 139)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(leave_type)
                            .addComponent(enddate_lbl1)
                            .addComponent(startdate_lbl2))
                        .addGap(18, 18, 18)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leaveTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enddate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startdate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(leave_title)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        leaveTabLayout.setVerticalGroup(
            leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaveTabLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(leave_title, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startdate_lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startdate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(enddate_chooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(enddate_lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(leave_type)
                            .addComponent(leaveTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(leaveNum1)
                            .addComponent(leaveNum_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noe_title6)
                            .addComponent(leaveFirstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noe_title7)
                            .addComponent(leaveLastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        tab.addTab("Leave", leaveTab);

        leaveTab1.setBackground(new java.awt.Color(30, 43, 93));
        leaveTab1.setPreferredSize(new java.awt.Dimension(650, 450));

        leaveTable1.setBackground(new java.awt.Color(250, 250, 255));
        leaveTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leaveTable1.setForeground(new java.awt.Color(92, 101, 138));
        leaveTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Payroll ID", "Payslip Number", "Employee ID", "Start Date", "End Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(leaveTable1);

        noe_title9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title9.setForeground(new java.awt.Color(250, 250, 255));
        noe_title9.setText("First Name :");

        leaveFirstName_field1.setBackground(new java.awt.Color(250, 250, 255));
        leaveFirstName_field1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveFirstName_field1.setForeground(new java.awt.Color(92, 101, 138));

        noe_title10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title10.setForeground(new java.awt.Color(250, 250, 255));
        noe_title10.setText("Last Name :");

        leaveLastName_field1.setBackground(new java.awt.Color(250, 250, 255));
        leaveLastName_field1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveLastName_field1.setForeground(new java.awt.Color(92, 101, 138));

        payslipBtn.setBackground(new java.awt.Color(253, 56, 29));
        payslipBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        payslipBtn.setForeground(new java.awt.Color(250, 250, 255));
        payslipBtn.setText("View Payslip");
        payslipBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payslipBtnActionPerformed(evt);
            }
        });

        leave_title1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        leave_title1.setForeground(new java.awt.Color(253, 56, 29));
        leave_title1.setText("EMPLOYEE PAYSLIP");

        noe_title11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title11.setForeground(new java.awt.Color(250, 250, 255));
        noe_title11.setText("Pay Period:");

        jComboBox1.setBackground(new java.awt.Color(250, 250, 255));
        jComboBox1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(30, 43, 93));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1st Cut-off", "2nd Cut-off" }));

        javax.swing.GroupLayout leaveTab1Layout = new javax.swing.GroupLayout(leaveTab1);
        leaveTab1.setLayout(leaveTab1Layout);
        leaveTab1Layout.setHorizontalGroup(
            leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaveTab1Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leaveTab1Layout.createSequentialGroup()
                        .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leaveTab1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(leaveTab1Layout.createSequentialGroup()
                                        .addComponent(noe_title9)
                                        .addGap(18, 18, 18)
                                        .addComponent(leaveFirstName_field1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(leaveTab1Layout.createSequentialGroup()
                                        .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(noe_title10)
                                            .addComponent(noe_title11))
                                        .addGap(18, 18, 18)
                                        .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(leaveLastName_field1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(leaveTab1Layout.createSequentialGroup()
                                        .addComponent(payslipBtn)
                                        .addGap(54, 54, 54))))
                            .addComponent(leave_title1))
                        .addGap(233, 233, 233))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leaveTab1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))))
        );
        leaveTab1Layout.setVerticalGroup(
            leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaveTab1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(leave_title1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noe_title9)
                    .addComponent(leaveFirstName_field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noe_title10)
                    .addComponent(leaveLastName_field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noe_title11))
                .addGap(26, 26, 26)
                .addComponent(payslipBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        tab.addTab("Payslip", leaveTab1);

        empName.setFont(new java.awt.Font("Bernard MT Condensed", 1, 36)); // NOI18N
        empName.setForeground(new java.awt.Color(30, 43, 93));
        empName.setText("Employee Name ");

        date.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        date.setForeground(new java.awt.Color(253, 56, 29));
        date.setText("Date");

        time.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        time.setForeground(new java.awt.Color(253, 56, 29));
        time.setText(" Time");

        en_title.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        en_title.setForeground(new java.awt.Color(92, 101, 138));
        en_title.setText("Employee Number :");

        id_field.setBackground(new java.awt.Color(250, 250, 255));
        id_field.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        id_field.setForeground(new java.awt.Color(253, 56, 29));
        id_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_fieldKeyReleased(evt);
            }
        });

        img.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Monina\\MMDC\\Term 3 24-25\\icon.png")); // NOI18N

        logOut.setBackground(new java.awt.Color(253, 56, 29));
        logOut.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        logOut.setForeground(new java.awt.Color(250, 250, 255));
        logOut.setText("Log Out");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(en_title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                        .addComponent(date)
                        .addGap(18, 18, 18)
                        .addComponent(time)
                        .addGap(85, 85, 85))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(empName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logOut)
                        .addGap(20, 20, 20))))
            .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(empName)
                            .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(en_title)
                            .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(time)
                                    .addComponent(date)))
                            .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))))
                .addGap(10, 10, 10)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void id_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_fieldKeyReleased
       // type employee number
        String employeeId = id_field.getText();
        try {
            conn = dbConnect.connect();
            Admin_Class employee = dbConnect.getEmployeeDetails(employeeId);
            if (employee != null) {
                firstName_field.setText(employee.getFirstName());
                lastname_field.setText(employee.getLastName());
                timeLogFirstNameField.setText(employee.getFirstName());
                timeLogLastNameField.setText(employee.getLastName());
                leaveFirstName_field.setText(employee.getFirstName());
                leaveLastName_field.setText(employee.getLastName());
                bday_field.setText(employee.getBirthday());
                street_field.setText(employee.getStreet());
                brgy_field.setText(employee.getBarangay());
                city_field.setText(employee.getCity());
                province_field.setText(employee.getProvince());
                postalcode_field.setText(employee.getPostalcode());
                contact_field.setText(employee.getPhoneNumber());
                email_field.setText(employee.getEmail());
                sss_field.setText(employee.getSssNum());
                philhealth_field.setText(employee.getPhilHealthNum());
                pagibig_field.setText(employee.getPagibigNum());
                tin_field.setText(employee.getTinNum());
            } else {
            }   // Handle the case where no employee was found
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No employee found with ID: " + employeeId, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_id_fieldKeyReleased

    private void leaveNum_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_leaveNum_fieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_leaveNum_fieldKeyReleased

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // add data to sql
        String leaveNum = leaveNum_field.getText();
        String employeeId = id_field.getText();
        String firstName = timeLogFirstNameField.getText();
        String lastName = timeLogLastNameField.getText();
        String startDate = startdate_chooser.getDate() != null ?
        new SimpleDateFormat("MMMM d, y").format(enddate_chooser.getDate()) : "";
        String endDate = enddate_chooser.getDate() != null ?
        new SimpleDateFormat("MMMM d, y").format(enddate_chooser.getDate()) : "";
        String leaveType = leaveTypeComboBox.getSelectedItem().toString();
        String status = "Pending";

        DefaultTableModel model = (DefaultTableModel) leaveTable.getModel();
        model.addRow(new Object[]{leaveNum, employeeId, firstName, lastName, startDate, endDate, leaveType});
        LeaveRecord leaveRecord = new LeaveRecord(leaveNum, employeeId, firstName, lastName, startDate, endDate, leaveType, status);

        dbConnect.addLeaveRequest(leaveRecord);
        JOptionPane.showMessageDialog(null, "Leave request added successfully!");
        refreshList();
    }//GEN-LAST:event_addButtonActionPerformed

    private void timeOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeOutBtnActionPerformed
        // Time Out Function + insert to SQL
        DefaultTableModel model = (DefaultTableModel) attendance_table.getModel();
        model.addRow(new Object[]{timeLogFirstNameField.getText(), timeLogLastNameField.getText(), date.getText(),"", time.getText()});

        boolean success = dbConnect.logTimeOut(id_field.getText(), timeLogFirstNameField.getText(), timeLogLastNameField.getText(), date.getText(), time.getText());

        if (success) {
            JOptionPane.showMessageDialog(this, "Time Out Successfully Added");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add Time Out");
        }
    }//GEN-LAST:event_timeOutBtnActionPerformed

    private void timeInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeInBtnActionPerformed
        // Time In Function + insert to SQL
        DefaultTableModel model = (DefaultTableModel) attendance_table.getModel();
        model.addRow(new Object[]{timeLogFirstNameField.getText(), timeLogLastNameField.getText(), date.getText(), time.getText(),""});

        boolean success = dbConnect.logTimeIn(id_field.getText(), timeLogFirstNameField.getText(), timeLogLastNameField.getText(), date.getText(), time.getText());

        if (success) {
            JOptionPane.showMessageDialog(this, "Time In Successfully Added");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add Time In");
        }
    }//GEN-LAST:event_timeInBtnActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        // log out
                
        int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to Log Out?", "Employee Portal", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION){
        
        Login loginPage = new Login();
        loginPage.show();
                
        dispose();
        } else if (result == JOptionPane.NO_OPTION){
        EmployeePortal employeePortal = new EmployeePortal ();
        employeePortal.show();
                
        dispose();   
        }
        
    
    }//GEN-LAST:event_logOutActionPerformed

    private void payslipBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payslipBtnActionPerformed
      try{ 
        conn = dbConnect.connect();
        String reportpath  = "C:\\Users\\user\\Desktop\\Monina\\MMDC\\Term 2 24-25\\MotorPHPortal\\src\\main\\java\\Reports\\reportPayslipTemplate.jrxml";
        JasperReport jr = JasperCompileManager.compileReport(reportpath);
        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
        JasperViewer.viewReport(jp);
        conn.close();
        
            
    }catch (Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
        
    }//GEN-LAST:event_payslipBtnActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeeAccess_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccess_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccess_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccess_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeAccess_Profile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel address_title;
    private javax.swing.JLabel address_title1;
    private javax.swing.JLabel address_title2;
    private javax.swing.JLabel address_title3;
    private javax.swing.JLabel address_title4;
    private javax.swing.JTable attendance_table;
    private javax.swing.JLabel attendance_title;
    private javax.swing.JLabel b_title;
    private javax.swing.JTextField bday_field;
    private javax.swing.JTextField brgy_field;
    private javax.swing.JTextField city_field;
    private javax.swing.JLabel cn_title;
    private javax.swing.JTextField contact_field;
    private javax.swing.JLabel date;
    private javax.swing.JTextField email_field;
    private javax.swing.JLabel email_title;
    private javax.swing.JLabel empName;
    private javax.swing.JPanel empProfileTab;
    private javax.swing.JLabel empProfileTitle;
    private javax.swing.JLabel en_title;
    private com.toedter.calendar.JDateChooser enddate_chooser;
    private javax.swing.JLabel enddate_lbl1;
    private javax.swing.JTextField firstName_field;
    private javax.swing.JLabel first_name;
    private javax.swing.JLabel govIdNum_title;
    private javax.swing.JTextField id_field;
    private javax.swing.JLabel img;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField lastname_field;
    private javax.swing.JTextField leaveFirstName_field;
    private javax.swing.JTextField leaveFirstName_field1;
    private javax.swing.JTextField leaveLastName_field;
    private javax.swing.JTextField leaveLastName_field1;
    private javax.swing.JLabel leaveNum1;
    private javax.swing.JTextField leaveNum_field;
    private javax.swing.JPanel leaveTab;
    private javax.swing.JPanel leaveTab1;
    private javax.swing.JTable leaveTable;
    private javax.swing.JTable leaveTable1;
    private javax.swing.JComboBox<String> leaveTypeComboBox;
    private javax.swing.JLabel leave_title;
    private javax.swing.JLabel leave_title1;
    private javax.swing.JLabel leave_type;
    private javax.swing.JButton logOut;
    private javax.swing.JLabel noe_title;
    private javax.swing.JLabel noe_title10;
    private javax.swing.JLabel noe_title11;
    private javax.swing.JLabel noe_title6;
    private javax.swing.JLabel noe_title7;
    private javax.swing.JLabel noe_title8;
    private javax.swing.JLabel noe_title9;
    private javax.swing.JTextField pagibig_field;
    private javax.swing.JLabel pagibig_title;
    private javax.swing.JButton payslipBtn;
    private javax.swing.JLabel phhealth_title;
    private javax.swing.JTextField philhealth_field;
    private javax.swing.JTextField postalcode_field;
    private javax.swing.JTextField province_field;
    private javax.swing.JTextField sss_field;
    private javax.swing.JLabel sss_title;
    private com.toedter.calendar.JDateChooser startdate_chooser;
    private javax.swing.JLabel startdate_lbl2;
    private javax.swing.JTextField street_field;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JLabel time;
    private javax.swing.JButton timeInBtn;
    private javax.swing.JTextField timeLogFirstNameField;
    private javax.swing.JTextField timeLogLastNameField;
    private javax.swing.JLabel timeLogLastNameLbl;
    private javax.swing.JPanel timeLogTab;
    private javax.swing.JButton timeOutBtn;
    private javax.swing.JTextField tin_field;
    private javax.swing.JLabel tin_title;
    // End of variables declaration//GEN-END:variables
}
