package com.appstudiomr.apps.alarma.notification.application.port;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface PushNotificationUseCase {

    void sendPushNotificationAndData(SendPushNotificationAndDataCommand command) throws ExecutionException, InterruptedException;

    void sendPushWithData(SendPushWithDataCommand command) throws ExecutionException, InterruptedException;

    void sendPushNotificationToTopic(SendPushNotificationToTopicCommand command);

    void sendPushNotificationToToken(SendPushNotificationToTokenCommand command);

    @Value
    @Builder
    class SendPushNotificationAndDataCommand {
        String title;
        String message;
        String topic;
        @Singular("data")
        Map<String, String> data;
    }

    @Value
    @Builder
    class SendPushWithDataCommand {
        String title;
        String message;
        String topic;
        @Singular("data")
        Map<String, String> data;
    }

    @Value
    @Builder
    class SendPushNotificationToTopicCommand {
        String title;
        String message;
        String topic;
    }

    @Value
    @Builder
    class SendPushNotificationToTokenCommand {
        String title;
        String message;
        String token;
    }


}
