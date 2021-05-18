package com.appstudiomr.apps.alarma.notification.web;

import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase;
import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase.SendPushNotificationToTokenCommand;
import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase.SendPushNotificationToTopicCommand;
import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase.SendPushWithDataCommand;
import lombok.AllArgsConstructor;
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
        try {
            notificationUseCase.sendPushNotificationToTopic(command);
            return ResponseEntity.ok().build();
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody SendPushNotificationToTokenCommand command) {
        try {
            notificationUseCase.sendPushNotificationToToken(command);
            return ResponseEntity.ok().build();
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.badRequest().build();
        }
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
