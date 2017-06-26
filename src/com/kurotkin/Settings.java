package com.kurotkin;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

/**
 * Created by Vitaly Kurotkin on 26.06.2017.
 */
public class Settings {

    private static String HTTPgetterUrl = "http://192.168.1.50/awp/json.htm";
    private static String InfluxDBUrl = "http://127.0.0.1:8086";
    private static String InfluxDBUser = "root";
    private static String InfluxDBPass = "root";
    private static String InfluxDBdbName = "aTimeSeries";

    public static void loadSetting(){
        YamlReader reader = null;
        try {
            reader = new YamlReader(new FileReader("settings.yml"));
            Object object = reader.read();
            Map map = (Map)object;
            HTTPgetterUrl = map.get("HTTPgetterUrl").toString();
            InfluxDBUrl = map.get("InfluxDBUrl").toString();
            InfluxDBUser = map.get("InfluxDBUser").toString();
            InfluxDBPass = map.get("InfluxDBPass").toString();
            InfluxDBdbName = map.get("InfluxDBdbName").toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (YamlException e) {
            e.printStackTrace();
        }
    }

    public static String getHTTPgetterUrl() {
        return HTTPgetterUrl;
    }

    public static void setHTTPgetterUrl(String HTTPgetterUrl) {
        Settings.HTTPgetterUrl = HTTPgetterUrl;
    }

    public static String getInfluxDBUrl() {
        return InfluxDBUrl;
    }

    public static void setInfluxDBUrl(String influxDBUrl) {
        InfluxDBUrl = influxDBUrl;
    }

    public static String getInfluxDBUser() {
        return InfluxDBUser;
    }

    public static void setInfluxDBUser(String influxDBUser) {
        InfluxDBUser = influxDBUser;
    }

    public static String getInfluxDBPass() {
        return InfluxDBPass;
    }

    public static void setInfluxDBPass(String influxDBPass) {
        InfluxDBPass = influxDBPass;
    }

    public static String getInfluxDBdbName() {
        return InfluxDBdbName;
    }

    public static void setInfluxDBdbName(String influxDBdbName) {
        InfluxDBdbName = influxDBdbName;
    }
}
