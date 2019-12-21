package com.hellofresh.utils;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class JSONUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class);
    public JSONUtils() {

    }

    /**
     * Methos to get POJO object from json string
     * @param rawJSONString : json string to convert to object
     * @param type class of the object
     * @param <T> any POJO class
     * @return Object of POJO class
     */
    public <T> T getJSONObjectFromString(final String rawJSONString, Class<T> type) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(rawJSONString, type);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("I/O Error while converting JSON to Map", e);
            return null;
        }
    }

    /**
     * Method to convert POJOs to json string
     * @param object which is required to be converted to JSON string
     * @return json string
     */
    public String getJSONFromObject(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("I/O Error while converting JSON to Map", e);
            return null;
        }
    }

}
