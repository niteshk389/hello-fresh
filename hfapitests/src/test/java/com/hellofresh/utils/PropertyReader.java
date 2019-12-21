package com.hellofresh.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This singleton class loads the properties from <env>_config.properties file.
 */
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

        //Default environment is INT
        String env = "int";

        //if env is provided as system property take that
        if(System.getProperty("env") != null) {
            env = System.getProperty("env");
        }
        try {
            //load properties from environment specific property file
            inputStream = getClass().getClassLoader().getResourceAsStream(env.toLowerCase() + "_config.properties");
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
