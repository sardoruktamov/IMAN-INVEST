package uz.java.springdatajpa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.ResponseDto;

import java.io.IOException;

/**
 * @author a.ergashev
 * Date: 24.04.2023
 * Time: 22:35
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSenderImpl javaMailSender;


    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.username}")
    private String username;

    public ResponseDto<Void> sendForRecreatePassword(String to, String subject, String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);

        javaMailSender.send(simpleMailMessage);

        return ResponseDto.<Void>builder()
                .success(true)
                .message("OK")
                .build();
    }

    public ResponseDto<Void> sendHtmlPageToResetPassword(String to, String uuid) throws MessagingException, IOException {
        Session session = Session.getInstance(javaMailSender.getJavaMailProperties(),
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

        String html = new ObjectMapper()
                .readValue(getClass()
                        .getClassLoader()
                        .getResourceAsStream("reset.html"),
                        String.class
                );

        html = html.replace("${uuid}", uuid);

        message.setContent(html, "text/html");

        Transport.send(message);

        return ResponseDto.<Void>builder()
                .success(true)
                .message("OK")
                .build();
    }
}
