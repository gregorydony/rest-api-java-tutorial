package org.gisobject.tutorial.restapi.jackson.treemodel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gisobject.tutorial.restapi.bean.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by GIS Object on 23/09/2017.
 */
class TreeModelJacksonJsonProcessorTest {

    String jsonFileName ="emp-array.json";

    public void testObjectToJsonMapping() {
        List<Employee> employees;
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(jsonFileName)) {
            //Create a ObjectMapper instance
            //ObjectMapper provides functionality for creating tree
            ObjectMapper objectMapper = new ObjectMapper();

            //Read JSON content in to tree
            JsonNode rootNode = objectMapper.readTree(inputStream);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}