package helperCode.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class emailHelper {
    static final String username = "20102001Son@gmail.com";
    final static String password = "zxfoaolupyatefus";
    final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss\nyyyy/MM/dd");

    public void sendEmail(String EMAIL_TO, String EMAIL_SUBJECT, String EMAIL_TEXT) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO));
            message.setSubject(EMAIL_SUBJECT);
            LocalDateTime now = LocalDateTime.now();
            message.setText(EMAIL_TEXT + "\n" + dtf.format(now));
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
