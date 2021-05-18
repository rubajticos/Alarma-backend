package com.appstudiomr.apps.alarma.notification.infrastructure;

import com.appstudiomr.apps.alarma.notification.domain.NotificationParameter;
import com.appstudiomr.apps.alarma.notification.domain.PushDataNotification;
import com.appstudiomr.apps.alarma.notification.domain.PushNotification;
import com.appstudiomr.apps.alarma.notification.domain.PushNotificationSender;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseCloudMessagingService implements PushNotificationSender {
    @Override
    public void sendMessageWithDataAndNotificationToTopic(PushDataNotification request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithData(request.getNotificationData(), request.getNotification());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        System.out.println("Sent message with data. Topic: " + request.getNotification().getTopic() + ", " + response + " msg " + jsonOutput);
    }

    @Override
    public void sendMessageWithDataOnlyToTopic(PushDataNotification request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithDataForTopic(request.getNotificationData(), request.getNotification());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        System.out.println("Sent message with data. Topic: " + request.getNotification().getTopic() + ", " + response + " msg " + jsonOutput);
    }

    @Override
    public void sendMessageWithoutDataToTopic(PushNotification request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithoutData(request);
        String response = sendAndGetResponse(message);
        System.out.println("Sent message without data. Topic: " + request.getTopic() + ", " + response);
    }

    @Override
    public void sendMessageToToken(PushNotification request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        System.out.println("Sent message to token. Device token: " + request.getToken() + ", " + response + " msg " + jsonOutput);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private Message getPreconfiguredMessageToToken(PushNotification request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                .build();
    }

    private Message getPreconfiguredMessageWithoutData(PushNotification request) {
        return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
                .build();
    }

    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotification request) {
        return getPreconfiguredMessageBuilder(request).putAllData(data).setToken(request.getToken())
                .build();
    }

    private Message getPreconfiguredMessageWithDataForTopic(Map<String, String> data, PushNotification request) {
        return getPreconfiguredMessageBuilderWithoutNotification(request).putAllData(data).setTopic(request.getTopic())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotification request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());

        Notification notification = Notification.builder().setTitle(request.getTitle()).setBody(request.getMessage()).build();

        return Message.builder()
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setNotification(notification);
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
                        .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    private Message.Builder getPreconfiguredMessageBuilderWithoutNotification(PushNotification request) {
        AndroidConfig androidConfig = getAndroidConfigWithoutNotification(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());

        return Message.builder()
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig);
    }

    private AndroidConfig getAndroidConfigWithoutNotification(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .build();
    }
}
