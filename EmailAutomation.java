// src/EmailAutomation.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.mail.MessagingException;

public class EmailAutomation {

    private static final String EMAIL_LIST_FILE = "emails.txt";

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(EMAIL_LIST_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    String email = parts[0].trim();
                    String filePath = parts[1].trim();
                    String subject = "Subject of the Email";
                    String body = "Body of the email goes here";

                    try {
                        EmailUtils.sendEmail(email, subject, body, filePath);
                    } catch (MessagingException e) {
                        System.err.println("Failed to send email to " + email);
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the email list file");
            e.printStackTrace();
        }
    }
}
