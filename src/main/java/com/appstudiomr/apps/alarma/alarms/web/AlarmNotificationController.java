package com.appstudiomr.apps.alarma.alarms.web;

import com.appstudiomr.apps.alarma.alarms.application.port.AlarmNotificationUseCase;
import com.appstudiomr.apps.alarma.alarms.application.port.AlarmNotificationUseCase.SendNotificationCommand;
import com.appstudiomr.apps.alarma.alarms.application.port.AlarmNotificationUseCase.SendNotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class AlarmNotificationController {

    private final AlarmNotificationUseCase notificationUseCase;

    @PostMapping("/alarmnew")
    public ResponseEntity<Void> sendAlarmNotification(@RequestBody SendNotificationCommand command) {
        SendNotificationResponse response = notificationUseCase.sendNotification(command);
        System.out.println("Send notification response: " + response.toString());

        if (!response.isSuccess()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, response.getErrors().toString());
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/alarm")
    public ResponseEntity<Void> sendAlarmNotification(@RequestBody AlarmNotificationUseCase.SendNotificationCommandOld command) {

        SendNotificationCommand newCommand = SendNotificationCommand.builder()
                .title(command.getTitle())
                .topic(command.getTopic())
                .alarmId(command.getAlarmId())
                .senderId(command.getSenderId())
                .fireBrigadeName(command.getMessage())
                .build();

        SendNotificationResponse response = notificationUseCase.sendNotification(newCommand);
        System.out.println("Send notification response: " + response.toString());

        if (!response.isSuccess()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, response.getErrors().toString());
        }

        return ResponseEntity.ok().build();
    }

}
