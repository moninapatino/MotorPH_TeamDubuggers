
package com.mmdc.motor_ph_portal.AdminAccess;

import com.motorph_util.Postgresql;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;




public class Payroll extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "@dm1n";
    
    double hourlyRate;
    double hoursWorked;
    double basicSalary;
    double totalAllowances;
    double totalDeductions;
    double riceA;
    double phoneA;
    double clothingA;
    double sssC;
    double phealthC;
    double pagibigC;
    double grossPay;
    double netPay;
    double tax2;
    double tax3;
    double taxF;
    
    
    public Payroll() {
        initComponents();
        setTitle ("Employee Payroll");
        //setSize(700, 850);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        
        conn = Postgresql.java_db();
        
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
      public void netPay() {
        taxF= Double.parseDouble(tax_field.getText());
        
        netPay = grossPay - taxF;
        String netPayF = Double.toString(netPay);
        netPayF = new DecimalFormat("#.#").format(netPay);
        netpay_field.setText(netPayF);
        
      }
    
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        payroll_title = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();
        greetings = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        noe_label = new javax.swing.JLabel();
        clearButton1 = new javax.swing.JButton();
        payslipButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        hoursWorked_lb = new javax.swing.JLabel();
        hr_label = new javax.swing.JLabel();
        riceA_label = new javax.swing.JLabel();
        phoneA_label = new javax.swing.JLabel();
        clothingA_label = new javax.swing.JLabel();
        sss_label = new javax.swing.JLabel();
        pHealth_label = new javax.swing.JLabel();
        pagibig_label = new javax.swing.JLabel();
        td_label = new javax.swing.JLabel();
        ta_label = new javax.swing.JLabel();
        employeeid_field = new javax.swing.JTextField();
        hoursWorked_field = new javax.swing.JTextField();
        phoneA_field = new javax.swing.JTextField();
        hourlyRate_field = new javax.swing.JTextField();
        sss_field = new javax.swing.JTextField();
        clothing_field = new javax.swing.JTextField();
        riceA_field = new javax.swing.JTextField();
        pagibig_field = new javax.swing.JTextField();
        pHealth_field = new javax.swing.JTextField();
        employeeid_label = new javax.swing.JLabel();
        ename_field = new javax.swing.JTextField();
        totalA_field = new javax.swing.JTextField();
        totalD_field = new javax.swing.JTextField();
        basicsalary_label = new javax.swing.JLabel();
        grosspay_label = new javax.swing.JLabel();
        netpay_label = new javax.swing.JLabel();
        basicsalary_field = new javax.swing.JTextField();
        grosspay_field = new javax.swing.JTextField();
        tax_field = new javax.swing.JTextField();
        tax_label = new javax.swing.JLabel();
        calculateButton = new javax.swing.JButton();
        date = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        paySlipArea = new javax.swing.JTextArea();
        tax1_radio = new javax.swing.JRadioButton();
        tax2_radio = new javax.swing.JRadioButton();
        tax3_radio = new javax.swing.JRadioButton();
        netpay_field = new javax.swing.JTextField();
        noe_label1 = new javax.swing.JLabel();
        ename_field1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(30, 43, 93));
        mainPanel.setForeground(new java.awt.Color(217, 217, 217));

        jPanel1.setBackground(new java.awt.Color(250, 250, 255));

        payroll_title.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        payroll_title.setForeground(new java.awt.Color(92, 101, 138));
        payroll_title.setText("EMPLOYEE PAYROLL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(LOGO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payroll_title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(payroll_title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LOGO)
                .addGap(21, 21, 21))
        );

        greetings.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        greetings.setForeground(new java.awt.Color(250, 250, 255));
        greetings.setText("Greetings!");

        time.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        time.setForeground(new java.awt.Color(250, 250, 255));
        time.setText("Time");

        noe_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        noe_label.setForeground(new java.awt.Color(250, 250, 255));
        noe_label.setText("First Name :");

        clearButton1.setBackground(new java.awt.Color(253, 56, 29));
        clearButton1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        clearButton1.setForeground(new java.awt.Color(250, 250, 255));
        clearButton1.setText("Clear");
        clearButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });

        payslipButton.setBackground(new java.awt.Color(253, 56, 29));
        payslipButton.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        payslipButton.setForeground(new java.awt.Color(250, 250, 255));
        payslipButton.setText("Generate Payslip");
        payslipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payslipButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(253, 56, 29));
        backButton.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(250, 250, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        hoursWorked_lb.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        hoursWorked_lb.setForeground(new java.awt.Color(250, 250, 255));
        hoursWorked_lb.setText("Numbers of hours worked:");

        hr_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        hr_label.setForeground(new java.awt.Color(250, 250, 255));
        hr_label.setText("Hourly Rate:");

        riceA_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        riceA_label.setForeground(new java.awt.Color(250, 250, 255));
        riceA_label.setText("Rice Subsidy:");

        phoneA_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        phoneA_label.setForeground(new java.awt.Color(250, 250, 255));
        phoneA_label.setText("Phone Allowance:");

        clothingA_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        clothingA_label.setForeground(new java.awt.Color(250, 250, 255));
        clothingA_label.setText("Clothing Allowance:");

        sss_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        sss_label.setForeground(new java.awt.Color(250, 250, 255));
        sss_label.setText("SSS Contribution:");

        pHealth_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        pHealth_label.setForeground(new java.awt.Color(250, 250, 255));
        pHealth_label.setText("PhilHealth Contribution:");

        pagibig_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        pagibig_label.setForeground(new java.awt.Color(250, 250, 255));
        pagibig_label.setText("Pag-ibig Contribution:");

        td_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        td_label.setForeground(new java.awt.Color(250, 250, 255));
        td_label.setText("Total Deductions:");

        ta_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        ta_label.setForeground(new java.awt.Color(250, 250, 255));
        ta_label.setText("Total Allowances:");

        employeeid_field.setBackground(new java.awt.Color(250, 250, 255));
        employeeid_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        employeeid_field.setForeground(new java.awt.Color(92, 101, 138));
        employeeid_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                employeeid_fieldKeyReleased(evt);
            }
        });

        hoursWorked_field.setBackground(new java.awt.Color(250, 250, 255));
        hoursWorked_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        hoursWorked_field.setForeground(new java.awt.Color(92, 101, 138));

        phoneA_field.setEditable(false);
        phoneA_field.setBackground(new java.awt.Color(250, 250, 255));
        phoneA_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        phoneA_field.setForeground(new java.awt.Color(92, 101, 138));

        hourlyRate_field.setEditable(false);
        hourlyRate_field.setBackground(new java.awt.Color(250, 250, 255));
        hourlyRate_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        hourlyRate_field.setForeground(new java.awt.Color(92, 101, 138));

        sss_field.setEditable(false);
        sss_field.setBackground(new java.awt.Color(250, 250, 255));
        sss_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        sss_field.setForeground(new java.awt.Color(92, 101, 138));

        clothing_field.setEditable(false);
        clothing_field.setBackground(new java.awt.Color(250, 250, 255));
        clothing_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        clothing_field.setForeground(new java.awt.Color(92, 101, 138));

        riceA_field.setEditable(false);
        riceA_field.setBackground(new java.awt.Color(250, 250, 255));
        riceA_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        riceA_field.setForeground(new java.awt.Color(92, 101, 138));

        pagibig_field.setEditable(false);
        pagibig_field.setBackground(new java.awt.Color(250, 250, 255));
        pagibig_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        pagibig_field.setForeground(new java.awt.Color(92, 101, 138));

        pHealth_field.setEditable(false);
        pHealth_field.setBackground(new java.awt.Color(250, 250, 255));
        pHealth_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        pHealth_field.setForeground(new java.awt.Color(92, 101, 138));

        employeeid_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        employeeid_label.setForeground(new java.awt.Color(250, 250, 255));
        employeeid_label.setText("Employee ID :");

        ename_field.setEditable(false);
        ename_field.setBackground(new java.awt.Color(250, 250, 255));
        ename_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        ename_field.setForeground(new java.awt.Color(92, 101, 138));

        totalA_field.setBackground(new java.awt.Color(250, 250, 255));
        totalA_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        totalA_field.setForeground(new java.awt.Color(92, 101, 138));

        totalD_field.setEditable(false);
        totalD_field.setBackground(new java.awt.Color(250, 250, 255));
        totalD_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        totalD_field.setForeground(new java.awt.Color(92, 101, 138));

        basicsalary_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        basicsalary_label.setForeground(new java.awt.Color(250, 250, 255));
        basicsalary_label.setText("Basic Salary:");

        grosspay_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        grosspay_label.setForeground(new java.awt.Color(250, 250, 255));
        grosspay_label.setText("Gross Pay:");

        netpay_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        netpay_label.setForeground(new java.awt.Color(250, 250, 255));
        netpay_label.setText("Net Pay:");

        basicsalary_field.setBackground(new java.awt.Color(250, 250, 255));
        basicsalary_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        basicsalary_field.setForeground(new java.awt.Color(92, 101, 138));

        grosspay_field.setBackground(new java.awt.Color(250, 250, 255));
        grosspay_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        grosspay_field.setForeground(new java.awt.Color(92, 101, 138));

        tax_field.setBackground(new java.awt.Color(250, 250, 255));
        tax_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        tax_field.setForeground(new java.awt.Color(92, 101, 138));

        tax_label.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        tax_label.setForeground(new java.awt.Color(250, 250, 255));
        tax_label.setText("Withholding Tax:");

        calculateButton.setBackground(new java.awt.Color(253, 56, 29));
        calculateButton.setForeground(new java.awt.Color(250, 250, 255));
        calculateButton.setText("Calculate Pay");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });

        date.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        date.setForeground(new java.awt.Color(250, 250, 255));
        date.setText("Date ");

        paySlipArea.setBackground(new java.awt.Color(250, 250, 255));
        paySlipArea.setColumns(20);
        paySlipArea.setFont(new java.awt.Font("DialogInput", 0, 11)); // NOI18N
        paySlipArea.setForeground(new java.awt.Color(92, 101, 138));
        paySlipArea.setRows(5);
        jScrollPane1.setViewportView(paySlipArea);

        tax1_radio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tax1_radio.setForeground(new java.awt.Color(250, 250, 255));
        tax1_radio.setText("Gross Pay = 20,832 and below");
        tax1_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tax1_radioActionPerformed(evt);
            }
        });

        tax2_radio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tax2_radio.setForeground(new java.awt.Color(250, 250, 255));
        tax2_radio.setText("Gross Pay = 20,833 to below 33,333");
        tax2_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tax2_radioActionPerformed(evt);
            }
        });

        tax3_radio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tax3_radio.setForeground(new java.awt.Color(250, 250, 255));
        tax3_radio.setText("Gross Pay = 33,333 to below 66,667");
        tax3_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tax3_radioActionPerformed(evt);
            }
        });

        netpay_field.setBackground(new java.awt.Color(250, 250, 255));
        netpay_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        netpay_field.setForeground(new java.awt.Color(92, 101, 138));

        noe_label1.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        noe_label1.setForeground(new java.awt.Color(250, 250, 255));
        noe_label1.setText("Last Name :");

        ename_field1.setEditable(false);
        ename_field1.setBackground(new java.awt.Color(250, 250, 255));
        ename_field1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        ename_field1.setForeground(new java.awt.Color(92, 101, 138));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(greetings)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(0, 20, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(noe_label)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ename_field, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(noe_label1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ename_field1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(employeeid_label)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(employeeid_field, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(103, 103, 103)
                                        .addComponent(clearButton1))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                                        .addGap(42, 42, 42)
                                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(tax1_radio)
                                                            .addComponent(tax2_radio)
                                                            .addComponent(tax3_radio)))
                                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                            .addComponent(hr_label)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(hourlyRate_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(phoneA_label)
                                                                .addComponent(riceA_label))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(phoneA_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(riceA_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(mainPanelLayout.createSequentialGroup()
                                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(grosspay_label)
                                                        .addComponent(basicsalary_label))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(grosspay_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(basicsalary_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(mainPanelLayout.createSequentialGroup()
                                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(ta_label)
                                                        .addComponent(clothingA_label))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(clothing_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(totalA_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(34, 34, 34)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(hoursWorked_lb)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(hoursWorked_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(pHealth_label)
                                                    .addComponent(sss_label, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(td_label, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(pagibig_label, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(sss_field, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                                    .addComponent(pHealth_field)
                                                    .addComponent(pagibig_field)
                                                    .addComponent(totalD_field)))
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(netpay_label)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(netpay_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(tax_label)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tax_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(calculateButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(payslipButton)))))
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(date)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton)))
                        .addGap(21, 21, 21))))
        );

        mainPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {employeeid_field, ename_field, ename_field1});

        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(greetings)
                .addGap(17, 17, 17)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employeeid_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeid_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)
                        .addGap(15, 15, 15))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ename_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noe_label)
                            .addComponent(clearButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ename_field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noe_label1))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hoursWorked_lb)
                            .addComponent(hoursWorked_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hr_label)
                            .addComponent(hourlyRate_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(riceA_label)
                                    .addComponent(riceA_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(phoneA_label)
                                    .addComponent(phoneA_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(clothingA_label)
                                    .addComponent(clothing_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ta_label)
                                    .addComponent(totalA_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sss_label)
                                    .addComponent(sss_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pHealth_label)
                                    .addComponent(pHealth_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pagibig_label)
                                    .addComponent(pagibig_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(td_label)
                                    .addComponent(totalD_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tax_label)
                                    .addComponent(tax_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(netpay_label)
                                    .addComponent(netpay_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(basicsalary_label)
                                    .addComponent(basicsalary_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(grosspay_label)
                                    .addComponent(grosspay_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(tax1_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax2_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax3_radio)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(payslipButton)
                            .addComponent(calculateButton))
                        .addGap(54, 54, 54)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(time)
                            .addComponent(date))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // go back to Employee Portal
        EmployeePortal employeePortal = new EmployeePortal();
        employeePortal.show();
        
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed
        // calculate pay
        try {
            hourlyRate= Double.parseDouble(hourlyRate_field.getText());
            hoursWorked= Double.parseDouble(hoursWorked_field.getText());
            riceA= Double.parseDouble(riceA_field.getText());
            phoneA= Double.parseDouble(phoneA_field.getText());
            clothingA= Double.parseDouble(clothing_field.getText());
            basicSalary= Double.parseDouble(basicsalary_field.getText());
            sssC= Double.parseDouble(sss_field.getText());
            hourlyRate= Double.parseDouble(hourlyRate_field.getText());
        
            totalAllowances = riceA + phoneA + clothingA;
            String totalA = Double.toString(totalAllowances);
            totalA_field.setText(totalA);
                               
            phealthC = basicSalary * 0.05/2;
            String philHealth = Double.toString(phealthC);
            pHealth_field.setText(philHealth);
        
            pagibigC = basicSalary * 0.02;
            String pagibig = Double.toString(phealthC);
            pagibig_field.setText(pagibig);
        
            totalDeductions = phealthC + pagibigC + sssC;
            String totalD = Double.toString(totalDeductions);
            totalD_field.setText(totalD);
                        
            grossPay = hourlyRate * hoursWorked + totalAllowances - totalDeductions;
            String grossPayF = Double.toString(grossPay);
            grossPayF = new DecimalFormat("#.0#").format(grossPay);
            grosspay_field.setText(grossPayF);
                             
        }
        catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Please enter Number of hours worked");  
        }
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void employeeid_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeid_fieldKeyReleased
        // enter employee_id
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            String sql = "SELECT * FROM public.employee_data WHERE employee_id= ?";
            
            pst=conn.prepareStatement(sql);
            pst.setString(1, employeeid_field.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                
                String firstName =rs.getString("first_name");
                ename_field.setText(firstName);
                
                String lastName =rs.getString("last_name");
                ename_field1.setText(lastName);
                
                String hourly_rate =rs.getString("hourly_rate");
                hourlyRate_field.setText(hourly_rate);
                
                String rice_s =rs.getString("rice_s");
                riceA_field.setText(rice_s);
                
                String phone_a =rs.getString("phone_a");
                phoneA_field.setText(phone_a);
                
                String clothing_a =rs.getString("clothing_a");
                clothing_field.setText(clothing_a);
                
                String sss_c =rs.getString("sss_c");
                sss_field.setText(sss_c);
                
                String basic_salary =rs.getString("basic_salary");
                basicsalary_field.setText(basic_salary);
                                           
            }
            
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            
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
    }//GEN-LAST:event_employeeid_fieldKeyReleased

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton1ActionPerformed
        // Clear data from textbox
        employeeid_field.setText("");
        ename_field.setText("");
        ename_field1.setText("");
        hourlyRate_field.setText("");
        riceA_field.setText("");
        phoneA_field.setText("");
        clothing_field.setText("");
        sss_field.setText("");
        basicsalary_field.setText("");
        totalA_field.setText("");
        pHealth_field.setText("");
        pagibig_field.setText("");
        totalD_field.setText("");
        grosspay_field.setText("");
        tax_field.setText("");
        grosspay_field.setText("");
        netpay_field.setText("");
        hoursWorked_field.setText("");
        tax1_radio.setSelected(false);
        tax2_radio.setSelected(false);
        tax3_radio.setSelected(false);
        paySlipArea.setText("");
        
        
                
    }//GEN-LAST:event_clearButton1ActionPerformed

    private void tax1_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tax1_radioActionPerformed
        // tax1 = no tax
         
        if (tax1_radio.isSelected()){
            tax_field.setText("0");
            
            netPay();
        }
        
    }//GEN-LAST:event_tax1_radioActionPerformed

    private void tax2_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tax2_radioActionPerformed
        // tax 2 = radio button
        tax2 = (grossPay - 20833) *.2;
        
        if (tax2_radio.isSelected()){
            String tax_2 = Double.toString(tax2);
            tax_2 = new DecimalFormat("#.0#").format(tax2);
            tax_field.setText(tax_2);
            
            netPay();
        }
        
    }//GEN-LAST:event_tax2_radioActionPerformed

    private void tax3_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tax3_radioActionPerformed
        // tax3 radio button
        tax3 = (grossPay - 33333) * .25 + 2500;
        if (tax3_radio.isSelected()){
            String tax_3 = Double.toString(tax3);
            tax_3 = new DecimalFormat("#.0#").format(tax3);
            tax_field.setText(tax_3);
            
            netPay();
        }
                
    }//GEN-LAST:event_tax3_radioActionPerformed

    private void payslipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payslipButtonActionPerformed
        // payslip area
        
        paySlipArea.setText("*******************************************\n");
        paySlipArea.setText(paySlipArea.getText()+"**               Payslip               **\n"); //16 spaces
        paySlipArea.setText(paySlipArea.getText()+"*******************************************\n");
        
        paySlipArea.setText(paySlipArea.getText()+"Date : "+date.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Time : "+time.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Employee First Name : "+ename_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Employee Last Name : "+ename_field1.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Employee ID : "+employeeid_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"*******************************************\n");
        
        paySlipArea.setText(paySlipArea.getText()+"Basic Salary : "+basicsalary_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Hourly Rate : "+hourlyRate_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Number of Hours Worked : "+hoursWorked_field.getText()+"\n");
        
        paySlipArea.setText(paySlipArea.getText()+"*******************************************\n");
        paySlipArea.setText(paySlipArea.getText()+"**               Benefits              **\n");//15 spaces
        paySlipArea.setText(paySlipArea.getText()+"*******************************************\n");
        paySlipArea.setText(paySlipArea.getText()+"Rice Subsidy : "+riceA_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Phone Allowance : "+phoneA_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Clothing Allowance : "+clothing_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Total Allowances : "+totalA_field.getText()+"\n");
        
        paySlipArea.setText(paySlipArea.getText()+"*******************************************\n");
        paySlipArea.setText(paySlipArea.getText()+"**              Deductions             **\n");//12 spaces
        paySlipArea.setText(paySlipArea.getText()+"*******************************************\n");
        paySlipArea.setText(paySlipArea.getText()+"SSS Contribution: "+sss_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"PhilHealth Contribution : "+pHealth_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"PAG-IBIG Contribution : "+pagibig_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Withholding Tax : "+tax_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Total Deductions : "+totalD_field.getText()+"\n\n");
        
        paySlipArea.setText(paySlipArea.getText()+"*******************************************\n");
        paySlipArea.setText(paySlipArea.getText()+"**               Summary               **\n");//15 spaces
        paySlipArea.setText(paySlipArea.getText()+"*******************************************\n");
        paySlipArea.setText(paySlipArea.getText()+"Gross Pay : "+grosspay_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Total Allowances : "+totalA_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Total Deductions : "+totalD_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Withholding Tax : "+tax_field.getText()+"\n");
        paySlipArea.setText(paySlipArea.getText()+"Net Pay : "+netpay_field.getText()+"\n");
        
    }//GEN-LAST:event_payslipButtonActionPerformed

            
            
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
            java.util.logging.Logger.getLogger(Payroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payroll().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LOGO;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField basicsalary_field;
    private javax.swing.JLabel basicsalary_label;
    private javax.swing.JButton calculateButton;
    private javax.swing.JButton clearButton1;
    private javax.swing.JLabel clothingA_label;
    private javax.swing.JTextField clothing_field;
    private javax.swing.JLabel date;
    private javax.swing.JTextField employeeid_field;
    private javax.swing.JLabel employeeid_label;
    private javax.swing.JTextField ename_field;
    private javax.swing.JTextField ename_field1;
    private javax.swing.JLabel greetings;
    private javax.swing.JTextField grosspay_field;
    private javax.swing.JLabel grosspay_label;
    private javax.swing.JTextField hourlyRate_field;
    private javax.swing.JTextField hoursWorked_field;
    private javax.swing.JLabel hoursWorked_lb;
    private javax.swing.JLabel hr_label;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField netpay_field;
    private javax.swing.JLabel netpay_label;
    private javax.swing.JLabel noe_label;
    private javax.swing.JLabel noe_label1;
    private javax.swing.JTextField pHealth_field;
    private javax.swing.JLabel pHealth_label;
    private javax.swing.JTextField pagibig_field;
    private javax.swing.JLabel pagibig_label;
    private javax.swing.JTextArea paySlipArea;
    private javax.swing.JLabel payroll_title;
    private javax.swing.JButton payslipButton;
    private javax.swing.JTextField phoneA_field;
    private javax.swing.JLabel phoneA_label;
    private javax.swing.JTextField riceA_field;
    private javax.swing.JLabel riceA_label;
    private javax.swing.JTextField sss_field;
    private javax.swing.JLabel sss_label;
    private javax.swing.JLabel ta_label;
    private javax.swing.JRadioButton tax1_radio;
    private javax.swing.JRadioButton tax2_radio;
    private javax.swing.JRadioButton tax3_radio;
    private javax.swing.JTextField tax_field;
    private javax.swing.JLabel tax_label;
    private javax.swing.JLabel td_label;
    private javax.swing.JLabel time;
    private javax.swing.JTextField totalA_field;
    private javax.swing.JTextField totalD_field;
    // End of variables declaration//GEN-END:variables
}
