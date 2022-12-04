package com.murad.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest{
  private Integer notificationId;
  private Integer toCustomerId;
  private String toCustomerEmail;
  private String sender;
  private String message;
  private LocalDateTime sentAt;

}

