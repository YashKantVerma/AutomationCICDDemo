package com.qa.framework.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	 // This method converts your JSON file → List of HashMaps
    public static List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        
        // 1️⃣ Read JSON file as a String
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        
        // 2️⃣ Convert JSON string into List<HashMap<String, String>>
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(
                jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {}
        );
        
        return data;
    }
}

