package misc;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class OTPAuthenticator {
    private static final int OTP_LENGTH = 6; // Length of the OTP

    private static final String SENDER_EMAIL = "mails.mail.mm@gmail.com";
    private static final String SENDER_PASS = "okokokok";

    public static boolean sendOTP(String receiverEmail) {
        Scanner scanner = new Scanner(System.in);

        String otp = generateOTP();
        sendEmail(SENDER_EMAIL, SENDER_PASS, receiverEmail, "OTP Verification", "Your OTP is: " + otp);

        // Verify OTP
        System.out.print("Enter the OTP received:\n");
        String enteredOTP = scanner.nextLine();

        if (otp.equals(enteredOTP)) {
            System.out.println("OTP verification successful!");
            return true;
        } else {
            System.out.println("OTP verification failed!");
            return false;
        }
    }

    // Generate a random OTP
    private static String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }

    // Send email using JavaMail
    private static void sendEmail(String senderEmail, String senderPassword, String receiverEmail, String subject, String messageText) {
        // Mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
            message.setSubject(subject);
            message.setText(messageText);

            // Send the message
            Transport.send(message);

            System.out.println("OTP sent successfully to " + receiverEmail);
        } catch (MessagingException e) {
            System.out.println("Failed to send OTP to " + receiverEmail);
            e.printStackTrace();
        }
    }
}