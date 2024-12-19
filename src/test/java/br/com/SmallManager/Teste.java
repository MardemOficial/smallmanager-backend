package br.com.SmallManager;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Teste {

    public static void sendEmail(String recipient, String subject, String body) {
        final String senderEmail = "mardenexecutive@gmail.com";
        final String password = "itjk ryra ecsx xqmk";
        String host = "smtp.gmail.com";
        int port = 587;

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message, senderEmail, password);
            System.out.println("Email sent successfully to: " + recipient);

        } catch (MessagingException e) {
            System.out.println("Deu erro!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String recipient = "mardem.paz@tce.ce.gov.br";
        String subject = "Test Email from Jakarta Mail";
        String body = "Hello,\nThis is a test email sent from Jakarta Mail.";

        // Sending email without attachment
        sendEmail(recipient, subject, body);
    }
}
