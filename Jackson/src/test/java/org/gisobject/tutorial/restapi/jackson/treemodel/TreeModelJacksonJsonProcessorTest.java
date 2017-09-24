package org.gisobject.tutorial.restapi.jackson.treemodel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gisobject.tutorial.restapi.bean.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.gisobject.tutorial.restapi.test.TestResource.EMPLOYEE_ARRAY;

/**
 * Created by GIS Object on 23/09/2017.
 */
class TreeModelJacksonJsonProcessorTest {

    public void testObjectToJsonMapping() {
        List<Employee> employees;
        try (InputStream inputStream = EMPLOYEE_ARRAY.asInputStream()) {
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