package bankproject.onlinebanking.Service.ServiceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import bankproject.onlinebanking.Model.Mail;
import bankproject.onlinebanking.Service.MailService;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(Mail theMail) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo("dummybankprojectemail@gmail.com");
            message.setSubject(theMail.subject);
            message.setText(theMail.body + "\n\nFrom: " + theMail.email + "\n\nSent Date: " + theMail.sentDate);
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println("----------------------------------------");
            System.out.println("WARNING: Email sending failed. Logging content to console instead.");
            System.out.println("To: dummybankprojectemail@gmail.com");
            System.out.println("Subject: " + theMail.subject);
            System.out.println("Body: " + theMail.body);
            System.out.println("From: " + theMail.email);
            System.out.println("----------------------------------------");
        }
    }

    @Override
    public void transactionMail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println("----------------------------------------");
            System.out.println("WARNING: Email sending failed. Logging content to console instead.");
            System.out.println("To: " + to);
            System.out.println("Subject: " + subject);
            System.out.println("Body: " + body);
            System.out.println("----------------------------------------");
        }
    }

    @Override
    public void sendMail(String email, String link) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setText(link);
            message.setSubject("Reset Password");
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println("----------------------------------------");
            System.out.println("WARNING: Email sending failed. Logging content to console instead.");
            System.out.println("To: " + email);
            System.out.println("Subject: Reset Password");
            System.out.println("Link/Body: " + link);
            System.out.println("----------------------------------------");
        }

    }

}
