package com.appstudiomr.apps.alarma.alarms.application;

import com.appstudiomr.apps.alarma.alarms.application.port.AlarmNotificationUseCase;
import com.appstudiomr.apps.alarma.notification.application.port.PushNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmNotificationService implements AlarmNotificationUseCase {

    private final PushNotificationUseCase notificationUseCase;

    @Override
    public SendNotificationResponse sendNotification(SendNotificationCommand command) {
        PushNotificationUseCase.SendPushWithDataCommand pushCommand = PushNotificationUseCase.SendPushWithDataCommand.builder()
                .title(command.getTitle())
                .message(command.getFireBrigadeName())
                .topic(command.getTopic())
                .data("alarmId", command.getAlarmId())
                .data("senderId", command.getSenderId())
                .data("fireBrigadeName", command.getFireBrigadeName())
                .build();

        try {
            notificationUseCase.sendPushWithData(pushCommand);
            return SendNotificationResponse.success();
        } catch (Exception e) {
            return SendNotificationResponse.failure(e.getMessage());
        }
    }
}
