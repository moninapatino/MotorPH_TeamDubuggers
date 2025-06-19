package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_util.DatabaseConnect;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.WindowConstants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class EmployeeProfile extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    DatabaseConnect dbConnect = new DatabaseConnect() {};

    
    public EmployeeProfile() {
        initComponents();
        setTitle("Motor PH Employee Profile");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        //Displaying date and Time
        time();
        date();
        conn = dbConnect.connect();
    }

    public final void time() {
        DateTimeFormatter times = DateTimeFormatter.ofPattern("hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        time.setText(times.format(now));
    }

    public final void date() {
        DateTimeFormatter dates = DateTimeFormatter.ofPattern("MMMM d, y");
        LocalDateTime now = LocalDateTime.now();
        date.setText(dates.format(now));
    }

    public final void clear() {
        // Clear data from textbox
        id_field.setText("");
        firstname_field.setText("");
        lastname_field.setText("");
        bday_field.setText("yyyy-mm-dd");
        address_field.setText("");
        contact_field.setText("");
        email_field.setText("");
        sss_field.setText("");
        phhealth_field.setText("");
        pagibig_field.setText("");
        tin_field.setText("");
    }
    
    private String[] parseAddress(String fullAddress) {
    // Initialize with empty strings to avoid null values
    String[] addressParts = {"", "", "", "", ""};
    
    if (fullAddress != null && !fullAddress.trim().isEmpty()) {
        // Split by comma and newline
        String[] parts = fullAddress.split("[,]\\s*");
        
        // Trim each part and assign to respective positions
        for (int i = 0; i < parts.length && i < 5; i++) {
            addressParts[i] = parts[i].trim();
        }
    }
    
    return addressParts;
}

    private static final int MAX_POSTAL_CODE_LENGTH = 4;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        greetings = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();
        noe_title = new javax.swing.JLabel();
        b_title = new javax.swing.JLabel();
        cn_title = new javax.swing.JLabel();
        en_title = new javax.swing.JLabel();
        email_title = new javax.swing.JLabel();
        firstname_field = new javax.swing.JTextField();
        bday_field = new javax.swing.JTextField();
        contact_field = new javax.swing.JTextField();
        id_field = new javax.swing.JTextField();
        email_field = new javax.swing.JTextField();
        govIdNum_title = new javax.swing.JLabel();
        sss_title = new javax.swing.JLabel();
        pagibig_title = new javax.swing.JLabel();
        phhealth_title = new javax.swing.JLabel();
        tin_title = new javax.swing.JLabel();
        sss_field = new javax.swing.JTextField();
        pagibig_field = new javax.swing.JTextField();
        phhealth_field = new javax.swing.JTextField();
        tin_field = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        noe_title1 = new javax.swing.JLabel();
        lastname_field = new javax.swing.JTextField();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        updateBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        address_title = new javax.swing.JLabel();
        address_field = new javax.swing.JTextField();
        deleteBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(30, 43, 93));
        mainPanel.setForeground(new java.awt.Color(30, 43, 93));

        greetings.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        greetings.setForeground(new java.awt.Color(250, 250, 255));
        greetings.setText("Greetings!");

        jPanel1.setBackground(new java.awt.Color(250, 250, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(256, 70));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(92, 101, 138));
        jLabel1.setText("EMPLOYEE PROFILE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LOGO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(178, 178, 178))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 67, Short.MAX_VALUE)
                        .addComponent(LOGO)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );

        noe_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        noe_title.setForeground(new java.awt.Color(250, 250, 255));
        noe_title.setText("First Name :");

        b_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        b_title.setForeground(new java.awt.Color(250, 250, 255));
        b_title.setText("Birthday :");

        cn_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cn_title.setForeground(new java.awt.Color(250, 250, 255));
        cn_title.setText("Contact Number:");

        en_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        en_title.setForeground(new java.awt.Color(250, 250, 255));
        en_title.setText("Employee Number :");

        email_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        email_title.setForeground(new java.awt.Color(250, 250, 255));
        email_title.setText("Email:");

        firstname_field.setBackground(new java.awt.Color(250, 250, 255));
        firstname_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        firstname_field.setForeground(new java.awt.Color(92, 101, 138));

        bday_field.setBackground(new java.awt.Color(250, 250, 255));
        bday_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        bday_field.setForeground(new java.awt.Color(92, 101, 138));
        bday_field.setText("yyyy-mm-dd");

        contact_field.setBackground(new java.awt.Color(250, 250, 255));
        contact_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        contact_field.setForeground(new java.awt.Color(92, 101, 138));

        id_field.setBackground(new java.awt.Color(250, 250, 255));
        id_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        id_field.setForeground(new java.awt.Color(92, 101, 138));
        id_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_fieldKeyReleased(evt);
            }
        });

        email_field.setBackground(new java.awt.Color(250, 250, 255));
        email_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        email_field.setForeground(new java.awt.Color(92, 101, 138));

        govIdNum_title.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
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

        phhealth_field.setBackground(new java.awt.Color(250, 250, 255));
        phhealth_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        phhealth_field.setForeground(new java.awt.Color(92, 101, 138));

        tin_field.setBackground(new java.awt.Color(250, 250, 255));
        tin_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tin_field.setForeground(new java.awt.Color(92, 101, 138));

        backButton.setBackground(new java.awt.Color(253, 56, 29));
        backButton.setForeground(new java.awt.Color(250, 250, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(253, 56, 29));
        clearButton.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        clearButton.setForeground(new java.awt.Color(250, 250, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        noe_title1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        noe_title1.setForeground(new java.awt.Color(250, 250, 255));
        noe_title1.setText("Last Name :");

        lastname_field.setBackground(new java.awt.Color(250, 250, 255));
        lastname_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lastname_field.setForeground(new java.awt.Color(92, 101, 138));

        date.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        date.setForeground(new java.awt.Color(250, 250, 255));
        date.setText("Date ");

        time.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        time.setForeground(new java.awt.Color(250, 250, 255));
        time.setText(" Time");

        updateBtn.setBackground(new java.awt.Color(253, 56, 29));
        updateBtn.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(250, 250, 255));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(253, 56, 29));
        addBtn.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        addBtn.setForeground(new java.awt.Color(250, 250, 255));
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        address_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title.setForeground(new java.awt.Color(250, 250, 255));
        address_title.setText("Address :");

        address_field.setBackground(new java.awt.Color(250, 250, 255));
        address_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        address_field.setForeground(new java.awt.Color(92, 101, 138));

        deleteBtn.setBackground(new java.awt.Color(253, 56, 29));
        deleteBtn.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(250, 250, 255));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pagibig_title, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sss_title, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(tin_title, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(phhealth_title, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pagibig_field)
                            .addComponent(phhealth_field)
                            .addComponent(tin_field)
                            .addComponent(sss_field, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                        .addGap(113, 113, 113)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(contact_field, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(backButton)
                                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(9, 9, 9))
                                .addComponent(bday_field))
                            .addComponent(email_field, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(en_title)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(firstname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email_title, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(b_title, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cn_title, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(236, 236, 236))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(date)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(govIdNum_title)
                                    .addComponent(greetings)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(noe_title)
                                    .addComponent(noe_title1)
                                    .addComponent(address_title))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(address_field, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(47, Short.MAX_VALUE))))
        );

        mainPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {pagibig_field, phhealth_field, sss_field, tin_field});

        mainPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {address_title, b_title, cn_title, email_title, en_title, noe_title, noe_title1});

        mainPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bday_field, contact_field, email_field, firstname_field, id_field, lastname_field});

        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(greetings)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(address_title)
                            .addComponent(address_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(contact_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cn_title, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(email_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email_title))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bday_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_title)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(en_title)
                                    .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(firstname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noe_title))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(noe_title1)
                                    .addComponent(lastname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(69, 69, 69)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(govIdNum_title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sss_title)
                            .addComponent(sss_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pagibig_title)
                            .addComponent(pagibig_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phhealth_title)
                            .addComponent(phhealth_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tin_title)
                            .addComponent(tin_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(addBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(backButton)
                .addGap(11, 11, 11)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date)
                    .addComponent(time))
                .addContainerGap())
        );

        mainPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bday_field, contact_field, email_field, firstname_field, id_field, lastname_field, pagibig_field, pagibig_title, phhealth_field, phhealth_title, sss_field, sss_title, tin_field, tin_title});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // back to Employee Portal
        EmployeePortal employeePortal = new EmployeePortal();
        employeePortal.show();

        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed

         clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void id_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_fieldKeyReleased
        // type employee number        
        String employeeId = id_field.getText();         
        try {
             conn = dbConnect.connect();
            Admin_Class employee = dbConnect.getEmployeeDetails(employeeId);
            if (employee != null) {
                firstname_field.setText(employee.getFirstName());
                lastname_field.setText(employee.getLastName());
                bday_field.setText(employee.getBirthday());
                email_field.setText(employee.getEmail());
                
            String fullAddress = String.format("%s, %s, %s, %s, %s",
                employee.getStreet(),
                employee.getBarangay(),
                employee.getCity(),
                employee.getProvince(),
                employee.getPostalcode());
                address_field.setText(fullAddress);
                
                contact_field.setText(employee.getPhoneNumber());
                sss_field.setText(employee.getSssNum());
                pagibig_field.setText(employee.getPagibigNum());
                phhealth_field.setText(employee.getPhilHealthNum());
                tin_field.setText(employee.getTinNum());
                
            } else {
            }   // Handle the case where no employee was found
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No employee found with ID: " + employeeId,
                        "Error", JOptionPane.ERROR_MESSAGE);
                
       } 

    }//GEN-LAST:event_id_fieldKeyReleased

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // Update Information from SQL
        
        String lastName = lastname_field.getText();
        String phoneNumber = contact_field.getText();
        String fullAddress = address_field.getText().trim();
            
            String[] addressParts = parseAddress(fullAddress);
            if (addressParts.length < 5) {
            JOptionPane.showMessageDialog(this, "Address format is incorrect.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; 
            }
            
            String street = addressParts[0];
            String barangay = addressParts[1];
            String city = addressParts[2];
            String province = addressParts[3];
            String postalcode = addressParts[4].trim();
            String employeeID = id_field.getText();
            
            if (employeeID.equals("")) {
        JOptionPane.showMessageDialog(this, "Enter Employee ID!");
        return; // Exit if Employee ID is empty
    }
            
        // Create an Employee object
        Admin_Class employee = new Admin_Class(employeeID, null, lastName, null, null,
                    street, barangay, city, province, postalcode, phoneNumber, 
                    null, null, null, null, null, null, null);
        
        if(employeeID.equals("")){
            JOptionPane.showMessageDialog(this, "Enter Employee ID!");
        }
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this?", 
                "Employee Profile Updating...", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {

            dbConnect.updateEmployee(employee); // Call the updateEmployee method
        } 
        
        else if (result == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Data not successfully updated!");
        }

    }//GEN-LAST:event_updateBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // ADD EMPLOYEE RECORD

        try {
            // Retrieve input values from fields
            String employeeID = id_field.getText();
            String firstName = firstname_field.getText();
            String lastName = lastname_field.getText();
            String birthday = bday_field.getText();
            String fullAddress = address_field.getText().trim();
            
            String[] addressParts = parseAddress(fullAddress);
            if (addressParts.length < 5) {
            JOptionPane.showMessageDialog(this, "Address format is incorrect.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; 
            }
            
            String street = addressParts[0];
            String barangay = addressParts[1];
            String city = addressParts[2];
            String province = addressParts[3];
            String postalcode = addressParts[4].trim();
            
            String phoneNumber = contact_field.getText();
            String email = email_field.getText();
            String sssNum = sss_field.getText();
            String philHealthNum = phhealth_field.getText();
            String pagibigNum = pagibig_field.getText();
            String tinNum = tin_field.getText();
            
            
            if (employeeID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || birthday.isEmpty()
                    || street.isEmpty() || barangay.isEmpty() || city.isEmpty() || province.isEmpty()
                    || postalcode.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()
                    || sssNum.isEmpty() || philHealthNum.isEmpty() || pagibigNum.isEmpty() || tinNum.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if validation fails
            }
            
            if (postalcode.length() > MAX_POSTAL_CODE_LENGTH) {
            JOptionPane.showMessageDialog(this, "Postal code is too long.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if validation fails
        }
            if (!postalcode.matches("\\d{1,4}")) {
            JOptionPane.showMessageDialog(this, "Postal code must be numeric and up to 4 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if validation fails
        }
            
            Admin_Class newEmployee = new Admin_Class(employeeID, firstName, lastName, birthday, 
                 null, street, barangay, city, province,
                 postalcode, email, phoneNumber, sssNum, 
                 philHealthNum, tinNum, pagibigNum, 
                 null, null);

            // call the method from DatabaseConnect
            dbConnect.addEmployee(newEmployee);
            JOptionPane.showMessageDialog(this, "Employee Profile Added!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // DELETE RECORD
        String employeeId = id_field.getText();

        if (employeeId.equals("")) {
            JOptionPane.showMessageDialog(this, "Enter Employee ID!");
        }

        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?", "Employee Profile Deleting...", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {

            
            dbConnect.deleteEmployee(employeeId); // Call the deleteEmployee method

        } else if (result == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Employee Profile Deletion Not Successful!");
        }
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
            java.util.logging.Logger.getLogger(EmployeeProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                {
                    new EmployeeProfile().setVisible(true);
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LOGO;
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField address_field;
    private javax.swing.JLabel address_title;
    private javax.swing.JLabel b_title;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField bday_field;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel cn_title;
    private javax.swing.JTextField contact_field;
    private javax.swing.JLabel date;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField email_field;
    private javax.swing.JLabel email_title;
    private javax.swing.JLabel en_title;
    private javax.swing.JTextField firstname_field;
    private javax.swing.JLabel govIdNum_title;
    private javax.swing.JLabel greetings;
    private javax.swing.JTextField id_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lastname_field;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel noe_title;
    private javax.swing.JLabel noe_title1;
    private javax.swing.JTextField pagibig_field;
    private javax.swing.JLabel pagibig_title;
    private javax.swing.JTextField phhealth_field;
    private javax.swing.JLabel phhealth_title;
    private javax.swing.JTextField sss_field;
    private javax.swing.JLabel sss_title;
    private javax.swing.JLabel time;
    private javax.swing.JTextField tin_field;
    private javax.swing.JLabel tin_title;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
