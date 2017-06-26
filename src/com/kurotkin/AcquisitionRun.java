package com.kurotkin;

/**
 * Created by Vitaly Kurotkin on 26.06.2017.
 */
public class AcquisitionRun implements Runnable {
    @Override
    public void run() {
        HTTPgetter getJson = new HTTPgetter("http://192.168.1.50/awp/json.htm");
        String jsonLine = getJson.get();
    }
}
