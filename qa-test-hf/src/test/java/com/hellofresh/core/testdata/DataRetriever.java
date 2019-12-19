package com.hellofresh.core.testdata;

import java.util.List;
import java.util.Map;

public interface DataRetriever {

    Map<String, String> getDataObject(String filename);

    List<Map<String, String>> getDataObjectArray(String filename);
}
