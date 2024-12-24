package in.hefshine.NaukriProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body, int password) {
        String fullBody = body + "\n\nYour Password: " + password;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("madhurikatrajkar@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(fullBody);

        mailSender.send(message);
        System.out.println("Email sent successfully to: " + toEmail);
    }
}
