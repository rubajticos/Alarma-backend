package com.appstudiomr.apps.alarma.alarms.dto;

import lombok.Data;

@Data
public class SendAlarmDTO {

    private String title;
    private String message;
    private String topic;
    private String alarmId;
    private String senderId;

}
