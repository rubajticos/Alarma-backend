package com.appstudiomr.apps.alarma.alarms;

import com.appstudiomr.apps.alarma.alarms.dto.SendAlarmDTO;

public interface AlarmsNotificationsService {

    CreateAlarmResponse sendAlarm(SendAlarmDTO sendAlarmDTO);

}
