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
            message.setText("Hola,\n\n" +
                    "Has solicitado restablecer tu contraseña en PetCare.\n\n" +
                    "Por favor, utiliza el siguiente código para cambiar tu contraseña:\n\n" +
                    "   " + resetToken + "\n\n" +
                    "Este código expirará en 1 hora.\n\n" +
                    "Si no realizaste esta solicitud, puedes ignorar este correo de forma segura.\n\n" +
                    "Atentamente,\n" +
                    "El equipo de PetCare.");

            mailSender.send(message);
            System.out.println("Email enviado exitosamente a: " + toEmail);
        } catch (Exception e) {
            System.err.println("Error al intentar enviar el email. Verifica tus credenciales SMTP en application.properties.");
            System.err.println("El token generado es: " + resetToken);
        }
    }
}
