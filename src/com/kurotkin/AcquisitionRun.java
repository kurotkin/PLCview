package com.kurotkin;

import com.google.gson.Gson;

/**
 * Created by Vitaly Kurotkin on 26.06.2017.
 */
public class AcquisitionRun implements Runnable {
    private Influxdb influxdb;

    public AcquisitionRun() {
        influxdb = new Influxdb();
    }

    @Override
    public void run() {
        String HTTPgetterUrl = Settings.getHTTPgetterUrl();
        HTTPgetter getJson = new HTTPgetter(HTTPgetterUrl);
        String jsonLine = getJson.get();

        Gson gson = new Gson();
        Device[] data = gson.fromJson(jsonLine, Device[].class);

        influxdb.insert(data);
    }
}
