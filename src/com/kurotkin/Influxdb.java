package com.kurotkin;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vitaly Kurotkin on 26.06.2017.
 */
public class Influxdb {
    private static InfluxDB influxDB;
    private static String dbName;

    public void Influxdb() {
        String InfluxDBUrl = Settings.getInfluxDBUrl();
        String InfluxDBUser = Settings.getInfluxDBUrl();
        String InfluxDBPass = Settings.getInfluxDBPass();
        influxDB = InfluxDBFactory.connect(InfluxDBUrl, InfluxDBUser, InfluxDBPass);
        dbName = Settings.getInfluxDBdbName();
        influxDB.createDatabase(dbName);
    }

    public static void insert (Device[] data) {
        BatchPoints batchPoints = BatchPoints
                .database(dbName)
                .tag("async", "true")
                .retentionPolicy("autogen")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();

        for(Device d : data) {
            String tableName = d.name;
            Point.Builder builder = Point.measurement(tableName);
            builder.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            for (Content c : d.content) {
                builder.addField(c.name, c.val);
            }
            batchPoints.point(builder.build());
        }
        influxDB.write(batchPoints);
    }
}
