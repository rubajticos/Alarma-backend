package com.appstudiomr.apps.alarma.alarms;

import com.appstudiomr.apps.alarma.alarms.dto.SendAlarmDTO;
import com.appstudiomr.apps.alarma.fcm.PushNotificationRequest;
import com.appstudiomr.apps.alarma.fcm.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlarmsNotificationsServiceImpl implements AlarmsNotificationsService {

    private final PushNotificationService notificationService;

    @Autowired
    public AlarmsNotificationsServiceImpl(PushNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public CreateAlarmResponse sendAlarm(SendAlarmDTO sendAlarmDTO) {
        PushNotificationRequest notificationRequest = new PushNotificationRequest();
        notificationRequest.setTitle(sendAlarmDTO.getTitle());
        notificationRequest.setMessage(sendAlarmDTO.getMessage());
        notificationRequest.setTopic(sendAlarmDTO.getTopic());

        Map<String, String> alarmData = new HashMap<>();
        alarmData.put("alarmId", sendAlarmDTO.getAlarmId());
        alarmData.put("senderId", sendAlarmDTO.getSenderId());
        alarmData.put("fireBrigadeName", sendAlarmDTO.getMessage());

        try {
            notificationService.sendPushNotificationWithDataOnly(notificationRequest, alarmData);
            return new CreateAlarmResponse(true, null);
        } catch (Exception e) {
            return new CreateAlarmResponse(false, e);
        }

    }
}
