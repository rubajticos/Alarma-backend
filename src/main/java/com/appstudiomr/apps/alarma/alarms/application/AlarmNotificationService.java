package com.appstudiomr.apps.alarma.alarms.application;

import com.appstudiomr.apps.alarma.alarms.application.port.AlarmNotificationUseCase;
import org.springframework.stereotype.Service;

@Service
public class AlarmNotificationService implements AlarmNotificationUseCase {

    @Override
    public SendNotificationResponse sendNotification(SendNotificationCommand command) {
        return null;
    }
}
