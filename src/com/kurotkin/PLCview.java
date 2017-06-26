package com.kurotkin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PLCview {

    public static void main(String[] args) {
        Settings.loadSetting();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new AcquisitionRun(), 0, 10, TimeUnit.SECONDS);
    }
}
