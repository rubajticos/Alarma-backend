package com.appstudiomr.apps.alarma.notification.application;

import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase;
import com.appstudiomr.apps.alarma.notification.domain.PushDataNotification;
import com.appstudiomr.apps.alarma.notification.domain.PushNotification;
import com.appstudiomr.apps.alarma.notification.domain.PushNotificationSender;
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

        PushDataNotification pushDataNotification = PushDataNotification.builder()
                .notification(notification)
                .notificationData(command.getData())
                .build();

        notificationSender.sendMessageWithDataAndNotificationToTopic(pushDataNotification);
    }

    public void sendPushWithData(SendPushWithDataCommand command) throws ExecutionException, InterruptedException {
        PushNotification notification = PushNotification.builder()
                .title(command.getTitle())
                .message(command.getMessage())
                .topic(command.getTopic())
                .build();

        PushDataNotification pushDataNotification = PushDataNotification.builder()
                .notification(notification)
                .notificationData(command.getData())
                .build();

        notificationSender.sendMessageWithDataOnlyToTopic(pushDataNotification);
    }

    public void sendPushNotificationToTopic(SendPushNotificationToTopicCommand command) throws ExecutionException, InterruptedException {
        PushNotification notification = PushNotification.builder()
                .title(command.getTitle())
                .message(command.getMessage())
                .topic(command.getTopic())
                .build();

        notificationSender.sendMessageWithoutDataToTopic(notification);
    }

    public void sendPushNotificationToToken(SendPushNotificationToTokenCommand command) throws ExecutionException, InterruptedException {
        PushNotification notification = PushNotification.builder()
                .title(command.getTitle())
                .message(command.getMessage())
                .token(command.getToken())
                .build();

        notificationSender.sendMessageToToken(notification);
    }
}