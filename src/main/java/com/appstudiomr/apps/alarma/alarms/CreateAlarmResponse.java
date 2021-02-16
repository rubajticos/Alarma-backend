package com.appstudiomr.apps.alarma.alarms;

import java.util.Optional;

public class CreateAlarmResponse {

    private final boolean success;
    private final Exception error;

    public CreateAlarmResponse(boolean isSuccess, Exception error) {
        this.success = isSuccess;
        this.error = error;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Optional<Exception> getError() {
        return Optional.ofNullable(this.error);
    }
}
