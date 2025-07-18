package com.motorph.model;

public class PasswordRecovery {
    private String email;
    private String verificationCode;
    private String employeeId;

    public PasswordRecovery(String email, String verificationCode, String employeeId) {
        this.email = email;
        this.verificationCode = verificationCode;
        this.employeeId = employeeId;
    }

    public String getEmail() { return email; }
    public String getVerificationCode() { return verificationCode; }
    public String getEmployeeId() { return employeeId; }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
