package com.appstudiomr.apps.alarma.notification.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PushNotification {
    private String title;
    private String message;
    private String topic;
    private String token;
}

