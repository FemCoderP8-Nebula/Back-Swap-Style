package com.swapstyle.swapstyle.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

        private final JavaMailSender mailSender;

        public EmailServiceImpl(JavaMailSender mailSender) {
                this.mailSender = mailSender;
        }

        @Override
        public void sendReservationConfirmation(String buyerEmail, String sellerEmail,
                        String articleTitle, String buyerName) {
                sendEmail(buyerEmail,
                                "Reservation confirmed - " + articleTitle,
                                "Hi " + buyerName + ",\n\nYou have successfully reserved \"" + articleTitle +
                                                "\".\nYour reservation will expire in 24 hours.\n\nSwapStyle Team");

                sendEmail(sellerEmail,
                                "Your article has been reserved - " + articleTitle,
                                "Hi,\n\nYour article \"" + articleTitle + "\" has been reserved by " +
                                                buyerName
                                                + ".\nThe reservation will expire in 24 hours.\n\nSwapStyle Team");
        }

        @Override
        public void sendCancellationNotification(String buyerEmail, String sellerEmail,
                        String articleTitle, String buyerName) {
                sendEmail(buyerEmail,
                                "Reservation cancelled - " + articleTitle,
                                "Hi " + buyerName + ",\n\nYour reservation for \"" + articleTitle +
                                                "\" has been cancelled.\n\nSwapStyle Team");

                sendEmail(sellerEmail,
                                "Reservation cancelled - " + articleTitle,
                                "Hi,\n\nThe reservation for your article \"" + articleTitle +
                                                "\" has been cancelled by " + buyerName + ".\n\nSwapStyle Team");
        }

        @Override
        public void sendExpirationNotification(String buyerEmail, String sellerEmail,
                        String articleTitle, String buyerName) {
                sendEmail(buyerEmail,
                                "Reservation expired - " + articleTitle,
                                "Hi " + buyerName + ",\n\nYour reservation for \"" + articleTitle +
                                                "\" has expired.\n\nSwapStyle Team");

                sendEmail(sellerEmail,
                                "Reservation expired - " + articleTitle,
                                "Hi,\n\nThe reservation for your article \"" + articleTitle +
                                                "\" has expired and is now available again.\n\nSwapStyle Team");
        }

        private void sendEmail(String to, String subject, String body) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(to);
                message.setSubject(subject);
                message.setText(body);
                mailSender.send(message);
        }
}
