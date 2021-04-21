package com.appstudiomr.apps.alarma.notification.domain;

import java.util.concurrent.ExecutionException;

public interface PushNotificationSender {
    void sendMessageWithDataAndNotificationToTopic(PushDataNotification request)
            throws InterruptedException, ExecutionException;

    void sendMessageWithDataOnlyToTopic(PushDataNotification request)
            throws InterruptedException, ExecutionException;

    void sendMessageWithoutDataToTopic(PushNotification request)
            throws InterruptedException, ExecutionException;

    void sendMessageToToken(PushNotification request)
            throws InterruptedException, ExecutionException;
}
