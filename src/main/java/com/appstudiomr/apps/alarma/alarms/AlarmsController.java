package com.appstudiomr.apps.alarma.alarms;

import com.appstudiomr.apps.alarma.alarms.dto.SendAlarmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AlarmsController {

    private final AlarmsNotificationsService alarmsService;

    @Autowired
    public AlarmsController(AlarmsNotificationsService alarmsService) {
        this.alarmsService = alarmsService;
    }

    @PostMapping("/alarm")
    public ResponseEntity<Void> sendAlarm(@RequestBody SendAlarmDTO request) {
        CreateAlarmResponse createAlarmResponse = alarmsService.sendAlarm(request);

        if (!createAlarmResponse.isSuccess()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alarm not sent. An error occured");
        }

        return ResponseEntity.ok().build();
    }

}
