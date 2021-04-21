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
public class AlarmsController {

    private final AlarmNotificationUseCase notificationUseCase;

    @PostMapping("/alarm")
    public ResponseEntity<Void> sendAlarmNotification(@RequestBody SendNotificationCommand command) {
        SendNotificationResponse response = notificationUseCase.sendNotification(command);

        if (!response.isSuccess()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, response.getErrors().toString());
        }

        return ResponseEntity.ok().build();
    }

}
