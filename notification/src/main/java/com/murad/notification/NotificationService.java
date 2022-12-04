package com.murad.notification;

import com.murad.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository notificationRepository) {

  public void send(NotificationRequest notificationRequest) {
    notificationRepository.save(
      Notification.builder()
                  .toCustomerId(notificationRequest.getToCustomerId())
                  .toCustomerEmail(notificationRequest.getToCustomerEmail())
                  .sender("murad")
                  .message(notificationRequest.getMessage())
                  .sentAt(LocalDateTime.now())
                  .build()
    );

  }

}
