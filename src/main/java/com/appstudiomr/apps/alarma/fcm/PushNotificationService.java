package com.appstudiomr.apps.alarma.fcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationService {
    private final FCMService fcmService;

    @Autowired
    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    public void sendPushNotificationWithData(PushNotificationRequest request, Map<String, String> data) throws ExecutionException, InterruptedException {
        fcmService.sendMessageWithDataAndNotificationToTopic(data, request);
    }

    public void sendPushNotificationWithDataOnly(PushNotificationRequest request, Map<String, String> data) throws ExecutionException, InterruptedException {
        fcmService.sendMessageWithDataOnlyToTopic(data, request);
    }

    public void sendPushNotificationWithoutData(PushNotificationRequest request) {
        try {
            fcmService.sendMessageWithoutDataToTopic(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToToken(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Map<String, String> getSamplePayloadData() {
        Map<String, String> pushData = new HashMap<>();
        pushData.put("alarmId", "32322323");
        pushData.put("text", "txt");
        pushData.put("user", "pankaj singh");
        return pushData;
    }
}