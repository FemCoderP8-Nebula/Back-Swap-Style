package com.swapstyle.swapstyle.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.swapstyle.swapstyle.dto.request.ContactRequestDTO;

@Service
public class ContactEmailService {


    private final JavaMailSender mailSender;

    public ContactEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactEmail(ContactRequestDTO dto) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("swapstylestaff@gmail.com");
        mail.setSubject("New message from " + dto.name());
        mail.setText(
        "Name: " + dto.name() + "\n" +
        "Email: " + dto.email() + "\n" +
        "City: " + dto.city() + "\n\n" +
        "Message:\n" + dto.message()
        );
        mailSender.send(mail);
    }
}
