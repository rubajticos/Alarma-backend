package com.appstudiomr.apps.alarma.alarms.application.port;

import lombok.Builder;
import lombok.Value;

import java.util.Arrays;
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
    class SendNotificationResponse {
        boolean success;
        List<String> errors;

        public static SendNotificationResponse success() {
            return new SendNotificationResponse(true, Collections.emptyList());
        }

        public static SendNotificationResponse failure(String... errors) {
            return new SendNotificationResponse(false, Arrays.asList(errors));
        }
    }
}
