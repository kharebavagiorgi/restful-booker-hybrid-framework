package com.automation.booking.utils;

import com.automation.booking.model.ui.ContactModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class JsonReader {
    private static final String DATA_PATH = "src/test/resources/testdata/contactData.json";

    public static List<ContactModel> getContactData(String sectionName){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(DATA_PATH));
            JsonNode sectionNode = rootNode.get(sectionName);

            return mapper.readerForListOf(ContactModel.class).readValue(sectionNode);

        } catch (Exception e) {
            throw new RuntimeException("CRITICAL: Could not read JSON test data for section: " + sectionName, e);
        }
    }
}
