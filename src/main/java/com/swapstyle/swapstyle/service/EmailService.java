package com.swapstyle.swapstyle.service;

public interface EmailService {
        public void sendReservationConfirmation(String buyerEmail, String sellerEmail,
                        String articleTitle, String buyerName);

        public void sendCancellationNotification(String buyerEmail, String sellerEmail,
                        String articleTitle, String buyerName);

        public void sendExpirationNotification(String buyerEmail, String sellerEmail,
                        String articleTitle, String buyerName);
}
