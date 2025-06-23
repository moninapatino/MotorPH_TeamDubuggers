
package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_util.DatabaseConnect;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class Payroll extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
    DatabaseConnect dbConnect = new DatabaseConnect() {
    };


    public Payroll() {
        initComponents();
        setTitle ("Employee Payroll");
        //setSize(700, 850);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        
        conn = dbConnect.connect();
        
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

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        payroll_title = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();
        greetings = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        noe_label = new javax.swing.JLabel();
        clearButton1 = new javax.swing.JButton();
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
        id_field = new javax.swing.JTextField();
        hoursWorked_field = new javax.swing.JTextField();
        phoneA_field = new javax.swing.JTextField();
        hourlyRate_field = new javax.swing.JTextField();
        sss_field = new javax.swing.JTextField();
        clothing_field = new javax.swing.JTextField();
        riceA_field = new javax.swing.JTextField();
        pagibig_field = new javax.swing.JTextField();
        pHealth_field = new javax.swing.JTextField();
        employeeid_label = new javax.swing.JLabel();
        firstname_field = new javax.swing.JTextField();
        totalA_field = new javax.swing.JTextField();
        totalD_field = new javax.swing.JTextField();
        basicsalary_label = new javax.swing.JLabel();
        grosspay_label = new javax.swing.JLabel();
        netpay_label = new javax.swing.JLabel();
        basicSalary_field = new javax.swing.JTextField();
        grosspay_field = new javax.swing.JTextField();
        tax_field = new javax.swing.JTextField();
        tax_label = new javax.swing.JLabel();
        calculateButton = new javax.swing.JButton();
        date = new javax.swing.JLabel();
        netpay_field = new javax.swing.JTextField();
        noe_label1 = new javax.swing.JLabel();
        lastname_field = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        payperiod = new javax.swing.JLabel();
        payslipBtn = new javax.swing.JButton();

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
        hoursWorked_lb.setText("Days of Worked:");

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

        id_field.setBackground(new java.awt.Color(250, 250, 255));
        id_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        id_field.setForeground(new java.awt.Color(92, 101, 138));
        id_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_fieldKeyReleased(evt);
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

        firstname_field.setEditable(false);
        firstname_field.setBackground(new java.awt.Color(250, 250, 255));
        firstname_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        firstname_field.setForeground(new java.awt.Color(92, 101, 138));

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

        basicSalary_field.setBackground(new java.awt.Color(250, 250, 255));
        basicSalary_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        basicSalary_field.setForeground(new java.awt.Color(92, 101, 138));

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

        netpay_field.setBackground(new java.awt.Color(250, 250, 255));
        netpay_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        netpay_field.setForeground(new java.awt.Color(92, 101, 138));

        noe_label1.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        noe_label1.setForeground(new java.awt.Color(250, 250, 255));
        noe_label1.setText("Last Name :");

        lastname_field.setEditable(false);
        lastname_field.setBackground(new java.awt.Color(250, 250, 255));
        lastname_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        lastname_field.setForeground(new java.awt.Color(92, 101, 138));

        jComboBox1.setBackground(new java.awt.Color(92, 101, 138));
        jComboBox1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(250, 250, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1st Cut-off", "2nd Cut-off" }));

        payperiod.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        payperiod.setForeground(new java.awt.Color(250, 250, 255));
        payperiod.setText("Pay Period:");

        payslipBtn.setBackground(new java.awt.Color(253, 56, 29));
        payslipBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        payslipBtn.setForeground(new java.awt.Color(250, 250, 255));
        payslipBtn.setText("View Payslip");
        payslipBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payslipBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(greetings)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(noe_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(firstname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(employeeid_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(noe_label1)
                                    .addComponent(payperiod))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lastname_field, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(103, 103, 103)
                        .addComponent(clearButton1))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                            .addComponent(clothingA_label)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(clothing_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(mainPanelLayout.createSequentialGroup()
                                                    .addComponent(hr_label)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(hourlyRate_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(mainPanelLayout.createSequentialGroup()
                                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(phoneA_label)
                                                        .addComponent(riceA_label)
                                                        .addComponent(hoursWorked_lb))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(phoneA_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(riceA_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(hoursWorked_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(grosspay_label)
                                                    .addComponent(basicsalary_label))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(grosspay_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(basicSalary_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(ta_label)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(totalA_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(79, 79, 79))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(date)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                .addComponent(payslipBtn))
                            .addComponent(backButton))))
                .addGap(73, 73, 73))
        );

        mainPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {firstname_field, id_field, lastname_field});

        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(greetings)
                .addGap(17, 17, 17)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeid_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstname_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noe_label)
                    .addComponent(clearButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastname_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noe_label1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payperiod))
                .addGap(16, 16, 16)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sss_label)
                            .addComponent(sss_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pHealth_label)
                            .addComponent(pHealth_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hr_label)
                            .addComponent(hourlyRate_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hoursWorked_lb)
                            .addComponent(hoursWorked_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pagibig_label)
                            .addComponent(pagibig_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(td_label)
                            .addComponent(totalD_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tax_label)
                                    .addComponent(tax_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(netpay_label)
                                    .addComponent(netpay_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ta_label)
                                    .addComponent(totalA_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(basicsalary_label)
                                    .addComponent(basicSalary_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(grosspay_label)
                                    .addComponent(grosspay_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(calculateButton)
                            .addComponent(payslipBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(time)
                                .addComponent(date))
                            .addComponent(backButton))
                        .addGap(21, 21, 21))
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
        // go back to Employee Portal
        EmployeePortal employeePortal = new EmployeePortal();
        employeePortal.show();
        
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void id_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_fieldKeyReleased
        // enter employee_id

        try {
            conn = dbConnect.connect(); // Establish the connection
            String employeeId = id_field.getText();
            System.out.println("Employee ID: " + employeeId); // Debugging output

            PayrollCalculation employee = dbConnect.getPayrollDetails(employeeId);

            if (employee != null) {
                firstname_field.setText(employee.getFirstName());
                lastname_field.setText(employee.getLastName());
                sss_field.setText(String.format("%.2f", employee.getSssC()));
                riceA_field.setText(String.format("%.2f", employee.getRiceA()));
                phoneA_field.setText(String.format("%.2f", employee.getPhoneA()));
                clothing_field.setText(String.format("%.2f", employee.getClothingA()));
                hourlyRate_field.setText(String.format("%.2f", employee.getHourlyRate()));
            } else {
                
            }
        } catch (Exception e) {
            // Display the employeeId in the error message
            JOptionPane.showMessageDialog(null, "No employee found with ID: " 
                    + id_field.getText(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
           
            if (conn != null) {
                
    }
}
    }//GEN-LAST:event_id_fieldKeyReleased

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton1ActionPerformed
        // Clear data from textbox
        id_field.setText("");
        firstname_field.setText("");
        lastname_field.setText("");
        hourlyRate_field.setText("");
        riceA_field.setText("");
        phoneA_field.setText("");
        clothing_field.setText("");
        sss_field.setText("");
        basicSalary_field.setText("");
        totalA_field.setText("");
        pHealth_field.setText("");
        pagibig_field.setText("");
        totalD_field.setText("");
        grosspay_field.setText("");
        tax_field.setText("");
        grosspay_field.setText("");
        netpay_field.setText("");
        hoursWorked_field.setText("");
        
        
                
    }//GEN-LAST:event_clearButton1ActionPerformed

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed
        // calculate pay
        
        
       try {
            
            Double hourlyRate = Double.parseDouble(hourlyRate_field.getText()); 
            Double riceA = Double.parseDouble(riceA_field.getText());
            Double phoneA = Double.parseDouble(phoneA_field.getText());
            Double clothingA = Double.parseDouble(clothing_field.getText());
            //Double basicSalary = Double.parseDouble(clothing_field.getText());
            Double hoursWorked = Double.parseDouble(hoursWorked_field.getText());
            Double sssC = Double.parseDouble(sss_field.getText());
            
  
            // Create an instance of PayrollCalculation
              PayrollCalculation payroll = new PayrollCalculation (null, null, null, 0.0, sssC,
                    riceA, phoneA, clothingA, hourlyRate, hoursWorked, 0.0,
                    0.0, 0.0, 0.0, 0.0, 0.0);
 
            // Call the calculatePayroll method
            
            double totalAllowances = payroll.calculateTotalAllowances();
            double philhealthC = payroll.calculatePhilHealth();
            double pagibigC = payroll.calculatePagibig();
            double totalDeductions = payroll.calculateTotalDeductions();
            double grossPay = payroll.calculateGrossPay();
            double basicSalary = payroll.calculateBasicSalary();
            double tax = payroll.calculateTax();
            double netPay = payroll.calculateNetPay();
           
           String totalA = Double.toString(totalAllowances);
           totalA = new DecimalFormat("#.0#").format(totalAllowances);           
           totalA_field.setText(totalA); 
           
           String pHeathC = Double.toString(philhealthC);
           pHeathC = new DecimalFormat("#.0#").format(philhealthC);           
           pHealth_field.setText(pHeathC);
           
           String pagibigCon = Double.toString(pagibigC);
           pagibigCon = new DecimalFormat("#.0#").format(pagibigC);           
           pagibig_field.setText(pagibigCon);
           
           String totalD = Double.toString(totalDeductions);
           totalD = new DecimalFormat("#.0#").format(totalDeductions);           
           totalD_field.setText(totalD);
           
           String basicS = Double.toString(basicSalary);
           basicS = new DecimalFormat("#.0#").format(basicSalary);
           basicSalary_field.setText(basicS);
           
           String grossPayF = Double.toString(grossPay);
            grossPayF = new DecimalFormat("#.0#").format(grossPay);
            grosspay_field.setText(grossPayF);
            
           String totalTax = Double.toString(tax);
            totalTax = new DecimalFormat("#.0#").format(tax);
            tax_field.setText(totalTax);
            
            String netPayF = Double.toString(netPay);
            netPayF = new DecimalFormat("#.0#").format(netPay);
            netpay_field.setText(netPayF);
            
            


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Enter number of hours worked.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
    
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void payslipBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payslipBtnActionPerformed
        try{
            DatabaseConnect dbConnect = new DatabaseConnect() {};
            conn = dbConnect.connect();
            String reportpath  = "C:\\Users\\user\\Desktop\\Monina\\MMDC\\Term 2 24-25\\MotorPHPortal\\src\\main\\java\\com\\mmdc\\motor_ph_util\\reportPayslipTemplate.jrxml";
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
    private javax.swing.JTextField basicSalary_field;
    private javax.swing.JLabel basicsalary_label;
    private javax.swing.JButton calculateButton;
    private javax.swing.JButton clearButton1;
    private javax.swing.JLabel clothingA_label;
    private javax.swing.JTextField clothing_field;
    private javax.swing.JLabel date;
    private javax.swing.JLabel employeeid_label;
    private javax.swing.JTextField firstname_field;
    private javax.swing.JLabel greetings;
    private javax.swing.JTextField grosspay_field;
    private javax.swing.JLabel grosspay_label;
    private javax.swing.JTextField hourlyRate_field;
    private javax.swing.JTextField hoursWorked_field;
    private javax.swing.JLabel hoursWorked_lb;
    private javax.swing.JLabel hr_label;
    private javax.swing.JTextField id_field;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lastname_field;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField netpay_field;
    private javax.swing.JLabel netpay_label;
    private javax.swing.JLabel noe_label;
    private javax.swing.JLabel noe_label1;
    private javax.swing.JTextField pHealth_field;
    private javax.swing.JLabel pHealth_label;
    private javax.swing.JTextField pagibig_field;
    private javax.swing.JLabel pagibig_label;
    private javax.swing.JLabel payperiod;
    private javax.swing.JLabel payroll_title;
    private javax.swing.JButton payslipBtn;
    private javax.swing.JTextField phoneA_field;
    private javax.swing.JLabel phoneA_label;
    private javax.swing.JTextField riceA_field;
    private javax.swing.JLabel riceA_label;
    private javax.swing.JTextField sss_field;
    private javax.swing.JLabel sss_label;
    private javax.swing.JLabel ta_label;
    private javax.swing.JTextField tax_field;
    private javax.swing.JLabel tax_label;
    private javax.swing.JLabel td_label;
    private javax.swing.JLabel time;
    private javax.swing.JTextField totalA_field;
    private javax.swing.JTextField totalD_field;
    // End of variables declaration//GEN-END:variables
}
