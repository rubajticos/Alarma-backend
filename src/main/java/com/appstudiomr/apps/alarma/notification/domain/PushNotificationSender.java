package com.appstudiomr.apps.alarma.notification.domain;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface PushNotificationSender {
    void sendMessageWithDataAndNotificationToTopic(Map<String, String> data, PushNotification request)
            throws InterruptedException, ExecutionException;

    void sendMessageWithDataOnlyToTopic(Map<String, String> data, PushNotification request)
            throws InterruptedException, ExecutionException;

    void sendMessageWithoutDataToTopic(PushNotification request)
            throws InterruptedException, ExecutionException;

    void sendMessageToToken(PushNotification request)
            throws InterruptedException, ExecutionException;
}
