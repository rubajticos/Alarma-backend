package com.appstudiomr.apps.alarma.alarms.application.port;

import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

public interface AlarmNotificationUseCase {

    SendNotificationResponse sendNotification(SendNotificationCommand command);

    @Value
    @Builder
    class SendNotificationCommand {
        String title;
        String message;
        String topic;
        String alarmId;
        String senderId;
    }

    @Value
    @Builder
    class SendNotificationResponse {
        public static SendNotificationResponse SUCCESS = new SendNotificationResponse(true, Collections.emptyList());

        boolean success;
        List<String> errors;
    }
}
