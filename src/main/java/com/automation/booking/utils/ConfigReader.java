package com.automation.booking.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try(InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(
                "config.properties")){
            if(inputStream == null){
                throw new RuntimeException("Could not find config.properties!");
            }
            properties.load(inputStream);
        }catch (IOException e){
            throw new RuntimeException("Could not properties file");
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
