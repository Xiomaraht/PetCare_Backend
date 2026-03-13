package com.edu.sena.Petcare.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("PetCare - Recuperación de Contraseña");
            message.setText("Hola,\n\nHas solicitado restablecer tu contraseña.\n" +
                    "Por favor utiliza el siguiente código o token para cambiar tu contraseña:\n\n" +
                    resetToken + "\n\n" +
                    "Si no fuiste tú, ignora este mensaje.");

            mailSender.send(message);
            System.out.println("Email enviado exitosamente a: " + toEmail);
        } catch (Exception e) {
            System.err.println("Error al intentar enviar el email. Verifica tus credenciales SMTP en application.properties.");
            System.err.println("El token generado es: " + resetToken);
        }
    }
}
