package com.mmdc.motor_ph_portal.Portal_Access;

public class MotorPH_Login {
    public static void main(String[] args) {
        try {
            // Set Nimbus look and feel for a modern UI
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to default
        }
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}