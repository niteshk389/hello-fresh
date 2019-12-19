package com.hellofresh.core.testdata;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class JSONDataRetriever implements DataRetriever {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONDataRetriever.class);

    public Map<String, String> getDataObject(String filename) {
        String json = readJsonFile(filename);
        return getJSONObjectFromString(json, Map.class);
    }

    public List<Map<String, String>> getDataObjectArray(String filename) {
        return null;
    }


    private static String readJsonFile(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

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

}
