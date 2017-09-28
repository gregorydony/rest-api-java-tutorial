package org.gisobject.tutorial.restapi.jackson.treemodel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gisobject.tutorial.restapi.bean.Employee;
import org.gisobject.tutorial.restapi.jackson.JacksonJsonProcessor;
import org.gisobject.tutorial.restapi.json.EmployeeJsonProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GIS Object on 23/09/2017.
 */
public enum TreeModelJacksonJsonProcessor implements EmployeeJsonProcessor, JacksonJsonProcessor<Employee> {

    TREE_MODEL_JACKSON_JSON_PROCESSOR;
    
    @Override
    public List<Employee> readJson(InputStream inputStream) {
        //Create a ObjectMapper instance
        //ObjectMapper provides functionality for creating tree
        ObjectMapper objectMapper = new ObjectMapper();
        //Read JSON content in to tree
        try {
            JsonNode rootNode = objectMapper.readTree(inputStream);
            if (rootNode.isArray()) {
                List<Employee> employees = new ArrayList<>();
                rootNode.forEach(jsonNode -> employees.add(fromJson(jsonNode)));
                return employees;
            }
            throw new IllegalArgumentException("Root JsonNode is a " + rootNode.getNodeType() + "but should be an array");
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public void writeJson(List<Employee> input, OutputStream os) {

    }

    @Override
    public Employee fromJson(JsonNode jsonNode) {
        Employee employee = new Employee();
        employee.setEmployeeId(jsonNode.path(EMPLOYEE_ID).asInt());
        employee.setFirstName(jsonNode.path(FIRST_NAME).asText());
        employee.setLastName(jsonNode.path(LAST_NAME).asText());
        employee.setEmail(jsonNode.path(EMAIL).asText());
        return employee;
    }

    @Override
    public List<Employee> fromJsonArray(JsonNode jsonNode) {
        return null;
    }

    @Override
    public JsonNode toJson(List<Employee> listOfElements) {
        return null;
    }

    @Override
    public JsonNode toJson(Employee element) {
        return null;
    }
}
