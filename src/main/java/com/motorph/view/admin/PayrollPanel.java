package com.motorph.view.admin;

import com.motorph.model.PayrollEntry;
import com.motorph.service.PayrollService;
import com.motorph.util.ServiceLocator;
import javax.swing.*;
import java.awt.*;
import java.time.Month;

public class PayrollPanel extends JPanel {
    private final PayrollService payrollService;

    private JTextField idField;
    private JTextField employeeNameField;
    private JTextField hourlyRateField;
    private JTextField netPaidHoursField;
    private JTextField riceAField;
    private JTextField phoneAField;
    private JTextField clothingField;
    private JTextField totalAField;
    private JTextField sssField;
    private JTextField pHealthField;
    private JTextField pagibigField;
    private JTextField taxField;
    private JTextField totalDField;
    private JTextField grossPayField;
    private JTextField netPayField;
    private JTextField payDateField;
    private JComboBox<String> monthComboBox;
    private JButton calculateButton;
    private JButton payslipButton;

    public PayrollPanel() {
        this.payrollService = ServiceLocator.getService(PayrollService.class);
        initializeUI();
        setupListeners();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        idField = new JTextField();
        employeeNameField = new JTextField();
        hourlyRateField = new JTextField();
        netPaidHoursField = new JTextField();
        riceAField = new JTextField();
        phoneAField = new JTextField();
        clothingField = new JTextField();
        totalAField = new JTextField();
        sssField = new JTextField();
        pHealthField = new JTextField();
        pagibigField = new JTextField();
        taxField = new JTextField();
        totalDField = new JTextField();
        grossPayField = new JTextField();
        netPayField = new JTextField();
        payDateField = new JTextField();
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        calculateButton = new JButton("Calculate Pay");
        payslipButton = new JButton("Generate Payslip");

        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Employee Name:"));
        formPanel.add(employeeNameField);
        formPanel.add(new JLabel("Pay Month:"));
        formPanel.add(monthComboBox);
        formPanel.add(new JLabel("Hourly Rate:"));
        formPanel.add(hourlyRateField);
        formPanel.add(new JLabel("Net Paid Hours:"));
        formPanel.add(netPaidHoursField);
        formPanel.add(new JLabel("Rice Subsidy:"));
        formPanel.add(riceAField);
        formPanel.add(new JLabel("Phone Allowance:"));
        formPanel.add(phoneAField);
        formPanel.add(new JLabel("Clothing Allowance:"));
        formPanel.add(clothingField);
        formPanel.add(new JLabel("Total Allowances:"));
        formPanel.add(totalAField);
        formPanel.add(new JLabel("SSS Contribution:"));
        formPanel.add(sssField);
        formPanel.add(new JLabel("PhilHealth Contribution:"));
        formPanel.add(pHealthField);
        formPanel.add(new JLabel("Pag-ibig Contribution:"));
        formPanel.add(pagibigField);
        formPanel.add(new JLabel("Withholding Tax:"));
        formPanel.add(taxField);
        formPanel.add(new JLabel("Total Deductions:"));
        formPanel.add(totalDField);
        formPanel.add(new JLabel("Gross Pay:"));
        formPanel.add(grossPayField);
        formPanel.add(new JLabel("Net Pay:"));
        formPanel.add(netPayField);
        formPanel.add(new JLabel("Pay Date:"));
        formPanel.add(payDateField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(calculateButton);
        buttonPanel.add(payslipButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        calculateButton.addActionListener(e -> calculatePayroll());
        payslipButton.addActionListener(e -> generatePayslip());
    }

    private void calculatePayroll() {
        String employeeId = idField.getText().trim();
        String selectedMonth = (String) monthComboBox.getSelectedItem();
        int monthNumber = Month.valueOf(selectedMonth.toUpperCase()).getValue();
        try {
            PayrollEntry payroll = payrollService.getPayrollEntry(employeeId, monthNumber);
            if (payroll != null) {
                populateFields(payroll);
                JOptionPane.showMessageDialog(this, "Payroll calculated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No payroll record found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error calculating payroll: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generatePayslip() {
        String employeeId = idField.getText().trim();
        String selectedMonth = (String) monthComboBox.getSelectedItem();
        int monthNumber = Month.valueOf(selectedMonth.toUpperCase()).getValue();
        try {
            String pdfPath = payrollService.generatePayslip(employeeId, monthNumber);
            JOptionPane.showMessageDialog(this, "Payslip generated: " + pdfPath);
            // Optionally open the PDF
            Desktop.getDesktop().open(new java.io.File(pdfPath));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generating payslip: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateFields(PayrollEntry payroll) {
        idField.setText(payroll.getEmployeeId());
        employeeNameField.setText(payroll.getEmployeeId());
        hourlyRateField.setText(String.valueOf(payroll.getHourlyRate()));
        netPaidHoursField.setText(String.valueOf(payroll.getNetPaidHours()));
        riceAField.setText(String.valueOf(payroll.getRiceSubsidy()));
        phoneAField.setText(String.valueOf(payroll.getPhoneAllowance()));
        clothingField.setText(String.valueOf(payroll.getClothingAllowance()));
        totalAField.setText(String.valueOf(payroll.getTotalAllowances()));
        sssField.setText(String.valueOf(payroll.getSssContribution()));
        pHealthField.setText(String.valueOf(payroll.getPhilhealthContribution()));
        pagibigField.setText(String.valueOf(payroll.getPagibigContribution()));
        taxField.setText(String.valueOf(payroll.getWithholdingTax()));
        totalDField.setText(String.valueOf(payroll.getTotalDeductions()));
        grossPayField.setText(String.valueOf(payroll.getGrossPay()));
        netPayField.setText(String.valueOf(payroll.getNetPay()));
        payDateField.setText(payroll.getPayDate());
    }
}
