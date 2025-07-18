package com.motorph.service;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.motorph.dao.PasswordRecoveryDAO;
import com.motorph.dao.PasswordRecoveryDAOImpl;
import com.motorph.model.PasswordRecovery;

public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {
    private final PasswordRecoveryDAO passwordRecoveryDAO = new PasswordRecoveryDAOImpl();
    private static final String EMAIL_FROM = "amotorph@gmail.com";
    private static final String EMAIL_PASSWORD = "znvm dejb erhe wink";

    public PasswordRecoveryServiceImpl(PasswordRecoveryDAO passwordRecoveryDAO2) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public PasswordRecovery initiatePasswordRecovery(String email) {
        if (!passwordRecoveryDAO.verifyEmail(email)) {
            return null;
        }
        String employeeId = passwordRecoveryDAO.findEmployeeIdByEmail(email);
        if (employeeId == null) {
            return null;
        }
        String verificationCode = generateVerificationCode();
        if (sendVerificationCode(email, verificationCode)) {
            return new PasswordRecovery(email, verificationCode, employeeId);
        }
        return null;
    }

    @Override
    public boolean verifyCode(String email, String code) {
        // In a real application, you would store and verify codes in the database
        // For now, the verification is handled in the UI layer
        return true;
    }

    @Override
    public boolean updatePassword(String employeeId, String newPassword) {
        return passwordRecoveryDAO.updatePassword(employeeId, newPassword);
    }

    @Override
    public boolean sendVerificationCode(String email, String code) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Password Recovery Code");
            message.setText("Your verification code is: " + code);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }
}
