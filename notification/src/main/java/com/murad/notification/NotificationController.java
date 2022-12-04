package com.murad.notification;

import com.murad.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
public class NotificationController {
  private final NotificationService notificationService;

  @PostMapping
  public void setNotification(@RequestBody NotificationRequest notificationRequest){
    log.info("New Notification {}",notificationRequest);
    notificationService.send(notificationRequest);
  }
}
