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
        influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086", "root", "root");
        dbName = "aTimeSeries";
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
