package com.hellofresh.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private Properties properties;
    private static PropertyReader instance = new PropertyReader();

    public static PropertyReader getInstance() {
        if (instance == null) {
            synchronized (PropertyReader.class) {
                if (instance == null) {
                    instance = new PropertyReader();
                }
            }
        }
        return instance;
    }

    private PropertyReader() {
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
