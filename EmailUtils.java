// src/EmailUtils.java
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtils {

    private static final String SMTP_HOST = "smtp.example.com"; // Replace with your SMTP host
    private static final String SMTP_PORT = "587"; // Replace with your SMTP port
    private static final String USERNAME = "your-email@example.com"; // Replace with your email
    private static final String PASSWORD = "your-email-password"; // Replace with your email password

    public static void sendEmail(String to, String subject, String body, String filePath) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (filePath != null && !filePath.isEmpty()) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(filePath);
            multipart.addBodyPart(attachmentPart);
        }

        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Email sent successfully to " + to);
    }
}
