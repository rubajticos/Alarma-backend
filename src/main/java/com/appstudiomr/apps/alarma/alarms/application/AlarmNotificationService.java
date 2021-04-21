package com.appstudiomr.apps.alarma.alarms.application;

import com.appstudiomr.apps.alarma.alarms.application.port.AlarmNotificationUseCase;
import com.appstudiomr.apps.alarma.fcm.PushNotificationRequest;
import com.appstudiomr.apps.alarma.fcm.PushNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlarmNotificationService implements AlarmNotificationUseCase {

    private final PushNotificationService notificationService;

    @Override
    public SendNotificationResponse sendNotification(SendNotificationCommand command) {
        PushNotificationRequest notificationRequest = PushNotificationRequest.builder()
                .title(command.getTitle())
                .message(command.getMessage())
                .topic(command.getTopic())
                .build();

        Map<String, String> alarmData = new HashMap<>();
        alarmData.put("alarmId", command.getAlarmId());
        alarmData.put("senderId", command.getSenderId());
        alarmData.put("fireBrigadeName", command.getMessage());

        try {
            notificationService.sendPushNotificationWithDataOnly(notificationRequest, alarmData);
            return SendNotificationResponse.success();
        } catch (Exception e) {
            return SendNotificationResponse.failure(e.getMessage());
        }
    }
}
