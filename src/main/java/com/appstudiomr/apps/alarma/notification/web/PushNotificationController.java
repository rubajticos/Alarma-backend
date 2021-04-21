package com.appstudiomr.apps.alarma.notification.web;

import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase;
import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase.SendPushNotificationToTokenCommand;
import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase.SendPushNotificationToTopicCommand;
import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase.SendPushWithDataCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
public class PushNotificationController {
    private final PushNotificationUseCase notificationUseCase;

    @PostMapping("/notification/topic")
    public ResponseEntity sendNotification(@RequestBody SendPushNotificationToTopicCommand command) {
        notificationUseCase.sendPushNotificationToTopic(command);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody SendPushNotificationToTokenCommand command) {
        notificationUseCase.sendPushNotificationToToken(command);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/notification/data")
    public ResponseEntity sendDataNotification(@RequestBody SendPushWithDataCommand command) {
        try {
            notificationUseCase.sendPushWithData(command);
            return ResponseEntity.ok().build();
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
