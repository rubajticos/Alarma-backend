package com.appstudiomr.apps.alarma.notification.application;

import com.appstudiomr.apps.alarma.notification.domain.PushNotificationSender;
import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase;
import com.appstudiomr.apps.alarma.notification.domain.PushNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
class PushNotificationService implements PushNotificationUseCase {
    private final PushNotificationSender notificationSender;

    public void sendPushNotificationAndData(SendPushNotificationAndDataCommand command) throws ExecutionException, InterruptedException {
        PushNotification notification = PushNotification.builder()
                .title(command.getTitle())
                .message(command.getMessage())
                .topic(command.getTopic())
                .build();

        notificationSender.sendMessageWithDataAndNotificationToTopic(command.getData(), notification);
    }

    public void sendPushWithData(SendPushWithDataCommand command) throws ExecutionException, InterruptedException {
        PushNotification notification = PushNotification.builder()
                .title(command.getTitle())
                .message(command.getMessage())
                .topic(command.getTopic())
                .build();

        notificationSender.sendMessageWithDataOnlyToTopic(command.getData(), notification);
    }

    public void sendPushNotificationToTopic(SendPushNotificationToTopicCommand command) {
        try {
            PushNotification notification = PushNotification.builder()
                    .title(command.getTitle())
                    .message(command.getMessage())
                    .topic(command.getTopic())
                    .build();

            notificationSender.sendMessageWithoutDataToTopic(notification);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendPushNotificationToToken(SendPushNotificationToTokenCommand command) {
        try {
            PushNotification notification = PushNotification.builder()
                    .title(command.getTitle())
                    .message(command.getMessage())
                    .token(command.getToken())
                    .build();

            notificationSender.sendMessageToToken(notification);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}