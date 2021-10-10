package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.email;

import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, "utf-8");

            messageHelper.setTo(to);
            messageHelper.setSubject("Confirm your email in order to activate account");
            messageHelper.setText(email, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            LOGGER.error("Failed to send email.", e);
            throw new IllegalStateException("Failed to send email.");
        }
    }
}
