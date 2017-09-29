package org.gisobject.tutorial.restapi.jackson.treemodel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.gisobject.tutorial.restapi.bean.Employee;
import org.gisobject.tutorial.restapi.jackson.JacksonJsonProcessor;
import org.gisobject.tutorial.restapi.json.EmployeeJsonProcessor;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GIS Object on 23/09/2017.
 */
public enum TreeModelJacksonJsonProcessor implements EmployeeJsonProcessor, JacksonJsonProcessor<Employee> {

    TREE_MODEL_JACKSON_JSON_PROCESSOR;

    private transient ObjectReader objectReader;

    private transient ObjectWriter objectWriter;

    TreeModelJacksonJsonProcessor() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectReader = objectMapper.reader().forType(new TypeReference<List<Employee>>(){});
        objectWriter = objectMapper.writer();
    }

    @Override
    @NotNull
    public List<Employee> readJson(@NotNull InputStream inputStream) {
        //Read JSON content in to tree
        try {
            JsonNode rootNode = objectReader.readTree(inputStream);
            if (!rootNode.isArray()) {
                throw new IllegalArgumentException("Root JsonNode is a " + rootNode.getNodeType() + "but should be an array");
            }
            return fromJson((ArrayNode) rootNode);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public void writeJson(@NotNull List<Employee> input, @NotNull OutputStream os) {
        try {
            objectWriter.writeValue(os,toJson(input));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

    }

    @Override
    public Employee fromJson(@NotNull JsonNode jsonNode) {
        DateFormat dateFormat = new SimpleDateFormat(HIRE_DATE_PATTERN);
        Employee employee = new Employee();
        employee.setEmployeeId(jsonNode.path(EMPLOYEE_ID).asInt());
        employee.setFirstName(jsonNode.path(FIRST_NAME).asText());
        employee.setLastName(jsonNode.path(LAST_NAME).asText());
        employee.setEmail(jsonNode.path(EMAIL).asText());
        final String strHireDate = jsonNode.path(HIRE_DATE).asText();
        try {
            employee.setHireDate(dateFormat.parse(strHireDate));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date " + strHireDate);
        }
        return employee;
    }

    @Override
    public List<Employee> fromJson(@NotNull ArrayNode jsonRootNode) {
        List<Employee> employees = new ArrayList<>();
        jsonRootNode.forEach(jsonNode -> employees.add(fromJson(jsonNode)));
        return employees;
    }

    @Override
    @NotNull
    public ArrayNode toJson(@NotNull List<Employee> listOfElements) {
        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode(listOfElements.size());
        listOfElements.forEach(employee -> arrayNode.add(toJson(employee)));
        return arrayNode;
    }

    @Override
    @NotNull
    public ObjectNode toJson(@NotNull Employee element) {
        DateFormat dateFormat = new SimpleDateFormat(HIRE_DATE_PATTERN);
        return JsonNodeFactory.instance.objectNode()
                .put(EMPLOYEE_ID, element.getEmployeeId())
                .put(FIRST_NAME, element.getFirstName())
                .put(LAST_NAME, element.getLastName())
                .put(EMAIL, element.getEmail())
                .put(HIRE_DATE, dateFormat.format(element.getHireDate()));
    }
}
