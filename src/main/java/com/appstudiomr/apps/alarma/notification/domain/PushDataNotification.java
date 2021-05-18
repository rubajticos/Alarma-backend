package com.appstudiomr.apps.alarma.notification.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
@Builder
public class PushDataNotification {
    private final PushNotification notification;
    private final Map<String, String> notificationData;
}
