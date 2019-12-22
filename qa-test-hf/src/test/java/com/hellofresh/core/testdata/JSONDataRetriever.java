package com.hellofresh.core.testdata;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class JSONDataRetriever implements DataRetriever {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONDataRetriever.class);

    public Map<String, String> getDataObject(String filename) {
        String filePath = getClass().getClassLoader().getResource( "testData" + File.separator + filename).getPath();
        String json = readJsonFile(filePath);
        return getJSONObjectFromString(json, Map.class);
    }

    public List<Map<String, String>> getDataObjectArray(String filename) {
        ///TODO implement method to get data from JSON arrays
        return null;
    }

    /**
     * Method to Read JSON file
     * @param filePath
     * @return
     */
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

    /**
     * Method to convert json string to object of class passed as parameter
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

}
