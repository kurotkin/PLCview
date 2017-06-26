package com.kurotkin;

import com.google.gson.Gson;

/**
 * Created by Vitaly Kurotkin on 26.06.2017.
 */
public class AcquisitionRun implements Runnable {
    private static Influxdb influxdb = new Influxdb();

    @Override
    public void run() {
        HTTPgetter getJson = new HTTPgetter("http://192.168.1.50/awp/json.htm");
        String jsonLine = getJson.get();

        Gson gson = new Gson();
        Device[] data = gson.fromJson(jsonLine, Device[].class);

        influxdb.insert(data);
    }
}
