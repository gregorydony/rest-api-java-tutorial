package org.gisobject.tutorial.restapi.jsr353.objectmodel;

import org.gisobject.tutorial.restapi.bean.Employee;
import org.gisobject.tutorial.restapi.json.EmployeeJsonProcessor;
import org.gisobject.tutorial.restapi.jsr353.JSR353JsonProcessor;

import javax.json.*;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by GIS Object on 27/08/2017.
 */
public enum ObjectModelJSR353JsonProcessor implements EmployeeJsonProcessor, JSR353JsonProcessor<Employee> {

    OBJECT_MODEL_JSON_PROCESSOR;

    @Override
    public Employee fromJson(JsonObject jsonObject) {

        Employee employee = new Employee();
        employee.setEmployeeId(jsonObject.getInt(EMPLOYEE_ID));
        employee.setFirstName(jsonObject.getString(FIRST_NAME));
        employee.setLastName(jsonObject.getString(LAST_NAME));
        employee.setEmail(jsonObject.getString(EMAIL));

        DateFormat dateFormat = new SimpleDateFormat(HIRE_DATE_PATTERN);
        final String strHireDate = jsonObject.getString(HIRE_DATE);
        try {
            employee.setHireDate(dateFormat.parse(strHireDate));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date " + strHireDate);
        }
        return employee;
    }

    @Override
    public List<Employee> readJson(InputStream inputStream) {
        try (// Create JsonReader to read JSON data from a stream
             Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             JsonReader jsonReader = Json.createReader(reader)) {
            // Create an object model in memory
            JsonArray employeeArray = jsonReader.readArray();
            List<Employee> employees = fromJson(employeeArray);
            return employees;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public void writeJson(List<Employee> input, OutputStream os) {
        try (JsonWriter jsonWriter = Json.createWriter(os)) {
            JsonArray jsonArray = toJson(input);
            jsonWriter.writeArray(jsonArray);
        }
    }

    @Override
    public List<Employee> fromJson(JsonArray jsonArray) {
        return jsonArray.stream().map(jsonValue -> OBJECT_MODEL_JSON_PROCESSOR.fromJson(jsonValue.asJsonObject())).collect(Collectors.toList());
    }


    @Override
    public JsonArray toJson(@NotNull List<Employee> employees) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        employees.forEach(employee -> jsonArrayBuilder.add(toJson(employee)));
        return jsonArrayBuilder.build();
    }

    @Override
    public JsonObject toJson(@NotNull Employee employee) {
        DateFormat dateFormat = new SimpleDateFormat(HIRE_DATE_PATTERN);
        return Json.createObjectBuilder()
                .add(EMPLOYEE_ID, employee.getEmployeeId())
                .add(FIRST_NAME, employee.getFirstName())
                .add(LAST_NAME, employee.getLastName())
                .add(EMAIL, employee.getEmail())
                .add(HIRE_DATE, dateFormat.format(employee.getHireDate())).build();
    }
}
